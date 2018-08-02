package moe.him188.gui.window.listener.response;

import cn.nukkit.Player;
import cn.nukkit.form.response.FormResponse;

/**
 * 表单返回事件监测器. <br/>
 * 提供方法接收返回数据和关闭窗口事件 <br>
 * <br>
 * 当你的表单返回数据处理过程很多时, 你可能就不应该使用原 ResponsibleForm 中的函数式(lambda) onClick 了.<br>
 * {@link ResponseListener} 是不错的替代! <br>
 *
 * @author Him188moe @ ProjectARK Project
 */
interface ResponseListener<R extends FormResponse> {
    /**
     * 当表单提交数据并关闭窗口时调用
     *
     * @param response 表单提交的数据
     * @param player   player
     */
    default void onClicked(R response, Player player) {

    }

    /**
     * 当表单被关闭(点击右上角关闭按钮), 而没有提交数据时调用
     *
     * @param player player
     */
    default void onClosed(Player player) {

    }
}
