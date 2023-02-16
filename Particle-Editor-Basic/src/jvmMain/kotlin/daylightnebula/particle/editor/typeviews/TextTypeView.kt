package daylightnebula.particle.editor.typeviews

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.selection.DisableSelection
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.input.key.*
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import daylightnebula.particle.editor.BasicColors
import daylightnebula.particle.editor.pages.FileViewer
import java.io.File

class TextTypeView(types: Array<String>): TypeView(types) {

    private val loadedFiles = hashMapOf<File, String>()

    @Composable
    override fun render(file: File) {
        // remove any loaded files that are not currently in use
        val toRemove = loadedFiles.keys.filter { f -> !FileViewer.views.any { it.file == f } }
        toRemove.forEach { it ->
            loadedFiles.remove(it)
        }

        // if no loaded file, load the given file
        if (!loadedFiles.containsKey(file)) {
            loadedFiles[file] = file.readText()
        }
        println("Rendering ${file.path} ${loadedFiles[file]!!.replace("\n", "")}")

        // try to get a loaded file
        var text by remember { mutableStateOf("") }
        text = loadedFiles[file]!! // kinda stupid that I have to do this, but hey it works now, I'm going to bed

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
            item {
                BasicTextField(
                    value = text,
                    onValueChange = { text = it; file.writeText(it) },
                    cursorBrush = SolidColor(BasicColors.foreground),
                    modifier = Modifier
                        .padding(start = 5.dp)
                        .wrapContentWidth(),
                    textStyle = LocalTextStyle.current.merge(
                        TextStyle(
                            color = BasicColors.foreground
                        )
                    )
                )
            }
        }
    }
}