package me.emyar.gamesprocessor.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

sealed class BalanceUpdateRequest : Request() {
    abstract val data: BalanceUpdateData
}

@Serializable
@SerialName("debit")
data class DebitRequest(
    override val data: BalanceUpdateData,
) : BalanceUpdateRequest()

@Serializable
@SerialName("credit")
data class CreditRequest(
    override val data: BalanceUpdateData,
) : BalanceUpdateRequest()

@Serializable
@SerialName("rollback")
data class RollbackRequest(
    override val data: BalanceUpdateData,
) : BalanceUpdateRequest()

@Serializable
data class BalanceUpdateData(
    val transactionId: String,
    val gameSessionId: String,
    val currency: String,
    val amount: Long,
    val betId: String?,
    val spinMeta: SpinMeta?,
    val betMeta: BetMeta?,
    val notes: String?,
)

@Serializable
data class SpinMeta(
    val lines: Int,
    val betPerLine: Int,
    val totalBet: Int,
    val symbolMatrix: Array<IntArray>,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SpinMeta

        return lines == other.lines &&
                betPerLine == other.betPerLine &&
                totalBet == other.totalBet &&
                symbolMatrix.contentDeepEquals(other.symbolMatrix)
    }

    override fun hashCode(): Int {
        var result = lines
        result = 31 * result + betPerLine
        result = 31 * result + totalBet
        result = 31 * result + symbolMatrix.contentDeepHashCode()
        return result
    }
}

@Serializable
data class BetMeta(
    val bets: Array<Bet>,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BetMeta

        return bets.contentEquals(other.bets)
    }

    override fun hashCode(): Int =
        bets.contentHashCode()
}

@Serializable
data class Bet(
    val balls: IntArray,
    val colors: Array<String>,
    val amount: Int,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Bet

        return balls.contentEquals(other.balls) &&
                colors.contentEquals(other.colors) &&
                amount == other.amount
    }

    override fun hashCode(): Int {
        var result = balls.contentHashCode()
        result = 31 * result + colors.contentHashCode()
        result = 31 * result + amount
        return result
    }
}