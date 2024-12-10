package day11

import helper.Debug
import helper.readDayFile
import kotlin.test.Test
import kotlin.test.assertEquals


internal class Day11KtTest {

    private val day = 11

    @Test
    fun sample1() {
        val text = """
            |
        """.trimMargin().trimEnd()

        assertEquals("abcdffaa", solveA("abcdefgh", Debug.Enabled))
        assertEquals("ghjaabcc", solveA("ghijklmn", Debug.Enabled))
    }

    @Test
    fun solve() {
        val lines = readDayFile(day, "input").readText().trimEnd()

        val solveA = solveA(lines)
        println("A: $solveA")
        assertEquals("hepxxyzz", solveA)

        val solveB = solveB(lines)
        println("B: $solveB")
//        assertEquals(0, solveB)
    }
}