package me.emyar.gamesprocessor.service

import me.emyar.gamesprocessor.dto.request.BalanceUpdateRequest
import me.emyar.gamesprocessor.dto.request.CreditRequest
import me.emyar.gamesprocessor.dto.request.DebitRequest
import me.emyar.gamesprocessor.dto.request.RollbackRequest
import me.emyar.gamesprocessor.dto.response.*
import me.emyar.gamesprocessor.model.Balance
import me.emyar.gamesprocessor.model.Player
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Service

@Service
class BalanceUpdateProcessor {

    fun updateBalance(request: BalanceUpdateRequest): BalanceUpdateResponse = transaction {
        val player = Player.findBySession(request.data.gameSessionId) // TODO session not found exception
        val balance = player.balances
            .firstOrNull { it.currency.code == request.data.currency }

        when (request) {
            is DebitRequest -> doDebit(player, balance, request)
            is CreditRequest -> doCredit(request)
            is RollbackRequest -> doRollback(request)
        }
    }


    fun doDebit(player: Player, balance: Balance?, request: DebitRequest): DebitResponse {
        if (balance == null || balance.amount < request.data.amount) {
            return DebitResponse(
                isSuccess = false,
                error = "Insufficient funds in the account",
                errorMsg = BalanceUpdateResponseErrors.INSUFFICIENT_BALANCE,
                null
            )
        }


    }

    fun doCredit(request: CreditRequest): CreditResponse {

    }

    fun doRollback(request: RollbackRequest): RollbackResponse {

    }
}