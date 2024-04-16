package com.example.hexagon_employer_list

import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
) : ViewModel() {
    private val _result =
        MutableStateFlow<MainState>(MainState.Loading("Aguardando Autenticação..."))
    val result: StateFlow<MainState> = _result.asStateFlow()

    fun requestBiometrics(handler: FragmentActivity) {
        val executor = ContextCompat.getMainExecutor(handler)
        val biometricPrompt = BiometricPrompt(handler, executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(
                    errorCode: Int,
                    errString: CharSequence
                ) {
                    super.onAuthenticationError(errorCode, errString)
                    _result.value = MainState.Error
                }

                override fun onAuthenticationSucceeded(
                    result: BiometricPrompt.AuthenticationResult
                ) {
                    super.onAuthenticationSucceeded(result)
                    _result.value = MainState.Success
                }
            })

        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Confirmação de Identidade")
            .setSubtitle("Para cumprir os parâmetros da LGPD, somente usuários autorizados poderão entrar.")
            .setAllowedAuthenticators(BiometricManager.Authenticators.BIOMETRIC_STRONG or BiometricManager.Authenticators.DEVICE_CREDENTIAL)
            .build()

        biometricPrompt.authenticate(promptInfo)
    }
}


sealed class MainState {
    data object Error : MainState()
    data class Loading(val message: String) : MainState()
    data object Success : MainState()
}
