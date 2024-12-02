package day9

import aoc2015.day9.solveA
import aoc2015.day9.solveB
import helper.Debug
import helper.readDayFile
import kotlin.test.Test
import kotlin.test.assertEquals


internal class Day9KtTest {

    private val day = 9

    @Test
    fun sample1() {
        val text = """
            |London to Dublin = 464
            |London to Belfast = 518
            |Dublin to Belfast = 141
        """.trimMargin().trimEnd()

        assertEquals(605, solveA(text, Debug.Enabled))
        assertEquals(982, solveB(text, Debug.Enabled))
    }

    @Test
    fun solve() {
        val lines = readDayFile(day, "input").readText().trimEnd()

        val solveA = solveA(lines)
        println("A: $solveA")
        assertEquals(251, solveA)

        val solveB = solveB(lines)
        println("B: $solveB")
        assertEquals(898, solveB)
    }
}