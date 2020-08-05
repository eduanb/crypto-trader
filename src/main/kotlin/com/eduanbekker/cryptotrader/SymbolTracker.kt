package com.eduanbekker.cryptotrader

import com.eduanbekker.cryptotrader.domain.Symbol
import com.eduanbekker.cryptotrader.domain.SymbolRepository
import com.eduanbekker.cryptotrader.exchange.ExchangeApi
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class SymbolTracker(
		val exchanges: List<ExchangeApi>,
		val symbolRepository: SymbolRepository
) {

	@Scheduled(fixedDelay = 1 * 24 * 60 * 60 * 1000)
	fun saveValues() {
		exchanges.forEach { exchange ->
			exchange.getSymbols().forEach { symbol ->
				symbolRepository.save(
						Symbol(exchange = exchange.getName(),
								symbol = symbol.symbol,
								baseCurrency = symbol.baseCurrency,
								quoteCurrency = symbol.quoteCurrency
						)
				)
			}
		}
	}

}