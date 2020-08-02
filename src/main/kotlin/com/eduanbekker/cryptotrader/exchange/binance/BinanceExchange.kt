package com.eduanbekker.cryptotrader.exchange.binance

import com.binance.api.client.BinanceApiRestClient
import com.binance.api.client.domain.account.NewOrder
import com.binance.api.client.domain.account.NewOrderResponseType
import com.eduanbekker.cryptotrader.exchange.ExchangeApi
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.text.NumberFormat

class BinanceExchange(
		private val binanceApiRestClient: BinanceApiRestClient,
		private val converter: (String) -> NumberFormat
) : ExchangeApi {
	val logger: Logger = LoggerFactory.getLogger(this.javaClass)

	override fun getName() = "Binance"

	override fun getPrice(symbol: String) =
			binanceApiRestClient.getPrice(symbol).price.toDouble()

	override fun getBalance(symbol: String) =
			binanceApiRestClient.account.getAssetBalance(symbol).free.toDouble()

	override fun getSymbols() = binanceApiRestClient.exchangeInfo.symbols.map { it.symbol }

	override fun marketBuyOrder(symbol: String, quantity: Double): Long =
			binanceApiRestClient.newOrder(NewOrder.marketBuy(symbol, convertAndLog(symbol, quantity))
					.newOrderRespType(NewOrderResponseType.FULL))
					.orderId

	override fun marketSellOrder(symbol: String, quantity: Double): Long =
			binanceApiRestClient.newOrder(NewOrder.marketSell(symbol, convertAndLog(symbol, quantity))
					.newOrderRespType(NewOrderResponseType.FULL))
					.orderId

	private fun convertAndLog(symbol: String, quantity: Double) =
			converter(symbol).format(quantity).also { logger.info("Converted $quantity to $it") }
}