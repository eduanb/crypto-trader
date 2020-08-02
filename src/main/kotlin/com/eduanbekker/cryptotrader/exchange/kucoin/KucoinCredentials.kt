package com.eduanbekker.cryptotrader.exchange.kucoin

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConditionalOnKucoin
@ConfigurationProperties("kucoin")
data class KucoinCredentials(
        val key: String,
        val secret: String,
        val passPhrase: String
)
