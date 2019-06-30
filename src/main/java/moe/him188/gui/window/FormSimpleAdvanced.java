package moe.him188.gui.window;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.function.Function;

/**
 * Alias of the Responsible one
 *
 * @author Him188moe @ GUI project
 */
public class FormSimpleAdvanced<K> extends ResponsibleFormWindowSimpleAdvanced<K> {

    public FormSimpleAdvanced(String title, String content, @NotNull Collection<K> entries, @NotNull Function<K, String> buttonTextGetter) {
        super(title, content, entries, buttonTextGetter);
    }

    public FormSimpleAdvanced(String content, @NotNull Collection<K> entries, @NotNull Function<K, String> buttonTextGetter) {
        super(content, entries, buttonTextGetter);
    }

    public FormSimpleAdvanced(@NotNull Collection<K> entries, @NotNull Function<K, String> buttonTextGetter) {
        super(entries, buttonTextGetter);
    }
}
