package io.github.dox4.candybox.entity

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import io.github.dox4.candybox.dao.BWDao
import io.github.dox4.candybox.dao.PropertyDao


@TypeConverters(TypeConverter1::class, TypeConverter2::class)
@Database(
    entities = [BookOrWorld::class, Template::class, Tab::class, Property::class],
    version = 1
)
abstract class BoxDatabase : RoomDatabase() {
    abstract fun propertyDao(): PropertyDao
    abstract fun bwDao(): BWDao
}