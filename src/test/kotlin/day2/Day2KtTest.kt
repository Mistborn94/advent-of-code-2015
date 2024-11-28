package day2

import helper.Debug
import helper.readDayFile
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals


internal class Day2KtTest {

    private val day = 2

    @Test
    fun sample1() {
        val text = """
            |
        """.trimMargin().trimEnd()

        assertEquals(58, solveA("2x3x4", Debug.Enabled))
        assertEquals(43, solveA("1x1x10", Debug.Enabled))
        assertEquals(34, solveB("2x3x4", Debug.Enabled))
        assertEquals(14, solveB("1x1x10", Debug.Enabled))
    }

    @Test
    @Ignore
    fun sample2() {
//        val text = readDayFile(day, "sample2.in").readText().trimEnd()

        val text = """
            |
        """.trimMargin().trimEnd()

        assertEquals(0, solveA(text, Debug.Enabled))
        assertEquals(0, solveB(text, Debug.Disabled))
    }

    @Test
    fun solve() {
        val lines = readDayFile(day, "input").readText().trimEnd()

        val solveA = solveA(lines)
        println("A: $solveA")
        assertEquals(1606483, solveA)

        val solveB = solveB(lines)
        println("B: $solveB")
        assertEquals(3842356, solveB)
    }
}