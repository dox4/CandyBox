package io.github.dox4.candybox.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import io.github.dox4.candybox.data.entity.TypeConverter1
import java.util.*

enum class BookOrWorldType {
    BOOK,
    WORLD,
}

@Entity(tableName = "book_or_world")
@TypeConverters(TypeConverter1::class)
data class BookOrWorld(
    @PrimaryKey val id: UUID = UUID.randomUUID(),
    val name: String,
    val desc: String,
    val type: BookOrWorldType,
    val createdAt: Date = Date(),
    val updatedAt: Date,
) {
    constructor() : this("", "", BookOrWorldType.BOOK)
    constructor(name: String, desc: String, type: BookOrWorldType) : this(
        UUID.randomUUID(),
        name,
        desc,
        type,
        Date(),
        Date()
    )
}

val GroupedTypes = arrayOf(
    arrayOf(TemplateType.CHARACTER, TemplateType.RELATION, TemplateType.GROUP),
    arrayOf(TemplateType.PLACE, TemplateType.RACE, TemplateType.CREATURE, TemplateType.PLANT),
    arrayOf(TemplateType.ITEM, TemplateType.GOD_OR_DEMON, TemplateType.MAGIC, TemplateType.CALENDAR)
)

enum class TemplateType(val hans: String) {
    // character
    CHARACTER("人物"),
    RELATION("关联"),
    GROUP("组织"),

    // nature
    PLACE("地点"),
    RACE("种族/族群"),
    CREATURE("动物"),
    PLANT("植物"),

    // craft
    ITEM("物品"),
    GOD_OR_DEMON("神魔"),
    MAGIC("魔法"),
    CALENDAR("历法")
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