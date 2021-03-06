package moe.him188.gui.template.response;

import cn.nukkit.Player;
import cn.nukkit.form.element.*;
import cn.nukkit.form.window.FormWindowCustom;
import moe.him188.gui.template.element.*;
import moe.him188.gui.utils.*;
import org.jetbrains.annotations.Nullable;

import java.util.*;

/**
 * @param <K> 用于取出 response 的 key
 *
 * @author Him188moe @ GUI Project
 */
public final class TemplateResponses<K> extends LinkedHashMap<K, TemplateResponse<?>> {
    private final Builder builder = new Builder();

    public Builder getBuilder() {
        return builder;
    }

    /**
     * 判断是否存在 <code>null</code> 数据. <br>
     * 不存在即代表所有数据均格式正确.
     *
     * @return true
     */
    public boolean hasNull() {
        for (TemplateResponse<?> value : this.values()) {
            if (value == null) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取 Input 内容. 注意请勿尝试将 String 类型转 int 类型, 即使输入的是纯数字. <br>
     * 不推荐使用这个方法, 因为它不具有安全性, 且容易失误. 请使用 <code>getInputXXX</code> 代替.
     *
     * @param key       key
     * @param inputType 用于转换类型. 必须是 {@link InputType} 的泛型或其子类
     * @param <R>       Input 的数据类型
     *
     * @return Input 内容
     *
     * @see InputType
     * @see TemplateElementInput
     */
    public <R> R getInput(K key, Class<R> inputType) {
        return getInputResponse(key, inputType).get();
    }

    /**
     * 获取 {@link InputTypeString} 类型 Input 的内容
     *
     * @param key key
     *
     * @return Input 内容
     *
     * @see InputTypeString
     * @see TemplateElementInput
     */
    public String getInputString(K key) {
        return (String) getInputResponse(key).get();
    }

    /**
     * 获取 {@link InputTypeDate} 类型 Input 的内容
     *
     * @param key key
     *
     * @return Input 内容
     *
     * @see InputTypeDate
     * @see TemplateElementInput
     */
    public Date getInputDate(K key) {
        return (Date) getInputResponse(key).get();
    }

    /**
     * 获取 {@link InputTypeInteger} 类型 Input 的内容
     *
     * @param key key
     *
     * @return Input 内容
     *
     * @see InputTypeInteger
     * @see TemplateElementInput
     */
    public int getInputInteger(K key) {
        return (int) getInputResponse(key).get();
    }

    /**
     * 获取 {@link InputTypeLong} 类型 Input 的内容
     *
     * @param key key
     *
     * @return Input 内容
     *
     * @see InputTypeLong
     * @see TemplateElementInput
     * @since 1.10
     */
    public long getInputLong(K key) {
        return (long) getInputResponse(key).get();
    }

    /**
     * 获取 {@link InputTypePlayer} 类型 Input 的内容
     *
     * @param key key
     *
     * @return Input 内容
     *
     * @see InputTypePlayer
     * @see TemplateElementInput
     * @since 1.11
     */
    public Player getInputPlayer(K key) {
        return (Player) getInputResponse(key).get();
    }

    /**
     * 获取 {@link InputTypeBoolean} 类型 Input 的内容
     *
     * @param key key
     *
     * @return Input 内容
     *
     * @see InputTypeBoolean
     * @see TemplateElementInput
     * @since 1.11
     */
    public boolean getInputBoolean(K key) {
        return (boolean) getInputResponse(key).get();
    }

    /**
     * 获取 {@link InputTypeDouble} 类型 Input 的内容
     *
     * @param key key
     *
     * @return Input 内容
     *
     * @see InputTypeDouble
     * @see TemplateElementInput
     * @since 1.10
     */
    public double getInputDouble(K key) {
        return (double) getInputResponse(key).get();
    }

    /**
     * 获取 {@link InputTypeFloat} 类型 Input 的内容
     *
     * @param key key
     *
     * @return Input 内容
     *
     * @see InputTypeFloat
     * @see TemplateElementInput
     * @since 1.10
     */
    public float getInputFloat(K key) {
        return (float) getInputResponse(key).get();
    }

    /**
     * 获取选中的项目 id. <br>
     * id 从 0 开始.
     *
     * @param key key
     *
     * @return 选中的项目 id
     *
     * @see TemplateElementDropdown
     */
    public int getDropdown(K key) {
        return getDropdownResponse(key).get();
    }

    /**
     * 获取选中的项目内容. <br>
     *
     * @param key key
     *
     * @return 选中的项目内容
     *
     * @see TemplateElementDropdown
     */
    public String getDropdownOption(K key) {
        return getDropdownResponse(key).getOption();
    }

    /**
     * 获取开关的状态
     *
     * @param key key
     *
     * @return 开启/关闭
     *
     * @see TemplateElementToggle
     */
    public boolean getToggle(K key) {
        return getToggleResponse(key).get();
    }

    /**
     * 获取滑块的位置
     *
     * @param key key
     *
     * @return 滑块停留的项目 ID
     *
     * @see TemplateElementStepSlider
     */
    public int getStepSlider(K key) {
        return getStepSliderResponse(key).get();
    }

    /**
     * 获取滑块位置对应的项目
     *
     * @param key key
     *
     * @return 滑块停留的项目
     *
     * @see TemplateElementStepSlider
     */
    public String getStepSliderValue(K key) {
        return getStepSliderResponse(key).getStep();
    }

    /**
     * 获取滑块的位置
     *
     * @param key key
     *
     * @return 滑块位置
     *
     * @see TemplateElementSlider
     */
    public float getSlider(K key) {
        return getSliderResponse(key).get();
    }

    @SuppressWarnings({"unchecked", "unused"})
    public <R> TemplateElementInput.Response<R> getInputResponse(K key, Class<R> inputType) {
        return (TemplateElementInput.Response<R>) get(key);
    }

    @SuppressWarnings("unchecked")
    private <R> TemplateElementInput.Response<R> getInputResponse(K key) {
        return (TemplateElementInput.Response<R>) get(key);
    }

    public TemplateElementDropdown.Response getDropdownResponse(K key) {
        return (TemplateElementDropdown.Response) get(key);
    }

    public TemplateElementToggle.Response getToggleResponse(K key) {
        return (TemplateElementToggle.Response) get(key);
    }

    public TemplateElementSlider.Response getSliderResponse(K key) {
        return (TemplateElementSlider.Response) get(key);
    }

    public TemplateElementStepSlider.Response getStepSliderResponse(K key) {
        return (TemplateElementStepSlider.Response) get(key);
    }

    public final class Builder {
        /**
         * 将返回数据内容填充到窗口中. <br>
         * 填充按照参数 <code>form</code> 元素顺序, 依次填入数据, 请确保类型相同!
         *
         * @param form            form
         * @param doNotKeepValues keys that will not be filled. These elements will be defaultValues
         */
        public void applyToWindow(FormWindowCustom form, @Nullable K[] doNotKeepValues) {
            final List<K> skip = doNotKeepValues == null ? new ArrayList<>() : Arrays.asList(doNotKeepValues);

            Iterator<K> keyIterator = keySet().iterator();
            for (Element element : form.getElements()) {
                if (element instanceof ElementLabel) {
                    continue;
                }

                //gets a key with non-null value.
                K key = null;
                while (key == null || get(key) == null) {
                    if (!keyIterator.hasNext()) {
                        throw new RuntimeException("unmatched form");
                    }
                    key = keyIterator.next();
                }

                if (skip.contains(key)) {
                    continue;
                }

                if (element instanceof ElementInput) {
                    ((ElementInput) element).setDefaultText(getInput(key, Object.class).toString());
                } else if (element instanceof ElementDropdown) {
                    ((ElementDropdown) element).setDefaultOptionIndex(getDropdown(key));
                } else if (element instanceof ElementSlider) {
                    ((ElementSlider) element).setDefaultValue(getSlider(key));
                } else if (element instanceof ElementStepSlider) {
                    ((ElementStepSlider) element).setDefaultOptionIndex(getStepSlider(key));
                } else if (element instanceof ElementToggle) {
                    ((ElementToggle) element).setDefaultValue(getToggle(key));
                }
            }
        }
    }
}
