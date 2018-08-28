package moe.him188.gui.window;

import cn.nukkit.Player;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerFormRespondedEvent;
import cn.nukkit.form.response.FormResponse;
import cn.nukkit.form.response.FormResponseCustom;
import cn.nukkit.form.window.FormWindow;
import cn.nukkit.form.window.FormWindowCustom;
import com.google.gson.Gson;
import moe.him188.gui.template.Template;
import moe.him188.gui.template.response.TemplateResponses;
import moe.him188.gui.utils.Backable;
import moe.him188.gui.utils.ExceptionConsumer;
import moe.him188.gui.utils.NoParentWindowFoundException;
import moe.him188.gui.utils.ResponseParseException;
import moe.him188.gui.window.listener.response.ResponseListenerTemplate;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * 能直接接受提交表单 ({@link #onResponded}) 和关闭窗口事件({@link #onClosed}) 的 {@link FormWindowCustom}.
 * 相较于 {@link FormWindowCustom}, 该类无需依赖于 {@link Listener} 去监听 {@link PlayerFormRespondedEvent}, 而可以直接通过 lambda 方法收到返回数据.
 * <br>
 * {@link FormWindowCustom} that can receive click button event({@link #onResponded}) and close window event({@link #onClosed}).
 * Comparing with {@link FormWindowCustom}, this responsible one does not need {@link Listener} to listen {@link PlayerFormRespondedEvent},
 * but it can directly receive {@link FormResponse} through lambda statements.
 *
 * @author Him188moe @ GUI Project
 */
public class ResponsibleFormWindowTemplated<K> extends FormWindowCustom implements Backable, ResponseListenerTemplate<K> {
    transient final Template<K> template;

    protected transient BiConsumer<TemplateResponses<K>, Player> buttonClickedListener = null;

    protected transient Consumer<Player> windowClosedListener = null;

    protected transient TemplateResponses<K> lastResponses = null;

    protected transient Player lastPlayer = null;

    protected transient ExceptionConsumer<ResponseParseException> exceptionConsumer;

    private transient FormWindow parent;

    public ResponsibleFormWindowTemplated(@NotNull Template<K> template) {
        this("", template);
    }

    public ResponsibleFormWindowTemplated(String title, @NotNull Template<K> template) {
        super(Objects.requireNonNull(title), new ArrayList<>(), "");
        Objects.requireNonNull(template);
        this.template = template;
        template.getBuilder().applyTemplateTo(this);
    }

    public Template<K> getTemplate() {
        return template;
    }

    @Override
    public void setParent(FormWindow parent) {
        this.parent = parent;
    }

    @Override
    public FormWindow getParent() {
        return this.parent;
    }

    @Override
    public void goBack(Player player) throws NoParentWindowFoundException {
        Objects.requireNonNull(player);
        if (getParent() == null) {
            throw new NoParentWindowFoundException();
        }
        if (this.lastResponses != null) {
            this.applyLastResponse();
        }
        player.showFormWindow(this.getParent());
    }

    /**
     * Get last responses
     *
     * @return last response, nullable.
     *
     * @see #setLastResponses(Player, FormResponseCustom)
     * @see #onEvent(PlayerFormRespondedEvent)
     */
    public TemplateResponses<K> getLastResponses() {
        return lastResponses;
    }

    /**
     * Apply {@link #lastResponses} to this.
     *
     * @see TemplateResponses.Builder#applyToWindow(FormWindowCustom, K[])
     */
    public void applyLastResponse() throws NullPointerException {
        this.applyLastResponse(null);
    }

    /**
     * Apply {@link #lastResponses} to this.
     *
     * @param doNotKeepValues keys that will not be filled. These elements will be defaultValues
     *
     * @see TemplateResponses.Builder#applyToWindow(FormWindowCustom, K[])
     */
    public void applyLastResponse(@Nullable K[] doNotKeepValues) throws NullPointerException {
        Objects.requireNonNull(this.lastResponses);
        this.lastResponses.getBuilder().applyToWindow(this, doNotKeepValues);
    }

    /**
     * Get last player who responded this form.
     *
     * @return last player, nullable
     *
     * @see #setLastResponses(Player, FormResponseCustom)
     * @see #onEvent(PlayerFormRespondedEvent)
     */
    public Player getLastPlayer() {
        return lastPlayer;
    }

    public void setLastResponses(@NotNull Player player, @NotNull FormResponseCustom response) {
        Objects.requireNonNull(player);
        Objects.requireNonNull(response);

        this.lastPlayer = player;
        this.lastResponses = this.template.getBuilder().parseTemplateResponse(player, response, this.exceptionConsumer);
    }

    /**
     * 当玩家提交表单后, 输入了错误的格式时会抛出异常
     *
     * @param exceptionConsumer 处理异常的函数
     */
    public final ResponsibleFormWindowTemplated<K> setExceptionConsumer(@NotNull ExceptionConsumer<ResponseParseException> exceptionConsumer) {
        Objects.requireNonNull(exceptionConsumer);
        this.exceptionConsumer = exceptionConsumer;
        return this;
    }

    /**
     * 在玩家提交表单后调用 <br>
     * Called on submitted <br>
     * 即使表单某项目格式不正确, 也会调用此方法. <br>
     * 请使用 {@link TemplateResponses#hasNull()} 判断 <br>
     * This method will still be called even if there are illegal input(s) <br>
     * Please use {@link TemplateResponses#hasNull()} to check
     *
     * @param listener 调用的方法
     */
    public final ResponsibleFormWindowTemplated<K> onResponded(@NotNull BiConsumer<TemplateResponses<K>, Player> listener) {
        this.buttonClickedListener = listener;
        return this;
    }

    /**
     * 在玩家提交表单后调用 <br>
     * Called on submitted <br>
     * 即使表单某项目格式不正确, 也会调用此方法. <br>
     * 请使用 {@link TemplateResponses#hasNull()} 判断 <br>
     * This method will still be called even if there are illegal input(s) <br>
     * Please use {@link TemplateResponses#hasNull()} to check
     *
     * @param listener 调用的方法(无 Player)
     */
    public final ResponsibleFormWindowTemplated<K> onResponded(@NotNull Consumer<TemplateResponses<K>> listener) {
        Objects.requireNonNull(listener);
        this.buttonClickedListener = (response, player) -> listener.accept(response);
        return this;
    }

    /**
     * 在玩家提交表单后调用 <br>
     * Called on submitted <br>
     * 即使表单某项目格式不正确, 也会调用此方法. <br>
     * 请使用 {@link TemplateResponses#hasNull()} 判断 <br>
     * This method will still be called even if there are illegal input(s) <br>
     * Please use {@link TemplateResponses#hasNull()} to check
     *
     * @param listener 调用的方法(无参数)
     */
    public final ResponsibleFormWindowTemplated<K> onResponded(@NotNull Runnable listener) {
        Objects.requireNonNull(listener);
        this.buttonClickedListener = (id, player) -> listener.run();
        return this;
    }

    /**
     * 在玩家关闭窗口而没有点击按钮提交表单后调用. <br>
     * Called on closed without submitting.
     *
     * @param listener 调用的方法
     */
    public final ResponsibleFormWindowTemplated<K> onClosed(@NotNull Consumer<Player> listener) {
        this.windowClosedListener = listener;
        return this;
    }

    /**
     * 在玩家关闭窗口而没有点击按钮提交表单后调用. <br>
     * Called on closed without submitting.
     *
     * @param listener 调用的方法
     */
    public final ResponsibleFormWindowTemplated<K> onClosed(@NotNull Runnable listener) {
        Objects.requireNonNull(listener);
        this.windowClosedListener = (player) -> listener.run();
        return this;
    }

    /**
     * 不会重置 {@linkplain #lastResponses 最后的回复数据}
     *
     * @param response response
     * @param player   player
     */
    @SuppressWarnings("unchecked")
    public void callClicked(@NotNull TemplateResponses<K> response, @NotNull Player player) {
        Objects.requireNonNull(player);
        Objects.requireNonNull(response);

        this.onResponded(response, player);

        if (this.buttonClickedListener != null) {
            this.buttonClickedListener.accept(response, player);
        }
    }

    /**
     * 将表单重新发送给玩家, 并保留已经填写的格式正确的数据.
     */
    public void resendWindow(@NotNull Player player) {
        this.resendWindow(player, null);
    }

    /**
     * 将表单重新发送给玩家, 并保留已经填写的格式正确的数据.
     *
     * @param doNotKeepValues keys that will not be filled
     */
    public void resendWindow(@NotNull Player player, @Nullable K[] doNotKeepValues) {
        Objects.requireNonNull(player);
        if (this.lastResponses == null) {
            throw new UnsupportedOperationException();
        }
        this.applyLastResponse(doNotKeepValues);
        player.showFormWindow(this);
    }


    public void callClosed(@NotNull Player player) {
        Objects.requireNonNull(player);

        this.onClosed(player);

        if (this.windowClosedListener != null) {
            this.windowClosedListener.accept(player);
        }
    }

    @Override
    public String getJSONData() {
        String toModify = new Gson().toJson(this, FormWindowCustom.class);//must!!
        //We need to replace this due to Java not supporting declaring class field 'default'
        return toModify.replace("defaultOptionIndex", "default")
                .replace("defaultText", "default")
                .replace("defaultValue", "default")
                .replace("defaultStepIndex", "default");
    }

    @SuppressWarnings("unchecked")
    static boolean onEvent(PlayerFormRespondedEvent event) {
        if (event.getWindow() instanceof ResponsibleFormWindowTemplated) {
            ResponsibleFormWindowTemplated window = (ResponsibleFormWindowTemplated) event.getWindow();

            if (event.getWindow().wasClosed() || event.getResponse() == null) {
                window.callClosed(event.getPlayer());
                window.closed = false;//for resending
            } else {
                window.setLastResponses(event.getPlayer(), (FormResponseCustom) event.getResponse());
                window.callClicked(window.getLastResponses(), event.getPlayer());
            }
            return true;
        }
        return false;
    }
}
