package data

import ActWhile
import Common

data class Archive(
    override val title: String,
    val content: ArrayList<Note>
) : Common(title), ActWhile {

    fun showMenu() {
        val menu = Menu("Меню заметок архива: '$title'", "заметку", content)
        menu.showMenu()
    }

    fun waitingAction() {
        userResultAction(content)
    }

    override fun createAction() {
        if (createNote()) {
            PrintData.success("Новая заметка создана.")
        }
        showMenu()
    }

    override fun showAction(num: Int) {
        PrintData.info("Просмотр заметки.", true)
        println(content[num])
        showMenu()
    }

    override fun wrongAction(number: String) {
        PrintData.error(number)
        showMenu()
    }

    private fun createNote(): Boolean {
        PrintData.info("Создание новой заметки.", true)
        val title = askActionWithExit(
            "Введите заголовок заметки",
            true,
            "Заголовок не должен быть пустым"
        )
        if (title == cancellationAction) {
            return cancelCreateNote()
        }

        val description = askActionWithExit(
            "Введите текст заметки",
            true,
            "Текст не должен быть пустым"
        )
        if (description == cancellationAction) {
            return cancelCreateNote()
        }

        return content.add(Note(title, description, this))
    }

    private fun cancelCreateNote(): Boolean {
        PrintData.info("Создание заметки отменено.")
        return false
    }
}
