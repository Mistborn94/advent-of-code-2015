package day10

import helper.Debug
import helper.readDayFile
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals

@Ignore
internal class Day10KtTest {

    private val day = 10


    @Test
    fun solve() {
        val lines = readDayFile(day, "input").readText().trimEnd()

        val solveA = solve(lines)
        println("A: $solveA")
        assertEquals(252594, solveA)

        val solveB = solve(lines, reps = 50, debug = Debug.Disabled)
        println("B: $solveB")
        assertEquals(3579328, solveB)
    }
}