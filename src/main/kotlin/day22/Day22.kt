package day22

import helper.Debug
import kotlin.math.max

val cache = mutableMapOf<GameState, Int?>()
fun solveA(text: String, debug: Debug = Debug.Disabled, yourHp: Int = 50, yourMana: Int = 500): Int {
    val lines = text.lines()
    val bossHp = lines[0].substringAfter(": ").toInt()
    val bossDamage = lines[1].substringAfter(": ").toInt()

    cache.clear()
    val initialState = GameState(yourHp, yourMana, bossHp)
    return simulate(initialState, bossDamage, emptyList(), false, initialState, debug)!!
}

fun solveB(text: String, debug: Debug = Debug.Disabled, yourHp: Int = 50, yourMana: Int = 500): Int {
    val lines = text.lines()
    val bossHp = lines[0].substringAfter(": ").toInt()
    val bossDamage = lines[1].substringAfter(": ").toInt()

    cache.clear()
    val initialState = GameState(yourHp, yourMana, bossHp)
    return simulate(initialState, bossDamage, emptyList(), true, initialState, debug)!!
}

val spells = mapOf(
    "Magic Missile" to Spell(53, 4, 0, 0),
    "Drain" to Spell(73, 2, 2, 0),
    "Shield" to Spell(113, 0, 0, 6),
    "Poison" to Spell(173, 0, 0, 6),
    "Recharge" to Spell(229, 0, 0, 5)
)

val spellEffects = mapOf(
    "Shield" to SpellEffect(7, 0, 0),
    "Poison" to SpellEffect(0, 3, 0),
    "Recharge" to SpellEffect(0, 0, 101)
)

data class Spell(val cost: Int, val damage: Int, val heal: Int, val effect: Int)
data class SpellEffect(val armor: Int, val damage: Int, val mana: Int)

data class GameState(
    val yourHp: Int,
    val yourMana: Int,
    val bossHp: Int,
    val effects: Map<String, Int> = emptyMap(),
    val yourTurn: Boolean = true
)

fun testSimulation(
    initialState: GameState,
    bossDamage: Int,
    castSpells: List<String>,
    part2: Boolean
): Pair<String, Boolean> {
    buildString {
        append("Simulating $castSpells\n")
        var (yourHp, yourMana, bossHp) = initialState
        var activeEffects = emptyMap<String, Int>()

        for (spellName in castSpells) {
            append("-- Player turn --\n")
            if (part2) {
                append("Hard Mode: -1 HP\n")
                yourHp -= 1
                if (yourHp <= 0) {
                    break
                }
            }
            append("Player: $yourHp hp, $yourMana mana\n")
            append("Boss: $bossHp hp\n")
            append("Active Effects: $activeEffects\n")
            append("Player Casts $spellName\n")

            val spell = spells[spellName]!!

            yourHp += spell.heal
            yourMana = yourMana + (if ("Recharge" in activeEffects) 101 else 0) - spell.cost
            bossHp = bossHp - (if ("Poison" in activeEffects) 3 else 0) - spell.damage

            if (bossHp <= 0) {
                break
            }

            activeEffects = (activeEffects.mapValues { (k, v) -> v - 1 }).filterValues { it > 0 }
            if (spell.effect > 0) {
                activeEffects = activeEffects + (spellName to spell.effect)
            }

            append("-- Boss turn --\n")
            append("Player: $yourHp hp, $yourMana mana\n")
            append("Boss: $bossHp hp\n")
            append("Active Effects: $activeEffects\n")

            val armor = if ("Shield" in activeEffects) 7 else 0
            val damage = max(1, bossDamage - armor)

            append("Boss deals $damage damage\n")

            yourMana += if ("Recharge" in activeEffects) 101 else 0
            if ("Recharge" in activeEffects) {
                append("Recharged player mana to $yourMana\n")
            }

            bossHp -= if ("Poison" in activeEffects) 3 else 0
            if (bossHp <= 0) {
                break
            }
            yourHp -= damage

            activeEffects = activeEffects.mapValues { (k, v) -> v - 1 }.filterValues { it > 0 }

            if (yourHp <= 0) {
                break
            }
            append("\n\n")
        }
        if (yourHp <= 0) {
            append("Player HP $yourHp -- You lose\n")
            append("=== Finished Simulation ===\n\n")
            return this.toString() to false
        }

        if ("Poison" in activeEffects && bossHp in 1..3) {
            append("Final poison damage did it!\n")
            bossHp -= 3
        }

        if (bossHp <= 0) {
            append("Boss HP $bossHp -- You Win!\n")
            append("=== Finished Simulation ===\n\n")
            return this.toString() to true
        }

        append("No one has lost - Things went weird :/\n")
        return this.toString() to false
    }
}

fun simulate(
    gameState: GameState,
    bossDamage: Int,
    castedSpells: List<String>,
    part2: Boolean,

    initialState: GameState,
    debug: Debug,
    depth: Int = 1,
): Int? {
    val (_, yourMana, bossHp, effects) = gameState
    val yourHpWithEffects = gameState.yourHp - (if (gameState.yourTurn && part2) 1 else 0)

    val effectDamage = if ("Poison" in effects) 3 else 0
    val effectMana = if ("Recharge" in effects) 101 else 0
    val remainingEffects = effects.mapValues { (k, v) -> v - 1 }.filterValues { it > 0 }

    val bossHpWithEffects = bossHp - effectDamage
    val yourManaWithEffects = yourMana + effectMana

//    debug {
//        val spaceIndent = " ".repeat(depth)
//        val dashIndent = "-".repeat(depth)
//        if (gameState.yourTurn) {
//            println("|$dashIndent Your turn")
//        } else {
//            println("|$dashIndent Boss turn")
//        }
//        println("|$spaceIndent   Active Effects: $effects")
//        println("|$spaceIndent   Your stats: $yourHpWithEffects HP, $yourMana + $effectMana Mana")
//        println("|$spaceIndent   Boss stats: $bossHp - $effectDamage HP")
//    }

    return if (yourHpWithEffects <= 0) {
        return null
    } else if (bossHpWithEffects <= 0) {
        debug {
            println("Found solution with cost ${castedSpells.sumOf { spells[it]!!.cost }}: $castedSpells")
            val (output, simulationResult) = testSimulation(initialState, bossDamage, castedSpells, part2)
            if (!simulationResult) {
                println(output)
            }
            require(simulationResult) { "Player must win!" }
        }
        return 0
    } else {
        if (gameState.yourTurn) {
            cache.getOrPut(gameState) {
                val possibleSpells =
                    spells.filter { (name, spell) -> spell.cost <= yourManaWithEffects && name !in remainingEffects }

                val spellResults = possibleSpells.mapNotNull { (name, spell) ->
//                    debug {
//                        println("|${"-".repeat(depth)}- Casting $name ($spell mana)")
//                    }

                    val nextEffects =
                        if (spell.effect > 0) remainingEffects + (name to spell.effect) else remainingEffects

                    val newGameState = GameState(
                        yourHpWithEffects + spell.heal,
                        yourManaWithEffects - spell.cost,
                        bossHpWithEffects - spell.damage,
                        nextEffects,
                        false
                    )

                    simulate(
                        newGameState,
                        bossDamage,
                        castedSpells + name,
                        part2,
                        initialState,
                        debug,
                        depth + 1
                    )?.let { it + spell.cost }
                }

                spellResults.minOrNull()
            }
        } else {
            val armor = if ("Shield" in effects) 7 else 0
            val damage = max(1, bossDamage - armor)

            val newGameState =
                GameState(yourHpWithEffects - damage, yourManaWithEffects, bossHpWithEffects, remainingEffects, true)
            simulate(newGameState, bossDamage, castedSpells, part2, initialState, debug, depth + 1)
        }
    }
}

