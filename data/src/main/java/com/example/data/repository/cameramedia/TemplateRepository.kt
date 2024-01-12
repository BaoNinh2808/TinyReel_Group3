package com.example.data.repository.cameramedia

import com.example.data.model.TemplateModel
import com.example.data.source.TemplateDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TemplateRepository @Inject constructor() {
    fun getTemplates(): Flow<List<TemplateModel>> {
        return TemplateDataSource.fetchTemplates()
    }
}