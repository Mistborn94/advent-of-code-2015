package day3

import helper.Debug
import helper.readDayFile
import kotlin.test.Test
import kotlin.test.assertEquals


internal class Day3KtTest {

    private val day = 3

    @Test
    fun sample1() {

        assertEquals(2, solveA(">", Debug.Enabled))
        assertEquals(4, solveA("^>v<", Debug.Enabled))
        assertEquals(2, solveA("^v^v^v^v^v", Debug.Enabled))

        assertEquals(3, solveB("^v", Debug.Enabled))
        assertEquals(3, solveB("^>v<", Debug.Enabled))
        assertEquals(11, solveB("^v^v^v^v^v", Debug.Enabled))
    }

    @Test
    fun solve() {
        val lines = readDayFile(day, "input").readText().trimEnd()

        val solveA = solveA(lines)
        println("A: $solveA")
        assertEquals(2572, solveA)

        val solveB = solveB(lines)
        println("B: $solveB")
        assertEquals(2631, solveB)
    }
}