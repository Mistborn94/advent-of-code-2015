package day7

import helper.Debug
import helper.readDayFile
import kotlin.test.Test
import kotlin.test.assertEquals


internal class Day7KtTest {

    private val day = 7

    @Test
    fun sample1() {
        val text = """
            123 -> x
            456 -> y
            x AND y -> d
            x OR y -> e
            x LSHIFT 2 -> f
            y RSHIFT 2 -> g
            NOT x -> h
            NOT y -> i
        """.trimIndent().trimEnd()

        assertEquals(
            mapOf(
                "d" to 72,
                "e" to 507,
                "f" to 492,
                "g" to 114,
                "h" to 65412,
                "i" to 65079,
                "x" to 123,
                "y" to 456
            ), solveA(text, Debug.Disabled)
        )
    }

    @Test
    fun solve() {
        val lines = readDayFile(day, "input").readText().trimEnd()

        val solveA = solveA(lines, Debug.Disabled)
        println("A: $solveA")
        assertEquals(16076, solveA["a"])

        val solveB = solveB(lines, Debug.Disabled)
        println("B: $solveB")
        assertEquals(2797, solveB)
    }
}