package net.iclassmate.teacherspace.bean.spaper;

import net.iclassmate.teacherspace.bean.Parserable;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by xydbj on 2016/4/8.
 */
public class ExamPaperUrls implements Serializable, Parserable {
    private String ecPage;
    private String prefixUrl;
    private String pageUrl;

    @Override
    public void parserJson(JSONObject jsonObject) {
        if (jsonObject != null) {
            try {
                ecPage = jsonObject.getString("ecPage");
                prefixUrl = jsonObject.optString("prefixUrl");
                pageUrl = jsonObject.getString("pageUrl");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public String getEcPage() {
        return ecPage;
    }

    public void setEcPage(String ecPage) {
        this.ecPage = ecPage;
    }

    public String getPrefixUrl() {
        return prefixUrl;
    }

    public void setPrefixUrl(String prefixUrl) {
        this.prefixUrl = prefixUrl;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }
}
