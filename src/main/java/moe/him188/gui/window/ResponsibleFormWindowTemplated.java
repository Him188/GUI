package moe.him188.gui.window;

import cn.nukkit.Player;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerFormRespondedEvent;
import cn.nukkit.form.response.FormResponse;
import cn.nukkit.form.response.FormResponseCustom;
import cn.nukkit.form.window.FormWindowCustom;
import com.google.gson.Gson;
import moe.him188.gui.template.Template;
import moe.him188.gui.template.response.TemplateResponses;
import moe.him188.gui.utils.ExceptionConsumer;
import moe.him188.gui.utils.ResponseParseException;
import moe.him188.gui.window.listener.response.ResponseListenerTemplate;

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
public class ResponsibleFormWindowTemplated<K> extends FormWindowCustom {
    private transient final Template<K> template;

    private transient BiConsumer<TemplateResponses<K>, Player> buttonClickedListener = null;

    private transient Consumer<Player> windowClosedListener = null;

    private transient TemplateResponses<K> lastResponses = null;

    private transient Player lastPlayer = null;

    private transient ExceptionConsumer<ResponseParseException> exceptionConsumer;

    public ResponsibleFormWindowTemplated(Template<K> template) {
        this("", template);
    }

    public ResponsibleFormWindowTemplated(String title, Template<K> template) {
        super(Objects.requireNonNull(title), new ArrayList<>(), "");
        Objects.requireNonNull(template);
        this.template = template;
        template.getBuilder().applyTemplateTo(this);
    }

    /**
     * Nullable
     *
     * @return last response, nullable.
     */
    public TemplateResponses<K> getLastResponses() {
        return lastResponses;
    }

    /**
     * Get last player who responded this form.
     *
     * @return last player, nullable
     */
    public Player getLastPlayer() {
        return lastPlayer;
    }

    public void setLastResponses(Player player, FormResponseCustom response) {
        this.lastPlayer = player;
        this.lastResponses = this.template.getBuilder().parseTemplateResponse(player, response, this.exceptionConsumer);
    }

    /**
     * 当玩家提交表单后, 输入了错误的格式时会抛出异常
     *
     * @param exceptionConsumer 处理异常的函数
     */
    public ResponsibleFormWindowTemplated<K> setExceptionConsumer(ExceptionConsumer<ResponseParseException> exceptionConsumer) {
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
    public ResponsibleFormWindowTemplated<K> onResponded(BiConsumer<TemplateResponses<K>, Player> listener) {
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
    public ResponsibleFormWindowTemplated<K> onResponded(Consumer<TemplateResponses<K>> listener) {
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
    public ResponsibleFormWindowTemplated<K> onResponded(Runnable listener) {
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
    public ResponsibleFormWindowTemplated<K> onClosed(Consumer<Player> listener) {
        this.windowClosedListener = listener;
        return this;
    }

    /**
     * 在玩家关闭窗口而没有点击按钮提交表单后调用. <br>
     * Called on closed without submitting.
     *
     * @param listener 调用的方法
     */
    public ResponsibleFormWindowTemplated<K> onClosed(Runnable listener) {
        Objects.requireNonNull(listener);
        this.windowClosedListener = (player) -> listener.run();
        return this;
    }

    public void callClicked(TemplateResponses<K> response, Player player) {
        Objects.requireNonNull(player);
        if (this.buttonClickedListener != null) {
            this.buttonClickedListener.accept(response, player);
        }
    }

    /**
     * 将表单重新发送给玩家, 并保留已经填写的格式正确的数据.
     */
    public void resendWindow(Player player) {
        if (this.lastResponses == null) {
            throw new UnsupportedOperationException();
        }
        this.lastResponses.getBuilder().applyToWindow(this);
        player.showFormWindow(this);
    }

    public void callClosed(Player player) {
        Objects.requireNonNull(player);
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
        if (event.getWindow() instanceof ResponsibleFormWindowTemplated && event.getResponse() instanceof FormResponseCustom) {
            ResponsibleFormWindowTemplated window = (ResponsibleFormWindowTemplated) event.getWindow();

            if (event.getWindow().wasClosed() || event.getResponse() == null) {
                window.callClosed(event.getPlayer());
            } else {
                window.setLastResponses(event.getPlayer(), (FormResponseCustom) event.getResponse());
                if (window instanceof ResponseListenerTemplate) {
                    ((ResponseListenerTemplate) window).onResponded(window.getLastResponses(), event.getPlayer());
                }
                window.callClicked(window.getLastResponses(), event.getPlayer());
            }
            return true;
        }
        return false;
    }
}
