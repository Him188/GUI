import cn.nukkit.Player;
import moe.him188.gui.template.Template;
import moe.him188.gui.template.element.TemplateElementInput;
import moe.him188.gui.template.response.TemplateResponses;
import moe.him188.gui.utils.ExceptionConsumerAll;
import moe.him188.gui.utils.InputFormatException;
import moe.him188.gui.utils.ResponseParseException;
import moe.him188.gui.window.ResponsibleFormWindowTemplated;
import moe.him188.gui.window.defaults.TipWindow;
import moe.him188.gui.window.listener.response.ResponseListenerTemplate;

/**
 * @author Him188moe @ GUI Project
 */
public class TestWindow extends ResponsibleFormWindowTemplated<TestTemplate.ElementKey> implements ResponseListenerTemplate<TestTemplate.ElementKey> {
    public TestWindow(Template<TestTemplate.ElementKey> template) {
        super(template);

        setExceptionConsumer(new ExceptionConsumerAll<ResponseParseException>() {
            @Override
            public void accept(Player player, ResponseParseException[] exception) {
                StringBuilder message = new StringBuilder();

                for (ResponseParseException parseException : exception) {
                    if (message.length() != 0) {
                        message.append("\n");
                    }

                    if (parseException.getCause() instanceof InputFormatException) {
                        TemplateElementInput<?, ?> input = (TemplateElementInput<?, ?>) parseException.getElement();
                        message.append("你输入的 ").append(input.getName()).append(" 有误. 请注意格式: \n    ").append(input.getPlaceholder());
                    }
                }

                player.showFormWindow(new TipWindow(message.toString()).onClicked(() -> resendWindow(player)));
            }
        });
    }

    @Override
    public void onResponded(TemplateResponses<TestTemplate.ElementKey> responses, Player player) {
        if (responses.hasNull()) {
            System.out.println("onResponded: null responses");
            return;
        }

        System.out.println(responses.getInputInteger(TestTemplate.ElementKey.INT_TEST));
        System.out.println(responses.getInputDate(TestTemplate.ElementKey.DATE_TEST));
        System.out.println(responses.getInputString(TestTemplate.ElementKey.STRING_TEST));
        System.out.println(responses.getDropdown(TestTemplate.ElementKey.DROPDOWN));
        System.out.println(responses.getToggle(TestTemplate.ElementKey.TOGGLE));
        System.out.println(responses.getSlider(TestTemplate.ElementKey.SLIDER));

        player.showFormWindow(new TipWindow("Success"));
    }
}