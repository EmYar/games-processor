package me.emyar.gamesprocessor.service

import me.emyar.gamesprocessor.dto.request.BalanceRequest
import me.emyar.gamesprocessor.dto.response.BalanceResponse
import me.emyar.gamesprocessor.dto.response.BalanceResponseData
import me.emyar.gamesprocessor.model.Currency
import me.emyar.gamesprocessor.model.GameSession
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Service
import java.util.*

@Service
class BalanceRequestProcessor {

    fun getBalance(request: BalanceRequest): BalanceResponse = transaction {
        val sessionUUID = UUID.fromString(request.data.gameSessionId)
        val session = GameSession[sessionUUID]
        val player = session.player
        val currency = Currency.findByCode(request.data.currency)!! // TODO currency not found exception
        val amount = player.getCurrencyAmount(currency)

        BalanceResponse(
            BalanceResponseData(
                userNick = player.nick,
                amount = amount,
                denomination = currency.denomination,
                maxWin = player.maxWin,
                currency = currency.code,
                userId = player.id.value,
                jpKey = null
            )
        )
    }
}