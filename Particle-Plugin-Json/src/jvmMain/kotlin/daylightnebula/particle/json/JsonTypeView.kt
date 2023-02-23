package daylightnebula.particle.json

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import daylightnebula.particle.editor.BasicColors
import daylightnebula.particle.editor.typeviews.TypeView
import java.io.File

class JsonTypeView: TypeView(arrayOf("json"), arrayOf()) {
    @Composable
    override fun render(file: File) {
        Box(
            Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(5.dp))
                .background(BasicColors.elementBackground)
        ) {

        }
    }
}