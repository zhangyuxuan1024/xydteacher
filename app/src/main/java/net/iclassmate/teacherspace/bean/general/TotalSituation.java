package net.iclassmate.teacherspace.bean.general;

import java.util.List;

/**
 * Created by xydbj on 2016.2.26.
 */
public class TotalSituation {
    private List<BackSliders> BackSliderslist;
    private List<ExcellentPersons> ExcellentPersonslist;
    private List<Improvers> Improverslist;
    private SummaryInfo summaryInfo;
    private String meDate;
    private String meName;

    public List<BackSliders> getBackSliderslist() {
        return BackSliderslist;
    }

    public void setBackSliderslist(List<BackSliders> backSliderslist) {
        BackSliderslist = backSliderslist;
    }

    public List<ExcellentPersons> getExcellentPersonslist() {
        return ExcellentPersonslist;
    }

    public void setExcellentPersonslist(List<ExcellentPersons> excellentPersonslist) {
        ExcellentPersonslist = excellentPersonslist;
    }

    public List<Improvers> getImproverslist() {
        return Improverslist;
    }

    public void setImproverslist(List<Improvers> improverslist) {
        Improverslist = improverslist;
    }

    public SummaryInfo getSummaryInfo() {
        return summaryInfo;
    }

    public void setSummaryInfo(SummaryInfo summaryInfo) {
        this.summaryInfo = summaryInfo;
    }

    public String getMeDate() {
        return meDate;
    }

    public void setMeDate(String meDate) {
        this.meDate = meDate;
    }

    public String getMeName() {
        return meName;
    }

    public void setMeName(String meName) {
        this.meName = meName;
    }

    @Override
    public String toString() {
        return "TotalSituation{" +
                "BackSliderslist=" + BackSliderslist +
                ", ExcellentPersonslist=" + ExcellentPersonslist +
                ", Improverslist=" + Improverslist +
                ", summaryInfo=" + summaryInfo +
                ", meDate='" + meDate + '\'' +
                ", meName='" + meName + '\'' +
                '}';
    }
}
