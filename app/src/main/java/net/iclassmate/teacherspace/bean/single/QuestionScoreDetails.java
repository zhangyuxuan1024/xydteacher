package net.iclassmate.teacherspace.bean.single;

import net.iclassmate.teacherspace.bean.Parserable;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by xydbj on 2016/2/24.
 */
public class QuestionScoreDetails implements Parserable, Serializable {
    /**
     * eqDifficulty : 0.8
     * eqDifficultyDesc : 难
     * eqDisplayIndex : 0
     * eqDisplayName : 21-1
     * fullScore : 6
     * score : 4.67
     * eqScoreRate
     */

    private double eqDifficulty;
    private String eqDifficultyDesc;
    private int eqDisplayIndex;
    private String eqDisplayName;
    private double fullScore;
    private double score;
    private String eqScoreRate;

    @Override
    public void parserJson(JSONObject jsonObject) {
        if (jsonObject != null) {
            try {
                eqDifficulty = jsonObject.getDouble("eqDifficulty");
                eqDifficultyDesc = jsonObject.getString("eqDifficultyDesc");
                eqDisplayIndex = jsonObject.getInt("eqDisplayIndex");
                eqDisplayName = jsonObject.getString("eqDisplayName");
                fullScore = jsonObject.getDouble("fullScore");
                score = jsonObject.getDouble("score");
                eqScoreRate = jsonObject.getString("eqScoreRate");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void setEqDifficulty(double eqDifficulty) {
        this.eqDifficulty = eqDifficulty;
    }

    public void setEqDifficultyDesc(String eqDifficultyDesc) {
        this.eqDifficultyDesc = eqDifficultyDesc;
    }

    public void setEqDisplayIndex(int eqDisplayIndex) {
        this.eqDisplayIndex = eqDisplayIndex;
    }

    public void setEqDisplayName(String eqDisplayName) {
        this.eqDisplayName = eqDisplayName;
    }

    public void setFullScore(double fullScore) {
        this.fullScore = fullScore;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public double getEqDifficulty() {
        return eqDifficulty;
    }

    public String getEqDifficultyDesc() {
        return eqDifficultyDesc;
    }

    public int getEqDisplayIndex() {
        return eqDisplayIndex;
    }

    public String getEqDisplayName() {
        return eqDisplayName;
    }

    public double getFullScore() {
        return fullScore;
    }

    public double getScore() {
        return score;
    }

    public String getEqScoreRate() {
        return eqScoreRate;
    }

    public void setEqScoreRate(String eqScoreRate) {
        this.eqScoreRate = eqScoreRate;
    }
}
