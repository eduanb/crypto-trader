package com.eduanbekker.cryptotrader.domain

import org.springframework.data.repository.CrudRepository

interface SymbolRepository: CrudRepository<Symbol, Long> {
	fun findByExchangeAndBaseCurrency(exchange: String, baseCurrency: String) : List<Symbol>
	fun findByExchangeAndQuoteCurrency(exchange: String, quoteCurrency: String): List<Symbol>
}