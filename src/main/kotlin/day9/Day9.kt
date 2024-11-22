package aoc2015.day9

import helper.Debug
import java.util.*
import kotlin.math.max

val pattern = "([^ ]+) to ([^ ]+) = (\\d+)".toRegex()

fun solveA(text: String, debug: Debug = Debug.Disabled): Int {
    val distances = parseDistances(text)

    return findShortestPath(distances)
}

private fun parseDistances(text: String): MutableMap<String, MutableMap<String, Int>> {
    val distances = mutableMapOf<String, MutableMap<String, Int>>()
    text.lines().forEach { line ->
        val matchResult = pattern.matchEntire(line)!!

        val firstCity = matchResult.groups[1]!!.value
        val secondCity = matchResult.groups[2]!!.value
        val distance = matchResult.groups[3]!!.value.toInt()

        distances.getOrPut(firstCity) { mutableMapOf() }[secondCity] = distance
        distances.getOrPut(secondCity) { mutableMapOf() }[firstCity] = distance
    }
    return distances
}

fun findShortestPath(distances: Map<String, Map<String, Int>>): Int {
    val cityCount = distances.size
    val toVisit = PriorityQueue(distances.keys.map { Path(it, setOf(it), 0) })

    var end = false

    var next: Path? = null
    while (toVisit.isNotEmpty() && !end) {
        next = toVisit.remove()

        if (next.seen.size == cityCount) {
            end = true
        } else {
            val neighbours = next.neighbours(distances)
            toVisit.addAll(neighbours)
        }
    }

    return next!!.score
}

fun findLongestPath(distances: Map<String, Map<String, Int>>): Int {
    val cityCount = distances.size
    val toVisit = PriorityQueue(distances.keys.map { Path(it, setOf(it), 0) })

    var highestScore = 0

    var next: Path? = null
    while (toVisit.isNotEmpty()) {
        next = toVisit.remove()

        if (next.seen.size == cityCount) {
            highestScore = max(highestScore, next.score)
        } else {
            val neighbours = next.neighbours(distances)
            toVisit.addAll(neighbours)
        }
    }

    return next!!.score
}

class Path(val current: String, val seen: Set<String>, val score: Int) : Comparable<Path> {

    override fun compareTo(other: Path): Int {
        return score.compareTo(other.score)
    }

    fun neighbours(distances: Map<String, Map<String, Int>>): List<Path> {
        return distances[current]!!.mapNotNull { (city, distance) ->
            if (city in seen) {
                null
            } else {
                Path(city, seen + city, score + distance)
            }
        }
    }
}


fun solveB(text: String, debug: Debug = Debug.Disabled): Int {
    val distances = parseDistances(text)
    return findLongestPath(distances)
}
