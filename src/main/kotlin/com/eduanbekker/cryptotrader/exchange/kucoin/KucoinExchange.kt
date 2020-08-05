package com.eduanbekker.cryptotrader.exchange.kucoin

import com.eduanbekker.cryptotrader.exchange.ExchangeApi
import com.eduanbekker.cryptotrader.exchange.LotSize
import com.eduanbekker.cryptotrader.exchange.Symbol
import com.kucoin.sdk.KucoinRestClient
import com.kucoin.sdk.rest.request.OrderCreateApiRequest
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

    override fun getSymbols(): List<Symbol> {
        return kucoinRestClient.symbolAPI().symbols.map {
			Symbol(
					symbol = it.symbol,
					baseCurrency = it.baseCurrency,
					quoteCurrency = it.quoteCurrency
			)
		}
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

	override fun getLotSize(symbol: String): LotSize =
		kucoinRestClient.symbolAPI().symbols
				.first { it.name == symbol }
				.let {
					LotSize(
							minimum = it.baseMinSize.toDouble(),
							maximum = it.baseMaxSize.toDouble(),
							step = it.priceIncrement.toString()
					)
				}

}
