package net.iclassmate.teacherspace.bean;

import org.json.JSONObject;

/**
 * Created by xydbj on 2016/1/29.
 */
public class ExamContent extends BaseExam implements Parserable{
    private String urlLeft;
    private String content;
    private String urlRight;
    @Override
    public void parserJson(JSONObject jsonObject) {

    }

    public String getUrlLeft() {
        return urlLeft;
    }

    public void setUrlLeft(String urlLeft) {
        this.urlLeft = urlLeft;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrlRight() {
        return urlRight;
    }

    public void setUrlRight(String urlRight) {
        this.urlRight = urlRight;
    }
}
