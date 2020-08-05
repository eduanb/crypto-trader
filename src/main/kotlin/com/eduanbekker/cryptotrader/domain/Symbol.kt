package com.eduanbekker.cryptotrader.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Symbol(
		@Id
		@GeneratedValue
		var id: Long = 0,

		val exchange: String,
		val symbol: String,
		val baseCurrency: String,
		val quoteCurrency: String
)