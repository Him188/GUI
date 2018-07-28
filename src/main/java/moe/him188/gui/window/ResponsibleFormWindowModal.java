package moe.him188.gui.window;

import cn.nukkit.Player;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerFormRespondedEvent;
import cn.nukkit.form.response.FormResponseModal;
import cn.nukkit.form.window.FormWindowModal;
import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import moe.him188.gui.window.listener.ModalResponseListener;

import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * 能直接接受提交表单 ({@link #onResponded(BiConsumer)}) 和关闭窗口事件({@link #onClosed(Consumer)}) 的 {@link FormWindowModal}.
 * 相较于 {@link FormWindowModal}, 该类无需依赖于 {@link Listener} 去监听 {@link PlayerFormRespondedEvent}, 而可以直接通过 lambda 方法收到返回数据.
 * 这个表单的组成是: 一段文字说明 + 两个按钮.
 *
 * @author Him188moe @ ProjectARK Project
 */
public class ResponsibleFormWindowModal extends FormWindowModal {
    @Expose(serialize = false, deserialize = false)
    private BiConsumer<Boolean, Player> buttonClickedListener = null;

    @Expose(serialize = false, deserialize = false)
    private Consumer<Player> windowClosedListener = null;

    public ResponsibleFormWindowModal(String title, String content, String trueButtonText, String falseButtonText) {
        super(title, content, trueButtonText, falseButtonText);
    }

    /**
     * 在玩家提交表单, 或关闭表单窗口后调用.
     *
     * @param listener 调用的方法
     */
    public ResponsibleFormWindowModal onResponded(BiConsumer<Boolean, Player> listener) {
        this.buttonClickedListener = listener;
        return this;
    }

    /**
     * 在玩家提交表单后调用
     *
     * @param listener 调用的方法(无 Player)
     */
    public ResponsibleFormWindowModal onResponded(Consumer<Boolean> listener) {
        Objects.requireNonNull(listener);
        this.buttonClickedListener = (response, player) -> listener.accept(response);
        return this;
    }

    /**
     * 在玩家提交表单后调用
     *
     * @param listener 调用的方法(无参数)
     */
    public ResponsibleFormWindowModal onResponded(Runnable listener) {
        Objects.requireNonNull(listener);
        this.buttonClickedListener = (id, player) -> listener.run();
        return this;
    }

    /**
     * 在玩家提交表单后调用
     *
     * @param listenerOnTrue  当玩家点击 <code>true</code> 按钮时调用的函数
     * @param listenerOnFalse 当玩家点击 <code>false</code> 按钮时调用的函数
     */
    public ResponsibleFormWindowModal onResponded(Runnable listenerOnTrue, Runnable listenerOnFalse) {
        Objects.requireNonNull(listenerOnTrue);
        Objects.requireNonNull(listenerOnFalse);
        this.buttonClickedListener = (confirmation, player) -> {
            if (confirmation) {
                listenerOnTrue.run();
            } else {
                listenerOnFalse.run();
            }
        };
        return this;
    }

    /**
     * 在玩家提交表单后调用
     *
     * @param listenerOnTrue  当玩家点击 <code>true</code> 按钮时调用的函数
     * @param listenerOnFalse 当玩家点击 <code>false</code> 按钮时调用的函数
     */
    public ResponsibleFormWindowModal onResponded(Consumer<Player> listenerOnTrue, Consumer<Player> listenerOnFalse) {
        Objects.requireNonNull(listenerOnTrue);
        Objects.requireNonNull(listenerOnFalse);
        this.buttonClickedListener = (confirmation, player) -> {
            if (confirmation) {
                listenerOnTrue.accept(player);
            } else {
                listenerOnFalse.accept(player);
            }
        };
        return this;
    }


    /**
     * 在玩家关闭窗口而没有点击按钮提交表单后调用.
     *
     * @param listener 调用的方法
     */
    public ResponsibleFormWindowModal onClosed(Consumer<Player> listener) {
        this.windowClosedListener = listener;
        return this;
    }

    @Override
    public String getJSONData() {
        return new Gson().toJson(this, FormWindowModal.class);
    }

    /**
     * 在玩家关闭窗口而没有点击按钮提交表单后调用.
     *
     * @param listener 调用的方法
     */
    public ResponsibleFormWindowModal onClosed(Runnable listener) {
        Objects.requireNonNull(listener);
        this.windowClosedListener = (player) -> listener.run();
        return this;
    }

    public void callClicked(boolean response, Player player) {
        this.buttonClickedListener.accept(response, Objects.requireNonNull(player));
    }

    public void callClosed(Player player) {
        this.windowClosedListener.accept(Objects.requireNonNull(player));
    }

    public static boolean onEvent(PlayerFormRespondedEvent event) {
        if (event.getWindow() instanceof ResponsibleFormWindowModal && event.getResponse() instanceof FormResponseModal) {
            ResponsibleFormWindowModal window = (ResponsibleFormWindowModal) event.getWindow();
            if (window instanceof ModalResponseListener) {
                ((ModalResponseListener) window).onClicked((FormResponseModal) event.getResponse(), event.getPlayer());
            }

            if (event.getWindow().wasClosed() || event.getResponse() == null) {
                if (window.windowClosedListener == null) {
                    return true;
                }
                window.callClosed(event.getPlayer());
            } else {
                if (window.buttonClickedListener == null) {
                    return true;
                }
                window.callClicked(((FormResponseModal) event.getResponse()).getClickedButtonId() == 0, event.getPlayer());
            }
            return true;
        }
        return false;
    }
}
