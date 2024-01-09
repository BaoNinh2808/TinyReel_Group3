package com.example.domain.cameramedia

import com.example.data.model.TemplateModel
import com.example.data.repository.cameramedia.TemplateRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTemplateUseCase @Inject constructor(
    private val templateRepository: TemplateRepository
) {
    operator fun invoke(): Flow<List<TemplateModel>> {
        return templateRepository.getTemplates()
    }
}