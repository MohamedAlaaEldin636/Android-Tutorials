package my.ym.androidtutorials.ui.screens.retrofitDynamicBaseUrl

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import my.ym.androidtutorials.extensions.LocalSnackbarHostState
import my.ym.androidtutorials.utils.MyAppConstants

@Composable
fun ScreenRetrofitDynamicBaseUrl(
    callApi: () -> Unit,

    stableState: RetrofitDynamicBaseUrlStableState,
    state: RetrofitDynamicBaseUrlState,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        SelectionContainer {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Base Url Fake 1\n${MyAppConstants.API.BASE_URL_FAKE_1}",
                textAlign = TextAlign.Center,
            )
        }

        SelectionContainer {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Base Url Fake 2\n${MyAppConstants.API.BASE_URL_FAKE_2}",
                textAlign = TextAlign.Center,
            )
        }

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),

            value = stableState.baseUrl,
            onValueChange = { stableState.baseUrl = it },

            label = { Text(text = "Base Url") }
        )

        val coroutineScope = rememberCoroutineScope()
        val snackbarHostState = LocalSnackbarHostState.current

        val keyboardController = LocalSoftwareKeyboardController.current

        Button(
            onClick = {
                when {
                    stableState.baseUrl.startsWith("http").not() -> {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar("Base Url Must Start with 'http'")
                        }
                    }
                    else -> {
                        keyboardController?.hide()

                        callApi()
                    }
                }
            },
            enabled = state != RetrofitDynamicBaseUrlState.LoadingApiCall,
        ) {
            Box(
                contentAlignment = Alignment.Center,
            ) {
                val color = if (state is RetrofitDynamicBaseUrlState.LoadingApiCall) {
                    Color.Transparent
                }else {
                    Color.Unspecified
                }

                var heightOfText by remember { mutableStateOf(0.dp) }
                val density = LocalDensity.current
                Text(
                    modifier = Modifier
                        .onSizeChanged { heightOfText = with(density) { it.height.toDp() } },
                    text = "Call Api ( GET Posts )",
                    color = color
                )

                if (state is RetrofitDynamicBaseUrlState.LoadingApiCall && heightOfText > 0.dp) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(heightOfText)
                    )
                }
            }
        }

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Full Url\n${stableState.baseUrl}${MyAppConstants.API.PATH_POSTS}",
            textAlign = TextAlign.Center,
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(color = Color.Gray)
        )

        val color = when (state) {
            RetrofitDynamicBaseUrlState.None,
            RetrofitDynamicBaseUrlState.LoadingApiCall -> Color.Black
            is RetrofitDynamicBaseUrlState.Success -> Color(0xFF1D681F)
            is RetrofitDynamicBaseUrlState.Error -> MaterialTheme.colorScheme.error
        }

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = state.result,
            textAlign = TextAlign.Center,
            color = color,
        )
    }
}
