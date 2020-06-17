package processor

interface Matrix {
    val matrix: MutableList<MutableList<Double>>
    val dimension: Pair<Int, Int>
    fun plus(m1: Matrix): Matrix
    fun multiplyByNumber(num: Double): Matrix
    fun multiplyByMatrix(m1: Matrix): Matrix
    fun getTranspose(diagonal: Diagonal): Matrix
    fun getSubMatrixWithoutRC(row: Int, col: Int): Matrix
    fun getDeterminant(): Double
    fun getInverseMatrix(): Matrix
}