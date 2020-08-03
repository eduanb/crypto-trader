package com.eduanbekker.cryptotrader.domain

import org.springframework.data.repository.CrudRepository

interface LotSizeRepository: CrudRepository<LotSize, Long> {
	fun findFirstByExchangeAndSymbolOrderByTimestampDesc(exchange: String, symbol: String): LotSize
}