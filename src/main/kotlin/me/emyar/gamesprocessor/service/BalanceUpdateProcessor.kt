package me.emyar.gamesprocessor.service

import me.emyar.gamesprocessor.dto.request.BalanceUpdateRequest
import me.emyar.gamesprocessor.dto.response.BalanceUpdateData
import me.emyar.gamesprocessor.dto.response.BalanceUpdateResponse
import me.emyar.gamesprocessor.dto.response.BalanceUpdateResponseErrors
import me.emyar.gamesprocessor.dto.response.DebitResponse
import org.springframework.stereotype.Service

@Service
class BalanceUpdateProcessor {

    fun updateBalance(request: BalanceUpdateRequest): BalanceUpdateResponse {
        return DebitResponse(
            true,
            "",
            BalanceUpdateResponseErrors.NO_ERRORS,
            BalanceUpdateData(
                "",
                "",
                0,
                0,
                "",
            )
        )
    }
}