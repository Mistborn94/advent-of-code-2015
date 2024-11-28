package day7

import helper.Debug

val outerPattern = "^(.+) -> (.+)$".toRegex()

fun solveA(text: String, debug: Debug = Debug.Disabled): Map<String, Int> {
    val (gates, wireValues) = parseInput(text, debug)

    return simulate(wireValues, gates, debug)
}

private fun simulate(
    inputWireValues: Map<String, Int>,
    inputGates: List<LogicGate>,
    debug: Debug,
): Map<String, Int> {
    val wireValues = inputWireValues.toMutableMap()
    val gates = inputGates.toMutableList()
    debug {
        println("Wires: $wireValues")
    }

    while (gates.isNotEmpty() && gates.any { it.ready(wireValues) }) {
        val gate = gates.first { it.ready(wireValues) }
        gates.remove(gate)
        gate.run(wireValues)
        debug {
            println("Applying Gate $gate")
            println("Wires: $wireValues")
        }
    }

    debug {
        println("Remaining gates: \n ${gates.joinToString("\n")}")
    }

    return wireValues
}

private fun parseInput(
    text: String,
    debug: Debug
): Pair<List<LogicGate>, Map<String, Int>> {
    val gates = mutableListOf<LogicGate>()
    val wireValues = mutableMapOf<String, Int>()

    text.trim().lines().forEach { line ->
        val (operation, output) = outerPattern.matchEntire(line)!!.destructured

        when {
            "AND" in operation -> {
                val a = operation.substringBefore("AND").trim()
                val b = operation.substringAfter("AND").trim()
                gates.add(LogicGate("AND", listOf(a, b), output) { it[0] and it[1] })
            }

            "OR" in operation -> {
                val a = operation.substringBefore("OR").trim()
                val b = operation.substringAfter("OR").trim()
                gates.add(LogicGate("OR", listOf(a, b), output) { it[0] or it[1] })
            }

            "LSHIFT" in operation -> {
                val wire = operation.substringBefore("LSHIFT").trim()
                val amount = operation.substringAfter("LSHIFT").trim().toInt()
                gates.add(LogicGate("LSHIFT", listOf(wire), output) { it[0] shl amount })
            }

            "RSHIFT" in operation -> {
                val wire = operation.substringBefore("RSHIFT").trim()
                val amount = operation.substringAfter("RSHIFT").trim().toInt()
                gates.add(LogicGate("RSHIFT", listOf(wire), output) { it[0] shr amount })
            }

            operation.startsWith("NOT") -> {
                val wire = operation.substringAfter("NOT").trim()
                gates.add(LogicGate("NOT", listOf(wire), output) { 65535 - it[0] })
            }

            else -> {
                debug {
                    println("Else block: $operation -> $output")
                }
                val intValue = operation.trim().toIntOrNull()
                if (intValue != null) {
                    wireValues[output] = intValue
                } else {
                    gates.add(LogicGate("SEND", listOf(operation.trim()), output) { it[0] })
                }
            }
        }
    }
    return Pair(gates, wireValues)
}


fun solveB(text: String, debug: Debug = Debug.Disabled): Int {
    val (gates, wireValues) = parseInput(text, debug)

    val firstRunOutputs = simulate(wireValues, gates, debug)
    val secondRunInputs = wireValues + mapOf("b" to firstRunOutputs["a"]!!)
    val secondRunOutputs = simulate(secondRunInputs, gates, debug)

    return secondRunOutputs["a"]!!
}

fun interface LogicFunction {
    operator fun invoke(inputs: List<Int>): Int
}

class LogicGate(val type: String, val inputs: List<String>, val output: String, val logicFunction: LogicFunction) {

    fun ready(wires: Map<String, Int>) = inputs.all {
        it in wires || it.toIntOrNull() != null
    }

    fun run(wires: MutableMap<String, Int>) {
        val inputValues = inputs.map {
            it.toIntOrNull() ?: wires[it]!!
        }
        val outputValue = logicFunction(inputValues)
        wires[output] = outputValue
    }

    override fun toString(): String {
        return "$type ${inputs.joinToString(" ")} -> $output"
    }


}
