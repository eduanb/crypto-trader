package com.eduanbekker.cryptotrader.exchange.binance

import com.binance.api.client.BinanceApiClientFactory
import com.binance.api.client.BinanceApiRestClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@ConditionalOnBinance
class BinanceConfig {
	@Bean
	fun binanceApiRestClient(binanceCredentials: BinanceCredentials): BinanceApiRestClient =
			BinanceApiClientFactory.newInstance(binanceCredentials.key, binanceCredentials.secret).newRestClient()

	@Bean
	fun binanceExchangeApi(binanceApiRestClient: BinanceApiRestClient) = BinanceExchange(binanceApiRestClient)
}