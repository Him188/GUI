package moe.him188.gui.utils;

import org.jetbrains.annotations.NotNull;

/**
 * @author Him188moe @ GUI Project
 */
public class InputTypeLong extends InputType<Long> {
    @NotNull
    @Override
    public Long parseResponse(String content) throws InputFormatException {
        try {
            return Long.parseLong(content);
        } catch (NumberFormatException e) {
            throw new InputFormatException(InputFormatException.ReasonDefaults.NUMBER_FORMAT, content, e);
        }
    }
}
