package net.iclassmate.teacherspace.bean.single;

import net.iclassmate.teacherspace.bean.Parserable;

import org.json.JSONObject;

/**
 * Created by xydbj on 2016/2/2.
 */
public class ClassValue implements Parserable{
    private String sClass;
    private String avg;
    private String excellentRate;
    private String passRate;
    private String lowRate;

    public String getsClass() {
        return sClass;
    }

    public void setsClass(String sClass) {
        this.sClass = sClass;
    }

    public String getAvg() {
        return avg;
    }

    public void setAvg(String avg) {
        this.avg = avg;
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

    @Override
    public void parserJson(JSONObject jsonObject) {

    }
}
