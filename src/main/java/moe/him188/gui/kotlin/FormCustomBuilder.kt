package moe.him188.gui.kotlin

import cn.nukkit.Player
import cn.nukkit.form.element.Element
import cn.nukkit.form.response.FormResponseCustom
import moe.him188.gui.window.FormCustom
import moe.him188.gui.window.ResponsibleFormWindowCustom

@Suppress("unused")
class FormCustomBuilder {
    private var clickListener: (FormResponseCustom) -> Unit = CLICK_LISTENER_NOP
    private var closeListener: (Player) -> Unit = CLOSE_LISTENER_NOP

    companion object {
        private val CLICK_LISTENER_NOP: (FormResponseCustom) -> Unit = {}
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

    fun onClicked(listener: (FormResponseCustom) -> Unit) {
        clickListener = listener
    }

    fun onClosed(listener: (Player) -> Unit) {
        closeListener = listener
    }

    @PublishedApi
    internal val elements: MutableList<Element> = mutableListOf()

    fun element(element: Element) {
        elements += element
    }

    fun element(vararg elements: Element) {
        this.elements.addAll(elements)
    }

    fun elements(vararg element: Element) {
        elements += element
    }

    operator fun Element.unaryPlus() {
        element(this)
    }

    inline fun elements(block: MutableList<Element>.() -> Unit) {
        elements.apply(block)
    }

    fun build(): ResponsibleFormWindowCustom = FormCustom(title, elements).onResponded(clickListener).onClosed(closeListener)
}