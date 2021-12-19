package io.github.dox4.candybox.data.entity

import androidx.room.TypeConverter
import java.util.*

class TypeConverter1 {
    @TypeConverter
    fun fromUUID(uuid: UUID): String {
        return uuid.toString()
    }

    @TypeConverter
    fun fromString(string: String): UUID {
        return UUID.fromString(string)
    }
}

class TypeConverter2 {
    @TypeConverter
    fun fromDate(date: Date): Long {
        return date.time
    }

    @TypeConverter
    fun fromLong(long: Long): Date {
        return Date(long)
    }
}