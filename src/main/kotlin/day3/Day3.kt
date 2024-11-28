package day3

import helper.Debug
import helper.point.Direction
import helper.point.base.Point

fun solveA(text: String, debug: Debug = Debug.Disabled): Int {
    var currentPosition = Point(0, 0)
    val visitedHouses = mutableSetOf(currentPosition)
    text.forEach {
        val direction = parseDirection(it)

        currentPosition += direction.point
        visitedHouses.add(currentPosition)
    }
    return visitedHouses.size
}

private fun parseDirection(it: Char) = when (it) {
    '^' -> Direction.UP
    'v' -> Direction.DOWN
    '<' -> Direction.LEFT
    '>' -> Direction.RIGHT
    else -> TODO("Unknown direction $it")
}


fun solveB(text: String, debug: Debug = Debug.Disabled): Int {
    var santaPosition = Point(0, 0)
    val visited = mutableSetOf(santaPosition)

    var roboPosition = Point(0, 0)

    text.forEachIndexed { i, char ->
        val direction = parseDirection(char)

        if (i % 2 == 0) {
            santaPosition += direction.point
            visited.add(santaPosition)
        } else {
            roboPosition += direction.point
            visited.add(roboPosition)
        }
    }
    return visited.size
}
