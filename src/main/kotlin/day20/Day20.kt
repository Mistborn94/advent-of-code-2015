package day20

import helper.Debug
import kotlin.math.min

fun solveA(text: String, debug: Debug = Debug.Disabled): Int {
    val target = text.toInt() / 10

    val counts = mutableMapOf<Int, Long>().withDefault { 1L }
    var end = target

    var elf = 2
    while (elf < end) {
        for (house in elf..end step elf) {
            val total = counts.getValue(house) + elf
            counts[house] = total
            if (total >= target) {
                end = house
                break
            }
        }
        debug {
            if (elf % 10_000 == 0) {
                println("Finished elf $elf")
            }
        }
        elf++
    }

    return counts.filterValues { it >= target }.keys.min()
}


fun solveB(text: String, debug: Debug = Debug.Disabled): Int {
    val target = text.toInt()

    val counts = mutableMapOf<Int, Long>().withDefault { 10L }
    var end = target

    var elf = 2
    while (elf < end) {
        for (house in elf..min(end, elf * 50) step elf) {
            val total = counts.getValue(house) + elf * 11
            counts[house] = total
            if (total >= target) {
                end = house
                break
            }
        }
        debug {
            if (elf % 10_000 == 0) {
                println("Finished elf $elf")
            }
        }
        elf++
    }

    return counts.filterValues { it >= target }.keys.min()
}
