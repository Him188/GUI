package moe.him188.gui.utils;

/**
 * @author Him188moe @ GUI Project
 */
public class InputTypeDouble extends InputType<Double> {
    @Override
    public Double parseResponse(String content) throws InputFormatException {
        try {
            return Double.parseDouble(content);
        } catch (NumberFormatException e) {
            throw new InputFormatException(InputFormatException.Reason.NUMBER_FORMAT, content, e);
        }
    }
}
