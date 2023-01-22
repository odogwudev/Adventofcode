fun main() {
    val (year, day) = "2022" to "Day08"

    fun List<String>.parseTrees(): Map<Pair<Int, Int>, Char> {
        val trees = mutableMapOf<Pair<Int, Int>, Char>()
        this.forEachIndexed { y, line ->
            line.forEachIndexed { x, c ->
                trees[x to y] = c
            }
        }
        return trees
    }

    fun viewingDistance(range: IntProgression, height: Char, tree: (Int) -> Char) =
        when {
            range.isEmpty() -> 0
            else -> {
                val index = range.map { tree(it) }.indexOfFirst { it >= height }
                if (index < 0) range.count() else index + 1
            }
        }

    fun part1(input: List<String>): Int {
        val trees = input.parseTrees()
        val size = input.first().length
        return trees.entries.count { (pos, height) ->
            val (x, y) = pos
            val horizontal =
                (0 until x).all { trees[it to y]!! < height } ||
                        (x + 1 until size).all { trees[it to y]!! < height }
            val vertical =
                (0 until y).all { trees[x to it]!! < height } ||
                        (y + 1 until size).all { trees[x to it]!! < height }
            horizontal || vertical
        }
    }

    val testInput = readInput(name = "${day}_test", year = year)
    val input = readInput(name = day, year = year)

    check(part1(testInput) == 21)
    println(part1(input))

    check(part2(testInput) == 8)
    println(part2(input))
}
/**
 * --- Day 8: Treetop Tree House ---
 * The expedition comes across a peculiar patch of tall trees all planted carefully in a grid. The Elves explain that a previous expedition planted these trees as a reforestation effort. Now, they're curious if this would be a good location for a tree house.
 *
 * First, determine whether there is enough tree cover here to keep a tree house hidden. To do this, you need to count the number of trees that are visible from outside the grid when looking directly along a row or column.
 *
 * The Elves have already launched a quadcopter to generate a map with the height of each tree (your puzzle input). For example:
 *
 * 30373
 * 25512
 * 65332
 * 33549
 * 35390
 * Each tree is represented as a single digit whose value is its height, where 0 is the shortest and 9 is the tallest.
 *
 * A tree is visible if all of the other trees between it and an edge of the grid are shorter than it. Only consider trees in the same row or column; that is, only look up, down, left, or right from any given tree.
 *
 * All of the trees around the edge of the grid are visible - since they are already on the edge, there are no trees to block the view. In this example, that only leaves the interior nine trees to consider:
 *
 * The top-left 5 is visible from the left and top. (It isn't visible from the right or bottom since other trees of height 5 are in the way.)
 * The top-middle 5 is visible from the top and right.
 * The top-right 1 is not visible from any direction; for it to be visible, there would need to only be trees of height 0 between it and an edge.
 * The left-middle 5 is visible, but only from the right.
 * The center 3 is not visible from any direction; for it to be visible, there would need to be only trees of at most height 2 between it and an edge.
 * The right-middle 3 is visible from the right.
 * In the bottom row, the middle 5 is visible, but the 3 and 4 are not.
 * With 16 trees visible on the edge and another 5 visible in the interior, a total of 21 trees are visible in this arrangement.
 *
 * Consider your map; how many trees are visible from outside the grid?
 */