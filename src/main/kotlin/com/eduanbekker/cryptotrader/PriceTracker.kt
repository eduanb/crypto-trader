package com.eduanbekker.cryptotrader

import com.eduanbekker.cryptotrader.domain.Price
import com.eduanbekker.cryptotrader.domain.PriceRepository
import com.eduanbekker.cryptotrader.events.PriceUpdatedEvent
import com.eduanbekker.cryptotrader.exchange.ExchangeApi
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.ApplicationEventPublisher
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.time.ZonedDateTime

@Service
class PriceTracker(
		val exchanges: List<ExchangeApi>,
		val priceRepository: PriceRepository,
		val config: Config,
		val applicationEventPublisher: ApplicationEventPublisher
) {
	val logger: Logger = LoggerFactory.getLogger(this.javaClass)

	@Scheduled(fixedDelay = 60 * 1000)
	fun saveValues() {
		exchanges.forEach { exchange ->
			config.symbols.forEach { symbol ->
				getAndSave(exchange, symbol)
			}
		}
		applicationEventPublisher.publishEvent(PriceUpdatedEvent(this))
	}

	fun getAndSave(exchange: ExchangeApi, symbol: String) = exchange.getPrice(symbol).let { priceFromExchange ->
		priceRepository.save(Price(
				exchange = exchange.getName(),
				timestamp = ZonedDateTime.now(),
				price = priceFromExchange,
				symbol = symbol
		)).also {
			logger.info(it.toString())
		}
	}

}
