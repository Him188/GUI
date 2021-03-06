package moe.him188.gui.utils;

import cn.nukkit.Player;
import cn.nukkit.level.Level;

import java.text.DateFormat;
import java.util.Date;

/**
 * 默认的输入类型
 *
 * @author Him188moe @ GUI Project
 */
public final class InputTypes {
    /**
     * @since 1.7
     */
    public static final InputType<Long> LONG = new InputTypeLong();
    public static final InputType<Integer> INTEGER = new InputTypeInteger();
    public static final InputType<Double> DOUBLE = new InputTypeDouble();
    /**
     * @since 1.7
     */
    public static final InputType<Float> FLOAT = new InputTypeFloat();
    /**
     * @since 1.7
     */
    public static final InputType<String> USERNAME = new InputTypeUsername();
    public static final InputType<String> STRING = new InputTypeString();
    public static final InputType<Boolean> BOOLEAN = new InputTypeBoolean();

    public static final InputType<Player> PLAYER = new InputTypePlayer();
    public static final InputType<Level> LEVEL = new InputTypeLevel();

    public static InputType<Date> DATE(DateFormat format) {
        return new InputTypeDate(format);
    }
}
