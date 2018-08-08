package moe.him188.gui.window.listener.response;

import cn.nukkit.Player;

/**
 * @author Him188moe @ GUI Project
 */
public interface ResponseListenerAdvanced<E> {
    /**
     * 当表单提交数据时调用
     *
     * @param entry  数据
     * @param player player
     */
    default void onClicked(E entry, Player player) {

    }

    /**
     * 当表单关闭而没有提交数据时调用
     *
     * @param player player
     */
    default void onClosed(Player player) {

    }
}
