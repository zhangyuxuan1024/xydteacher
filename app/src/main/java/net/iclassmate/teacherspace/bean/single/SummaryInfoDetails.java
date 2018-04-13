package net.iclassmate.teacherspace.bean.single;

import net.iclassmate.teacherspace.bean.Parserable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xydbj on 2016/2/24.
 */
public class SummaryInfoDetails implements Parserable,Serializable{
    private String classId;
    private String className;
    private double unitAvgScore;
    private String unitOrder;
    private String offsetOrder;
    @Override
    public void parserJson(JSONObject jsonObject) {
        if (jsonObject != null) {
            try {
                classId = jsonObject.getString("classId");
                className = jsonObject.getString("className");
                unitAvgScore = jsonObject.getDouble("unitAvgScore");
                unitOrder = jsonObject.getString("unitOrder");
                offsetOrder = jsonObject.getString("offsetOrder");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public double getUnitAvgScore() {
        return unitAvgScore;
    }

    public void setUnitAvgScore(double unitAvgScore) {
        this.unitAvgScore = unitAvgScore;
    }

    public String getUnitOrder() {
        return unitOrder;
    }

    public void setUnitOrder(String unitOrder) {
        this.unitOrder = unitOrder;
    }

    public String getOffsetOrder() {
        return offsetOrder;
    }

    public void setOffsetOrder(String offsetOrder) {
        this.offsetOrder = offsetOrder;
    }
}
