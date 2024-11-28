package day4

import helper.Debug
import java.security.MessageDigest

fun solveA(text: String, debug: Debug = Debug.Disabled): Int {

    return solve(text, "00000")
}

@OptIn(ExperimentalStdlibApi::class)
private fun solve(text: String, prefix: String): Int {
    val messageDigest = MessageDigest.getInstance("MD5")
    return generateSequence(0, Int::inc)
        .first { messageDigest.digest("$text$it".toByteArray()).toHexString().startsWith(prefix) }
}


fun solveB(text: String, debug: Debug = Debug.Disabled): Int {

    return solve(text, "000000")
}
