package moe.him188.gui.window;

import cn.nukkit.form.element.Element;
import cn.nukkit.form.window.FormWindowCustom;
import cn.nukkit.form.window.FormWindowSimple;
import com.google.gson.Gson;

import java.util.List;

/**
 * 拥有 ID 标记的 {@link FormWindowSimple}. <br/>
 * 用于 {@link ResponsibleFormWindowSimpleAdvanced} 的识别. <br/>
 * <br/>
 * Windows that was numbered by {@link #id}. <br/>
 * Used for the identify of {@link ResponsibleFormWindowSimpleAdvanced}
 *
 * @author Him188moe @ ProjectARK Project
 */
class MarkedFormWindowCustom extends FormWindowCustom {
    private static int id_internal = 0;

    private final int id;

    MarkedFormWindowCustom(String title, List<Element> contents, String icon) {
        super(title, contents, icon);
        this.id = id_internal++;
    }

    public int getId() {
        return id;
    }

    @Override
    public String getJSONData() {
        return new Gson().toJson(this, MarkedFormWindowCustom.class); //必须以无泛型类转换 json, 否则 StackOverFollow
    }
}
