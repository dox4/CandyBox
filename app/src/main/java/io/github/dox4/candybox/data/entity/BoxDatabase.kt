package io.github.dox4.candybox.data.entity

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import io.github.dox4.candybox.data.dao.BookOrWorldDao
import io.github.dox4.candybox.data.dao.PropertyDao
import io.github.dox4.candybox.domain.BookOrWorld
import io.github.dox4.candybox.domain.Property
import io.github.dox4.candybox.domain.Tab
import io.github.dox4.candybox.domain.Template


@TypeConverters(TypeConverter1::class, TypeConverter2::class)
@Database(
    entities = [BookOrWorld::class, Template::class, Tab::class, Property::class],
    version = 1
)
abstract class BoxDatabase : RoomDatabase() {
    abstract fun propertyDao(): PropertyDao
    abstract fun bookOrWorldDao(): BookOrWorldDao
}