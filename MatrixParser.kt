package processor

interface MatrixParser {
    fun parseDimension(input: String): Pair<Int, Int>
    fun parseRow(input: String): MutableList<Double>
    fun parseMatrix(rows: MutableList<MutableList<Double>>)
    fun parseOneDidigt(digit: String): Double
}