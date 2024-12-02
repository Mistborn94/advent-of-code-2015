package day8

import helper.Debug

fun solveA(text: String, debug: Debug = Debug.Disabled): Int {
    return text.lines().sumOf { charDifferenceA(it, debug) }
}

fun charDifferenceA(line: String, debug: Debug): Int {
    val transformed = line.trim('"')
        .replace("""\\x[a-f0-9][a-f0-9]""".toRegex(), "x")
        .replace("""\"""", """"""")
        .replace("""\\""", """\""")

    debug {
        println("[${line.length} -> ${transformed.length}]: $line - $transformed")
    }

    return line.length - transformed.length
}

fun charDifferenceB(line: String, debug: Debug): Int {
    val transformed = line.replace("""\""", """\\""")
        .replace("\"", """\"""")
        .let { "\"$it\"" }

    debug {
        println("[${line.length} -> ${transformed.length}]: $line - $transformed")
    }

    return transformed.length - line.length
}


fun solveB(text: String, debug: Debug = Debug.Disabled): Int {
    return text.lines().sumOf { charDifferenceB(it, debug) }
}
