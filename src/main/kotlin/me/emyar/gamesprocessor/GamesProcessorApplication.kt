package me.emyar.gamesprocessor

import me.emyar.gamesprocessor.model.Balances
import me.emyar.gamesprocessor.model.Currencies
import me.emyar.gamesprocessor.model.GameSessions
import me.emyar.gamesprocessor.model.Players
import org.jetbrains.exposed.spring.autoconfigure.ExposedAutoConfiguration
import org.jetbrains.exposed.sql.deleteAll
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.boot.autoconfigure.ImportAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.context.event.EventListener
import java.util.*

@SpringBootApplication
@ImportAutoConfiguration(
    value = [ExposedAutoConfiguration::class],
    exclude = [DataSourceTransactionManagerAutoConfiguration::class]
)
class GamesProcessorApplication {

    @EventListener
    fun onApplicationEvent(event: ContextRefreshedEvent) {
        transaction {
            Players.deleteAll()
            val playerId = Players.insertAndGetId {
                it[id] = "4699292b-770f-4710-9496-ed9493aa0a6b123123"
                it[nick] = "t*****st@gmail.com"
                it[maxWin] = 15000u
            }

            GameSessions.deleteAll()
            GameSessions.insert {
                it[id] = UUID.fromString("16e2ea64-5725-40a4-99cc-c5c0deb8568d")
                it[player] = playerId
                it[isActive] = true
            }

            Currencies.deleteAll()
//			val btcCurrencyId = Currencies.insertAndGetId {
//				it[code] = "BTC"
//				it[denomination] = 8u
//			}
//			val funCurrencyId = Currencies.insertAndGetId {
//				it[code] = "FUN"
//				it[denomination] = 8u
//			}
            val usdCurrencyId = Currencies.insertAndGetId {
                it[code] = "USD"
                it[denomination] = 8u
            }
//			val rubCurrencyId = Currencies.insertAndGetId {
//				it[code] = "RUB"
//				it[denomination] = 8u
//			}

            Balances.deleteAll()
            Balances.insert {
                it[player] = playerId
                it[currency] = usdCurrencyId
                it[amount] = 1000
            }
        }
    }
}

fun main(args: Array<String>) {
    runApplication<GamesProcessorApplication>(*args)
}
