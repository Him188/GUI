package moe.him188.gui.utils;

import cn.nukkit.Player;
import cn.nukkit.form.response.FormResponseCustom;
import moe.him188.gui.template.Template;

/**
 * 异常处理器. <br>
 * 用于 {@link Template.Builder#parseTemplateResponse(Player, FormResponseCustom, ExceptionConsumer)}
 *
 * @author Him188moe @ GUI Project
 */
public interface ExceptionConsumer<E extends Exception> {
    /**
     * 接受新的异常 <br>
     * 但并不代表处理立即这个异常.
     *
     * @param exception exception
     */
    void catchException(Player player, E exception);

    /**
     * 处理异常
     *
     * @param exception exception, notnull, maybe empty
     */
    void accept(Player player, E[] exception);

    /**
     * 当需要处理的异常已经全部被 {@link #catchException(Player, Exception)} 时调用
     */
    default void onFinished(Player player) {

    }
}
