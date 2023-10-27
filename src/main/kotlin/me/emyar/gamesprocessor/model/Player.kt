package me.emyar.gamesprocessor.model

import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.and
import java.util.*

object Players : IdTable<String>() {
    override val id: Column<EntityID<String>> = varchar("id", 100).entityId()

    val nick = varchar("userNick", 50)
    val maxWin = ulong("maxWin")

    override val primaryKey = PrimaryKey(id)
}

class Player(id: EntityID<String>) : Entity<String>(id) {

    companion object : EntityClass<String, Player>(Players) {
        fun findBySession(sessionId: String): Player =
            findBySession(UUID.fromString(sessionId))

        fun findBySession(sessionId: UUID): Player =
            GameSession[sessionId]
                .player
    }

    var nick by Players.nick
    var maxWin by Players.maxWin
    val balances by Balance referrersOn Balances.player

    fun getCurrencyAmount(currencyCode: String): ULong =
        getCurrencyAmount(Currency.findByCode(currencyCode)!!)

    fun getCurrencyAmount(currency: Currency): ULong =
        Balance.find { (Balances.player eq id) and (Balances.currency eq currency.id) }
            .firstOrNull()
            ?.amount
            ?.toULong()
            ?: 0UL
}