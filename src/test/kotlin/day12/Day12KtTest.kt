package day12

import aoc2015.day12.solveA
import aoc2015.day12.solveB
import helper.Debug
import helper.readDayFile
import kotlin.test.Test
import kotlin.test.assertEquals


internal class Day12KtTest {

    private val day = 12

    @Test
    fun samples() {

        assertEquals(6, solveA("[1,2,3]", Debug.Enabled))
        assertEquals(6, solveA("""{"a":2,"b":4}""", Debug.Enabled))
        assertEquals(3, solveA("[[[3]]]", Debug.Enabled))
        assertEquals(3, solveA("""{"a":{"b":4},"c":-1}""", Debug.Enabled))
        assertEquals(0, solveA("""{"a":[-1,1]}""", Debug.Enabled))
        assertEquals(0, solveA("[]", Debug.Enabled))
        assertEquals(0, solveA("{}", Debug.Enabled))

        assertEquals(6, solveB("[1,2,3]", Debug.Enabled))
        assertEquals(4, solveB("""[1,{"c":"red","b":2},3]""", Debug.Enabled))
        assertEquals(0, solveB("""{"d":"red","e":[1,2,3,4],"f":5}""", Debug.Enabled))
        assertEquals(6, solveB("""[1,"red",5]""", Debug.Enabled))
    }

    @Test
    fun solve() {
        val lines = readDayFile(day, "input").readText().trimEnd()

        val solveA = solveA(lines)
        println("A: $solveA")
        assertEquals(191164, solveA)

        val solveB = solveB(lines)
        println("B: $solveB")
        assertEquals(87842, solveB)
    }
}