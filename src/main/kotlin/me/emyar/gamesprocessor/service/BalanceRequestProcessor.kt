package me.emyar.gamesprocessor.service

import me.emyar.gamesprocessor.dto.request.BalanceRequest
import me.emyar.gamesprocessor.dto.response.BalanceResponse
import me.emyar.gamesprocessor.dto.response.BalanceResponseData
import org.springframework.stereotype.Service

@Service
class BalanceRequestProcessor {

    fun getBalance(request: BalanceRequest): BalanceResponse {
        return BalanceResponse(
            BalanceResponseData(
                "",
                0,
                0,
                0,
                "",
                "",
                null,
            )
        )
    }
}