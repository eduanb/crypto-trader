package com.eduanbekker.cryptotrader.exchange.kucoin

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty

@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@ConditionalOnProperty(value = ["exchange.kucoin.enabled"], havingValue = "true")
annotation class ConditionalOnKucoin
