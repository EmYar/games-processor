package me.emyar.gamesprocessor.model

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.Column
import java.util.*

object Players : UUIDTable() {
    val nick: Column<String> = varchar("userNick", 50)
    val maxWin: Column<Long> = long("maxWin")
}

class Player(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<Player>(Players)

    var nick by Players.nick
    var maxWin by Players.maxWin
    val balances by Balance referrersOn Balances.player
}