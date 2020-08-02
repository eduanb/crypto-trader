package com.eduanbekker.cryptotrader.exchange.kucoin

import com.eduanbekker.cryptotrader.exchange.ExchangeApi
import com.kucoin.sdk.KucoinRestClient
import com.kucoin.sdk.rest.request.OrderCreateApiRequest
import java.math.BigDecimal
import java.util.*


class KucoinExchange(private val kucoinRestClient: KucoinRestClient) : ExchangeApi {
    override fun getName() = "kucoin"

    override fun getPrice(symbol: String): Double {
        return kucoinRestClient.symbolAPI().getTicker(symbol).price.toDouble()
    }

    override fun getBalance(symbol: String): Double {
        //  Account type:，"main" or "trade" (We are interested in trade account)
        return kucoinRestClient.accountAPI().listAccounts(symbol, "trade")[1].balance.toDouble()
    }

    override fun getSymbols(): List<String> {
        return kucoinRestClient.symbolAPI().symbols.map { it.symbol }
    }

    override fun marketBuyOrder(symbol: String, quantity: Double, price: Double?): String {
        val request = OrderCreateApiRequest.builder()
                .price(price?.toBigDecimal()).size(quantity.toBigDecimal()).side("buy")
                .symbol(symbol).type("market").clientOid(UUID.randomUUID().toString()).build()
        return kucoinRestClient.orderAPI().createOrder(request).orderId
    }

    override fun marketSellOrder(symbol: String, quantity: Double, price: Double?): String {
        val request = OrderCreateApiRequest.builder()
                .price(price?.toBigDecimal()).size(quantity.toBigDecimal()).side("buy")
                .symbol(symbol).type("market").clientOid(UUID.randomUUID().toString()).build()
        return kucoinRestClient.orderAPI().createOrder(request).orderId
    }

}