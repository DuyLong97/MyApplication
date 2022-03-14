package com.example.myapplication

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull

fun main(){
    runBlocking {
        a()
    }
}

suspend fun a() {
    var count = 0
    withTimeoutOrNull(4000) {
        while (true) {
            delay(500)
            println(++count)
        }
    }
    println("End")
}

