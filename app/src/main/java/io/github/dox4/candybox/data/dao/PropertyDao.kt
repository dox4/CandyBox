package io.github.dox4.candybox.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import io.github.dox4.candybox.domain.Property
import java.util.*

@Dao
interface PropertyDao {
    @Query("SELECT * FROM property WHERE label = :name")
    fun findByName(name: String): List<Property>

    @Query("SELECT * FROM property WHERE fkTabId = :fkTabId")
    fun findByTabId(fkTabId: UUID): List<Property>

    @Insert
    fun insert(property: Property)

    @Delete
    fun delete(property: Property)
}