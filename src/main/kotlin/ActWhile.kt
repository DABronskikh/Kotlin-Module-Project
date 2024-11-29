import enums.DefaultActions
import java.util.*
import kotlin.collections.ArrayList

interface ActWhile {
    val cancellationAction: String
        get() = DefaultActions.Cancel.action
    val scanner
        get() = Scanner(System.`in`)

    fun askAction(): String {
        println("Введите номер пункта меню:")
        return scanner.nextLine()
    }

    fun askActionWithExit(
        text: String,
        isNotEmpty: Boolean = false,
        isNotEmptyErrorMessage: String = "Значение не должно быть пустым"
    ): String {
        val actionMessage = "$text или введите \"$cancellationAction\" для отмены операции"
        println(actionMessage)

        var str = scanner.nextLine()
        while (isNotEmpty && str.trim().isEmpty()) {
            PrintData.error(isNotEmptyErrorMessage)
            println(actionMessage)
            str = scanner.nextLine()
        }

        return str.trim()
    }

    fun <T : Common> userResultAction(dataList: ArrayList<T>) {
        while (true) {
            val number = askAction()
            val num = number.toIntOrNull()
            val exitAction = DefaultActions.Exit.action.toInt()
            val createAction = DefaultActions.Create.action.toInt()

            if (num != null && num in exitAction..dataList.size) {
                when (num) {
                    exitAction -> {
                        finishAction()
                        break
                    }

                    createAction -> createAction()
                    else -> showAction(num - 1)
                }
            } else {
                wrongAction(number)
            }
        }
    }

    fun finishAction() {}
    fun createAction()
    fun showAction(num: Int)
    fun wrongAction(number: String)
}
