package moe.him188.gui.kotlin

import cn.nukkit.Player
import moe.him188.gui.template.Template
import moe.him188.gui.template.response.TemplateResponses
import moe.him188.gui.window.FormTemplated
import moe.him188.gui.window.ResponsibleFormWindowTemplated

@Suppress("unused")
class FormTemplatedBuilder<K> {
    private var clickListener: (TemplateResponses<K>) -> Unit = CLICK_LISTENER_NOP
    private var closeListener: (Player) -> Unit = CLOSE_LISTENER_NOP

    companion object {
        private val CLICK_LISTENER_NOP: (TemplateResponses<*>) -> Unit = {}
        private val CLOSE_LISTENER_NOP: (Player) -> Unit = {}
    }

    fun onClicked(listener: (TemplateResponses<K>) -> Unit) {
        clickListener = listener
    }

    fun onClosed(listener: (Player) -> Unit) {
        closeListener = listener
    }

    fun build(template: Template<K>): ResponsibleFormWindowTemplated<K> = FormTemplated(template).onResponded(clickListener).onClosed(closeListener)
}