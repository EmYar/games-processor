package me.emyar.gamesprocessor.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("balance")
data class BalanceResponse(
    val data: BalanceResponseData,
) : Response()

@Serializable
data class BalanceResponseData(
    val userNick: String,
    val amount: Long,
    val denomination: Byte,
    val maxWin: Long,
    val currency: String,
    val userId: String,
    val jpKey: String?,
)