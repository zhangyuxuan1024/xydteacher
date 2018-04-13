package net.iclassmate.teacherspace.bean.general;

import java.util.List;

/**
 * Created by xydbj on 2016.2.26.
 */
public class ClassSituation {
    private List<ScoreReports> scoreReportslist;

    public List<ScoreReports> getScoreReportslist() {
        return scoreReportslist;
    }

    public void setScoreReportslist(List<ScoreReports> scoreReportslist) {
        this.scoreReportslist = scoreReportslist;
    }

    @Override
    public String toString() {
        return "ClassSituation{" +
                "scoreReportslist=" + scoreReportslist +
                '}';
    }
}
