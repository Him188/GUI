package moe.him188.gui.utils;

import org.jetbrains.annotations.NotNull;

/**
 * 输入数据类型.
 * 用于检查类型格式是否正确
 *
 * @author Him188moe @ GUI Project
 * @see InputTypes
 */
public abstract class InputType<R> {

    /**
     * 检查内容是否符合规范
     */
    @NotNull
    public abstract R parseResponse(String content) throws InputFormatException;
}
