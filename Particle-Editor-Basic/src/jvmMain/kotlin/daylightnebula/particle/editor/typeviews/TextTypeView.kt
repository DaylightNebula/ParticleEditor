package daylightnebula.particle.editor.typeviews

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import daylightnebula.particle.editor.BasicColors
import daylightnebula.particle.editor.pages.FileViewer
import java.io.File

class TextTypeView: TypeView(arrayOf()) {

    val loadedFiles = hashMapOf<File, String>()

    @Composable
    override fun render(file: File) {
        // remove any loaded files that are not currently in use
        val toRemove = loadedFiles.keys.filter { file -> !FileViewer.views.any { it.file == file } }
        toRemove.forEach { loadedFiles.remove(it) }

        // try to get a loaded file
        var loaded = loadedFiles[file]

        // if no loaded file, load the given file
        if (loaded == null) {
            loaded = file.readText()
            loadedFiles[file] = loaded
        }

        // create a scrollable window for the text
        val scrollState = rememberLazyListState()
        LazyColumn(
            state = scrollState,
            modifier =
            Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(5.dp))
                .background(BasicColors.elementBackground)
        ) {
            // draw the text
            item {
                Text(loaded, color = BasicColors.foreground)
            }
        }
    }
}