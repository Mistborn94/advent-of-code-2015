package day1

import helper.Debug

fun solveA(text: String, debug: Debug = Debug.Disabled): Int {

    return text.fold(0) { acc, char ->
        when (char) {
            '(' -> acc + 1
            ')' -> acc - 1
            else -> TODO("Invalid char $char")
        }
    }
}


fun solveB(text: String, debug: Debug = Debug.Disabled): Int {
    var acc = 0
    text.forEachIndexed() { i, char ->
        acc = when (char) {
            '(' -> acc + 1
            ')' -> acc - 1
            else -> TODO("Invalid char $char")
        }

        if (acc < 0) return i + 1
    }
    return -1
}
