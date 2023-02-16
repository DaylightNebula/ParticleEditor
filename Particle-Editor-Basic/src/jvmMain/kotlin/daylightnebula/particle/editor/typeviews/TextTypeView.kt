package daylightnebula.particle.editor.typeviews

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.selection.DisableSelection
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.key.*
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import daylightnebula.particle.editor.BasicColors
import daylightnebula.particle.editor.pages.FileViewer
import java.io.File

class TextTypeView(types: Array<String>, val annotateString: (str: String) -> AnnotatedString): TypeView(types) {

    private val loadedFiles = hashMapOf<File, TextFile>()

    @OptIn(ExperimentalComposeUiApi::class)
    @Composable
    override fun render(file: File) {
        // remove any loaded files that are not currently in use
        val toRemove = loadedFiles.keys.filter { file -> !FileViewer.views.any { it.file == file } }
        toRemove.forEach { it ->
            loadedFiles.remove(it)
        }

        // if no loaded file, load the given file
        if (!loadedFiles.containsKey(file)) {
            loadedFiles[file] = TextFile(file)
        }

        // try to get a loaded file
        val lines = remember { mutableStateListOf(*loadedFiles[file]!!.lines.toTypedArray()) }
        var reload by remember { mutableStateOf(false) }

        // create a scrollable window for the text
        val scrollState = rememberLazyListState()
        LazyColumn(
            state = scrollState,
            modifier =
            Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(5.dp))
                .background(BasicColors.textViewBackground)
        ) {
        }
    }
}
data class Line(var text: String)
data class TextFile(val file: File, val lines: MutableList<Line>) {
    constructor(file: File): this(file, file.readLines().map { Line(it) }.toMutableList())
}