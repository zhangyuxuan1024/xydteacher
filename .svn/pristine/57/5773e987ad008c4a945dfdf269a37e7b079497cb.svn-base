package net.iclassmate.teacherspace.bean.spaper;

import net.iclassmate.teacherspace.bean.Parserable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xydbj on 2016/3/23.
 */
public class StudentExam implements Serializable,Parserable{
    private List<StudentExamPapers> studentExamPapersList;
    @Override
    public void parserJson(JSONObject jsonObject) {
        studentExamPapersList = new ArrayList<>();
        try {
            JSONArray array = jsonObject.getJSONArray("studentExamPapers");
            for (int i = 0; i < array.length(); i++) {
                StudentExamPapers papers = new StudentExamPapers();
                papers.parserJson(array.getJSONObject(i));
                studentExamPapersList.add(papers);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public List<StudentExamPapers> getStudentExamPapersList() {
        return studentExamPapersList;
    }

    public void setStudentExamPapersList(List<StudentExamPapers> studentExamPapersList) {
        this.studentExamPapersList = studentExamPapersList;
    }
}
