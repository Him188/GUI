package moe.him188.gui.window.listener.response;

import cn.nukkit.Player;
import cn.nukkit.form.response.FormResponseModal;

/**
 * @author Him188moe @ ProjectARK Project
 */
public interface ModalResponseListener extends ResponseListener<FormResponseModal> {
    /**
     * 当表单提交数据并关闭窗口时调用
     *
     * @param confirmation 点击按钮类型. true就是trueButton
     * @param player       player
     */
    void onClicked(boolean confirmation, Player player);

    @Override
    default void onClicked(FormResponseModal response, Player player) {
        onClicked(response.getClickedButtonId() == 0, player);
    }
}
