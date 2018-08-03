package moe.him188.gui.template.element;

import cn.nukkit.form.element.Element;
import moe.him188.gui.template.response.TemplateResponse;
import moe.him188.gui.utils.ResponseParseException;

/**
 * 模板的元素
 *
 * @author Him188moe @ GUI Project
 */
public abstract class TemplateElement<K> { // TODO: 2018/8/1 0001 添加所有 element
    /**
     * 用于模板中通过这个key获取这个element的返回值
     */
    private final K responseKey;

    public TemplateElement(K responseKey) {
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
