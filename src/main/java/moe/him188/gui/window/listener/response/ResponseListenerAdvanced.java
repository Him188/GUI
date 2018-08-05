package moe.him188.gui.window.listener.response;

import cn.nukkit.Player;
import cn.nukkit.form.response.FormResponseSimple;
import moe.him188.gui.window.ResponsibleFormWindowSimpleAdvanced;

/**
 * @author Him188moe @ GUI Project
 */
public interface ResponseListenerAdvanced<E> extends ResponseListener<FormResponseSimple> {
    /**
     * 当表单提交数据并关闭窗口时调用
     *
     * @param entry  数据
     * @param player player
     */
    default void onClicked(E entry, Player player) {

    }

    @SuppressWarnings("unchecked")
    @Override
    default void onClicked(FormResponseSimple response, Player player) {
        if (this instanceof ResponsibleFormWindowSimpleAdvanced) {
            onClicked((E) ((ResponsibleFormWindowSimpleAdvanced) this).getEntry(response.getClickedButtonId()), player);
        }
    }
}
