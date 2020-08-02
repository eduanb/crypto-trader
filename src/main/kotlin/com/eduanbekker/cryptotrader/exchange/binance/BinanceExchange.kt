package com.eduanbekker.cryptotrader.exchange.binance

import com.binance.api.client.BinanceApiRestClient
import com.binance.api.client.domain.account.NewOrder
import com.binance.api.client.domain.account.NewOrderResponseType
import com.eduanbekker.cryptotrader.exchange.ExchangeApi

class BinanceExchange(private val binanceApiRestClient: BinanceApiRestClient) : ExchangeApi {
	override fun getName() = "Binance"

	override fun getPrice(symbol: String) =
			binanceApiRestClient.getPrice(symbol).price.toDouble()

	override fun getBalance(symbol: String) =
			binanceApiRestClient.account.getAssetBalance(symbol).free.toDouble()

	override fun getSymbols() = binanceApiRestClient.exchangeInfo.symbols.map { it.symbol }

	override fun marketBuyOrder(symbol: String, quantity: Double): Long =
			binanceApiRestClient.newOrder(NewOrder.marketBuy(symbol, quantity.toString())
					.newOrderRespType(NewOrderResponseType.FULL))
					.orderId

	override fun marketSellOrder(symbol: String, quantity: Double): Long =
			binanceApiRestClient.newOrder(NewOrder.marketSell(symbol, quantity.toString())
					.newOrderRespType(NewOrderResponseType.FULL))
					.orderId

}