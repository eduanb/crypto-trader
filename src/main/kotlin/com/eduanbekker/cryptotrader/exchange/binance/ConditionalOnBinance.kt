package com.eduanbekker.cryptotrader.exchange.binance

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty

@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@ConditionalOnProperty(value = ["exchange.binance.enabled"], havingValue = "true")
annotation class ConditionalOnBinance