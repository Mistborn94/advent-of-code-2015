package day13

import helper.Debug
import helper.permutations

val me = "ME"
val pattern = "([^ ]+) would (gain|lose) (\\d+) happiness units by sitting next to ([^ ]+).".toRegex()
fun solveA(text: String, debug: Debug = Debug.Disabled): Int {
    val rules = parseRules(text)
    val names = rules.keys.mapTo(mutableSetOf()) { it.first }

    return optimalSeatingScore(names, rules)
}

private fun optimalSeatingScore(
    names: Set<String>,
    rules: Map<Pair<String, String>, Int>
) = names.drop(1).permutations().map { listOf(names.first()) + it }
    .maxOf { happinessGain(it, rules) }

private fun parseRules(text: String) = text.lines().associate { line ->
    val (target, effect, number, neighbour) = pattern.matchEntire(line)!!.destructured

    val happinessEffect = if (effect == "gain") number.toInt() else -number.toInt()

    val key = target to neighbour
    key to happinessEffect
}

fun happinessGain(names: List<String>, rules: Map<Pair<String, String>, Int>): Int =
    names.zipWithNext().sumOf { (a, b) ->
        happinessGain(rules, a, b)
    } + happinessGain(rules, names.first(), names.last())

private fun happinessGain(
    rules: Map<Pair<String, String>, Int>,
    a: String,
    b: String
): Int {
    return if (a == me || b == me) 0 else rules[a to b]!! + rules[b to a]!!
}


fun solveB(text: String, debug: Debug = Debug.Disabled): Int {
    val rules = parseRules(text)
    val names = rules.keys.mapTo(mutableSetOf()) { it.first } + me

    return optimalSeatingScore(names, rules)
}
