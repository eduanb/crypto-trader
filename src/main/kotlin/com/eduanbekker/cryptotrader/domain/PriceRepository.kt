package com.eduanbekker.cryptotrader.domain

import org.springframework.data.repository.CrudRepository

interface PriceRepository : CrudRepository<Price, Long>