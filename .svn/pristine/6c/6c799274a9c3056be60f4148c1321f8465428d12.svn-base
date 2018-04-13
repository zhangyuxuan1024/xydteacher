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
public class SingleTotalSituation implements Parserable, Serializable {
    private List<SummaryInfoDetails> listSummaryInfoDetails;
    private List<ExcellentPersons> listExcellentPersons;
    private List<Improvers> listImprovers;
    private List<BackSliders> listBackSliders;
    private String seDate;
    private String seName;

    @Override
    public void parserJson(JSONObject jsonObject) {
        if (jsonObject != null) {
            try {
                listSummaryInfoDetails = new ArrayList<>();
                JSONArray array1 = jsonObject.getJSONArray("summaryInfoDetails");
                for (int i = 0; i < array1.length(); i++) {
                    SummaryInfoDetails summaryInfoDetails = new SummaryInfoDetails();
                    summaryInfoDetails.parserJson(array1.getJSONObject(i));
                    listSummaryInfoDetails.add(summaryInfoDetails);
                }

                listExcellentPersons = new ArrayList<>();
                JSONArray array2 = jsonObject.optJSONArray("excellentPersons");
                if (array2 != null && array2.length() > 0) {
                    for (int i = 0; i < array2.length(); i++) {
                        ExcellentPersons excellentPersons = new ExcellentPersons();
                        excellentPersons.parserJson(array2.getJSONObject(i));
                        listExcellentPersons.add(excellentPersons);
                    }
                }

                listImprovers = new ArrayList<>();
                JSONArray array3 = jsonObject.optJSONArray("improvers");
                if (array3 != null && array3.length() > 0) {
                    for (int i = 0; i < array3.length(); i++) {
                        Improvers improvers = new Improvers();
                        improvers.parserJson(array3.getJSONObject(i));
                        listImprovers.add(improvers);
                    }
                }

                listBackSliders = new ArrayList<>();
                JSONArray array4 = jsonObject.optJSONArray("backSliders");
                for (int i = 0; i < array4.length(); i++) {
                    BackSliders backSliders = new BackSliders();
                    backSliders.parserJson(array4.getJSONObject(i));
                    listBackSliders.add(backSliders);
                }

                seDate = jsonObject.getString("seDate");
                seName = jsonObject.getString("seName");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public List<SummaryInfoDetails> getListSummaryInfoDetails() {
        return listSummaryInfoDetails;
    }

    public void setListSummaryInfoDetails(List<SummaryInfoDetails> listSummaryInfoDetails) {
        this.listSummaryInfoDetails = listSummaryInfoDetails;
    }

    public List<ExcellentPersons> getListExcellentPersons() {
        return listExcellentPersons;
    }

    public void setListExcellentPersons(List<ExcellentPersons> listExcellentPersons) {
        this.listExcellentPersons = listExcellentPersons;
    }

    public List<Improvers> getListImprovers() {
        return listImprovers;
    }

    public void setListImprovers(List<Improvers> listImprovers) {
        this.listImprovers = listImprovers;
    }

    public List<BackSliders> getListBackSliders() {
        return listBackSliders;
    }

    public void setListBackSliders(List<BackSliders> listBackSliders) {
        this.listBackSliders = listBackSliders;
    }

    public String getSeDate() {
        return seDate;
    }

    public void setSeDate(String seDate) {
        this.seDate = seDate;
    }

    public String getSeName() {
        return seName;
    }

    public void setSeName(String seName) {
        this.seName = seName;
    }

}
