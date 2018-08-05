# Template Example

First, create a [`Template`](src/main/java/moe/him188/gui/template/Template.java).  
[`TestTemplate.java`](src/test/java/TestTemplate.java):
```java
class TestTemplate extends Template<TestTemplate.ElementKey> {
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
        addElement(new TemplateElementInput<>(ElementKey.INT_TEST, InputTypes.INTEGER, "int test"));
        addElement(new TemplateElementInput<>(ElementKey.DATE_TEST, InputTypes.DATE(new SimpleDateFormat("yyyy/MM/dd HH:mm")), "date test"));
        addElement(new TemplateElementInput<>(ElementKey.STRING_TEST, InputTypes.STRING, "string test"));
        addElement(new TemplateElementDropdown<>(ElementKey.DROPDOWN, "dropdown", Arrays.asList("1", "2", "3")));
        addElement(new TemplateElementToggle<>(ElementKey.TOGGLE, "toggle", true));
        addElement(new TemplateElementSlider<>(ElementKey.SLIDER, "slider", 0, 100));
        addElement(new TemplateElementStepSlider<>(ElementKey.STEP_SLIDER, "step slider", Arrays.asList("step1", "step2")));
    }
}
```

Second, create a [`ResponsibleFormWindowTemplated`](src/main/java/moe/him188/gui/window/ResponsibleFormWindowTemplated.java)  
[`TestWindow.java`](src/test/java/TestWindow.java)

```java
class TestWindow extends ResponsibleFormWindowTemplated<TestTemplate.ElementKey> implements ResponseListenerTemplate<TestTemplate.ElementKey> {
    public TestWindow(Template<TestTemplate.ElementKey> template) {
        super(template);
        setExceptionConsumer(new ExceptionConsumerAll<ResponseParseException>() {
            @Override
            public void accept(Player player, ResponseParseException[] exception) {
                StringBuilder message = new StringBuilder();
                for (ResponseParseException parseException : exception) {
                    if (parseException.getCause() instanceof InputFormatException) {
                        TemplateElementInput<?, ?> input = (TemplateElementInput<?, ?>) parseException.getElement();
                        message.append("Your input ").append(input.getName()).append(" is bad. Pay attention to format: \n    ").append(input.getPlaceholder());
                        message.append("\n");
                    }
                }
                player.showFormWindow(new TipWindow(message.toString()).onClicked(() -> resendWindow(player)));
            }
        });
    }
    @Override
    public void onResponded(TemplateResponses<TestTemplate.ElementKey> responses, Player player) {
        if (responses.hasNull()) {
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
```

Pay attention to `responses.getInputInteger(TestTemplate.ElementKey.INT_TEST)`, that shows you can get response in your own key instead of int id.  
Pay attention to `resendWindow(player)` that shows you can fast resend, and it will keep what player has already typed correctly.
