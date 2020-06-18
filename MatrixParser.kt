package processor

interface MatrixParser {
    fun parseDimension(input: String): Pair<Int, Int>
    fun parseRow(input: String): MutableList<Double>
    fun parseOneDigit(digit: String): Double
}