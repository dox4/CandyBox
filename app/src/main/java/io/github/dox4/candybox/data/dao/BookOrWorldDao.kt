package io.github.dox4.candybox.data.dao

import androidx.room.*
import io.github.dox4.candybox.domain.BookOrWorld
import io.github.dox4.candybox.domain.BookOrWorldType

@Dao
interface BookOrWorldDao {
    @Query("SELECT * FROM book_or_world WHERE type = :type ORDER BY createdAt DESC;")
    fun findBookOrWorlds(type: BookOrWorldType): List<BookOrWorld>

    @Query("SELECT * FROM book_or_world WHERE id = :id")
    suspend fun findBookOrWorld(id: String): BookOrWorld

    @Insert
    suspend fun insertBookOrWorld(bw: BookOrWorld)

    @Delete
    suspend fun deleteBookOrWorld(bw: BookOrWorld)

    @Update
    suspend fun updateBookOrWorld(bw: BookOrWorld)
}