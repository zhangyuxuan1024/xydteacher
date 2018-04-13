package net.iclassmate.teacherspace.bean.spaper;

import net.iclassmate.teacherspace.bean.Parserable;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by xydbj on 2016/4/8.
 */
public class ExamQuestionPapers implements Serializable, Parserable {
    /**
     * answerIpxywh : 1,1,1078,555,32,32
     * displayIndex : 1
     * displayName : 1
     * ecPage : 1
     * fullScore : 5
     * score : 5
     * seId : 197
     */

    private String answerIpxywh;
    private int displayIndex;
    private String displayName;
    private String ecPage;
    private double fullScore;
    private double score;
    private int seId;

    @Override
    public void parserJson(JSONObject jsonObject) {
        if (jsonObject != null) {
            try {
                answerIpxywh = jsonObject.getString("answerIpxywh");
                displayIndex = jsonObject.getInt("displayIndex");
                displayName = jsonObject.getString("displayName");
                ecPage = jsonObject.getString("ecPage");
                fullScore = jsonObject.getDouble("fullScore");
                score = jsonObject.getDouble("score");
                seId = jsonObject.getInt("seId");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public String getAnswerIpxywh() {
        return answerIpxywh;
    }

    public void setAnswerIpxywh(String answerIpxywh) {
        this.answerIpxywh = answerIpxywh;
    }

    public int getDisplayIndex() {
        return displayIndex;
    }

    public void setDisplayIndex(int displayIndex) {
        this.displayIndex = displayIndex;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEcPage() {
        return ecPage;
    }

    public void setEcPage(String ecPage) {
        this.ecPage = ecPage;
    }

    public double getFullScore() {
        return fullScore;
    }

    public void setFullScore(double fullScore) {
        this.fullScore = fullScore;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getSeId() {
        return seId;
    }

    public void setSeId(int seId) {
        this.seId = seId;
    }
}
