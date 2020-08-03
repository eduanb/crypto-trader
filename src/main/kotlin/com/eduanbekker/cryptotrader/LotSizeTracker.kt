package com.eduanbekker.cryptotrader

import com.eduanbekker.cryptotrader.domain.LotSize
import com.eduanbekker.cryptotrader.domain.LotSizeRepository
import com.eduanbekker.cryptotrader.exchange.ExchangeApi
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.time.ZonedDateTime

@Service
class LotSizeTracker(
		val exchanges: List<ExchangeApi>,
		val lotSizeRepository: LotSizeRepository,
		val config: Config
) {

	@Scheduled(fixedDelay = 1 * 24 * 60 * 60 * 1000)
	fun saveValues() {
		exchanges.forEach { exchange ->
			config.symbols.forEach { symbol ->
				val lotSize = exchange.getLotSize(symbol)
				lotSizeRepository.save(
						LotSize(exchange = exchange.getName(),
								symbol = symbol,
								timestamp = ZonedDateTime.now(),
								step = lotSize.step,
								maximum = lotSize.maximum,
								minimum = lotSize.minimum
						)
				)
			}
		}
	}

}