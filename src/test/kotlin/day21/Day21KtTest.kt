package day21

import helper.readDayFile
import kotlin.test.Test
import kotlin.test.assertEquals


internal class Day21KtTest {

    private val day = 21

    @Test
    fun solve() {
        val lines = readDayFile(day, "input").readText().trimEnd()

        val solveA = solveA(lines)
        println("A: $solveA")
        assertEquals(121, solveA)

        val solveB = solveB(lines)
        println("B: $solveB")
        assertEquals(201, solveB)
    }
}