package day8

import helper.Debug
import helper.readDayFile
import kotlin.test.Test
import kotlin.test.assertEquals


internal class Day8KtTest {

    private val day = 8

    @Test
    fun sample1() {
        val text = """
            |""
            |"abc"
            |"aaa\"aaa"
            |"\x27"
        """.trimMargin().trimEnd()

        assertEquals(12, solveA(text, Debug.Enabled))
        assertEquals(19, solveB(text, Debug.Enabled))
    }

    @Test
    fun solve() {
        val lines = readDayFile(day, "input").readText().trimEnd()

        val solveA = solveA(lines, Debug.Disabled)
        println("A: $solveA")
        assertEquals(1371, solveA)

        val solveB = solveB(lines)
        println("B: $solveB")
        assertEquals(2117, solveB)
    }
}