package moe.him188.gui.utils;

/**
 * @author Him188moe @ GUI Project
 */
public class InputTypeInteger extends InputType<Integer> {
    @Override
    public Integer parseResponse(String content) throws InputFormatException {
        try {
            return Integer.parseInt(content);
        } catch (NumberFormatException e) {
            throw new InputFormatException(InputFormatException.Reason.NUMBER_FORMAT, content, e);
        }
    }
}
