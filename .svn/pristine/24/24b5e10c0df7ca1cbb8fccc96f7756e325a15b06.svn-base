package net.iclassmate.teacherspace.bean.single;

import net.iclassmate.teacherspace.bean.Parserable;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by xydbj on 2016/2/24.
 */
public class ObjectQuestion implements Serializable, Parserable {
    /**
     * answerContentCount : 0
     * correctAnswer : B
     * correctRate : 41.67%
     * eqDisplayIndex : 0
     * eqDisplayName
     * optionANum : 6
     * optionBNum : 15
     * optionCNum : 9
     * optionDNum : 6
     * optionOtherNum : 0
     */

    private int answerContentCount;
    private String correctAnswer;
    private String correctRate;
    private int eqDisplayIndex;
    private String eqDisplayName;
    private int optionANum;
    private int optionBNum;
    private int optionCNum;
    private int optionDNum;
    private int optionOtherNum;

    @Override
    public void parserJson(JSONObject jsonObject) {
        if (jsonObject != null) {
            try {
                answerContentCount = jsonObject.getInt("answerContentCount");
                correctAnswer = jsonObject.getString("correctAnswer");
                correctRate = jsonObject.getString("correctRate");
                eqDisplayIndex = jsonObject.getInt("eqDisplayIndex");
                eqDisplayName = jsonObject.getString("eqDisplayName");
                optionANum = jsonObject.getInt("optionANum");
                optionBNum = jsonObject.getInt("optionBNum");
                optionCNum = jsonObject.getInt("optionCNum");
                optionDNum = jsonObject.getInt("optionDNum");
                optionOtherNum = jsonObject.getInt("optionOtherNum");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public String getEqDisplayName() {
        return eqDisplayName;
    }

    public void setEqDisplayName(String eqDisplayName) {
        this.eqDisplayName = eqDisplayName;
    }

    public void setAnswerContentCount(int answerContentCount) {
        this.answerContentCount = answerContentCount;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public void setCorrectRate(String correctRate) {
        this.correctRate = correctRate;
    }

    public void setEqDisplayIndex(int eqDisplayIndex) {
        this.eqDisplayIndex = eqDisplayIndex;
    }

    public void setOptionANum(int optionANum) {
        this.optionANum = optionANum;
    }

    public void setOptionBNum(int optionBNum) {
        this.optionBNum = optionBNum;
    }

    public void setOptionCNum(int optionCNum) {
        this.optionCNum = optionCNum;
    }

    public void setOptionDNum(int optionDNum) {
        this.optionDNum = optionDNum;
    }

    public void setOptionOtherNum(int optionOtherNum) {
        this.optionOtherNum = optionOtherNum;
    }

    public int getAnswerContentCount() {
        return answerContentCount;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String getCorrectRate() {
        return correctRate;
    }

    public int getEqDisplayIndex() {
        return eqDisplayIndex;
    }

    public int getOptionANum() {
        return optionANum;
    }

    public int getOptionBNum() {
        return optionBNum;
    }

    public int getOptionCNum() {
        return optionCNum;
    }

    public int getOptionDNum() {
        return optionDNum;
    }

    public int getOptionOtherNum() {
        return optionOtherNum;
    }
}
