package com.eduanbekker.cryptotrader.events

import org.springframework.context.ApplicationEvent

class PriceUpdatedEvent(source: Any) : ApplicationEvent(source)