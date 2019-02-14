package moe.him188.gui.window;

import cn.nukkit.Player;
import cn.nukkit.event.player.PlayerFormRespondedEvent;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.response.FormResponseSimple;
import cn.nukkit.form.window.FormWindow;
import cn.nukkit.form.window.FormWindowSimple;
import com.google.gson.Gson;
import moe.him188.gui.utils.Backable;
import moe.him188.gui.utils.NoParentWindowFoundException;
import moe.him188.gui.window.listener.response.ResponseListenerAdvanced;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * 类似于 {@link ResponsibleFormWindowSimpleAdvanced}.
 *
 * @author Him188moe @ GUI Project
 * @since 1.8
 */
public class ResponsibleFormWindowSimpleMap<K, V> extends FormWindowSimple implements Backable, ResponseListenerAdvanced<V> {
    protected transient BiConsumer<V, Player> buttonClickedListener = null;

    protected transient Consumer<Player> windowClosedListener = null;

    protected transient final Map<K, V> entries;

    protected transient final List<V> values;

    private transient FormWindow parent;

    /**
     * @param title            标题
     * @param content          内容
     * @param entries          需要展示在每个按钮上的数据 | entries to show in the buttons
     * @param buttonTextGetter 按钮名字获取器. 用于获取每个数据对应的按钮的名字. (通过 map 的 key) | Used to get the name of each button (By map key)
     */
    public ResponsibleFormWindowSimpleMap(String title, String content, @NotNull Map<K, V> entries, @NotNull Function<K, String> buttonTextGetter) {
        super(Objects.requireNonNull(title), Objects.requireNonNull(content));
        Objects.requireNonNull(buttonTextGetter);
        Objects.requireNonNull(entries);

        for (K entry : entries.keySet()) {
            this.addButton(new ElementButton(buttonTextGetter.apply(entry)));
        }
        this.values = Collections.unmodifiableList(new ArrayList<>(entries.values()));

        this.entries = Collections.unmodifiableMap(entries);
    }

    /**
     * @param content          内容
     * @param entries          需要展示在每个按钮上的数据 | entries to show in the buttons
     * @param buttonTextGetter 按钮名字获取器. 用于获取每个数据对应的按钮的名字. (通过 map 的 key) | Used to get the name of each button (By map key)
     */
    public ResponsibleFormWindowSimpleMap(String content, @NotNull Map<K, V> entries, @NotNull Function<K, String> buttonTextGetter) {
        this("", content, entries, buttonTextGetter);
    }

    /**
     * @param entries          需要展示在每个按钮上的数据 | entries to show in the buttons
     * @param buttonTextGetter 按钮名字获取器. 用于获取每个数据对应的按钮的名字. (通过 map 的 key) | Used to get the name of each button (By map key)
     */
    public ResponsibleFormWindowSimpleMap(@NotNull Map<K, V> entries, @NotNull Function<K, String> buttonTextGetter) {
        this("", "", entries, buttonTextGetter);
    }

    public Map<K, V> getEntries() {
        return entries;
    }

    public V getEntryValue(int id) {
        return this.values.get(id);
    }

    @Override
    public void setParent(FormWindow parent) throws NoParentWindowFoundException {
        this.parent = parent;
    }

    @Override
    public FormWindow getParent() {
        return parent;
    }

    /**
     * 在玩家提交表单后调用 <br>
     * Called on submitted
     *
     * @param listener 调用的方法
     */
    public final ResponsibleFormWindowSimpleMap<K, V> onClicked(@NotNull BiConsumer<V, Player> listener) {
        Objects.requireNonNull(listener);
        this.buttonClickedListener = listener;
        return this;
    }

    /**
     * 在玩家提交表单后调用 <br>
     * Called on submitted
     *
     * @param listener 调用的方法(无 Player)
     */
    public final ResponsibleFormWindowSimpleMap<K, V> onClicked(@NotNull Consumer<V> listener) {
        Objects.requireNonNull(listener);
        this.buttonClickedListener = (response, player) -> listener.accept(response);
        return this;
    }

    /**
     * 在玩家关闭窗口而没有点击按钮提交表单后调用. <br>
     * Called on closed without submitting.
     *
     * @param listener 调用的方法
     */
    public final ResponsibleFormWindowSimpleMap<K, V> onClosed(@NotNull Consumer<Player> listener) {
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
    public final ResponsibleFormWindowSimpleMap<K, V> onClosed(@NotNull Runnable listener) {
        Objects.requireNonNull(listener);
        this.windowClosedListener = (player) -> listener.run();
        return this;
    }

    @Override
    public void addButton(@NotNull ElementButton button) {
        Objects.requireNonNull(button);
        if (this.entries != null) {
            throw new UnsupportedOperationException("could not addButton after construction!");
        }
        super.addButton(button);
    }

    @SuppressWarnings("unchecked")
    public void callClicked(@NotNull V entry, @NotNull Player player) {
        Objects.requireNonNull(player);
        Objects.requireNonNull(entry);

        this.onClicked(entry, player);

        if (this.buttonClickedListener != null) {
            this.buttonClickedListener.accept(entry, player);
        }
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
        return new Gson().toJson(this, FormWindowSimple.class); //必须以无泛型类转换 json, 否则 StackOverFollow
    }

    @SuppressWarnings("unchecked")
    static boolean onEvent(PlayerFormRespondedEvent event) {
        if (event.getWindow() instanceof ResponsibleFormWindowSimpleMap) {
            ResponsibleFormWindowSimpleMap window = (ResponsibleFormWindowSimpleMap) event.getWindow();

            if (event.getWindow().wasClosed() || event.getResponse() == null) {
                window.callClosed(event.getPlayer());
                window.closed = false;//for resending
            } else {
                window.callClicked(window.getEntryValue(((FormResponseSimple) event.getResponse()).getClickedButtonId()), event.getPlayer());
            }

            return true;
        }
        return false;
    }

}
