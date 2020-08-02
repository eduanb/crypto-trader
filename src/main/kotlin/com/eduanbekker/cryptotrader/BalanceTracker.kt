package com.eduanbekker.cryptotrader

import com.eduanbekker.cryptotrader.domain.Balance
import com.eduanbekker.cryptotrader.domain.BalanceRepository
import com.eduanbekker.cryptotrader.exchange.ExchangeApi
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.time.ZonedDateTime

@Service
class BalanceTracker(
		val exchanges: List<ExchangeApi>,
		val balanceRepository: BalanceRepository,
		val config: Config
) {
	val logger: Logger = LoggerFactory.getLogger(this.javaClass)

	@Scheduled(fixedDelay = 60 * 1000)
	fun saveValues() {
		exchanges.forEach { exchange ->
			config.currencies.forEach { symbol ->
				val balanceFromExchange = exchange.getBalance(symbol)
				logger.info("$symbol:$balanceFromExchange")
				balanceRepository.save(
						Balance(exchange = exchange.getName(),
								symbol = symbol,
								balance = balanceFromExchange,
								timestamp = ZonedDateTime.now()
						)
				)
			}
		}
	}

}