package processor

import java.lang.Double.parseDouble
import java.lang.Exception
import java.lang.Integer.parseInt

class MatrixValidatorImpl : MatrixValidator {
    override fun validateDimension(input: String): Boolean {
        val strArr = input.trim().split(" ")
        if (strArr.size != 2) {
            return false
        }
        strArr.forEach {
            try {
                parseInt(it)
            } catch (e: Exception) {
                return false
            }
        }
        return true
    }

    override fun validateRow(input: String, colNum: Int): Boolean {
        val strArr = input.trim().split(" ")
        if (strArr.size != colNum)
            return false
        strArr.forEach {
            try {
                parseDouble(it)
            } catch (e: Exception) {
                return false
            }
        }
        return true
    }

    override fun validateOneDigit(input: String): Boolean {
        try {
            parseDouble(input)
        } catch (e: Exception) {
            return false
        }
        return true
    }

    override fun validateAddition(m1: Matrix, m2: Matrix): Boolean {
        return m1.dimension == m2.dimension
    }
}