package com.eduanbekker.cryptotrader

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConfigurationProperties("config")
@ConstructorBinding
data class Config(
		val symbols: List<String>,
		val currencies: List<String>
)
