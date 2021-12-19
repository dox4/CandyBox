package io.github.dox4.candybox.data.dao

import androidx.room.*
import io.github.dox4.candybox.domain.BookOrWorld
import kotlinx.coroutines.flow.Flow

@Dao
interface BookOrWorldDao {
    @Query("SELECT * FROM book_or_world WHERE type = 'BOOK' ORDER BY createdAt DESC;")
    fun findBooks(): Flow<List<BookOrWorld>>

    @Query("SELECT * FROM book_or_world WHERE type = 'WORLD' ORDER BY createdAt DESC;")
    fun findWorlds(): Flow<List<BookOrWorld>>

    @Insert
    suspend fun insertBookOrWorld(bw: BookOrWorld)

    @Delete
    suspend fun deleteBookOrWorld(bw: BookOrWorld)

    @Update
    suspend fun updateBookOrWorld(bw: BookOrWorld)
}