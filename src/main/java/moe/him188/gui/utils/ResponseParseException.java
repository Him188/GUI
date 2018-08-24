package moe.him188.gui.utils;

import moe.him188.gui.template.element.TemplateElement;
import moe.him188.gui.template.element.TemplateElementDropdown;

/**
 * @author Him188moe @ GUI Project
 */
public class ResponseParseException extends Exception {
    private final TemplateElement<?> element; // TODO: 2018/8/2 0002 改进, 变得容易获取是哪一个项目异常

    public ResponseParseException(TemplateElement<?> element, Throwable cause) {
        super(cause);
        this.element = element;
    }

    public TemplateElement<?> getElement() {
        return element;
    }

    /**
     * 目前可能有以下异常
     * <ul>
     * <li>{@link InputFormatException]}</li>
     * </ul>
     * 意外时(在 {@link TemplateElementDropdown} 中转换选中 ID)还可能出现
     * <ul>
     * <li>{@link NumberFormatException]}</li>
     * </ul>
     *
     * @return Notnull
     *
     * @see InputFormatException
     */
    @Override
    public synchronized Throwable getCause() {
        return super.getCause();
    }
}
