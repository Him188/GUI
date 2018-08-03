package moe.him188.gui.template.response;

import cn.nukkit.form.response.FormResponse;

/**
 * @author Him188moe @ GUI Project
 */
public abstract class TemplateResponse<R> extends FormResponse {
    protected final R response;

    protected TemplateResponse(R response) {
        this.response = response;
    }

    public R get() {
        return response;
    }
}
