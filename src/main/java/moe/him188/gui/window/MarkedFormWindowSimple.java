package moe.him188.gui.window;

import cn.nukkit.form.window.FormWindowSimple;
import com.google.gson.Gson;

/**
 * 拥有 ID 标记的 {@link FormWindowSimple}. <br/>
 * 用于 {@link ResponsibleFormWindowSimpleAdvanced} 的识别. <br/>
 * <br/>
 * Windows that was numbered by {@link #id}. <br/>
 * Used for the identify of {@link ResponsibleFormWindowSimpleAdvanced}
 *
 * @author Him188moe @ GUI Project
 */
class MarkedFormWindowSimple extends FormWindowSimple {
    private static int id_internal = 0;

    private final int id;

    MarkedFormWindowSimple(String title, String content) {
        super(title, content);
        this.id = id_internal++;
    }

    public int getId() {
        return id;
    }

    @Override
    public String getJSONData() {
        return new Gson().toJson(this, MarkedFormWindowSimple.class); //必须以无泛型类转换 json, 否则 StackOverFollow
    }
}
