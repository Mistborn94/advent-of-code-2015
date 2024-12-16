package day18

import helper.Debug
import helper.readDayFile
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals


internal class Day18KtTest {

    private val day = 18

    @Test
    fun sample1() {
        val text = """
        .#.#.#
        ...##.
        #....#
        ..#...
        #.#..#
        ####..
        """.trimIndent().trimEnd()

        assertEquals(4, solveA(text, Debug.Enabled, 4, 6))
        assertEquals(17, solveB(text, Debug.Enabled, 5, 6))
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
        assertEquals(814, solveA)

        val solveB = solveB(lines)
        println("B: $solveB")
        assertEquals(924, solveB)
    }
}