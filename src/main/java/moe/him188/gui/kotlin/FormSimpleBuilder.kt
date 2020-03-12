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

    fun button(name: String) {
        form.addButton(name)
    }

    fun button(vararg name: String) {
        name.forEach {
            form.addButton(it)
        }
    }

    fun buttons(vararg name: String) {
        name.forEach {
            form.addButton(it)
        }
    }

    operator fun String.unaryPlus() {
        button(this)
    }

    inline fun buttons(block: ButtonsBuilder.() -> Unit) {
        ButtonsBuilder().apply(block)
    }

    inner class ButtonsBuilder {
        operator fun String.invoke(onClick: (Player) -> Unit) {
            form.addButton(this, onClick)
        }
    }

    @PublishedApi
    internal val form = FormSimple(title, content)

    fun build(): ResponsibleFormWindowSimple = form.onClicked(clickListener).onClosed(closeListener)
}