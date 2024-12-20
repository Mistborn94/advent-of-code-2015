package helper

import java.io.File
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse.BodyHandlers

private const val YEAR = 2015
private const val INPUT_FILE = "input"

fun readDayFile(day: Int, fileName: String): File {
    val directory = "src/main/resources/inputs/$YEAR/day$day"
    val file = File("$directory/$fileName")

    if (!file.exists() && fileName == INPUT_FILE) {
        File(directory).mkdirs()
        downloadInputFile(day, file)
    }

    return file
}

private fun downloadInputFile(day: Int, file: File) {
    val cookie = File("aoc_cookie").readText()
    val request = HttpRequest.newBuilder(URI("https://adventofcode.com/$YEAR/day/$day/input"))
        .header("Cookie", cookie)
        .header("accept", "text/plain")
        .GET()
        .build()

    val response = HttpClient.newHttpClient()
        .send(request, BodyHandlers.ofFile(file.toPath()))

    if (response.statusCode() != 200) {
        file.delete()
        throw IllegalArgumentException("Failed to download input file: Http ${response.statusCode()}")
    }
}