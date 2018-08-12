package moe.him188.gui.window.listener.response;

import cn.nukkit.Player;

/**
 * @author Him188moe @ GUI Project
 */
public interface ResponseListenerModal extends ResponseListener {
    /**
     * 当表单提交数据并关闭窗口时调用
     *
     * @param confirmation 点击按钮类型. true就是trueButton
     * @param player       player
     */
    default void onClicked(boolean confirmation, Player player) {

    }

    /**
     * 当表单关闭而没有提交数据时调用
     *
     * @param player player
     */
    default void onClosed(Player player) {

    }
}
