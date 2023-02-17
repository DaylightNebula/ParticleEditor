package daylightnebula.particle.editor.plugins

import daylightnebula.particle.editor.pages.TopBarButton
import daylightnebula.particle.editor.typeviews.TypeView

abstract class ParticlePlugin {
    abstract fun getTopBarButtons(): Array<TopBarButton>
    abstract fun getTypeViews(): Array<TypeView>
}