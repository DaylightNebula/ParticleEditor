package daylightnebula.particle.editor

import java.io.File
import java.lang.StringBuilder

object SettingsManager {

    private val settingsFile = File(System.getProperty("user.dir"), "settings.settings")
    private val settings = hashMapOf<String, String>()

    init {
        if (settingsFile.exists()) {
            settingsFile.forEachLine {
                val tokens = it.split("=")
                settings[tokens.first()] = tokens.last()
            }
        }
    }

    fun setDirectory(file: File) {
        settings["directory"] = file.absolutePath
        save()
    }

    fun getDirectory(): File = File(settings["directory"] ?: System.getProperty("user.dir"))

    private fun save() {
        val builder = StringBuilder()
        settings.forEach { (a, b) ->
            builder.append("$a=$b\n")
        }
        settingsFile.writeText(builder.toString())
    }
}