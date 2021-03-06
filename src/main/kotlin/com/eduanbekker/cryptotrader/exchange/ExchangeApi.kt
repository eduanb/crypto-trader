package com.eduanbekker.cryptotrader.exchange

interface ExchangeApi {
	fun getName(): String
	fun getPrice(symbol: String): Double
	fun getBalance(symbol: String): Double
	fun getSymbols(): List<Symbol>

	/**
	 * Buys the symbol at the current market price
	 * @param symbol The symbol to buy like ETHBTC
	 * @param quantity The quantity to order
	 * @return OrderId
	 */
	fun marketBuyOrder(symbol: String, quantity: Double, price: Double?): String

	/**
	 * Sells the symbol at the current market price
	 * @param symbol The symbol to buy like ETHBTC
	 * @param quantity The quantity to order
	 * @return OrderId
	 */
	fun marketSellOrder(symbol: String, quantity: Double, price: Double?): String

	fun getLotSize(symbol: String): LotSize
}
