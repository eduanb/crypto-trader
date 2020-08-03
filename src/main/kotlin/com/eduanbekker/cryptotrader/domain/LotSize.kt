package com.eduanbekker.cryptotrader.domain

import java.time.ZonedDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class LotSize(
		@Id
		@GeneratedValue
		var id: Long = 0,

		val exchange: String,
		val timestamp: ZonedDateTime,
		val symbol: String,

		val minimum: Double,
		val maximum: Double,
		val step: String
)