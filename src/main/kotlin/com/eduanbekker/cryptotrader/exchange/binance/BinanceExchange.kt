package com.eduanbekker.cryptotrader.exchange.binance

import com.binance.api.client.BinanceApiRestClient
import com.binance.api.client.domain.account.NewOrder
import com.binance.api.client.domain.account.NewOrderResponseType
import com.binance.api.client.domain.general.FilterType
import com.eduanbekker.cryptotrader.exchange.ExchangeApi
import com.eduanbekker.cryptotrader.exchange.LotSize
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.text.NumberFormat

class BinanceExchange(
		private val binanceApiRestClient: BinanceApiRestClient,
		private val converter: (String, String) -> NumberFormat
) : ExchangeApi {
	val logger: Logger = LoggerFactory.getLogger(this.javaClass)

	//Memoizes info in memory for lifetime of app
	private val exchangeInfo by lazy {
		binanceApiRestClient.exchangeInfo
	}

	override fun getName() = "Binance"

	override fun getPrice(symbol: String) =
			binanceApiRestClient.getPrice(symbol).price.toDouble()

	override fun getBalance(symbol: String) =
			binanceApiRestClient.account.getAssetBalance(symbol).free.toDouble()

	override fun getSymbols() = exchangeInfo.symbols.map { it.symbol }

	override fun marketBuyOrder(symbol: String, quantity: Double, price: Double?): String =
			binanceApiRestClient.newOrder(NewOrder.marketBuy(symbol, convertAndLog(symbol, quantity))
					.newOrderRespType(NewOrderResponseType.FULL))
					.orderId.toString()

	override fun marketSellOrder(symbol: String, quantity: Double, price: Double?): String =
			binanceApiRestClient.newOrder(NewOrder.marketSell(symbol, convertAndLog(symbol, quantity))
					.newOrderRespType(NewOrderResponseType.FULL))
					.orderId.toString()

	override fun getLotSize(symbol: String): LotSize =
			exchangeInfo.symbols
					.first { it.symbol == symbol }
					.getSymbolFilter(FilterType.LOT_SIZE)
					.let {
						LotSize(
								minimum = it.minQty.toDouble(),
								maximum = it.maxQty.toDouble(),
								step = it.stepSize
						)
					}

	private fun convertAndLog(symbol: String, quantity: Double) =
			converter(getName(), symbol).format(quantity).also { logger.info("Converted $quantity to $it") }
}
