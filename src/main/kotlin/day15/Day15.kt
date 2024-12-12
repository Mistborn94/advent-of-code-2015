package day15

import helper.Debug


val pattern =
    """([A-Za-z]+): capacity (-?\d+), durability (-?\d+), flavor (-?\d+), texture (-?\d+), calories (-?\d+)""".toRegex()

fun solveA(text: String, debug: Debug = Debug.Disabled): Int {
    val ingredients = parseIngredients(text)
    return bestCookie(emptyList(), ingredients, 100, debug)
}

private fun parseIngredients(text: String) = text.lines().map { line ->
    val matchResult = pattern.matchEntire(line)!!
    val (capacity: Int, durability: Int, flavor: Int, texture: Int, calories: Int) = matchResult.groupValues
        .drop(2)
        .map { it.toInt() }
    Ingredient(matchResult.groupValues[1], capacity, durability, flavor, texture, calories)
}

data class Ingredient(
    val name: String,
    val capacity: Int,
    val durability: Int,
    val flavor: Int,
    val texture: Int,
    val calories: Int
) {
    override fun toString(): String {
        return name
    }
}

fun solveB(text: String, debug: Debug = Debug.Disabled): Int {

    val ingredients = parseIngredients(text)
    return bestCookie(emptyList(), ingredients, 100, debug, true)
}

fun bestCookie(
    cookie: List<Pair<Ingredient, Int>>,
    remainingIngredients: List<Ingredient>,
    remainingSpoons: Int,
    debug: Debug = Debug.Disabled,
    b: Boolean = false
): Int {
    return if (remainingSpoons == 0) {
        val capacity = maxOf(0, cookie.sumOf { (k, v) -> k.capacity * v })
        val durability = maxOf(0, cookie.sumOf { (k, v) -> k.durability * v })
        val flavor = maxOf(0, cookie.sumOf { (k, v) -> k.flavor * v })
        val texture = maxOf(0, cookie.sumOf { (k, v) -> k.texture * v })
        val calories = maxOf(0, cookie.sumOf { (k, v) -> k.calories * v })
        if (b && calories != 500) 0 else capacity * durability * flavor * texture
    } else if (remainingIngredients.size == 1) {
        val newCookie = cookie + (remainingIngredients.first() to remainingSpoons)
        bestCookie(newCookie, emptyList(), 0, debug, b)
    } else {
        val ingredient = remainingIngredients.first()
        (0..remainingSpoons).maxOf { count ->
            val newCookie = cookie + (ingredient to count)
            bestCookie(newCookie, remainingIngredients.drop(1), remainingSpoons - count, debug, b)
        }
    }
}


