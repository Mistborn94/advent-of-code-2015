package aoc2015.day12

import helper.Debug

val numberRegex = "[\\[{:,](-?\\d+)(?=[},\\]])".toRegex()

fun solveA(text: String, debug: Debug = Debug.Disabled): Int {
    val results = numberRegex.findAll(text)
    return results.sumOf { it.groupValues[1].toInt() }
}


fun solveB(text: String, debug: Debug = Debug.Disabled): Int {
    val (json, _) = parseJson(text)
    return json.score
}

fun parseJson(text: String, index: Int = 0): Pair<JsonScoreNode, Int> {
    return when (text[index]) {
        '{' -> parseObject(text, index)
        '"' -> parseString(text, index)
        '[' -> parseArray(text, index)
        '-', in '0'..'9' -> parseNumber(text, index)
        else -> TODO("${text[index]} not supported")
    }
}

fun parseString(text: String, index: Int): Pair<JsonScoreNode, Int> {
    val endIndex = text.indexOf('"', index + 1)
    val value = text.substring(index + 1, endIndex)

    val node = if (value == "red") JsonScoreNode.RedNode else JsonScoreNode.ZeroNode

    return node to endIndex + 1
}

fun parseNumber(text: String, index: Int): Pair<JsonScoreNode.NumberNode, Int> {
    val endIndex = text.indexOfAny(",]}".toCharArray(), index + 1)
    val value = text.substring(index..<endIndex)

    return JsonScoreNode.NumberNode(value.toInt()) to endIndex
}

fun parseArray(text: String, index: Int): Pair<JsonScoreNode.NumberNode, Int> {
    val values = mutableListOf<JsonScoreNode>()
    var currentIndex = index
    do {
        val (value, nextIndex) = parseJson(text, currentIndex + 1)
        values.add(value)
        currentIndex = nextIndex
    } while (text[currentIndex] != ']')

    return JsonScoreNode.NumberNode(values.sumOf { it.score }) to currentIndex + 1
}

fun parseObject(text: String, index: Int): Pair<JsonScoreNode, Int> {
    val values = mutableListOf<JsonScoreNode>()
    var currentIndex = index
    do {
        val keyStart = currentIndex + 1
        val keyEnd = text.indexOf('"', keyStart + 1)
        val (value, nextIndex) = parseJson(text, keyEnd + 2)
        values.add(value)
        currentIndex = nextIndex
    } while (text[currentIndex] != '}')

    val scoreNode =
        if (values.any { it is JsonScoreNode.RedNode }) JsonScoreNode.ZeroNode else JsonScoreNode.NumberNode(values.sumOf { it.score })
    return scoreNode to currentIndex + 1
}


sealed interface JsonScoreNode {

    val score: Int

    data object RedNode : JsonScoreNode {
        override val score: Int = 0
    }

    data object ZeroNode : JsonScoreNode {
        override val score: Int = 0
    }

    class NumberNode(override val score: Int) : JsonScoreNode

}
