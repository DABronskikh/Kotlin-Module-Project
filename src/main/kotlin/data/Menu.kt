package data

import Common
import PrintData
import enums.DefaultActions

data class Menu<T>(
    val header: String,
    val type: String,
    val dataList: ArrayList<T>
) where T : Common {

    fun showMenu() {
        PrintData.info(header, true)
        println("${DefaultActions.Exit.action}. Выход\n ${DefaultActions.Create.action}. Создать $type")

        dataList.forEachIndexed { idx, el -> println(" ${idx + 1}. ${el.title}") }
    }
}
