package day4

import helper.Debug
import helper.readDayFile
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals


internal class Day4KtTest {

    private val day = 4

    @Test
    fun sample1() {
        val text = """
            |
        """.trimMargin().trimEnd()

        assertEquals(609043, solveA("abcdef", Debug.Enabled))
        assertEquals(1048970, solveA("pqrstuv", Debug.Enabled))
//        assertEquals(0, solveB(text, Debug.Enabled))
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
        assertEquals(117946, solveA)

        val solveB = solveB(lines)
        println("B: $solveB")
        assertEquals(3938038, solveB)
    }
}