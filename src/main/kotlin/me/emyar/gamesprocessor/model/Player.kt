package me.emyar.gamesprocessor.model

import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.Column

object Players : IdTable<String>() {
    override val id: Column<EntityID<String>> = varchar("id", 100).entityId()

    val nick = varchar("userNick", 50)
    val maxWin = ulong("maxWin")

    override val primaryKey = PrimaryKey(id)
}

class Player(id: EntityID<String>) : Entity<String>(id) {
    companion object : EntityClass<String, Player>(Players)

    var nick by Players.nick
    var maxWin by Players.maxWin
    val balances by Balance backReferencedOn Balances.player
}