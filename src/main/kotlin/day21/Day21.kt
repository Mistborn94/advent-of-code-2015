package day21

import helper.Debug
import helper.cartesianProduct
import helper.pairwise
import kotlin.math.max

val weapons = mapOf(
    "Dagger" to Item(8, 4, 0),
    "Shortsword" to Item(10, 5, 0),
    "Warhammer" to Item(25, 6, 0),
    "Longsword" to Item(40, 7, 0),
    "Greataxe" to Item(74, 8, 0)
)

val armor = mapOf(
    "None" to Item(0, 0, 0),
    "Leather" to Item(13, 0, 1),
    "Chainmail" to Item(31, 0, 2),
    "Splintmail" to Item(53, 0, 3),
    "Bandedmail" to Item(75, 0, 4),
    "Platemail" to Item(102, 0, 5),
)

val rings = mapOf(
    "Damage +1" to Item(25, 1, 0),
    "Damage +2" to Item(50, 2, 0),
    "Damage +3" to Item(100, 3, 0),
    "Defense +1" to Item(20, 0, 1),
    "Defense +2" to Item(40, 0, 2),
    "Defense +3" to Item(80, 0, 3),
)

val ringOptions: List<List<Item>> = buildList {
    add(emptyList())
    addAll(rings.values.map { listOf(it) })
    addAll(rings.values.pairwise(rings.values) { a, b -> listOf(a, b) })
}

fun simulate(weapon: Item, armor: Item, rings: List<Item>, boss: Boss): Boolean {
    require(rings.size <= 2)

    val yourDamage = weapon.damage + rings.sumOf { it.damage }
    val yourArmor = armor.armor + rings.sumOf { it.armor }

    var yourHp = 100
    var bossHp = boss.hp
    var yourTurn = true

    while (bossHp > 0 && yourHp > 0) {
        if (yourTurn) {
            bossHp -= max(1, yourDamage - boss.armor)
        } else {
            yourHp -= max(1, boss.damage - yourArmor)
        }
        yourTurn = !yourTurn
    }

    require(yourHp > 0 || bossHp > 0) { "Someone must still be alive " }
    return yourHp > 0
}

fun solveA(text: String, debug: Debug = Debug.Disabled): Int {
    val lines = text.lines()
    val boss = Boss(
        hp = lines[0].substringAfter(": ").toInt(),
        damage = lines[1].substringAfter(": ").toInt(),
        armor = lines[2].substringAfter(": ").toInt()
    )

    return weapons.values.cartesianProduct(armor.values).cartesianProduct(ringOptions) { (w, a), r -> Triple(w, a, r) }
        .filter { (w, a, r) -> simulate(w, a, r, boss) }
        .minOf { (w, a, r) -> w.cost + a.cost + r.sumOf { it.cost } }
}


fun solveB(text: String, debug: Debug = Debug.Disabled): Int {
    val lines = text.lines()
    val boss = Boss(
        hp = lines[0].substringAfter(": ").toInt(),
        damage = lines[1].substringAfter(": ").toInt(),
        armor = lines[2].substringAfter(": ").toInt()
    )

    return weapons.values.cartesianProduct(armor.values).cartesianProduct(ringOptions) { (w, a), r -> Triple(w, a, r) }
        .filter { (w, a, r) -> !simulate(w, a, r, boss) }
        .maxOf { (w, a, r) -> w.cost + a.cost + r.sumOf { it.cost } }
}

data class Item(val cost: Int, val damage: Int, val armor: Int)
data class Boss(val damage: Int, val armor: Int, val hp: Int)
