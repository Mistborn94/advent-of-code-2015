package day16

import helper.Debug

val giftDetails = """
children: 3
cats: 7
samoyeds: 2
pomeranians: 3
akitas: 0
vizslas: 0
goldfish: 5
trees: 3
cars: 2
perfumes: 1
"""

fun solveA(text: String, debug: Debug = Debug.Disabled): Int {
    val (gift, sues) = parseInput(text)

    gift.forEach { (k, v) ->
        sues.removeIf { (_, map) -> map.contains(k) && map[k] != v }
    }

    check(sues.size == 1) { "There should be only one sue left " }

    return sues[0].first
}

private fun parseInput(text: String): Pair<Map<String, Int>, MutableList<Pair<Int, Map<String, Int>>>> {
    val gift = giftDetails.trim().lines().associate { line ->
        val (name, count) = line.split(":")
        name to count.trim().toInt()
    }

    val sues = text.lines().map { line ->
        val id = line.drop(4).substringBefore(':').toInt()
        val characteristics = line.substringAfter(':')
            .split(',')
            .associate {
                val (name, count) = it.split(":")
                name.trim() to count.trim().toInt()
            }

        id to characteristics
    }.toMutableList()
    return Pair(gift, sues)
}


fun solveB(text: String, debug: Debug = Debug.Disabled): Int {
    val (gift, sues) = parseInput(text)

    val gt = setOf("cats", "trees")
    val lt = setOf("pomeranians", "goldfish")
    val eq = gift.keys - gt - lt

    gift.forEach { (k, v) ->
        sues.removeIf { (_, map) ->
            val realValue = map[k]
            realValue != null
                    && ((k in lt && realValue >= v)
                    || (k in gt && realValue <= v)
                    || (k in eq && realValue != v))
        }
    }

    check(sues.size == 1) { "There should be only one sue left " }

    return sues[0].first
}
