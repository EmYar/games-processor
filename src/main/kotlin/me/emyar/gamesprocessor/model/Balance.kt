package me.emyar.gamesprocessor.model

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import java.util.*

object Balances : UUIDTable() {
    val player = reference("player", Players).uniqueIndex()
    val currency = reference("currency", Currencies)
    val amount = long("amount")
}

class Balance(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<Balance>(Balances)

    val player by Balances.player
    val currency by Balances.currency
    var amount by Balances.amount
    val transactions by Transaction referrersOn Transactions.balance
}