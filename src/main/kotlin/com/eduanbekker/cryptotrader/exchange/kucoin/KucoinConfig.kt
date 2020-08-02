package com.eduanbekker.cryptotrader.exchange.kucoin

import com.kucoin.sdk.KucoinClientBuilder
import com.kucoin.sdk.KucoinRestClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@ConditionalOnKucoin
class KucoinConfig {

    @Bean
    fun kucoinApiRestClient(kucoinCredentials: KucoinCredentials): KucoinRestClient =
            KucoinClientBuilder().withApiKey(kucoinCredentials.key, kucoinCredentials.secret, kucoinCredentials.passPhrase).buildRestClient()

    @Bean
    fun kucoinExchangeApi(kucoinRestClient: KucoinRestClient) = KucoinExchange(kucoinRestClient)
}
