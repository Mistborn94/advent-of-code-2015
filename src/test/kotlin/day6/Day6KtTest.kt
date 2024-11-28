package day6

import helper.readDayFile
import kotlin.test.Test
import kotlin.test.assertEquals


internal class Day6KtTest {

    private val day = 6

    @Test
    fun solve() {
        val lines = readDayFile(day, "input").readText().trimEnd()

        val solveA = solveA(lines)
        println("A: $solveA")
        assertEquals(400410, solveA)

        val solveB = solveB(lines)
        println("B: $solveB")
        assertEquals(15343601, solveB)
    }
}