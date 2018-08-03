package moe.him188.gui.utils;

import cn.nukkit.Player;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * 将所有异常记录并等待 {@link #onFinished(Player)} 后一次性处理
 *
 * @author Him188moe @ GUI Project
 */
public abstract class ExceptionConsumerAll<E extends Exception> implements ExceptionConsumer<E> {
    private List<E> list = new ArrayList<>();
    private E lastException = null;

    @Override
    public void catchException(Player player, E e) {
        list.add(this.lastException = e);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onFinished(Player player) {
        if (this.lastException != null) {
            this.accept(player, list.toArray((E[]) Array.newInstance(lastException.getClass(), 0)));
            this.list.clear();
            this.lastException = null;
        }
    }
}
