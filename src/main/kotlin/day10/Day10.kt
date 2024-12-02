package day10

import helper.Debug
import helper.removeFirstN

fun solve(text: String, debug: Debug = Debug.Disabled, reps: Int = 40): Int {
    var result = text
    repeat(reps) {
        result = lookAndSay(result)
        debug {
            println("Repetition $it: Length ${result.length}")
        }
    }
    return result.length
}

fun lookAndSay(result: String): String {
    val list = result.toMutableList()
    return buildString {
        while (list.isNotEmpty()) {
            val current = list.removeAt(0)
            val indexOfNext = list.indexOfFirst { it != current }
            val count = if (indexOfNext == -1) list.size + 1 else indexOfNext + 1
            list.removeFirstN(count - 1)
            append("${count}$current")
        }
    }
}
