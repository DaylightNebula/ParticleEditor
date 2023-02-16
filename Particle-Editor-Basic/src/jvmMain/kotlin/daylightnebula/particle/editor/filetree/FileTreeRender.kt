package daylightnebula.particle.editor.filetree

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.CoroutineScope
import java.io.File

class ExpandableFile(
    val file: FileTreeFileInterface,
    val level: Int,
) {
    var children: List<ExpandableFile> by mutableStateOf(emptyList())
    val canExpand: Boolean get() = file.hasChildren

    fun toggleExpanded() {
        children = if (children.isEmpty()) {
            file.children
                .map { ExpandableFile(it, level + 1) }
                .sortedWith(compareBy({ it.file.isDirectory }, { it.file.name }))
                .sortedBy { !it.file.isDirectory }
        } else {
            emptyList()
        }
    }
}

class FileTree(root: FileTreeFileInterface) {
    private val expandableRoot = ExpandableFile(root, 0).apply {
        toggleExpanded()
    }

    val items: List<Item> get() = expandableRoot.toItems()

    inner class Item constructor(
        private val file: ExpandableFile
    ) {
        val name: String get() = file.file.name

        val level: Int get() = file.level

        val type: ItemType
            get() = if (file.file.isDirectory) {
                ItemType.Folder(isExpanded = file.children.isNotEmpty(), canExpand = file.canExpand)
            } else {
                ItemType.File(ext = file.file.name.substringAfterLast(".").lowercase())
            }

        fun open() {
            when (type) {
                is ItemType.Folder -> {
                    file.toggleExpanded()
                    if (file.children.size == 1) {
                        val child = Item(file.children.first())
                        if (child.type is ItemType.Folder)
                            child.open()
                    }
                }
                is ItemType.File -> {

                }
            }
        }
    }

    sealed class ItemType {
        class Folder(val isExpanded: Boolean, val canExpand: Boolean) : ItemType()
        class File(val ext: String) : ItemType()
    }

    private fun ExpandableFile.toItems(): List<Item> {
        fun ExpandableFile.addTo(list: MutableList<Item>) {
            list.add(Item(this))
            for (child in children) {
                child.addTo(list)
            }
        }

        val list = mutableListOf<Item>()
        addTo(list)
        return list
    }
}

interface FileTreeFileInterface {
    val name: String
    val isDirectory: Boolean
    val children: List<FileTreeFileInterface>
    val hasChildren: Boolean

    fun readLines(scope: CoroutineScope): List<String>
}
class FileTreeFile(private val file: File): FileTreeFileInterface {
    override val name: String = file.name
    override val isDirectory = file.isDirectory
    override val children = file.listFiles()?.map { FileTreeFile(it) } ?: listOf()
    override val hasChildren = file.listFiles() != null

    override fun readLines(scope: CoroutineScope): List<String> {
        return file.readLines()
    }
}