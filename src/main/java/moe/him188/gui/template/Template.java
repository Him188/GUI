package moe.him188.gui.template;

import cn.nukkit.Player;
import cn.nukkit.form.response.FormResponseCustom;
import cn.nukkit.form.window.FormWindowCustom;
import moe.him188.gui.template.element.TemplateElement;
import moe.him188.gui.template.response.TemplateResponses;
import moe.him188.gui.utils.ExceptionConsumer;
import moe.him188.gui.utils.ExceptionConsumerIgnore;
import moe.him188.gui.utils.KeyAlreadyContainsException;
import moe.him188.gui.utils.ResponseParseException;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @param <K> 元素的 key 的类型
 *
 * @author Him188 @ GUI Project
 */
public class Template<K> {
    protected String title = null;
    protected String icon = null;

    protected Map<Integer, TemplateElement<K>> elements = new LinkedHashMap<>();

    protected Builder builder = new Builder();

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean hasTitle() {
        return this.title != null;
    }

    public String getTitle() {
        return this.title;
    }

    public String getIcon() {
        return this.icon;
    }

    public boolean hasIcon() {
        return this.icon != null;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public TemplateElement<K> getElement(K key) {
        if (key == null) {
            throw new UnsupportedOperationException("can't get elements with null key");
        }
        for (TemplateElement<K> value : this.elements.values()) {
            if (value.getResponseKey() == key) {
                return value;
            }
        }

        return null;
    }

    public Map<Integer, TemplateElement<K>> getElements() {
        return this.elements;
    }

    public void addElement(TemplateElement<K> element) throws KeyAlreadyContainsException {
        Objects.requireNonNull(element);
        checkElementKeyContains(element.getResponseKey());
        this.elements.put(this.elements.size(), element.setId(this.elements.size()));
    }

    private void checkElementKeyContains(K key) throws KeyAlreadyContainsException {
        if (key == null) {
            return;
        }
        for (TemplateElement<K> value : elements.values()) {
            if (value.getResponseKey() == key) {
                throw new KeyAlreadyContainsException(key.toString());
            }
        }
    }

    public Builder getBuilder() {
        return builder;
    }

    public class Builder {
        public void applyTemplateTo(FormWindowCustom window) {
            if (hasTitle()) {
                window.setTitle(getTitle());
            }
            if (hasIcon()) {
                window.setIcon(getIcon());
            }
            for (TemplateElement element : getElements().values()) {
                window.addElement(element.build());
            }
        }

        public TemplateResponses<K> parseTemplateResponse(Player player, FormResponseCustom form, ExceptionConsumer<ResponseParseException> exceptionConsumer) {
            Objects.requireNonNull(player);
            Objects.requireNonNull(form);
            if (exceptionConsumer == null) {
                exceptionConsumer = new ExceptionConsumerIgnore<>();
            }
            TemplateResponses<K> responses = new TemplateResponses<>();
            for (Map.Entry<Integer, Object> entry : form.getResponses().entrySet()) {
                if (!getElements().containsKey(entry.getKey())) {//最后一项为null?
                    break;
                }
                TemplateElement<K> element = getElements().get(entry.getKey());
                try {
                    responses.put(element.getResponseKey(), element.parseResponse(entry.getValue()));
                } catch (ResponseParseException e) {
                    responses.put(element.getResponseKey(), null);
                    exceptionConsumer.catchException(player, e);
                }
            }
            exceptionConsumer.onFinished(player);
            return responses;
        }
    }
}