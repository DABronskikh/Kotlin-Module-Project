import data.Archive
import data.Menu
import data.Note
import kotlin.collections.ArrayList

object App : ActWhile {
    private var listArchives: ArrayList<Archive> = arrayListOf()

    private fun showArchivesList() {
        val menu = Menu("Меню архивов:", "архив", listArchives)
        menu.showMenu()
    }

    fun work() {
        PrintData.info("Запуск приложения.", true)
        showArchivesList()
        userResultAction(listArchives)
    }

    override fun finishAction() {
        PrintData.info("Приложение закрыто.", true)
    }

    override fun createAction() {
        PrintData.info("Создание архива.", true)

        if (createArchive()) {
            PrintData.success("Архив создан.")
        } else {
            PrintData.info("Создание архива отменено")
        }
        showArchivesList()
    }

    override fun showAction(num: Int) {
        listArchives[num].showMenu()
        listArchives[num].waitingAction()
        PrintData.info("Работа с заметками завершена.")
        showArchivesList()
    }

    override fun wrongAction(number: String) {
        PrintData.error(number)
    }

    private fun createArchive(): Boolean {
        val title = askActionWithExit(
            "Введите заголовок архива",
            true,
            "Заголовок не должен быть пустым"
        )
        if (title == cancellationAction) {
            return false
        }

        val listNotes: ArrayList<Note> = arrayListOf()
        return listArchives.add(Archive(title, listNotes))
    }
}
