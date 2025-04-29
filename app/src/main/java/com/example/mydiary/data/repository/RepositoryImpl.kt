package com.example.mydiary.data.repository

import com.example.mydiary.data.data_source.local.EmotionDataSource
import com.example.mydiary.data.dbo.AnswerWithActive
import com.example.mydiary.data.entity.EmotionEntity
import com.example.mydiary.domain.repository.Repository

class RepositoryImpl(private val emotionDataSource: EmotionDataSource) : Repository {

}