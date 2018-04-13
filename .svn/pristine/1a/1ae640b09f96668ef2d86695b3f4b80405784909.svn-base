package net.iclassmate.teacherspace.bean.single;

import net.iclassmate.teacherspace.bean.Parserable;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by xydbj on 2016/2/24.
 */
public class QuestionDifficulty implements Parserable, Serializable {
    /**
     * esayEqFullScore : 56
     * esayEqLostScore : 8.330002
     * esayEqScore : 28.549997
     * hardEqFullScore : 52
     * hardEqLostScore : 8.77
     * hardEqScore : 43.23
     * midEqFullScore : 12
     * midEqLostScore : 7
     * midEqScore : 1.5099999
     */
    private double esayEqFullScore;
    private double esayEqLostScore;
    private double esayEqScore;
    private double hardEqFullScore;
    private double hardEqLostScore;
    private double hardEqScore;
    private double midEqFullScore;
    private double midEqLostScore;
    private double midEqScore;

    @Override
    public void parserJson(JSONObject jsonObject) {
        if (jsonObject != null) {
            try {
                esayEqFullScore = jsonObject.getDouble("esayEqFullScore");
                esayEqLostScore = jsonObject.getDouble("esayEqLostScore");
                esayEqScore = jsonObject.getDouble("esayEqScore");
                hardEqFullScore = jsonObject.getDouble("hardEqFullScore");
                hardEqLostScore = jsonObject.getDouble("hardEqLostScore");
                hardEqScore = jsonObject.getDouble("hardEqScore");
                midEqFullScore = jsonObject.getDouble("midEqFullScore");
                midEqLostScore = jsonObject.getDouble("midEqLostScore");
                midEqScore = jsonObject.getDouble("midEqScore");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public double getEsayEqFullScore() {
        return esayEqFullScore;
    }

    public void setEsayEqFullScore(double esayEqFullScore) {
        this.esayEqFullScore = esayEqFullScore;
    }

    public double getEsayEqLostScore() {
        return esayEqLostScore;
    }

    public void setEsayEqLostScore(double esayEqLostScore) {
        this.esayEqLostScore = esayEqLostScore;
    }

    public double getEsayEqScore() {
        return esayEqScore;
    }

    public void setEsayEqScore(double esayEqScore) {
        this.esayEqScore = esayEqScore;
    }

    public double getHardEqFullScore() {
        return hardEqFullScore;
    }

    public void setHardEqFullScore(double hardEqFullScore) {
        this.hardEqFullScore = hardEqFullScore;
    }

    public double getHardEqLostScore() {
        return hardEqLostScore;
    }

    public void setHardEqLostScore(double hardEqLostScore) {
        this.hardEqLostScore = hardEqLostScore;
    }

    public double getHardEqScore() {
        return hardEqScore;
    }

    public void setHardEqScore(double hardEqScore) {
        this.hardEqScore = hardEqScore;
    }

    public double getMidEqFullScore() {
        return midEqFullScore;
    }

    public void setMidEqFullScore(double midEqFullScore) {
        this.midEqFullScore = midEqFullScore;
    }

    public double getMidEqLostScore() {
        return midEqLostScore;
    }

    public void setMidEqLostScore(double midEqLostScore) {
        this.midEqLostScore = midEqLostScore;
    }

    public double getMidEqScore() {
        return midEqScore;
    }

    public void setMidEqScore(double midEqScore) {
        this.midEqScore = midEqScore;
    }
}
