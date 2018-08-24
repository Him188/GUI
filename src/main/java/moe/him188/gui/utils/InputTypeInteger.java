package moe.him188.gui.utils;

import org.jetbrains.annotations.NotNull;

/**
 * @author Him188moe @ GUI Project
 */
public class InputTypeInteger extends InputType<Integer> {
    @NotNull
    @Override
    public Integer parseResponse(String content) throws InputFormatException {
        try {
            return (int) Long.parseLong(content);
        } catch (NumberFormatException e) {
            throw new InputFormatException(InputFormatException.ReasonDefaults.NUMBER_FORMAT, content, e);
        }
    }
}
