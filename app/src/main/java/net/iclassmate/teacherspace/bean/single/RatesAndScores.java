package net.iclassmate.teacherspace.bean.single;

import net.iclassmate.teacherspace.bean.Parserable;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by xydbj on 2016/2/24.
 */
public class RatesAndScores implements Parserable, Serializable {
    /**
     * avgScore : 36.56
     * classId : 2
     * examNum : 34
     * excellentNum : 0
     * excellentRate : .00%
     * gradeOrder : 53
     * lowNum : 15
     * lowRate : 44.12%
     * passNum : 1
     * passRate : 2.94%
     */

    private String className;
    private double avgScore;
    private String excellentRate;
    private String passRate;
    private String lowRate;
    private int excellentNum;
    private int passNum;
    private int lowNum;
    private int examNum;
    private String classId;
    private int avgScoreOrder;
    private int excellentOrder;
    private int passOrder;
    private int lowOrder;
    private int gradeOrder;


    @Override
    public void parserJson(JSONObject jsonObject) {
        if (jsonObject != null) {
            try {
                className = jsonObject.getString("className");
                avgScoreOrder = jsonObject.optInt("avgScoreOrder");
                excellentOrder = jsonObject.optInt("excellentOrder");
                passOrder = jsonObject.optInt("passOrder");
                lowOrder = jsonObject.optInt("lowOrder");
                avgScore = jsonObject.getDouble("avgScore");
                classId = jsonObject.getString("classId");
                examNum = jsonObject.getInt("examNum");
                excellentNum = jsonObject.getInt("excellentNum");
                excellentRate = jsonObject.getString("excellentRate");
                gradeOrder = jsonObject.getInt("gradeOrder");
                lowNum = jsonObject.getInt("lowNum");
                lowRate = jsonObject.getString("lowRate");
                passNum = jsonObject.getInt("passNum");
                passRate = jsonObject.getString("passRate");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public double getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(double avgScore) {
        this.avgScore = avgScore;
    }

    public String getExcellentRate() {
        return excellentRate;
    }

    public void setExcellentRate(String excellentRate) {
        this.excellentRate = excellentRate;
    }

    public String getPassRate() {
        return passRate;
    }

    public void setPassRate(String passRate) {
        this.passRate = passRate;
    }

    public String getLowRate() {
        return lowRate;
    }

    public void setLowRate(String lowRate) {
        this.lowRate = lowRate;
    }

    public int getExcellentNum() {
        return excellentNum;
    }

    public void setExcellentNum(int excellentNum) {
        this.excellentNum = excellentNum;
    }

    public int getPassNum() {
        return passNum;
    }

    public void setPassNum(int passNum) {
        this.passNum = passNum;
    }

    public int getLowNum() {
        return lowNum;
    }

    public void setLowNum(int lowNum) {
        this.lowNum = lowNum;
    }

    public int getExamNum() {
        return examNum;
    }

    public void setExamNum(int examNum) {
        this.examNum = examNum;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public int getAvgScoreOrder() {
        return avgScoreOrder;
    }

    public void setAvgScoreOrder(int avgScoreOrder) {
        this.avgScoreOrder = avgScoreOrder;
    }

    public int getExcellentOrder() {
        return excellentOrder;
    }

    public void setExcellentOrder(int excellentOrder) {
        this.excellentOrder = excellentOrder;
    }

    public int getPassOrder() {
        return passOrder;
    }

    public void setPassOrder(int passOrder) {
        this.passOrder = passOrder;
    }

    public int getLowOrder() {
        return lowOrder;
    }

    public void setLowOrder(int lowOrder) {
        this.lowOrder = lowOrder;
    }

    public int getGradeOrder() {
        return gradeOrder;
    }

    public void setGradeOrder(int gradeOrder) {
        this.gradeOrder = gradeOrder;
    }
}
