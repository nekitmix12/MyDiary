package com.example.mydiary

import com.example.mydiary.domain.model.Result
import com.example.mydiary.domain.model.SettingsModel
import com.example.mydiary.domain.repository.Repository
import com.example.mydiary.domain.usecase.GetSettingsUseCase
import com.example.mydiary.domain.usecase.UseCase
import com.example.mydiary.stubs.TestRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import mu.KotlinLogging
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import javax.inject.Inject

@RunWith(JUnit4::class)
class GetSettingsUseCase  {

    private var repository: Repository = TestRepository()
    private var getSettingsUseCase: GetSettingsUseCase = GetSettingsUseCase(
        repository,
        UseCase.Configuration(Dispatchers.IO)
    )

    @Test
    fun `get settings`(): Unit = runBlocking {
        var error = ""
        var getSettingsResult: SettingsModel? = null
        launch {
            getSettingsUseCase.execute(GetSettingsUseCase.Request())
                .collect {
                    if (it is Result.Success) {
                        getSettingsResult = it.data.emotions
                    }
                    if(it is Result.Error)
                        error = it.exception
                }
        }.join()
        assert(getSettingsResult == SettingsModel("https://avatars.mds.yandex.net/i?id=fa83661396f15525e1f56fff075df025_l-9271382-images-thumbs&n=13",
            isSendRemindOn = true,
            isUseFingerprint = true,
            name = "Oleg Tinkoff"))
    }
}