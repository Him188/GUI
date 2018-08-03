package moe.him188.gui.window.listener.response;

import cn.nukkit.Player;
import moe.him188.gui.template.response.TemplateResponses;

/**
 * @author Him188moe @ ProjectARK Project
 */
public interface ResponseListenerTemplate<K> {
    /**
     * 当表单提交数据并关闭窗口时调用
     *
     * @param responses response
     * @param player    player
     */
    default void onResponded(TemplateResponses<K> responses, Player player) {

    }

    /**
     * 当表单被关闭(点击右上角关闭按钮), 而没有提交数据时调用
     *
     * @param player player
     */
    default void onClosed(Player player) {

    }
}
