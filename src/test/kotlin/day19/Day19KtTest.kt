package day19

import helper.Debug
import helper.readDayFile
import kotlin.test.Test
import kotlin.test.assertEquals


internal class Day19KtTest {

    private val day = 19

    @Test
    fun sample1() {
        val text = """
        e => H
        e => O
        H => HO
        H => OH
        O => HH
        
        HOH
        """.trimIndent().trimEnd()

        assertEquals(4, solveA(text, Debug.Enabled))
    }

    @Test
    fun sample2() {
//        val text = readDayFile(day, "sample2.in").readText().trimEnd()

        val text = """
        e => H
        e => O
        H => HO
        H => OH
        O => HH
        
        HOHOHO
        """.trimIndent().trimEnd()

        assertEquals(7, solveA(text, Debug.Enabled))
    }

    @Test
    fun solve() {
        val lines = readDayFile(day, "input").readText().trimEnd()

        val solveA = solveA(lines)
        println("A: $solveA")
        assertEquals(509, solveA)

        val solveB = solveB(lines)
        println("B: $solveB")
        //not 46
        assertEquals(195, solveB)
    }
}