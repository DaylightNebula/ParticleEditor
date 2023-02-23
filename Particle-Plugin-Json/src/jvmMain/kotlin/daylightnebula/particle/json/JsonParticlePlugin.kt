package daylightnebula.particle.json

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import daylightnebula.particle.editor.pages.TopBarButton
import daylightnebula.particle.editor.pages.TopBarButtonOption
import daylightnebula.particle.editor.plugins.ParticlePlugin
import daylightnebula.particle.editor.typeviews.TypeView

class JsonParticlePlugin: ParticlePlugin() {
    override fun getTopBarButtons(): Array<TopBarButton> {
        return arrayOf()
    }

    override fun getTypeViews(): Array<TypeView> {
        return arrayOf(JsonTypeView())
    }
}
