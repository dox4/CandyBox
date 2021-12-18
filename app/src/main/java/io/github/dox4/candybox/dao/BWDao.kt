package io.github.dox4.candybox.dao

import androidx.room.*
import io.github.dox4.candybox.entity.BookOrWorld

@Dao
interface BWDao {
    @Query("SELECT * FROM book_or_world WHERE type = 'BOOK' ORDER BY createdAt DESC;")
    fun findBooks(): List<BookOrWorld>

    @Query("SELECT * FROM book_or_world WHERE type = 'WORLD' ORDER BY createdAt DESC;")
    fun findWorlds(): List<BookOrWorld>

    @Insert
    fun insertBookOrWorld(bw: BookOrWorld)

    @Delete
    fun deleteBookOrWorld(bw: BookOrWorld)

    @Update
    fun updateBookOrWorld(bw: BookOrWorld)
}