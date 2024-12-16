package day16

import helper.readDayFile
import kotlin.test.Test
import kotlin.test.assertEquals


internal class Day16KtTest {

    private val day = 16

    @Test
    fun solve() {
        val lines = readDayFile(day, "input").readText().trimEnd()

        val solveA = solveA(lines)
        println("A: $solveA")
        assertEquals(103, solveA)

        val solveB = solveB(lines)
        println("B: $solveB")
        assertEquals(405, solveB)
    }
}