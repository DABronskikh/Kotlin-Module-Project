package data

import Common

data class Note(
    override val title: String,
    val description: String,
    val arc: Archive
) : Common(title) {

    override fun toString(): String {
        return "Архив: ${arc.title}\nЗаголовок: ${title}\nТекст: $description"
    }
}
