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
    arrayOf(TemplateType.CHARACTER, TemplateType.RELATION, TemplateType.GROUP, TemplateType.SCENE),
    arrayOf(
        TemplateType.GOD_OR_DEMON,
        TemplateType.RACE,
        TemplateType.CREATURE,
        TemplateType.PLANT,
        TemplateType.GEOGRAPHY
    ),
    arrayOf(TemplateType.PLACE, TemplateType.ITEM, TemplateType.MAGIC, TemplateType.CALENDAR)
)

enum class TemplateType(val hans: String) {
    // story
    CHARACTER("人物"),
    RELATION("关系"),
    GROUP("组织"),
    SCENE("场景"),

    // world
    GOD_OR_DEMON("神魔"),
    RACE("种族/族群"),
    CREATURE("动物"),
    PLANT("植物"),
    GEOGRAPHY("地理"),

    // craft
    PLACE("地点"),
    ITEM("物品"),
    MAGIC("魔法"),
    CALENDAR("历法")
}

@Entity(tableName = "template")
@TypeConverters(TypeConverter1::class)
data class Template(
    @PrimaryKey val id: UUID,
    val name: String,
    val type: TemplateType,
    val createdAt: Date = Date(),
    val updatedAt: Date = Date(),
    val isDefault: Boolean = false
)

@Entity(
    tableName = "tab",
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
)
@TypeConverters(TypeConverter1::class)
data class Property(
    @PrimaryKey val id: UUID,
    val fkTabId: UUID,  // associated tab
    val index: Int,
    val label: String,
    val hint: String,
)