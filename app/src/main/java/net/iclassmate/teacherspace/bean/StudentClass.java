package net.iclassmate.teacherspace.bean;

import org.json.JSONObject;

/**
 * Created by xydbj on 2016/1/30.
 */
public class StudentClass implements Parserable {
    private String sClass;
    private String avgScore;
    private String rank;
    private String rankChange;
    @Override
    public void parserJson(JSONObject jsonObject) {

    }

    public String getsClass() {
        return sClass;
    }

    public void setsClass(String sClass) {
        this.sClass = sClass;
    }

    public String getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(String avgScore) {
        this.avgScore = avgScore;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getRankChange() {
        return rankChange;
    }

    public void setRankChange(String rankChange) {
        this.rankChange = rankChange;
    }
}
