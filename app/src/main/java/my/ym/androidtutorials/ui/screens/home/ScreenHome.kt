package my.ym.androidtutorials.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import my.ym.androidtutorials.utils.NavUtils

@Composable
fun ScreenHome(
    navToScreen: (NavUtils.Screen) -> Unit,

    stableState: HomeStableState,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),

            value = stableState.search,
            onValueChange = { stableState.search = it },

            label = { Text(text = "Search here") }
        )

        if (stableState.screens.isEmpty()) {
            Text(text = "No Screens Found")
        }else {
            for (screen in stableState.screens) {
                Button(onClick = { navToScreen(screen) }) {
                    Text(text = stringResource(id = screen.titleStringRes))
                }
            }
        }
    }
}
