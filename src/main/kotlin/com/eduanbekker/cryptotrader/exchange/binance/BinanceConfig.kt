package com.eduanbekker.cryptotrader.exchange.binance

import com.binance.api.client.BinanceApiClientFactory
import com.binance.api.client.BinanceApiRestClient
import com.eduanbekker.cryptotrader.domain.LotSizeRepository
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
	fun binanceExchangeApi(binanceApiRestClient: BinanceApiRestClient, decimalConverter: (String, String) -> NumberFormat) =
			BinanceExchange(binanceApiRestClient, decimalConverter)

	@Bean
	fun decimalConverter(lotSizeRepository: LotSizeRepository): (String, String) -> NumberFormat = { exchange: String, symbol: String ->
		lotSizeRepository.findFirstByExchangeAndSymbolOrderByTimestampDesc(exchange, symbol)
				.step
				.substringBefore("1")
				.replace("0", "#")
				.plus("#")
				.let { DecimalFormat.getInstance(Locale.US) }
				.also { it.roundingMode = RoundingMode.DOWN }
	}
}