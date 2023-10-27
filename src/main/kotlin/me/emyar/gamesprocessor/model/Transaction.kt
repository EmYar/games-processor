package me.emyar.gamesprocessor.model

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import java.util.*

object Transactions : UUIDTable() {
    val balance = reference("balance", Balances)
    val amount = long("amount")
}

class Transaction(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<Transaction>(Transactions)

    val balance by Balance referencedOn Transactions.balance
    val amount by Transactions.amount
}