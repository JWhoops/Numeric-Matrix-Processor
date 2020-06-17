package processor

enum class Diagonal(val option: Int) {
    MAIN_DIAGONAL(1),
    SIDE_DIAGONAL(2),
    VERTICAL_LINE(3),
    HORIZONTAL_LINE(4),
    NULL(-1);

    companion object {
        fun findDiagonal(opt: Int): Diagonal {
            for (enum in Diagonal.values()) {
                if (enum.option == opt)
                    return enum
            }
            return Diagonal.NULL
        }
    }
}