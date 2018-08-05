import moe.him188.gui.template.Template;
import moe.him188.gui.template.element.*;
import moe.him188.gui.utils.InputTypes;

import java.text.SimpleDateFormat;
import java.util.Arrays;

/**
 * @author Him188moe @ GUI Project
 */
public class TestTemplate extends Template<TestTemplate.ElementKey> {
    public enum ElementKey {
        INT_TEST,
        DATE_TEST,
        STRING_TEST,

        DROPDOWN,
        TOGGLE,
        SLIDER,
        STEP_SLIDER,
    }

    private static TestTemplate instance = new TestTemplate();

    public static TestTemplate getInstance() {
        return instance;
    }

    public TestTemplate() {
        setTitle("Test Template");

        addElement(new TemplateElementLabel<>("test label"));
        addElement(new TemplateElementInput<>(ElementKey.INT_TEST, "int test", InputTypes.INTEGER));
        addElement(new TemplateElementInput<>(ElementKey.DATE_TEST, "date test", InputTypes.DATE(new SimpleDateFormat("yyyy/MM/dd HH:mm"))));
        addElement(new TemplateElementInput<>(ElementKey.STRING_TEST, "string test", InputTypes.STRING));
        addElement(new TemplateElementDropdown<>(ElementKey.DROPDOWN, "dropdown", Arrays.asList("1", "2", "3")));
        addElement(new TemplateElementToggle<>(ElementKey.TOGGLE, "toggle", true));
        addElement(new TemplateElementSlider<>(ElementKey.SLIDER, "slider", 0, 100));
        addElement(new TemplateElementStepSlider<>(ElementKey.STEP_SLIDER, "step slider", Arrays.asList("step1", "step2")));
    }
}