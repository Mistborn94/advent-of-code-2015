package day14

import helper.Debug

val pattern = "([A-Za-z]+) can fly (\\d+) km/s for (\\d+) seconds, but then must rest for (\\d+) seconds.".toRegex()

fun solveA(text: String, debug: Debug = Debug.Disabled, duration: Int = 2503): Int {
    return text.lines().maxOf {
        val (speed, travelTime, restTime) = pattern.matchEntire(it)!!.groupValues.drop(2).map { it.toInt() }
        distanceTravelled(travelTime, restTime, speed, duration)
    }
}

private fun distanceTravelled(flyDuration: Int, restDuration: Int, speed: Int, duration: Int): Int {
    val totalTime = flyDuration + restDuration
    val count = duration / totalTime
    val remainder = duration % totalTime

    return count * flyDuration * speed + minOf(flyDuration, remainder) * speed
}

data class Reindeer(val speed: Int, val flyDuration: Int, val restDuration: Int)

fun solveB(text: String, debug: Debug = Debug.Disabled, duration: Int = 2503): Int {
    val reindeerMap = text.lines().associate { line ->
        val matchResult = pattern.matchEntire(line)!!
        val (speed, travelTime, restTime) = matchResult.groupValues.drop(2).map { it.toInt() }
        matchResult.groupValues[1] to Reindeer(speed, travelTime, restTime)
    }

    val winners = (1..duration).flatMap { t ->
        var max = Int.MIN_VALUE
        val winningReindeer = mutableSetOf<String>()
        reindeerMap.keys.forEach { name ->
            val reindeer = reindeerMap[name]!!
            val travelDistance = distanceTravelled(reindeer.flyDuration, reindeer.restDuration, reindeer.speed, t)
            if (travelDistance > max) {
                winningReindeer.clear()
                winningReindeer.add(name)
                max = travelDistance
            } else if (travelDistance == max) {
                winningReindeer.add(name)
            }
        }
        winningReindeer
    }

    return winners.groupingBy { it }.eachCount().maxOf { it.value }
}
