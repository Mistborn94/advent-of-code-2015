package day1

import helper.Debug
import helper.readDayFile
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals


internal class Day1KtTest {

    private val day = 1

    @Test
    fun sample1() {

        assertEquals(0, solveA("(())", Debug.Enabled))
        assertEquals(0, solveA("()()", Debug.Enabled))
        assertEquals(1, solveB(")", Debug.Enabled))
        assertEquals(5, solveB("()())", Debug.Enabled))
    }

    @Test
    @Ignore
    fun sample2() {
//        val text = readDayFile(day, "sample2.in").readText().trimEnd()

        val text = """
            |232
        """.trimMargin().trimEnd()

        assertEquals(232, solveA(text, Debug.Enabled))
        assertEquals(0, solveB(text, Debug.Disabled))
    }

    @Test
    fun solve() {
        val lines = readDayFile(day, "input").readText().trimEnd()

        val solveA = solveA(lines)
        println("A: $solveA")
        assertEquals(232, solveA)

        val solveB = solveB(lines)
        println("B: $solveB")
        assertEquals(1783, solveB)
    }
}