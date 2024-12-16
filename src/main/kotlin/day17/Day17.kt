package day17

import helper.Debug

fun solveA(text: String, debug: Debug = Debug.Disabled, eggnogLitres: Int = 150): Int {
    val containers = text.lines().map { it.toInt() }.sorted()
    return countCombinations(containers, debug, eggnogLitres)
}

fun countCombinations(containers: List<Int>, debug: Debug, remainder: Int): Int {
    if (remainder == 0) {
        return 1
    } else if (remainder < 0 || containers.isEmpty()) {
        return 0
    } else {
        val newContainers = containers.drop(1)
        return countCombinations(newContainers, debug, remainder - containers[0]) +
                countCombinations(newContainers, debug, remainder)
    }
}

fun countCombinations(containers: List<Int>, debug: Debug, remainder: Int, remainingContainerCount: Int): Int {
    if (remainder == 0 && remainingContainerCount == 0) {
        return 1
    } else if (remainder <= 0 || containers.isEmpty() || remainingContainerCount < 0) {
        return 0
    } else {
        val newContainers = containers.drop(1)
        return countCombinations(newContainers, debug, remainder - containers[0], remainingContainerCount - 1) +
                countCombinations(newContainers, debug, remainder, remainingContainerCount)
    }
}

fun minContainerCount(containers: List<Int>, debug: Debug, remainder: Int, containerCount: Int = 0): Int {
    if (remainder == 0) {
        return containerCount
    } else if (remainder < 0 || containers.isEmpty()) {
        return Int.MAX_VALUE
    } else {
        val newContainers = containers.drop(1)
        return minOf(
            minContainerCount(newContainers, debug, remainder - containers[0], containerCount + 1),
            minContainerCount(newContainers, debug, remainder, containerCount)
        )
    }
}


fun solveB(text: String, debug: Debug = Debug.Disabled, eggnogLitres: Int = 150): Int {
    val containers = text.lines().map { it.toInt() }.sorted()
    val minContainerCount = minContainerCount(containers, debug, eggnogLitres)
    return countCombinations(containers, debug, eggnogLitres, minContainerCount)
}
