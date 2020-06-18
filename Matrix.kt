package processor

interface Matrix {
    val matrix: MutableList<MutableList<Double>>
    val dimension: Pair<Int, Int>
    operator fun plus(m1: Matrix): Matrix
    operator fun times(num: Double): Matrix
    operator fun times(m1: Matrix): Matrix
    fun getTranspose(diagonal: Diagonal): Matrix
    fun getSubMatrixWithoutRC(row: Int, col: Int): Matrix
    fun getDeterminant(): Double
    fun getInverseMatrix(): Matrix
}