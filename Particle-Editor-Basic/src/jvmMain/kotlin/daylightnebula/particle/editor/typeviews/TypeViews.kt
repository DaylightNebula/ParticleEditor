package daylightnebula.particle.editor.typeviews

import androidx.compose.runtime.Composable
import java.io.File

object TypeViewManager {
    private val views = mutableListOf<TypeView>()
    private val defaultView = TextTypeView()

    // to register views, simply add them to the list
    fun registerView(view: TypeView) { views.add(view) }

    @Composable
    fun drawViewForFile(file: File) {
        // try to get a view by first check if we have a type has is registered for the extension, if none found, use default
        val view = views.firstOrNull { it.types.contains(file.extension) } ?: defaultView

        // draw
        view.render(file)
    }
}
abstract class TypeView(val types: Array<String>) {
    @Composable
    abstract fun render(file: File)
}