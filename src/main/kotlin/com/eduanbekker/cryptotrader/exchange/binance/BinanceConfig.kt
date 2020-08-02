package com.eduanbekker.cryptotrader.exchange.binance

import com.binance.api.client.BinanceApiClientFactory
import com.binance.api.client.BinanceApiRestClient
import com.binance.api.client.domain.general.FilterType
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

@Configuration
@ConditionalOnBinance
class BinanceConfig {
	@Bean
	fun binanceApiRestClient(binanceCredentials: BinanceCredentials): BinanceApiRestClient =
			BinanceApiClientFactory.newInstance(binanceCredentials.key, binanceCredentials.secret).newRestClient()

	@Bean
	fun binanceExchangeApi(binanceApiRestClient: BinanceApiRestClient, decimalConverter: (String) -> NumberFormat) =
			BinanceExchange(binanceApiRestClient, decimalConverter)

	@Bean
	fun decimalConverter(binanceApiRestClient: BinanceApiRestClient): (String) -> NumberFormat = { symbol: String ->
		binanceApiRestClient.exchangeInfo.symbols
				.first { it.symbol == symbol }
				.getSymbolFilter(FilterType.LOT_SIZE)
				.stepSize
				.substringBefore("1")
				.replace("0", "#")
				.plus("#")
				.let { DecimalFormat.getInstance(Locale.US) }
				.also { it.roundingMode = RoundingMode.DOWN }
	}
}