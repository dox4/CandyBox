package io.github.dox4.candybox.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.util.*

enum class BookOrWorldType {
    BOOK,
    WORLD,
}

@Entity(tableName = "book_or_world")
@TypeConverters(TypeConverter1::class)
data class BookOrWorld(
    @PrimaryKey val id: UUID,
    val name: String,
    val desc: String,
    val type: BookOrWorldType,
    val createdAt: Date,
    val updatedAt: Date,
)

enum class TemplateType {
    // character
    CHARACTER,
    RELATION,
    GROUP,

    // world
    PLACE,
    RACE,
    ITEM,
    CREATURE,
    PLANT,
    GOD_OR_DEMON,
    MAGIC,
    CALENDAR
}

@Entity(tableName = "template")
@TypeConverters(TypeConverter1::class)
data class Template(
    @PrimaryKey val id: UUID,
    val name: String,
    val type: TemplateType,
)

@Entity(
    tableName = "tab",
//    foreignKeys = [ForeignKey(
//        entity = Template::class,
//        parentColumns = arrayOf("id"),
//        childColumns = arrayOf("fkTemplateId")
//    )]
)
@TypeConverters(TypeConverter1::class)
data class Tab(
    @PrimaryKey val id: UUID,
    val fkTemplateId: UUID, // associated template
    val name: String,
    val index: Int,
)


@Entity(
    tableName = "property",
//    foreignKeys = [ForeignKey(
//        entity = Tab::class,
//        parentColumns = arrayOf("id"),
//        childColumns = arrayOf("fkTabId")
//    )]
)

@TypeConverters(TypeConverter1::class)
data class Property(
    @PrimaryKey val id: UUID,
    val fkTabId: UUID,  // associated tab
    val index: Int,
    val label: String,
    val hint: String,
)