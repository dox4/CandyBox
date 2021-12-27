package io.github.dox4.candybox.data.dao

import androidx.room.Dao
import androidx.room.Query
import io.github.dox4.candybox.domain.Template

@Dao
interface TemplateDao {
    @Query("SELECT * FROM template ORDER BY createdAt")
    fun findAllTemplates(): List<Template>
}