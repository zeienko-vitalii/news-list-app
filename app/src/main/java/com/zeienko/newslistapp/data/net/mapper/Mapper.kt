package com.zeienko.newslistapp.data.net.mapper

interface Mapper<F, T> {
    fun map(from: F): T
}