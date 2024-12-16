package helper

fun <E> List<E>.permutations(): Sequence<List<E>> {
    return sequence {
        val mutableList = toMutableList()
        val c = Array(size) { 0 }.toMutableList()
        var i = 0
        while (i < size) {
            if (c[i] < i) {
                if (i % 2 == 0) {
                    swap(mutableList, 0, i)
                } else {
                    swap(mutableList, c[i], i)
                }
                yield(mutableList.toList())
                c[i] += 1
                i = 0
            } else {
                c[i] = 0
                i += 1
            }
        }
    }
}

private fun <E> swap(mutableList: MutableList<E>, i: Int, j: Int) {
    val tmp = mutableList[i]
    mutableList[i] = mutableList[j]
    mutableList[j] = tmp
}