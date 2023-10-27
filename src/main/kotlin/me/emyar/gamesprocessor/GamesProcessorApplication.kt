package me.emyar.gamesprocessor

import me.emyar.gamesprocessor.model.Currencies
import org.jetbrains.exposed.spring.autoconfigure.ExposedAutoConfiguration
import org.jetbrains.exposed.sql.deleteAll
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.boot.autoconfigure.ImportAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.context.event.EventListener

@SpringBootApplication
@ImportAutoConfiguration(
	value = [ExposedAutoConfiguration::class],
	exclude = [DataSourceTransactionManagerAutoConfiguration::class]
)
class GamesProcessorApplication {

	@EventListener
	fun onApplicationEvent(event: ContextRefreshedEvent) {
		transaction {
			Currencies.deleteAll()
			val btcCurrencyId = Currencies.insertAndGetId {
				it[code] = "BTC"
				it[denomination] = 8u
			}
			val funCurrencyId = Currencies.insertAndGetId {
				it[code] = "FUN"
				it[denomination] = 8u
			}
			val usdCurrencyId = Currencies.insertAndGetId {
				it[code] = "USD"
				it[denomination] = 8u
			}
			val rubCurrencyId = Currencies.insertAndGetId {
				it[code] = "RUB"
				it[denomination] = 8u
			}
		}
	}
}

fun main(args: Array<String>) {
	runApplication<GamesProcessorApplication>(*args)
}
