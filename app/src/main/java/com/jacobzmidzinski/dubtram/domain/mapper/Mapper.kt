package com.jacobzmidzinski.dubtram.domain.mapper

interface Mapper<T, E> {
    fun from(e: E): T
}