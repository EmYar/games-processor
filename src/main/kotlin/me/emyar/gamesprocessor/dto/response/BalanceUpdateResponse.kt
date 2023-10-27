package me.emyar.gamesprocessor.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed class BalanceUpdateResponse : Response() {
    abstract val isSuccess: Boolean
    abstract val error: String
    abstract val errorMsg: BalanceUpdateResponseErrors
    abstract val data: BalanceUpdateData
}

@Serializable
@SerialName("debit")
data class DebitResponse(
    override val isSuccess: Boolean,
    override val error: String,
    override val errorMsg: BalanceUpdateResponseErrors,
    override val data: BalanceUpdateData,
) : BalanceUpdateResponse()

@Serializable
@SerialName("credit")
data class CreditResponse(
    override val isSuccess: Boolean,
    override val error: String,
    override val errorMsg: BalanceUpdateResponseErrors,
    override val data: BalanceUpdateData,
) : BalanceUpdateResponse()

@Serializable
@SerialName("rollback")
data class RollbackResponse(
    override val isSuccess: Boolean,
    override val error: String,
    override val errorMsg: BalanceUpdateResponseErrors,
    override val data: BalanceUpdateData,
) : BalanceUpdateResponse()

@Serializable
data class BalanceUpdateData(
    val transactionId: String,
    val userNick: String,
    val amount: Long,
    val maxWin: Long,
    val currency: String,
)

@Serializable
enum class BalanceUpdateResponseErrors {
    NO_ERRORS,
    SIGN_NOT_PROVIDED,
    INVALID_SIGN,
    UNKNOWN_CURRENCY,
    INSUFFICIENT_BALANCE,
    INTERNAL_ERROR,
    ALREADY_PROCESSED,
}