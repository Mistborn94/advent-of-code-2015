package day14

import helper.Debug
import helper.readDayFile
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals


internal class Day14KtTest {

    private val day = 14

    @Test
    fun sample1() {
        val text = """
        Comet can fly 14 km/s for 10 seconds, but then must rest for 127 seconds.
        Dancer can fly 16 km/s for 11 seconds, but then must rest for 162 seconds.
        """.trimIndent().trimEnd()

        assertEquals(1120, solveA(text, Debug.Enabled, 1000))
        assertEquals(689, solveB(text, Debug.Enabled, 1000))
    }

    @Test
    @Ignore
    fun sample2() {
//        val text = readDayFile(day, "sample2.in").readText().trimEnd()

        val text = """
        
        """.trimIndent().trimEnd()

        assertEquals(0, solveA(text, Debug.Enabled))
        assertEquals(0, solveB(text, Debug.Disabled))
    }

    @Test
    fun solve() {
        val lines = readDayFile(day, "input").readText().trimEnd()

        val solveA = solveA(lines)
        println("A: $solveA")
        assertEquals(2655, solveA)

        val solveB = solveB(lines)
        println("B: $solveB")
        assertEquals(1059, solveB)
    }
}