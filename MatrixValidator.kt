package processor

interface MatrixValidator {
    fun validateDimension(input: String): Boolean
    fun validateRow(input: String, colNum: Int): Boolean
    fun validateOneDigit(input: String): Boolean
    fun validateAddition(m1: Matrix, m2: Matrix): Boolean
}