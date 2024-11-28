package day5

import helper.Debug

fun solveA(text: String, debug: Debug = Debug.Disabled): Int {
    return text.lines().count { isNiceA(it, debug) }
}

val vowels = setOf('a', 'e', 'i', 'o', 'u')
val forbidden = setOf("ab", "cd", "pq", "xy")
fun isNiceA(text: String, debug: Debug = Debug.Disabled): Boolean {
    return text.count { it in vowels } >= 3
            && forbidden.none { it in text }
            && text.zipWithNext().any { (a, b) -> a == b }
}


fun solveB(text: String, debug: Debug = Debug.Disabled): Int {

    return text.lines().count { isNiceB(it, debug) }
}

fun isNiceB(text: String, debug: Debug): Boolean {
    val range = 0..text.lastIndex - 2
    return range.any { text[it] == text[it + 2] }
            && range.any { text.indexOf("${text[it]}${text[it + 1]}", it + 2) > 0 }
}
