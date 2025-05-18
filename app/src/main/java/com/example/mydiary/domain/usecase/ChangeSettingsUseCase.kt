package com.example.mydiary.domain.usecase

import com.example.mydiary.di.IOPool
import com.example.mydiary.domain.model.SettingsModel
import com.example.mydiary.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ChangeSettingsUseCase @Inject constructor(
    private val repository: Repository,
    @IOPool
    configuration: Configuration,
) :
    UseCase<ChangeSettingsUseCase.Request, ChangeSettingsUseCase.Response>(configuration) {

    class Response : UseCase.Response
    data class Request(val setting: SettingsModel) : UseCase.Request

    override fun process(request: Request): Flow<Response> = flow {
        emit(repository.changeSettings(request.setting))
    }.map { Response() }

}