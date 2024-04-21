import java.net.URL
import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    // Ввод ссылки на страницу Википедии
    println("Введите ссылку на страницу Википедии:")
    val inputLink = readLine()

    if (inputLink != null) {
        // Создаем объект URL из введенной ссылки
        val url = URL(inputLink)
        // Устанавливаем соединение с этой ссылкой
        val connection = url.openConnection()
        // Создаем специального человека, чтобы он прочитал содержимое страницы))))
        val reader = BufferedReader(InputStreamReader(connection.getInputStream()))

        // Создаем коробочку для хранения уникальных ссылок
        val uniqueLinks = mutableSetOf<String>()

        var line: String?
        // Читаем содержимое страницы построчно
        while (reader.readLine().also { line = it } != null) {
            // Ищем все ссылки на странице
            val pattern = "<a href=\"/wiki/([^:#\"]*)\"".toRegex()
            val matches = pattern.findAll(line ?: "")
            // Добавляем найденные ссылки в коробочку
            for (match in matches) {
                val link = "https://en.wikipedia.org/wiki/${match.groups[1]?.value}"
                uniqueLinks.add(link)
            }
        }

        // Выводим найденные ссылки
        uniqueLinks.forEach { println(it) }
    }
}
