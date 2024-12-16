package day18

import helper.Debug
import helper.point.base.Point

fun solveA(text: String, debug: Debug = Debug.Disabled, steps: Int = 100, size: Int = 100): Int {
    var lights: Set<Point> = text.lines().flatMapIndexedTo(mutableSetOf()) { y, line ->
        line.mapIndexedNotNull { x, c ->
            if (c == '#') Point(x, y) else null
        }
    }

    repeat(steps) {
        val newLights = mutableSetOf<Point>()
        for (x in 0..<size) {
            for (y in 0..<size) {
                val point = Point(x, y)
                val neighbours = point.neighbours() + point.diagonalNeighbours()
                val neighbourOnCount = neighbours.count { it in lights }
                if (point in lights && (neighbourOnCount == 2 || neighbourOnCount == 3)
                    || point !in lights && neighbourOnCount == 3
                ) {
                    newLights.add(point)
                }

            }
        }
        lights = newLights
    }


    return lights.size
}


fun solveB(text: String, debug: Debug = Debug.Disabled, steps: Int = 100, size: Int = 100): Int {
    val corners = setOf(Point(0, 0), Point(0, size - 1), Point(size - 1, 0), Point(size - 1, size - 1))
    var lights: Set<Point> = text.lines().flatMapIndexedTo(mutableSetOf()) { y, line ->
        line.mapIndexedNotNull { x, c ->
            if (c == '#') Point(x, y) else null
        }
    } + corners

    repeat(steps) {
        val newLights = mutableSetOf<Point>()
        for (x in 0..<size) {
            for (y in 0..<size) {
                val point = Point(x, y)
                val neighbours = point.neighbours() + point.diagonalNeighbours()
                val neighbourOnCount = neighbours.count { it in lights }
                if (point in lights && (neighbourOnCount == 2 || neighbourOnCount == 3)
                    || point !in lights && neighbourOnCount == 3
                ) {
                    newLights.add(point)
                }

            }
        }
        lights = newLights + corners
    }


    return lights.size
}
