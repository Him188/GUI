package moe.him188.gui.kotlin

import cn.nukkit.Player
import moe.him188.gui.window.FormSimple
import moe.him188.gui.window.ResponsibleFormWindowSimple

@Suppress("unused")
class FormSimpleBuilder {
    var clickListener: (Int) -> Unit = CLICK_LISTENER_NOP
    var closeListener: (Player) -> Unit = CLOSE_LISTENER_NOP

    companion object {
        private val CLICK_LISTENER_NOP: (Int) -> Unit = {}
        private val CLOSE_LISTENER_NOP: (Player) -> Unit = {}
    }

    var title: String = ""
    var content: String = ""

    fun title(v: String) {
        title = v
    }

    fun content(v: String) {
        content = v
    }

    fun onClicked(listener: (Int) -> Unit) {
        clickListener = listener
    }

    fun onClosed(listener: (Player) -> Unit) {
        closeListener = listener
    }

    @PublishedApi
    internal val buttons: MutableList<String> = mutableListOf()

    fun button(name: String) {
        buttons += name
    }

    fun button(vararg name: String) {
        buttons += name
    }

    fun buttons(vararg name: String) {
        buttons += name
    }

    operator fun String.unaryPlus() {
        button(this)
    }

    inline fun buttons(block: MutableList<String>.() -> Unit) {
        buttons.apply(block)
    }

    fun build(): ResponsibleFormWindowSimple = FormSimple(title, content, *buttons.toTypedArray()).onClicked(clickListener).onClosed(closeListener)
}