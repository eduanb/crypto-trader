package com.eduanbekker.cryptotrader.exchange.binance

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConditionalOnBinance
@ConfigurationProperties("binance")
data class BinanceCredentials(
		val key: String,
		val secret: String
)