package moe.him188.gui.window.listener.response;

import cn.nukkit.Player;
import cn.nukkit.form.response.FormResponseCustom;

/**
 * @author Him188moe @ GUI Project
 */
public interface ResponseListenerCustom extends ResponseListener {
    /**
     * 当表单提交数据时调用
     *
     * @param response 数据
     * @param player   player
     */
    default void onClicked(FormResponseCustom response, Player player) {

    }

    /**
     * 当表单关闭而没有提交数据时调用
     *
     * @param player player
     */
    default void onClosed(Player player) {

    }
}
