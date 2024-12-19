package day22

import helper.Debug
import java.util.*
import kotlin.math.max

fun solveA(text: String, debug: Debug = Debug.Disabled, yourHp: Int = 50, yourMana: Int = 500): Int {
    val lines = text.lines()
    val bossHp = lines[0].substringAfter(": ").toInt()
    val bossDamage = lines[1].substringAfter(": ").toInt()

    val initialState = GameState(yourHp, yourMana, bossHp)
    return simulateIterative(initialState, bossDamage, false, debug)
}

fun solveB(text: String, debug: Debug = Debug.Disabled, yourHp: Int = 50, yourMana: Int = 500): Int {
    val lines = text.lines()
    val bossHp = lines[0].substringAfter(": ").toInt()
    val bossDamage = lines[1].substringAfter(": ").toInt()

    val initialState = GameState(yourHp, yourMana, bossHp)
    return simulateIterative(initialState, bossDamage, true, debug)
}

val spells = mapOf(
    "Magic Missile" to Spell(53, 4, 0, 0),
    "Drain" to Spell(73, 2, 2, 0),
    "Shield" to Spell(113, 0, 0, 6),
    "Poison" to Spell(173, 0, 0, 6),
    "Recharge" to Spell(229, 0, 0, 5)
)

data class Spell(val cost: Int, val damage: Int, val heal: Int, val effect: Int)

data class GameState(
    val yourHp: Int,
    val yourMana: Int,
    val bossHp: Int,
    val effects: Map<String, Int> = emptyMap(),
    val yourTurn: Boolean = true,
    val runningCost: Int = 0,
    val castedSpells: List<String> = emptyList(),
)

fun simulateIterative(initialState: GameState, bossDamage: Int, part2: Boolean, debug: Debug): Int {
    var minWinningCost = Int.MAX_VALUE

    val queue = LinkedList<GameState>()
    queue.add(initialState)

    while (queue.isNotEmpty()) {
        val gameState = queue.removeAt(0)
        val yourHpWithEffects = gameState.yourHp - (if (gameState.yourTurn && part2) 1 else 0)
        if (yourHpWithEffects > 0 && gameState.runningCost < minWinningCost) {
            val effectDamage = if ("Poison" in gameState.effects) 3 else 0
            val effectMana = if ("Recharge" in gameState.effects) 101 else 0
            val remainingEffects = gameState.effects.mapValues { (_, v) -> v - 1 }.filterValues { it > 0 }
            val bossHpWithEffects = gameState.bossHp - effectDamage
            val yourManaWithEffects = gameState.yourMana + effectMana
            if (bossHpWithEffects <= 0) {
                debug {
                    println("Possible Solution: $gameState")
                }
                minWinningCost = minOf(minWinningCost, gameState.runningCost)
            } else if (gameState.yourTurn) {
                val possibleSpells =
                    spells.filter { (name, spell) -> spell.cost <= yourManaWithEffects && name !in remainingEffects }

                val nextStates = possibleSpells.mapNotNull { (name, spell) ->
                    val nextEffects =
                        if (spell.effect > 0) remainingEffects + (name to spell.effect) else remainingEffects

                    GameState(
                        yourHpWithEffects + spell.heal,
                        yourManaWithEffects - spell.cost,
                        bossHpWithEffects - spell.damage,
                        nextEffects,
                        false,
                        gameState.runningCost + spell.cost,
                        gameState.castedSpells + name
                    )
                }.filter { it.runningCost < minWinningCost }
                queue.addAll(nextStates)
            } else {
                val armor = if ("Shield" in gameState.effects) 7 else 0
                val damage = max(1, bossDamage - armor)

                val newGameState =
                    GameState(
                        yourHpWithEffects - damage, yourManaWithEffects, bossHpWithEffects, remainingEffects, true,
                        gameState.runningCost, gameState.castedSpells
                    )
                queue.add(newGameState)
            }
        }
    }
    return minWinningCost
}