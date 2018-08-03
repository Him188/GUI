package moe.him188.gui.utils;

import moe.him188.gui.template.element.TemplateElementInput;

/**
 * 在表单元素 {@link TemplateElementInput} 中可能会出现的异常
 *
 * @author Him188moe @ GUI Project
 * @see InputType
 */
public class KeyAlreadyContainsException extends RuntimeException {
    public KeyAlreadyContainsException() {
        super();
    }

    public KeyAlreadyContainsException(String message) {
        super(message);
    }

    public KeyAlreadyContainsException(String message, Throwable cause) {
        super(message, cause);
    }

    public KeyAlreadyContainsException(Throwable cause) {
        super(cause);
    }
}
