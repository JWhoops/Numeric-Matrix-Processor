package processor

data class MatrixImpl(override val matrix: MutableList<MutableList<Double>>, override val dimension: Pair<Int, Int>) : Matrix {
    override operator fun plus(m1: Matrix): Matrix {
        val result: Matrix = MatrixImpl(mutableListOf(mutableListOf()), Pair(this.dimension.first, this.dimension.second))
        matrix.forEachIndexed { row, rowElements ->
            result.matrix.add(mutableListOf())
            rowElements.forEachIndexed { col, num ->
                result.matrix[row].add((num + m1.matrix[row][col]))
            }
        }
        return result
    }

    override operator fun times(num: Double): Matrix {
        val result: Matrix = MatrixImpl(mutableListOf(mutableListOf()), Pair(this.dimension.first, this.dimension.second))
        matrix.forEachIndexed { row, matrixRow ->
            result.matrix.add(mutableListOf())
            matrixRow.forEachIndexed { col, cell ->
                matrix[row][col] = cell * num
                result.matrix[row].add(cell * num)
            }
        }
        return result
    }

    override operator fun times(m1: Matrix): Matrix {
        val result: Matrix = MatrixImpl(mutableListOf(mutableListOf()), Pair(this.dimension.first, this.dimension.second))
        for (row in 0 until dimension.first) {
            result.matrix.add(mutableListOf())
            for (col in 0 until m1.dimension.second) {
                var temp = 0.0
                for (sCol in 0 until matrix[row].size) {
                    temp += (matrix[row][sCol] * m1.matrix[sCol][col])
                }
                result.matrix[row].add(temp)
            }
        }
        return result
    }

    override fun getTranspose(diagonal: Diagonal): Matrix {
        val result: Matrix = MatrixImpl(mutableListOf(mutableListOf()), Pair(this.dimension.first, this.dimension.second))
        for (row in 0 until dimension.first) {
            result.matrix.add(mutableListOf())
            for (col in 0 until dimension.second) {
                result.matrix[row].add(0.0)
            }
        }
        when (diagonal) {
            Diagonal.MAIN_DIAGONAL -> {
                matrix.forEachIndexed { row, rows ->
                    rows.forEachIndexed { col, num ->
                        result.matrix[col][row] = num
                    }
                }
            }
            Diagonal.SIDE_DIAGONAL -> {
                for (row in 0 until dimension.first) {
                    for (col in 0 until dimension.second) {
                        result.matrix[dimension.first - 1 - col][dimension.second - 1 - row] = matrix[row][col]
                    }
                }
            }
            Diagonal.VERTICAL_LINE -> {
                for (row in 0 until dimension.first) {
                    for (col in 0 until dimension.second) {
                        result.matrix[row][dimension.second - 1 - col] = matrix[row][col]
                    }
                }
            }
            Diagonal.HORIZONTAL_LINE -> {
                for (row in 0 until dimension.first) {
                    for (col in 0 until dimension.second) {
                        result.matrix[dimension.first - 1 - row][col] = matrix[row][col]
                    }
                }
            }
            else -> return this
        }
        return result
    }

    override fun getSubMatrixWithoutRC(row: Int, col: Int): Matrix {
        val result: Matrix = MatrixImpl(mutableListOf(), Pair(this.dimension.first - 1, this.dimension.second - 1))
        for (tempRow in 0 until dimension.first) {
            if (tempRow == row) continue
            result.matrix.add(mutableListOf())
            for (tempCol in 0 until dimension.second) {
                if (tempCol == col) continue
                result.matrix[result.matrix.size - 1].add(matrix[tempRow][tempCol])
            }
        }
        return result
    }

    override fun getDeterminant(): Double {
        return determinantHelper(this)
    }

    private fun determinantHelper(m: Matrix): Double {
        if (m.dimension == Pair(2, 2)) {
            return with(m) {
                matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0]
            }
        }
        var result = 0.0
        var coefficient = 1.0
        m.matrix[0].forEachIndexed { col, num ->
            val subMatrix = m.getSubMatrixWithoutRC(0, col)
            val temp = determinantHelper(subMatrix) * coefficient * num
            result += temp
            coefficient *= -1.0
        }
        return result
    }

    override fun getInverseMatrix(): Matrix {
        var result: Matrix = MatrixImpl(mutableListOf(), Pair(this.dimension.first, this.dimension.second))
        val determinant = this.getDeterminant()
        var coefficient = 1.0
        for (row in 0 until dimension.first) {
            result.matrix.add(mutableListOf())
            for (col in 0 until dimension.second) {
                result.matrix[row].add(coefficient * this.getSubMatrixWithoutRC(row, col).getDeterminant())
                coefficient *= -1.0
            }
            if (dimension.first % 2 == 0) coefficient *= -1
        }
        result = result.getTranspose(Diagonal.MAIN_DIAGONAL)
        return result * (1.0/determinant)
    }


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as MatrixImpl
        if (matrix != other.matrix) return false
        return true
    }

    override fun hashCode(): Int {
        return matrix.hashCode()
    }

    override fun toString(): String {
        var result = ""
        matrix.forEachIndexed { i, row ->
            row.forEach { col ->
                result += "$col "
            }
            if (i != matrix.size - 1)
                result += "\n"
        }
        return result
    }
}
