package moe.him188.gui.kotlin

import cn.nukkit.Player
import moe.him188.gui.window.FormModal
import moe.him188.gui.window.ResponsibleFormWindowModal

@Suppress("unused")
class FormModalBuilder {
    private lateinit var clickListener: (Boolean) -> Unit
    private lateinit var closeListener: (Player) -> Unit

    var title: String = ""
    var content: String = ""
    var trueButton: String = ""
    var falseButton: String = ""

    fun title(v: String) {
        title = v
    }

    fun content(v: String) {
        content = v
    }

    fun trueButton(v: String) {
        trueButton = v
    }

    fun falseButton(v: String) {
        falseButton = v
    }

    fun onClicked(listener: (Boolean) -> Unit) {
        clickListener = listener
    }

    fun onClosed(listener: (Player) -> Unit) {
        closeListener = listener
    }

    fun build(): ResponsibleFormWindowModal = FormModal(title, content, trueButton, falseButton).let {
        if (this::clickListener.isInitialized) {
            it.onResponded(clickListener)
        }
        if (this::closeListener.isInitialized) {
            it.onClosed(closeListener)
        }
        return it
    }
}