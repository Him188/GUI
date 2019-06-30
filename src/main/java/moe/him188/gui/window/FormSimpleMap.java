package moe.him188.gui.window;

import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.function.Function;

/**
 * Alias of the Responsible one
 *
 * @author Him188moe @ GUI project
 */
public class FormSimpleMap<K, V> extends ResponsibleFormWindowSimpleMap<K, V> {
    public FormSimpleMap(String title, String content, @NotNull Map<K, V> entries, @NotNull Function<K, String> buttonTextGetter) {
        super(title, content, entries, buttonTextGetter);
    }

    public FormSimpleMap(String content, @NotNull Map<K, V> entries, @NotNull Function<K, String> buttonTextGetter) {
        super(content, entries, buttonTextGetter);
    }

    public FormSimpleMap(@NotNull Map<K, V> entries, @NotNull Function<K, String> buttonTextGetter) {
        super(entries, buttonTextGetter);
    }
}
