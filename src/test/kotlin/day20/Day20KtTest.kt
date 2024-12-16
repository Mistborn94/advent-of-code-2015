package day20

import helper.readDayFile
import kotlin.test.Test
import kotlin.test.assertEquals


internal class Day20KtTest {

    private val day = 20

    @Test
    fun solve() {
        val lines = readDayFile(day, "input").readText().trimEnd()

        val solveA = solveA(lines)
        println("A: $solveA")
        assertEquals(665280, solveA)

        val solveB = solveB(lines)
        println("B: $solveB")
        assertEquals(705600, solveB)
    }
}