package day19

import helper.Debug

fun solveA(text: String, debug: Debug = Debug.Disabled): Int {
    val replacements = text.lines().dropLast(2)
        .map { line -> line.split(" => ") }
        .map { (a, b) -> a.toRegex() to b }

    val molecule = text.lines().last()

    return replacements.flatMapTo(mutableSetOf<String>()) { (match, replace) ->
        debug {
            println("Applying rule $match => $replace")
        }
        val replaced = applyReplacementRule(molecule, match, replace)
        debug {
            println(replaced.joinToString("\n"))
        }
        replaced
    }.size
}

private fun applyReplacementRule(
    molecule: String,
    match: Regex,
    replace: String
): Sequence<String> {
    val replaced = match.findAll(molecule).map { matchResult ->
        molecule.take(matchResult.range.first) + replace + molecule.substring(matchResult.range.last + 1)
    }
    return replaced
}


fun solveB(text: String, debug: Debug = Debug.Disabled): Int {
    val replacements = text.lines().dropLast(2)
        .map { line -> line.split(" => ") }

    var molecule = text.lines().last()

    var count = 0
    var matched = true
    //I guess greedy eval works for this input
    while (molecule != "e" && matched) {
        matched = false
        for ((replace, match) in replacements) {
            if (match in molecule) {
                molecule = molecule.replaceFirst(match, replace)
                count += 1
                matched = true
            }
        }
    }
    require(molecule == "e") { "e != $molecule" }
    return count
}
