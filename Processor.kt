package processor

import java.util.*

interface Processor {
    fun run()
    fun processAddMatrix(scanner: Scanner)
    fun processMultiplyMatrix(scanner: Scanner)
    fun processMultiplyNumber(scanner: Scanner)
    fun processTranspose(scanner: Scanner)
    fun processDeterminant(scanner: Scanner)
    fun processInverseMatrix(scanner: Scanner)
    fun processStrMatrix(scanner: Scanner, dimension: Pair<Int, Int>): Matrix
    fun processStrDimension(input: String): Pair<Int, Int>
}