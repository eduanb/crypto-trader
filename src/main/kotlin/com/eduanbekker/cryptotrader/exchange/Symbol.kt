package com.eduanbekker.cryptotrader.exchange

data class Symbol(
		val symbol: String,
		val baseCurrency: String, //LHS
		val quoteCurrency: String //RHS
)