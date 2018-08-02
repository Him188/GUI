package moe.him188.gui.window.listener.response;

import cn.nukkit.Player;
import cn.nukkit.form.response.FormResponseSimple;

/**
 * @author Him188moe @ ProjectARK Project
 */
public interface SimpleResponseListener extends ResponseListener<FormResponseSimple> {
    /**
     * 当表单提交数据并关闭窗口时调用. <br>
     * Called when player clicked a button.
     *
     * @param id     button id, starts from 0. | 按钮 ID, 从 0 开始
     * @param player player
     */
    default void onClicked(int id, Player player) {

    }

    @Override
    default void onClicked(FormResponseSimple response, Player player) {
        onClicked(response.getClickedButtonId(), player);
    }
}
