package com.cvofhope.sudokusolverscala

object Sudoku {

  def isSameRow (i:Int, j:Int): Boolean = {
    i / 9 == j / 9
  }

  def isSameColumn (i:Int, j:Int): Boolean = {
    i - j % 9 == 0
  }

  def isSameBlock (i:Int, j:Int): Boolean = {
    i / 27 == j / 27 && i % 9 / 3 == j % 9 / 3
  }

  def isExcludable (i:Int, j:Int): Boolean = {
    isSameRow(i, j) || isSameColumn(i, j) || isSameBlock(i, j)
  }

  val range: List[Int] = List.range(0, 81)
  val sudoku: List[Char] = List('1', '2', '3', '4', '5', '6', '7', '8', '9')

  def solve (puzzle:String): String = {
    val i:Int = puzzle.indexOf('0')

    if (i == -1) return puzzle

    val excludes:Set[Char] = range.collect {
      case j if isExcludable(i, j) => puzzle.charAt(j)
    }.toSet

    for (number <- sudoku) {
      if (!excludes(number)) {
        val p:String = puzzle.slice(0, i) + number + puzzle.slice(i + 1, puzzle.length)
        val solution = solve(p)
        if (solution.indexOf("0") == -1) return solution
      }
    }

    return puzzle
  }

}
