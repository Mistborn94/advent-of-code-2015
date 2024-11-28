package day2

import helper.Debug

val pattern = "(\\d+)x(\\d+)x(\\d+)".toRegex()

fun solveA(text: String, debug: Debug = Debug.Disabled): Int {
    return text.lines().sumOf {
        calcWrappingPaper(it)
    }
}

fun calcWrappingPaper(it: String): Int {
    val (l, w, h) = pattern.matchEntire(it)!!.groupValues.mapNotNull { it.toIntOrNull() }

    val face1 = l * w
    val face2 = w * h
    val face3 = h * l

    return 2 * face1 + 2 * face2 + 2 * face3 + minOf(face1, face2, face3)
}

fun calcRibbon(it: String): Int {
    val (l, w, h) = pattern.matchEntire(it)!!.groupValues.mapNotNull { it.toIntOrNull() }

    val volume = l * h * w
    val face1 = 2 * (l + w)
    val face2 = 2 * (l + h)
    val face3 = 2 * (w + h)

    return minOf(face1, face2, face3) + volume
}


fun solveB(text: String, debug: Debug = Debug.Disabled): Int {
    return text.lines().sumOf { calcRibbon(it) }
}
