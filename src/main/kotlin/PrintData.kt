object PrintData {
    private const val RED = "\u001b[31m"
    private const val GREEN = "\u001b[32m"
    private const val RESET = "\u001b[0m"

    private fun createDummyString(repeat: Int, alpha: Char) = alpha.toString().repeat(repeat)

    fun info(text: String, isMain: Boolean = false) {
        val charSeparator = if (isMain) '=' else '-'
        val stringSeparator = createDummyString(text.length, charSeparator)

        println("${stringSeparator}\n${text}\n${stringSeparator}")
    }

    fun error(text: String) {
        println(RED)
        info("Введено некорретное значение: $text")
        println(RESET)
    }

    fun success(text: String) {
        println(GREEN)
        info(text)
        println(RESET)
    }
}
