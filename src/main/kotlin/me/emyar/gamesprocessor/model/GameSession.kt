package me.emyar.gamesprocessor.model

import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import java.util.*

object GameSessions : UUIDTable() {
    val player = reference("player", Players)
    val isActive = bool("isActive")
}

class GameSession(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<GameSession>(GameSessions)

    var player by GameSessions.player
    var isActive by GameSessions.isActive
}