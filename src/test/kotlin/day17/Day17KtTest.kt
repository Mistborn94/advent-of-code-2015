package day17

import helper.Debug
import helper.readDayFile
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals


internal class Day17KtTest {

    private val day = 17

    @Test
    fun sample1() {
        val text = """
        20
        15
        10
        5
        5
        """.trimIndent().trimEnd()

        assertEquals(4, solveA(text, Debug.Enabled, 25))
        assertEquals(3, solveB(text, Debug.Enabled, 25))
    }

    @Test
    @Ignore
    fun sample2() {
//        val text = readDayFile(day, "sample2.in").readText().trimEnd()

        val text = """
        
        """.trimIndent().trimEnd()

        assertEquals(0, solveA(text, Debug.Enabled, 150))
        assertEquals(0, solveB(text, Debug.Disabled))
    }

    @Test
    fun solve() {
        val lines = readDayFile(day, "input").readText().trimEnd()

        val solveA = solveA(lines)
        println("A: $solveA")
        assertEquals(654, solveA)

        val solveB = solveB(lines)
        println("B: $solveB")
        assertEquals(57, solveB)
    }
}