package com.eduanbekker.cryptotrader.exchange.binance

import com.binance.api.client.BinanceApiRestClient
import com.eduanbekker.cryptotrader.exchange.ExchangeApi

class BinanceExchange(private val binanceApiRestClient: BinanceApiRestClient) : ExchangeApi {
	override fun getName() = "Binance"

	override fun getPrice(symbol: String) =
			binanceApiRestClient.getPrice(symbol).price.toDouble()

}