package day6

import helper.Debug

val pattern = "(turn on|turn off|toggle) (\\d+),(\\d+) through (\\d+),(\\d+)".toRegex()

fun solveA(text: String, debug: Debug = Debug.Disabled): Int {
    val grid: Array<Array<Boolean>> = Array(1000) { Array(1000) { false } }

    text.lines().forEach { line ->
        val (instruction, x1, y1, x2, y2) = pattern.matchEntire(line)!!.destructured

        val xRange = x1.toInt()..x2.toInt()
        val yRange = y1.toInt()..y2.toInt()

        xRange.forEach { x ->
            yRange.forEach { y ->
                when (instruction) {
                    "turn on" -> grid[x][y] = true
                    "turn off" -> grid[x][y] = false
                    "toggle" -> grid[x][y] = !grid[x][y]
                }
            }
        }
    }


    return grid.sumOf { row -> row.count { it } }
}

private operator fun <E> List<E>.component6(): E = this[5]


fun solveB(text: String, debug: Debug = Debug.Disabled): Int {
    val grid: Array<Array<Int>> = Array(1000) { Array(1000) { 0 } }

    text.lines().forEach { line ->
        val (_, instruction, x1, y1, x2, y2) = pattern.matchEntire(line)!!.groupValues

        val xRange = x1.toInt()..x2.toInt()
        val yRange = y1.toInt()..y2.toInt()

        xRange.forEach { x ->
            yRange.forEach { y ->
                when (instruction) {
                    "turn on" -> grid[x][y] += 1
                    "turn off" -> grid[x][y] = maxOf(grid[x][y] - 1, 0)
                    "toggle" -> grid[x][y] += 2
                }
            }
        }
    }


    return grid.sumOf { row -> row.sum() }
}
