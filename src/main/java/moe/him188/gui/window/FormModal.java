package moe.him188.gui.window;

/**
 * Alias of the Responsible one
 *
 * @author Him188moe @ GUI project
 */
public class FormModal extends ResponsibleFormWindowModal {
    public FormModal(String trueButtonText, String falseButtonText) {
        super(trueButtonText, falseButtonText);
    }

    public FormModal(String content, String trueButtonText, String falseButtonText) {
        super(content, trueButtonText, falseButtonText);
    }

    public FormModal(String title, String content, String trueButtonText, String falseButtonText) {
        super(title, content, trueButtonText, falseButtonText);
    }
}
