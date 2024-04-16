package com.example.hexagon_employer_list

import android.os.Build
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.fragment.app.FragmentActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.example.hexagon_employer_list.ui.components.template.ErrorTemplate
import com.example.hexagon_employer_list.ui.components.template.LoadingTemplate
import com.example.hexagon_employer_list.ui.navigation.MainNavigation
import com.example.hexagon_employer_list.ui.theme.HexagonTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : FragmentActivity() {
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val viewModel = hiltViewModel<MainViewModel>()
            val currentResult by viewModel.result.collectAsStateWithLifecycle()

            viewModel.requestBiometrics(this)

            HexagonTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    when (currentResult) {
                        is MainState.Error -> {
                            ErrorTemplate(
                                title = stringResource(R.string.cant_confirm_identity),
                                subTitle = stringResource(R.string.verify_phone_authentication)
                            )
                        }

                        is MainState.Loading -> {
                            LoadingTemplate(text = (currentResult as MainState.Loading).message)
                        }

                        MainState.Success -> {
                            val navController = rememberNavController()
                            MainNavigation(navHostController = navController)
                        }
                    }
                }

            }
        }
    }
}