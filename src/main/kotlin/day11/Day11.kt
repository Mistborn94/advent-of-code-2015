package day11

import helper.Debug

/**
 *     Passwords must include one increasing straight of at least three letters, like abc, bcd, cde, and so on, up to xyz. They cannot skip letters; abd doesn't count.
 *     Passwords may not contain the letters i, o, or l, as these letters can be mistaken for other characters and are therefore confusing.
 *     Passwords must contain at least two different, non-overlapping pairs of letters, like aa, bb, or zz.
 */

fun solveA(text: String, debug: Debug = Debug.Disabled): String {
    val password = text.trim().toMutableList()

    do {
        var index = password.lastIndex
        while (password[index] == 'z') {
            password[index] = 'a'
            index--
        }
        password[index] = password[index] + 1
    } while (!password.isValid())

    return password.joinToString(separator = "")
}

private fun List<Char>.isValid(): Boolean {
    val firstWindow = windowed(2).firstOrNull { (a, b) -> a == b }

    return firstWindow != null &&
            windowed(2).any { (a, b) -> a == b && a != firstWindow.first() } &&
            windowed(3).any { (a, b, c) -> b - a == 1 && c - b == 1 } &&
            none { it == 'i' || it == 'o' || it == 'l' }
}


fun solveB(text: String, debug: Debug = Debug.Disabled): String {
    return solveA(solveA(text))
}
