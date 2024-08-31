package my.ym.androidtutorials.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import my.ym.androidtutorials.extensions.LocalSnackbarHostState
import my.ym.androidtutorials.extensions.navHostScreens
import my.ym.androidtutorials.ui.theme.AndroidTutorialsTheme
import my.ym.androidtutorials.utils.NavUtils

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        //enableEdgeToEdge()

        super.onCreate(savedInstanceState)

        setContent {
            AndroidTutorialsTheme {
                val navController = rememberNavController()

                val currentBackstackEntry by navController.currentBackStackEntryAsState()
                val startDestination = NavUtils.Screen.startDestination
                val currentScreen = NavUtils.Screen.entries.firstOrNull {
                    it.route == currentBackstackEntry?.destination?.route?.substringBefore("/")
                } ?: startDestination

                val canNavUp = currentScreen != NavUtils.Screen.startDestination
                val onNavUpClick: () -> Unit = { navController.navigateUp() }

                val snackbarHostState = remember { SnackbarHostState() }

                BackHandler(enabled = canNavUp, onBack = onNavUpClick)

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        CenterAlignedTopAppBar(
                            title = {
                                Text(text = stringResource(id = currentScreen.titleStringRes))
                            },
                            navigationIcon = {
                                if (canNavUp) {
                                    IconButton(onClick = onNavUpClick) {
                                        Icon(
                                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                                            contentDescription = null
                                        )
                                    }
                                }
                            },
                            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primary,
                                titleContentColor = MaterialTheme.colorScheme.onPrimary,
                            )
                        )
                    },
                    snackbarHost = {
                        SnackbarHost(hostState = snackbarHostState)
                    }
                ) { paddingValues ->
                    CompositionLocalProvider(
                        LocalSnackbarHostState provides snackbarHostState
                    ) {
                        NavHost(
                            modifier = Modifier.padding(paddingValues),
                            navController = navController,
                            startDestination = startDestination.route
                        ) {
                            navHostScreens(navController)
                        }
                    }
                }
            }
        }
    }
}