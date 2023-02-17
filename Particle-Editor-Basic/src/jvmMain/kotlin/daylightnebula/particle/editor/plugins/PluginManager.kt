package daylightnebula.particle.editor.plugins

import daylightnebula.particle.editor.pages.TopBar
import daylightnebula.particle.editor.pages.TopBarButton
import daylightnebula.particle.editor.typeviews.TypeViewManager
import java.io.File
import java.lang.IllegalArgumentException
import java.net.URLClassLoader

object PluginManager {
    private lateinit var pluginsFolder: File

    fun init(pluginsFolder: File) {
        // save plugins folder
        this.pluginsFolder = pluginsFolder

        // make sure plugin folder exists
        if (!pluginsFolder.exists())
            pluginsFolder.mkdirs()

        // load all jars from the plugins folder as a plugin
        pluginsFolder.listFiles()?.forEach { file ->
            if (file.extension == "jar")
                loadPlugin(file)
        }
    }

    fun reload() {
        // call reset functions
        TypeViewManager.reset()
        TopBar.reset()

        // call init again
        init(pluginsFolder)
    }

    private fun loadPlugin(file: File) {
        val loader = URLClassLoader(arrayOf(file.toURI().toURL()))

        // load the main class from the config
        val config = loader.getResource("plugin.config")?.readText()?.split("\n")
            ?: return
        val mainClass = config.firstOrNull { it.startsWith("MAIN_CLASS") }?.split("=")?.last()
            ?: return

        // create the plugin
        val initPluginClass = Class.forName(mainClass, true, loader)
        val initPluginConstructor = initPluginClass.getDeclaredConstructor()
        initPluginConstructor.trySetAccessible()
        val initPlugin = initPluginConstructor.newInstance()

        // make sure it is a web stream application
        if (initPlugin !is ParticlePlugin) throw ClassCastException("Unable to load ParticlePlugin from ${file.path}")

        // load type views and top bar buttons
        initPlugin.getTopBarButtons().forEach { TopBar.addButton(it) }
        initPlugin.getTypeViews().forEach { TypeViewManager.registerView(it) }
    }
}