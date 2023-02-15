package daylightnebula.particle.editor.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import daylightnebula.particle.editor.BasicColors

object FileTree {
    @Composable
    fun drawFileTree() {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(300.dp)
                .padding(start = 5.dp, bottom = 5.dp, end = 5.dp)
                .clip(RoundedCornerShape(5.dp))
                .background(BasicColors.elementBackground)
        ) {

        }
    }
}