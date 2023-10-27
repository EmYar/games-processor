package me.emyar.gamesprocessor.model

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.Column
import java.util.*

object Players : UUIDTable() {
    val nick: Column<String> = varchar("userNick", 50)
    val amount: Column<Long> = long("amount")
    val denomination: Column<Int> = integer("denomination")
    val maxWin: Column<Long> = long("maxWin")
    val currency: Column<String> = varchar("currency", 3)
}

class Player(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<Player>(Players)

    var nick by Players.nick
    var amount by Players.amount
    var denomination by Players.denomination
    var maxWin by Players.maxWin
    var currency by Players.currency
}