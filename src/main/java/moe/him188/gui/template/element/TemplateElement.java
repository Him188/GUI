package moe.him188.gui.template.element;

import cn.nukkit.form.element.Element;
import moe.him188.gui.template.response.TemplateResponse;
import moe.him188.gui.utils.ResponseParseException;
import org.jetbrains.annotations.Nullable;

/**
 * 模板的元素
 *
 * @author Him188moe @ GUI Project
 */
public abstract class TemplateElement<K> {
    /**
     * 用于模板中通过这个key获取这个element的返回值
     */
    private final K responseKey;

    /**
     * <code>@Nullable</code> for {@link TemplateElementLabel}
     *
     * @param responseKey key
     */
    public TemplateElement(@Nullable K responseKey) {
        this.responseKey = responseKey;
    }

    public K getResponseKey() {
        return responseKey;
    }

    /**
     * 用于识别 Response
     */
    private int id;

    public int getId() {
        return id;
    }

    public TemplateElement<K> setId(int id) {
        this.id = id;
        return this;
    }

    public abstract String getName();

    /**
     * Build nukkit element
     */
    public abstract Element build();

    /**
     * Parse original response data to Response object.
     *
     * @param response original response
     *
     * @return Response object.
     */
    public abstract TemplateResponse parseResponse(Object response) throws ResponseParseException;
}
