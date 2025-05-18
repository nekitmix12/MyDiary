package com.example.mydiary.presentation.features.entrance.di

import android.content.Context
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import com.example.mydiary.BuildConfig
import com.example.mydiary.presentation.features.common.di.SignInIpCredentialRequest
import com.example.mydiary.presentation.features.common.di.SignInIpOption
import com.example.mydiary.presentation.features.common.di.SignUpIpCredentialRequest
import com.example.mydiary.presentation.features.common.di.SignUpIpOption
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import dagger.Module
import dagger.Provides

@Module
object GoogleApiModule {
    val clientId = BuildConfig.SERVER_CLIENT_ID

    @SignInIpCredentialRequest
    @Provides
    fun provideCredentialRequest(@SignInIpOption googleIdOption: GetGoogleIdOption) =
        GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()


    @SignInIpOption
    @Provides
    fun provideGoogleIdOption() = GetGoogleIdOption.Builder()
        .setServerClientId(clientId)
        .build()

    @SignUpIpOption
    @Provides
    fun provideSignUpOption() :GetGoogleIdOption =  GetGoogleIdOption.Builder()
        .setFilterByAuthorizedAccounts(false)
        .setServerClientId(clientId)
        .build()


    @SignUpIpCredentialRequest
    @Provides
    fun provideSignUpRequest(@SignUpIpOption signUpOption: GetGoogleIdOption) =
        GetCredentialRequest.Builder()
            .addCredentialOption(signUpOption)
            .build()

    @Provides
    fun provideCredentialManager(context: Context) = CredentialManager.create(context)


}