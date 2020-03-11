@file:Suppress("unused")

package moe.him188.gui.kotlin

import cn.nukkit.Player
import moe.him188.gui.template.Template


inline fun Player.showFormSimple(
        listener: FormSimpleBuilder.() -> Unit
) {
    this.showFormWindow(FormSimpleBuilder().apply(listener).build())
}

inline fun Player.showFormModal(listener: FormModalBuilder.() -> Unit) {
    this.showFormWindow(FormModalBuilder().apply(listener).build())
}

inline fun <K> Player.showFormTemplated(template: Template<K>, listener: FormTemplatedBuilder<K>.() -> Unit) {
    this.showFormWindow(FormTemplatedBuilder<K>().apply(listener).build(template))
}

inline fun Player.showFormCustom(listener: FormCustomBuilder.() -> Unit) {
    this.showFormWindow(FormCustomBuilder().apply(listener).build())
}

class ListenerBuilder<T> {
    private lateinit var clickListener: (Int) -> Unit
    private lateinit var closeListener: (Player) -> Unit
}