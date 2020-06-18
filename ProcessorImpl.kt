package processor

import java.util.*

class ProcessorImpl : Processor {
    override fun run() {
        val scanner = Scanner(System.`in`)
        while (true) {
            println(""" 
            1. Add matrices
            2. Multiply matrix to a constant
            3. Multiply matrices
            4. Transpose matrix
            5. Calculate a determinant
            6. Inverse Matrix
            0. Exit""".trimIndent())
            print("Your Choice:")
            val input = scanner.nextInt()
            when (ProcessorOption.findOperation(input)) {
                ProcessorOption.MULTIPLY_MATRIX -> processMultiplyMatrix(scanner)
                ProcessorOption.ADDITION -> processAddMatrix(scanner)
                ProcessorOption.MULTIPLY_NUMBER -> processMultiplyNumber(scanner)
                ProcessorOption.TRANSPOSE -> processTranspose(scanner)
                ProcessorOption.DETERMINANT -> processDeterminant(scanner)
                ProcessorOption.INVERSE_MATRIX -> processInverseMatrix(scanner)
                ProcessorOption.EXIT -> return
                ProcessorOption.NULL -> return
            }
        }

    }

    override fun processAddMatrix(scanner: Scanner) {
        scanner.nextLine()
        var input = scanner.nextLine()
        val dimension1 = processStrDimension(input)
        println("Enter first matrix: >")
        val matrix1 = processStrMatrix(scanner, dimension1)
        print("Enter size of second matrix: > ")
        input = scanner.nextLine()
        val dimension2 = processStrDimension(input)
        println("Enter second matrix: >")
        val matrix2 = processStrMatrix(scanner, dimension2)
        println("The addition result is:")
        println(matrix1 + matrix2)
    }

    override fun processMultiplyMatrix(scanner: Scanner) {
        scanner.nextLine()
        print("Enter size of first matrix: > ")
        var input = scanner.nextLine()
        val dimension1 = processStrDimension(input)
        println("Enter first matrix: >")
        val matrix1 = processStrMatrix(scanner, dimension1)
        print("Enter size of second matrix: > ")
        input = scanner.nextLine()
        val dimension2 = processStrDimension(input)
        println("Enter second matrix: >")
        val matrix2 = processStrMatrix(scanner, dimension2)
        println("The multiplication result is:")
        println(matrix1 * matrix2)
    }

    override fun processMultiplyNumber(scanner: Scanner) {
        scanner.nextLine()
        print("Enter size of matrix: > ")
        val input = scanner.nextLine()
        val dimension1 = processStrDimension(input)
        println("Enter matrix: >")
        val matrix1 = processStrMatrix(scanner, dimension1)
        val number = scanner.nextDouble()
        println("The multiplication result is:")
        println(matrix1 * number)
    }

    override fun processStrMatrix(scanner: Scanner, dimension: Pair<Int, Int>): Matrix {
        val tempMatrix = mutableListOf<MutableList<Double>>()
        val validator = MatrixValidatorImpl()
        val parser = MatrixParserImpl()
        repeat(dimension.first) {
            val input = scanner.nextLine()
            if (validator.validateRow(input, dimension.second)) {
                tempMatrix.add(parser.parseRow(input))
            } else {
                println("ERROR")
                return MatrixImpl(mutableListOf(mutableListOf()), Pair(0, 0))
            }
        }
        return MatrixImpl(tempMatrix, dimension)
    }

    override fun processStrDimension(input: String): Pair<Int, Int> {
        val validator = MatrixValidatorImpl()
        val parser = MatrixParserImpl()
        val dimension: Pair<Int, Int>
        if (validator.validateDimension(input)) {
            dimension = parser.parseDimension(input)
        } else {
            println("ERROR")
            return Pair(-1, -1)
        }
        return dimension
    }

    override fun processTranspose(scanner: Scanner) {
        println("""
           
           1. Main diagonal
           2. Side diagonal
           3. Vertical line
           4. Horizontal line
       """.trimIndent())
        print("Your choice: ")
        val choice = scanner.nextInt()
        val diagonal = Diagonal.findDiagonal(choice)
        scanner.nextLine()
        print("Enter matrix size: ")
        var input = scanner.nextLine()
        val dimension = processStrDimension(input)
        println("Enter matrix:")
        val matrix = processStrMatrix(scanner, dimension)
        val result = matrix.getTranspose(diagonal)
        println("The result is:")
        println(result.toString())
    }

    override fun processDeterminant(scanner: Scanner) {
        scanner.nextLine()
        print("Enter matrix size: > ")
        val input = scanner.nextLine()
        val dimension1 = processStrDimension(input)
        println("Enter matrix: >")
        val matrix1 = processStrMatrix(scanner, dimension1)
        println("The result is:")
        println(matrix1.getDeterminant())
    }

    override fun processInverseMatrix(scanner: Scanner) {
        scanner.nextLine()
        print("Enter matrix size: > ")
        val input = scanner.nextLine()
        val dimension1 = processStrDimension(input)
        println("Enter matrix: >")
        val matrix1 = processStrMatrix(scanner, dimension1)
        println("The result is:")
        println(matrix1.getInverseMatrix())
    }
}

enum class ProcessorOption(val option: Int) {
    ADDITION(1),
    MULTIPLY_NUMBER(2),
    MULTIPLY_MATRIX(3),
    TRANSPOSE(4),
    DETERMINANT(5),
    INVERSE_MATRIX(6),
    EXIT(0),
    NULL(-1);

    companion object {
        fun findOperation(opt: Int): ProcessorOption {
            for (enum in ProcessorOption.values()) {
                if (enum.option == opt) {
                    return enum
                }
            }
            return NULL
        }
    }
}