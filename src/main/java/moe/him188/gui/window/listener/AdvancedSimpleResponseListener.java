package moe.him188.gui.window.listener;

import cn.nukkit.Player;
import cn.nukkit.form.response.FormResponseSimple;
import moe.him188.gui.window.ResponsibleFormWindowSimpleAdvanced;

/**
 * @author Him188moe @ ProjectARK Project
 */
public interface AdvancedSimpleResponseListener<E> extends ResponseListener<FormResponseSimple> {
    /**
     * 当表单提交数据并关闭窗口时调用
     *
     * @param entry  数据
     * @param player player
     */
    void onClicked(E entry, Player player);

    @SuppressWarnings("unchecked")
    @Override
    default void onClicked(FormResponseSimple response, Player player) {
        if (this instanceof ResponsibleFormWindowSimpleAdvanced) {
            onClicked((E) ((ResponsibleFormWindowSimpleAdvanced) this).getEntry(response.getClickedButtonId()), player);
        }
    }
}
