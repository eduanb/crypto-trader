package com.eduanbekker.cryptotrader.exchange

interface ExchangeApi {
	fun getName(): String
	fun getPrice(symbol: String): Double
}