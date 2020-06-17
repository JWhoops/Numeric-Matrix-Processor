package processor

import java.lang.Double.parseDouble
import java.lang.Integer.parseInt

class MatrixParserImpl : MatrixParser {
    override fun parseDimension(input: String): Pair<Int, Int> {
        val arr = input.trim().split(" ")
        val row = parseInt(arr[0])
        val col = parseInt(arr[1])
        return Pair(row, col)
    }

    override fun parseRow(input: String): MutableList<Double> {
        val matrixRow: MutableList<Double> = mutableListOf()
        val strRow = input.trim().split(" ")
        strRow.forEach {
            matrixRow.add(parseDouble(it))
        }
        return matrixRow
    }

    override fun parseMatrix(rows: MutableList<MutableList<Double>>) {
        TODO("Not yet implemented")
    }

    override fun parseOneDidigt(digit: String): Double {
        return parseDouble(digit)
    }
}