package me.emyar.gamesprocessor.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("balance")
data class BalanceRequest(
    val data: BalanceRequestData,
) : Request()

@Serializable
data class BalanceRequestData(
    val gameSessionId: String,
    val currency: String,
)