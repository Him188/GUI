package moe.him188.gui.utils;

import moe.him188.gui.template.element.TemplateElementInput;

/**
 * 在表单元素 {@link TemplateElementInput} 中可能会出现的异常
 *
 * @author Him188moe @ GUI Project
 * @see InputType
 */
public class InputFormatException extends RuntimeException {
    private final Reason reason;
    private final String input;

    public enum Reason {
        NUMBER_FORMAT,
        PLAYER_NOT_FOUND,
        LEVEL_NOT_FOUND,
        DATE_FORMAT
    }

    public InputFormatException(Reason reason, String originalInput) {
        super();
        this.reason = reason;
        input = originalInput;
    }

    public InputFormatException(Reason reason, String originalInput, Throwable throwable) {
        super(throwable);
        this.input = originalInput;
        this.reason = reason;
    }

    public String getOriginalInput() {
        return input;
    }

    public Reason getReason() {
        return reason;
    }
}
