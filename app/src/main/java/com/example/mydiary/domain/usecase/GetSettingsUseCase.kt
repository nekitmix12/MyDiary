package com.example.mydiary.domain.usecase

import com.example.mydiary.di.IOPool
import com.example.mydiary.domain.model.SettingsModel
import com.example.mydiary.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetSettingsUseCase @Inject constructor(
    private val repository: Repository,
    @IOPool
    configuration: Configuration,
) :
    UseCase<GetSettingsUseCase.Request, GetSettingsUseCase.Response>(configuration) {

    data class Response(val emotions: SettingsModel) : UseCase.Response
    class Request : UseCase.Request

    override fun process(request: Request): Flow<Response> =
        repository.getSettings().map { Response(it) }

}