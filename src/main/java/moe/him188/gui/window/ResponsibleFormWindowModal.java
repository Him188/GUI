package moe.him188.gui.window;

import cn.nukkit.Player;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerFormRespondedEvent;
import cn.nukkit.form.response.FormResponse;
import cn.nukkit.form.response.FormResponseModal;
import cn.nukkit.form.window.FormWindow;
import cn.nukkit.form.window.FormWindowModal;
import com.google.gson.Gson;
import moe.him188.gui.utils.Backable;
import moe.him188.gui.window.listener.response.ResponseListenerModal;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * 能直接接受提交表单 ({@link #onResponded}) 和关闭窗口事件({@link #onClosed}) 的 {@link FormWindowModal}.
 * 相较于 {@link FormWindowModal}, 该类无需依赖于 {@link Listener} 去监听 {@link PlayerFormRespondedEvent}, 而可以直接通过 lambda 方法收到返回数据.
 * 这个表单的组成是: 一段文字说明 + 两个按钮.
 * <br>
 * {@link FormWindowModal} that can receive click button event({@link #onResponded}) and close window event({@link #onClosed}).
 * Comparing with {@link FormWindowModal}, this responsible one does not need {@link Listener} to listen {@link PlayerFormRespondedEvent},
 * but it can directly receive {@link FormResponse} through lambda statements.
 *
 * @author Him188moe @ GUI Project
 */
public class ResponsibleFormWindowModal extends FormWindowModal implements Backable, ResponseListenerModal {
    protected transient BiConsumer<Boolean, Player> buttonClickedListener = null;

    protected transient Consumer<Player> windowClosedListener = null;

    private transient FormWindow parent;

    public ResponsibleFormWindowModal(String title, String content, String trueButtonText, String falseButtonText) {
        super(title, content, trueButtonText, falseButtonText);
    }

    @Override
    public void setParent(FormWindow parent) {
        this.parent = parent;
    }

    @Override
    public FormWindow getParent() {
        return parent;
    }

    /**
     * 在玩家提交表单, 或关闭表单窗口后调用. <br>
     * Called on submitted
     *
     * @param listener 调用的方法
     */
    public final ResponsibleFormWindowModal onResponded(@NotNull BiConsumer<Boolean, Player> listener) {
        Objects.requireNonNull(listener);
        this.buttonClickedListener = listener;
        return this;
    }

    /**
     * 在玩家提交表单后调用. <br>
     * Called on submitted
     *
     * @param listener 调用的方法(无 Player)
     */
    public final ResponsibleFormWindowModal onResponded(@NotNull Consumer<Boolean> listener) {
        Objects.requireNonNull(listener);
        this.buttonClickedListener = (response, player) -> listener.accept(response);
        return this;
    }

    /**
     * 在玩家提交表单后调用 <br>
     * Called on submitted
     *
     * @param listener 调用的方法(无参数)
     */
    public final ResponsibleFormWindowModal onResponded(@NotNull Runnable listener) {
        Objects.requireNonNull(listener);
        this.buttonClickedListener = (id, player) -> listener.run();
        return this;
    }

    /**
     * 在玩家提交表单后调用 <br>
     * Called on submitted
     *
     * @param listenerOnTrue  当玩家点击 <code>true</code> 按钮时调用的函数
     * @param listenerOnFalse 当玩家点击 <code>false</code> 按钮时调用的函数
     */
    public final ResponsibleFormWindowModal onResponded(@NotNull Runnable listenerOnTrue, @NotNull Runnable listenerOnFalse) {
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
     * 在玩家提交表单后调用 <br>
     * Called on submitted <br>
     * 参数任意一项可以为 null. <br>
     * Args can be null.
     *
     * @param listenerOnTrue  当玩家点击 <code>true</code> 按钮时调用的函数
     * @param listenerOnFalse 当玩家点击 <code>false</code> 按钮时调用的函数
     */
    public final ResponsibleFormWindowModal onResponded(@Nullable Consumer<Player> listenerOnTrue, @Nullable Consumer<Player> listenerOnFalse) {
        this.buttonClickedListener = (confirmation, player) -> {
            if (confirmation) {
                if (listenerOnTrue != null) {
                    listenerOnTrue.accept(player);
                }
            } else {
                if (listenerOnFalse != null) {
                    listenerOnFalse.accept(player);
                }
            }
        };
        return this;
    }

    /**
     * 在玩家关闭窗口而没有点击按钮提交表单后调用. <br>
     * Called on closed without submitting.
     *
     * @param listener 调用的方法
     */
    public final ResponsibleFormWindowModal onClosed(@NotNull Consumer<Player> listener) {
        Objects.requireNonNull(listener);
        this.windowClosedListener = listener;
        return this;
    }

    /**
     * 在玩家关闭窗口而没有点击按钮提交表单后调用. <br>
     * Called on closed without submitting.
     *
     * @param listener 调用的方法
     */
    public final ResponsibleFormWindowModal onClosed(@NotNull Runnable listener) {
        Objects.requireNonNull(listener);
        this.windowClosedListener = (player) -> listener.run();
        return this;
    }

    @Override
    public String getJSONData() {
        return new Gson().toJson(this, FormWindowModal.class);
    }

    public void callClicked(boolean response, @NotNull Player player) {
        Objects.requireNonNull(player);

        this.onClicked(response, player);

        if (this.buttonClickedListener != null) {
            this.buttonClickedListener.accept(response, Objects.requireNonNull(player));
        }
    }

    public void callClosed(@NotNull Player player) {
        Objects.requireNonNull(player);

        this.onClosed(player);

        if (this.windowClosedListener != null) {
            this.windowClosedListener.accept(Objects.requireNonNull(player));
        }
    }

    @SuppressWarnings("UnusedReturnValue")
    static boolean onEvent(PlayerFormRespondedEvent event) {
        if (event.getWindow() instanceof ResponsibleFormWindowModal) {
            ResponsibleFormWindowModal window = (ResponsibleFormWindowModal) event.getWindow();

            if (event.getWindow().wasClosed() || event.getResponse() == null) {
                window.callClosed(event.getPlayer());
                window.closed = false;//for resending
            } else {
                window.callClicked(((FormResponseModal) event.getResponse()).getClickedButtonId() == 0, event.getPlayer());
            }
            return true;
        }
        return false;
    }
}
