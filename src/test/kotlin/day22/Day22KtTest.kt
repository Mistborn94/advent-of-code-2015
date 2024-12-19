package day22

import helper.Debug
import helper.readDayFile
import kotlin.test.Test
import kotlin.test.assertEquals


internal class Day22KtTest {

    private val day = 22

    @Test
    fun sample1() {
        val text = """
        Hit Points: 13
        Damage: 8
        """.trimIndent().trimEnd()

        assertEquals(226, solveA(text, Debug.Disabled, yourHp = 10, yourMana = 250))
    }

    @Test
    fun sample2() {
        val text = """
        Hit Points: 14
        Damage: 8
        """.trimIndent().trimEnd()

        assertEquals(641, solveA(text, Debug.Disabled, yourHp = 10, yourMana = 250))
    }

    @Test
    fun solve() {
        val lines = readDayFile(day, "input").readText().trimEnd()

        val solveA = solveA(lines, debug = Debug.Disabled)
        println("A: $solveA")
        assertEquals(900, solveA)

        val solveB = solveB(lines, debug = Debug.Disabled)
        println("B: $solveB")

        assertEquals(1216, solveB)
    }
}