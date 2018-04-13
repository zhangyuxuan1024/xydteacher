package net.iclassmate.teacherspace.bean.single;

import net.iclassmate.teacherspace.bean.Parserable;

import org.json.JSONObject;

/**
 * Created by xydbj on 2016/2/17.
 */
public class KnowledgePoint implements Parserable{
    private String point;
    private String score;
    private String degree;
    @Override
    public void parserJson(JSONObject jsonObject) {
        if (jsonObject != null) {

        }
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }
}
