package net.iclassmate.teacherspace.bean.text;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xydbj on 2016.3.23.
 */
public class TextPager implements Serializable{
    private String resultCode;
    private String resultDesc;
    private List<StudentExamPapers> studentExamPaperslist;

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultDesc() {
        return resultDesc;
    }

    public void setResultDesc(String resultDesc) {
        this.resultDesc = resultDesc;
    }

    public List<StudentExamPapers> getStudentExamPaperslist() {
        return studentExamPaperslist;
    }

    public void setStudentExamPaperslist(List<StudentExamPapers> studentExamPaperslist) {
        this.studentExamPaperslist = studentExamPaperslist;
    }

    @Override
    public String toString() {
        return "TextPager{" +
                "resultCode='" + resultCode + '\'' +
                ", resultDesc='" + resultDesc + '\'' +
                ", studentExamPaperslist=" + studentExamPaperslist +
                '}';
    }
}
