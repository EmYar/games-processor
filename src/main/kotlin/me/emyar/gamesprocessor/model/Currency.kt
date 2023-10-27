package me.emyar.gamesprocessor.model

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import java.util.*

object Currencies : UUIDTable() {
    val code = varchar("code", 3).uniqueIndex()
    val denomination = ubyte("denomination")
}

class Currency(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<Currency>(Currencies)

    val code by Currencies.code
    val denomination by Currencies.denomination
}