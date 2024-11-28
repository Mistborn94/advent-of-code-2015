package day5

import helper.readDayFile
import kotlin.test.Test
import kotlin.test.assertEquals


internal class Day5KtTest {

    private val day = 5

    @Test
    fun solve() {
        val lines = readDayFile(day, "input").readText().trimEnd()

        val solveA = solveA(lines)
        println("A: $solveA")
        assertEquals(258, solveA)

        val solveB = solveB(lines)
        println("B: $solveB")
        assertEquals(53, solveB)
    }
}