package com.example.myapplication

import java.util.*
import kotlin.math.abs
import kotlin.math.pow

fun main() {
//    println(reverse("-1221"))
    println(intToRoman(400))
}

fun reverse(string: String): Boolean {
    val charArray = string.toCharArray()
    val stack = Stack<Char>()
    val arrayReverse = CharArray(charArray.size)

    for (element in charArray) {
        stack.push(element)
    }
    for (i in charArray.indices) {
        arrayReverse[i] = stack.pop()
    }

    return String(arrayReverse) == string
}

fun intToRoman(num: Int): String {
    val arrayString = num.toString().toCharArray()
    val size = arrayString.size
    var number: Int
    val array = arrayListOf<Int>()
    var numberString = ""

    for (i in 0 until size) {
        number = arrayString[i].toString().toInt() * (10.toDouble().pow((size - 1 - i).toDouble())).toInt()
        array.add(number)
    }

    array.forEach { numberString += check(it) }
    return numberString
}

fun check(num: Int): String {
    val list = listOf(1, 5, 10, 50, 100, 500, 1000)
    val listUnitNum = listOf(1, 5, 10)

    return if (num in list) convert(num)
    else {
        val size = num.toString().toCharArray().size
        var romanNumberString = ""
        if (size > 1) {
            val romanNumberInt = (10.toDouble().pow((size - 1).toDouble())).toInt()
            val quantityRoman = num / romanNumberInt

            for (i in 0 until quantityRoman) {
                romanNumberString += convert(romanNumberInt)
            }
            println("roman $romanNumberString")
        } else {
            val max = listUnitNum.first { num < it }
            when {
                max - num == 1 -> romanNumberString = convert(1) + convert(max)
                max - num > 1 -> {
                    when (num) {
                        in 5..8 -> {
                            romanNumberString += convert(5)
                            for (i in 0 until abs(5 - num)) {
                                romanNumberString += convert(1)
                            }
                        }
                        in 1..3 -> {
                            for (i in 0 until num) {
                                romanNumberString += convert(1)
                            }
                        }
                        else -> romanNumberString = convert(1) + convert(max)
                    }
                }
            }
        }
        romanNumberString
    }

}

fun convert(num: Int): String {
    return when (num) {
        1 -> "I"
        5 -> "V"
        10 -> "X"
        50 -> "L"
        100 -> "C"
        500 -> "D"
        else -> "M"
    }
}

