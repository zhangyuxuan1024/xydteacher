package net.iclassmate.teacherspace.utils;

import android.util.Xml;

import net.iclassmate.teacherspace.bean.general.BackSliders;
import net.iclassmate.teacherspace.bean.general.ClassSituation;
import net.iclassmate.teacherspace.bean.general.ExcellentPersons;
import net.iclassmate.teacherspace.bean.general.GeneralAll;
import net.iclassmate.teacherspace.bean.general.GradeSituation;
import net.iclassmate.teacherspace.bean.general.Improvers;
import net.iclassmate.teacherspace.bean.general.MultiExams;
import net.iclassmate.teacherspace.bean.general.RatesAndScores;
import net.iclassmate.teacherspace.bean.general.ScoreReports;
import net.iclassmate.teacherspace.bean.general.SingleExamInfos;
import net.iclassmate.teacherspace.bean.general.SubjectSummaries;
import net.iclassmate.teacherspace.bean.general.SummaryInfo;
import net.iclassmate.teacherspace.bean.general.SummaryInfoDetails;
import net.iclassmate.teacherspace.bean.general.TotalSituation;
import net.iclassmate.teacherspace.bean.login.Login_school_config;
import net.iclassmate.teacherspace.bean.login.Login_teacherInfo;
import net.iclassmate.teacherspace.bean.login.Login_userInfo;
import net.iclassmate.teacherspace.bean.login.Roles;
import net.iclassmate.teacherspace.bean.notice.Notice_Info;
import net.iclassmate.teacherspace.bean.notice.Notice_Message;
import net.iclassmate.teacherspace.bean.notice.Notice_exam_info;
import net.iclassmate.teacherspace.bean.text.ExamPaperUrls;
import net.iclassmate.teacherspace.bean.text.ExamQuestionPapers;
import net.iclassmate.teacherspace.bean.text.StudentExamPapers;
import net.iclassmate.teacherspace.bean.text.TextPager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * json解析帮助类
 * Created by xyd on 2016/2/23.
 */
public class JsonUtils {
    public static Login_teacherInfo jsontologinuserInfo(String jsonString) {
        Login_teacherInfo login_teacherInfo=null;
        Login_userInfo loginuserInfo = null;
        Roles roles=null;
        ArrayList<Login_userInfo> login_userInfoArrayList;
        if (jsonString != null) {
            try {

                JSONObject jsonObject=new JSONObject(jsonString);


                int  resultCode = jsonObject.getInt("resultCode");
                String resultDesc = jsonObject.getString("resultDesc");

                JSONArray obj_userInfoList = jsonObject.getJSONArray("teachers");
                if (obj_userInfoList != null) {

                    login_userInfoArrayList=new ArrayList<Login_userInfo>();

                    for (int i = 0; i < obj_userInfoList.length(); i++){

                        String userCode=obj_userInfoList.getJSONObject(i).getString("userCode");
                        String userId = obj_userInfoList.getJSONObject(i)
                                .getString("userId");
                        String userName = obj_userInfoList.getJSONObject(i)
                                .getString("userName");
                        String teacherId = obj_userInfoList.getJSONObject(i)
                                .getString("teacherId");
                        String teacherName=obj_userInfoList.getJSONObject(i)
                                .getString("teacherName");

                        int schoolId = obj_userInfoList.getJSONObject(i).getInt("schoolId");
                        String schoolName = obj_userInfoList.getJSONObject(i).getString(
                                "schoolName");
                        int gradeId = obj_userInfoList.getJSONObject(i).getInt("gradeId");
                        String gradeName = obj_userInfoList.getJSONObject(i)
                                .getString("gradeName");
                        String mobileNum=obj_userInfoList.getJSONObject(i).getString("mobileNum");
                        String classCode = obj_userInfoList.getJSONObject(i)
                                .getString("classCode");
                        String className = obj_userInfoList.getJSONObject(i).getString(
                                "className");
                        int courseId = obj_userInfoList.getJSONObject(i).getInt(
                                "courseId");
                        String courseName = obj_userInfoList.getJSONObject(i).getString(
                                "courseName");
                        int termCode=obj_userInfoList.getJSONObject(i).getInt("termCode");
                        String enterYear=obj_userInfoList.getJSONObject(i).getString("enterYear");



                        JSONObject rolesObject=obj_userInfoList.getJSONObject(i).getJSONObject("role");
                            int roleId=rolesObject.getInt("roleId");
                            String roleName=rolesObject.getString("roleName");
                            roles=new Roles(roleId,roleName);
                        loginuserInfo=new Login_userInfo(roles,userCode,userId,userName,teacherId,teacherName,
                                termCode,schoolId,schoolName,gradeId,gradeName,classCode,className,courseId,courseName,mobileNum,enterYear);
                        login_userInfoArrayList.add(loginuserInfo);
                    }

                    login_teacherInfo=new Login_teacherInfo(login_userInfoArrayList,resultCode,resultDesc);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return login_teacherInfo;
    }

      /*
     * json arry 结构，其中appconfig 是个xml字符串
	 */

    public static Login_school_config analys_Login_schconfig(String jsonString) {
        try {
            JSONArray school_configarry = new JSONArray(jsonString);
            if (school_configarry != null && school_configarry.length() > 0) {
                JSONObject jsonresult = school_configarry.getJSONObject(0);
                if (jsonresult != null) {
                    Login_school_config loginschoolconfig = new Login_school_config();
                    int asId = jsonresult.optInt("asId");
                    String appId = jsonresult.optString("appId");
                    String appName = jsonresult.optString("appName");
                    int serverId = jsonresult.optInt("serverId");
                    String serverName = jsonresult.optString("serverName");
                    int areaId = jsonresult.optInt("areaId");
                    String areaName = jsonresult.optString("areaName");
                    int schoolId = jsonresult.optInt("schoolId");
                    String schoolName = jsonresult.optString("schoolName");
                    String status = jsonresult.optString("status");

                    loginschoolconfig.setAsId(asId);
                    loginschoolconfig.setAppId(appId);
                    loginschoolconfig.setAppName(appName);
                    loginschoolconfig.setServerId(serverId);
                    loginschoolconfig.setSchoolName(serverName);
                    loginschoolconfig.setAreaId(areaId);
                    loginschoolconfig.setAreaName(areaName);
                    loginschoolconfig.setSchoolId(schoolId);
                    loginschoolconfig.setSchoolName(schoolName);
                    loginschoolconfig.setStatus(status);

                    String appConfig = jsonresult.optString("appConfig");

                    String fileServUrl = "";
                    // String reportServUrl = "";
                    String weiKeServUrl = "";
                    String basecenterUrl = "";
                    String digital_camplusUrl = "";
                    String webSerUrl = "";
                    String appcenterUrl = "";

                    if (appConfig != null && appConfig.length() > 0) {
                        XmlPullParser xmlPullParser = Xml.newPullParser();
                        // parser.setInput(appConfig.get, "UTF-8");
                        InputStream ins = new ByteArrayInputStream(
                                appConfig.getBytes());
                        try {
                            xmlPullParser.setInput(ins, "UTF-8");
                            int eventType = xmlPullParser.getEventType();
                            while (eventType != XmlPullParser.END_DOCUMENT) {
                                switch (eventType) {
                                    case XmlPullParser.START_TAG:
                                        if (xmlPullParser.getName().equals(
                                                "digital_camplus")) {

                                            digital_camplusUrl = xmlPullParser
                                                    .getAttributeValue(0);
                                        }

                                        if (xmlPullParser.getName().equals(
                                                "appcenter")) {
                                            appcenterUrl = xmlPullParser
                                                    .getAttributeValue(0);
                                        }
                                        if (xmlPullParser.getName().equals(
                                                "basecenter")) {

                                            basecenterUrl = xmlPullParser
                                                    .getAttributeValue(0);
                                        }
                                        if (xmlPullParser.getName().equals("web")) {

                                            webSerUrl = xmlPullParser
                                                    .getAttributeValue(0);
                                        }
                                        if (xmlPullParser.getName().equals("file")) {

                                            fileServUrl = xmlPullParser
                                                    .getAttributeValue(0);
                                        }
                                        if (xmlPullParser.getName().equals("weike")) {
                                            weiKeServUrl = xmlPullParser
                                                    .getAttributeValue(0);
                                        }
                                        break;
                                    case XmlPullParser.END_TAG:
                                        break;
                                    default:
                                        break;
                                }
                                eventType = xmlPullParser.next();
                            }
                            //
                            String loginServUrl = basecenterUrl
                                    + "BaseCenter/rest/service/loginM/";
                            loginschoolconfig.setLoginServUrl(loginServUrl);
                            loginschoolconfig.setBasecenterUrl(basecenterUrl);
                            loginschoolconfig.setFileServUrl(fileServUrl);
                            loginschoolconfig.setReportServUrl(appcenterUrl);
                            loginschoolconfig.setWeiKeServUrl(weiKeServUrl);
                            loginschoolconfig.setWebUrl(webSerUrl);
                            loginschoolconfig
                                    .setDigital_camplusUrl(digital_camplusUrl);
                            return loginschoolconfig;

                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                            return null;
                        }
                    }
                }
            }

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static GeneralAll StartJson(String json) {
        GeneralAll generalAll = new GeneralAll();
        List<MultiExams> multiExamsList = null;
        try {
            JSONObject jsonObject1 = new JSONObject(json);
            JSONArray  jsonArray1  = jsonObject1.getJSONArray("multiExams");
            multiExamsList = new ArrayList<>();
            for(int i=0;i<jsonArray1.length();i++){
                if(i == 0){
                    JSONObject jsonObject2 = jsonArray1.getJSONObject(i);
                    MultiExams multiExams = new MultiExams();
                    multiExams.setClassId(jsonObject2.getString("classId"));
                    multiExams.setClassName(jsonObject2.getString("className"));
                    multiExams.setMeId(jsonObject2.getString("meId"));
                    multiExams.setSchoolId(jsonObject2.getString("schoolId"));
                    JSONObject jsonObject5 = jsonObject2.getJSONObject("gradeSituation");
                    GradeSituation gradeSituation = new GradeSituation();
                    JSONArray jsonArray3 = jsonObject5.getJSONArray("excellentPersons");
                    List<ExcellentPersons> excellentPersonsList = new ArrayList<>();
                    for(int k=0;k<jsonArray3.length();k++){
                        JSONObject jsonObject6 = jsonArray3.getJSONObject(k);
                        ExcellentPersons excellentPersons = new ExcellentPersons();
                        excellentPersons.setClassAvgScore(jsonObject6.getString("classAvgScore"));
                        excellentPersons.setClassId(jsonObject6.getString("classId"));
                        excellentPersons.setClassName(jsonObject6.getString("className"));
                        excellentPersons.setClassOrder(jsonObject6.getString("classOrder"));
                        excellentPersons.setClassOrderOffset(jsonObject6.getString("classOrderOffset"));
                        excellentPersons.setGradeOrder(jsonObject6.getString("gradeOrder"));
                        excellentPersons.setGradeOrderOffset(jsonObject6.getString("gradeOrderOffset"));
                        excellentPersons.setOffsetOrder(jsonObject6.getString("offsetOrder"));
                        excellentPersons.setSchoolId(jsonObject6.getString("schoolId"));
                        excellentPersons.setScore(jsonObject6.getString("score"));
                        excellentPersons.setStudentId(jsonObject6.getString("studentId"));
                        excellentPersons.setStudentName(jsonObject6.getString("studentName"));
                        excellentPersonsList.add(excellentPersons);
                    }
                    gradeSituation.setExcellentPersonslist(excellentPersonsList);//跟下面的一起加在gradeSituation
                    JSONArray jsonArray4 = jsonObject5.getJSONArray("ratesAndScores");
                    List<RatesAndScores> ratesAndScoresList = new ArrayList<>();
                    for(int a=0;a<jsonArray4.length();a++){
                        JSONObject jsonObject6 = jsonArray4.getJSONObject(a);
                        RatesAndScores ratesAndScores = new RatesAndScores();
                        ratesAndScores.setAvgScore(jsonObject6.getString("avgScore"));
                        ratesAndScores.setAvgScoreOrder(jsonObject6.getString("avgScoreOrder"));
                        ratesAndScores.setClassId(jsonObject6.getString("classId"));
                        ratesAndScores.setClassName(jsonObject6.getString("className"));
                        ratesAndScores.setExamNum(jsonObject6.getString("examNum"));
                        ratesAndScores.setExcellentNum(jsonObject6.getString("excellentNum"));
                        ratesAndScores.setExcellentOrder(jsonObject6.getString("excellentOrder"));
                        ratesAndScores.setExcellentRate(jsonObject6.getString("excellentRate"));
                        ratesAndScores.setGradeOrder(jsonObject6.getString("gradeOrder"));
                        ratesAndScores.setLowNum(jsonObject6.getString("lowNum"));
                        ratesAndScores.setLowOrder(jsonObject6.getString("lowOrder"));
                        ratesAndScores.setLowRate(jsonObject6.getString("lowRate"));
                        ratesAndScores.setPassNum(jsonObject6.getString("passNum"));
                        ratesAndScores.setPassOrder(jsonObject6.getString("passOrder"));
                        ratesAndScores.setPassRate(jsonObject6.getString("passRate"));
                        ratesAndScoresList.add(ratesAndScores);
                    }
                    gradeSituation.setRatesAndScoreslist(ratesAndScoresList);
                    multiExams.setGradeSituation(gradeSituation);
                    JSONObject jsonObject6 = jsonObject2.getJSONObject("totalSituation");
                    TotalSituation totalSituation = new TotalSituation();
                    JSONArray jsonArray5 = jsonObject6.getJSONArray("backSliders");
                    List<BackSliders> backSlidersList = new ArrayList<>();
                    for(int b=0;b<jsonArray5.length();b++){
                        JSONObject jsonObject7 = jsonArray5.getJSONObject(b);
                        BackSliders backSliders = new BackSliders();
                        backSliders.setClassId(jsonObject7.getString("classId"));
                        backSliders.setClassName(jsonObject7.getString("className"));
                        backSliders.setClassOrder(jsonObject7.getString("classOrder"));
                        backSliders.setClassOrderOffset(jsonObject7.getString("classOrderOffset"));
                        backSliders.setGradeOrder(jsonObject7.getString("gradeOrder"));
                        backSliders.setGradeOrderOffset(jsonObject7.getString("gradeOrderOffset"));
                        backSliders.setLastClassOrder(jsonObject7.getString("lastClassOrder"));
                        backSliders.setLastGradeOrder(jsonObject7.getString("lastGradeOrder"));
                        backSliders.setOffsetOrder(jsonObject7.getString("offsetOrder"));
                        backSliders.setSchoolId(jsonObject7.getString("schoolId"));
                        backSliders.setScore(jsonObject7.getString("score"));
                        backSliders.setStudentId(jsonObject7.getString("studentId"));
                        backSliders.setStudentName(jsonObject7.getString("studentName"));
                        backSlidersList.add(backSliders);
                    }
                    totalSituation.setBackSliderslist(backSlidersList);
                    JSONArray jsonArray6 = jsonObject6.getJSONArray("excellentPersons");
                    List<ExcellentPersons> excellentPersonsList1 = new ArrayList<>();
                    for(int c=0;c<jsonArray6.length();c++){
                        JSONObject jsonObject8 = jsonArray6.getJSONObject(c);
                        ExcellentPersons excellentPersons = new ExcellentPersons();
                        excellentPersons.setClassId(jsonObject8.getString("classId"));
                        excellentPersons.setClassName(jsonObject8.getString("className"));
                        excellentPersons.setClassOrder(jsonObject8.getString("classOrder"));
                        excellentPersons.setClassOrderOffset(jsonObject8.getString("classOrderOffset"));
                        excellentPersons.setGradeOrder(jsonObject8.getString("gradeOrder"));
                        excellentPersons.setGradeOrderOffset(jsonObject8.getString("gradeOrderOffset"));
                        excellentPersons.setOffsetOrder(jsonObject8.getString("offsetOrder"));
                        excellentPersons.setSchoolId(jsonObject8.getString("schoolId"));
                        excellentPersons.setScore(jsonObject8.getString("score"));
                        excellentPersons.setStudentId(jsonObject8.getString("studentId"));
                        excellentPersons.setStudentName(jsonObject8.getString("studentName"));
                        excellentPersonsList1.add(excellentPersons);
                    }
                    totalSituation.setExcellentPersonslist(excellentPersonsList1);
                    JSONArray jsonArray7 = jsonObject6.getJSONArray("improvers");
                    List<Improvers> improversList = new ArrayList<>();
                    for(int d=0;d<jsonArray7.length();d++){
                        JSONObject jsonObject9 = jsonArray7.getJSONObject(d);
                        Improvers improvers = new Improvers();
                        improvers.setClassId(jsonObject9.getString("classId"));
                        improvers.setClassName(jsonObject9.getString("className"));
                        improvers.setClassOrder(jsonObject9.getString("classOrder"));
                        improvers.setClassOrderOffset(jsonObject9.getString("classOrderOffset"));
                        improvers.setGradeOrder(jsonObject9.getString("gradeOrder"));
                        improvers.setGradeOrderOffset(jsonObject9.getString("gradeOrderOffset"));
                        improvers.setLastClassOrder(jsonObject9.getString("lastClassOrder"));
                        improvers.setLastGradeOrder(jsonObject9.getString("lastGradeOrder"));
                        improvers.setOffsetOrder(jsonObject9.getString("offsetOrder"));
                        improvers.setSchoolId(jsonObject9.getString("schoolId"));
                        improvers.setScore(jsonObject9.getString("score"));
                        improvers.setStudentId(jsonObject9.getString("studentId"));
                        improvers.setStudentName(jsonObject9.getString("studentName"));
                        improversList.add(improvers);
                    }
                    totalSituation.setImproverslist(improversList);
                    totalSituation.setMeDate(jsonObject6.getString("meDate"));
                    totalSituation.setMeName(jsonObject6.getString("meName"));
                    JSONObject jsonObject7 = jsonObject6.getJSONObject("summaryInfo");
                    SummaryInfo summaryInfo = new SummaryInfo();
                    JSONArray jsonArray8 = jsonObject7.getJSONArray("subjectSummaries");
                    List<SubjectSummaries> subjectSummariesList = new ArrayList<>();
                    for(int e=0;e<jsonArray8.length();e++){
                        JSONObject jsonObject8 = jsonArray8.getJSONObject(e);
                        SubjectSummaries subjectSummaries = new SubjectSummaries();
                        subjectSummaries.setName(jsonObject8.getString("name"));
                        JSONArray jsonArray9 = jsonObject8.getJSONArray("singleExamInfos");
                        List<SingleExamInfos> singleExamInfosList = new ArrayList<>();
                        for(int f=0;f<jsonArray9.length();f++){
                            JSONObject jsonObject9 = jsonArray9.getJSONObject(f);
                            SingleExamInfos singleExamInfos = new SingleExamInfos();
                            singleExamInfos.setCourseId(jsonObject9.getInt("courseId"));
                            singleExamInfos.setCourseName(jsonObject9.getString("courseName"));
                            singleExamInfos.setFlag(jsonObject9.optString("flag"));
                            singleExamInfos.setScore((float)jsonObject9.getDouble("score"));
                            singleExamInfos.setSeFullScore(jsonObject9.getInt("seFullScore"));
                            singleExamInfosList.add(singleExamInfos);
                        }
                        subjectSummaries.setSingleExamInfoslist(singleExamInfosList);
                        subjectSummariesList.add(subjectSummaries);
                    }
                    summaryInfo.setSubjectSummarieslist(subjectSummariesList);
                    JSONArray jsonArray9 = jsonObject7.getJSONArray("summaryInfoDetails");
                    List<SummaryInfoDetails> summaryInfoDetailsList = new ArrayList<>();
                    for(int g=0;g<jsonArray9.length();g++){
                        JSONObject jsonObject = jsonArray9.getJSONObject(g);
                        SummaryInfoDetails summaryInfoDetails = new SummaryInfoDetails();
                        summaryInfoDetails.setClassId(jsonObject.getString("classId"));
                        summaryInfoDetails.setClassName(jsonObject.getString("className"));
                        summaryInfoDetails.setOffsetOrder(jsonObject.getString("offsetOrder"));
                        summaryInfoDetails.setUnitAvgScore(jsonObject.getDouble("unitAvgScore"));
                        summaryInfoDetails.setUnitOrder(jsonObject.getString("unitOrder"));
                        summaryInfoDetailsList.add(summaryInfoDetails);
                    }
                    summaryInfo.setSummaryInfoDetailslist(summaryInfoDetailsList);
                    totalSituation.setSummaryInfo(summaryInfo);
                    multiExams.setTotalSituation(totalSituation);
                    multiExamsList.add(multiExams);
                }else{
                    JSONObject jsonObject2 = jsonArray1.getJSONObject(i);
                    MultiExams multiExams = new MultiExams();
                    multiExams.setClassId(jsonObject2.getString("classId"));
                    multiExams.setClassName(jsonObject2.getString("className"));
                    multiExams.setMeId(jsonObject2.getString("meId"));
                    multiExams.setSchoolId(jsonObject2.getString("schoolId"));
                    JSONObject jsonObject3 = jsonObject2.getJSONObject("classSituation");
                    ClassSituation classSituation = new ClassSituation();
                    JSONArray  jsonArray2 = jsonObject3.getJSONArray("scoreReports");
                    List<ScoreReports> scoreReportsList = new ArrayList<>();
                    for(int j=0;j<jsonArray2.length();j++){
                        JSONObject jsonObject4 = jsonArray2.getJSONObject(j);
                        ScoreReports scoreReports = new ScoreReports();
                        scoreReports.setClassOrder(jsonObject4.getString("classOrder"));
                        scoreReports.setClassOrderOffset(jsonObject4.getString("classOrderOffset"));
                        scoreReports.setFullScore(jsonObject4.getString("fullScore"));
                        scoreReports.setGradeOrderOffset(jsonObject4.getString("gradeOrderOffset"));
                        scoreReports.setOffsetOrder(jsonObject4.getString("offsetOrder"));
                        scoreReports.setScore(jsonObject4.getString("score"));
                        scoreReports.setScoreRate(jsonObject4.getString("scoreRate"));
                        scoreReports.setStudentId(jsonObject4.getString("studentId"));
                        scoreReports.setStudentName(jsonObject4.getString("studentName"));
                        scoreReportsList.add(scoreReports);
                    }
                    classSituation.setScoreReportslist(scoreReportsList);
                    multiExams.setClassSituation(classSituation);
                    JSONObject jsonObject6 = jsonObject2.getJSONObject("totalSituation");
                    TotalSituation totalSituation = new TotalSituation();
                    JSONArray jsonArray5 = jsonObject6.getJSONArray("backSliders");
                    List<BackSliders> backSlidersList = new ArrayList<>();
                    for(int b=0;b<jsonArray5.length();b++){
                        JSONObject jsonObject7 = jsonArray5.getJSONObject(b);
                        BackSliders backSliders = new BackSliders();
                        backSliders.setClassId(jsonObject7.getString("classId"));
                        backSliders.setClassName(jsonObject7.getString("className"));
                        backSliders.setClassOrder(jsonObject7.getString("classOrder"));
                        backSliders.setClassOrderOffset(jsonObject7.getString("classOrderOffset"));
                        backSliders.setGradeOrder(jsonObject7.getString("gradeOrder"));
                        backSliders.setGradeOrderOffset(jsonObject7.getString("gradeOrderOffset"));
                        backSliders.setLastClassOrder(jsonObject7.getString("lastClassOrder"));
                        backSliders.setLastGradeOrder(jsonObject7.getString("lastGradeOrder"));
                        backSliders.setOffsetOrder(jsonObject7.getString("offsetOrder"));
                        backSliders.setSchoolId(jsonObject7.getString("schoolId"));
                        backSliders.setScore(jsonObject7.getString("score"));
                        backSliders.setStudentId(jsonObject7.getString("studentId"));
                        backSliders.setStudentName(jsonObject7.getString("studentName"));
                        backSlidersList.add(backSliders);
                    }
                    totalSituation.setBackSliderslist(backSlidersList);
                    JSONArray jsonArray6 = jsonObject6.getJSONArray("excellentPersons");
                    List<ExcellentPersons> excellentPersonsList1 = new ArrayList<>();
                    for(int c=0;c<jsonArray6.length();c++){
                        JSONObject jsonObject8 = jsonArray6.getJSONObject(c);
                        ExcellentPersons excellentPersons = new ExcellentPersons();
                        excellentPersons.setClassId(jsonObject8.getString("classId"));
                        excellentPersons.setClassName(jsonObject8.getString("className"));
                        excellentPersons.setClassOrder(jsonObject8.getString("classOrder"));
                        excellentPersons.setClassOrderOffset(jsonObject8.getString("classOrderOffset"));
                        excellentPersons.setGradeOrder(jsonObject8.getString("gradeOrder"));
                        excellentPersons.setGradeOrderOffset(jsonObject8.getString("gradeOrderOffset"));
                        excellentPersons.setOffsetOrder(jsonObject8.getString("offsetOrder"));
                        excellentPersons.setSchoolId(jsonObject8.getString("schoolId"));
                        excellentPersons.setScore(jsonObject8.getString("score"));
                        excellentPersons.setStudentId(jsonObject8.getString("studentId"));
                        excellentPersons.setStudentName(jsonObject8.getString("studentName"));
                        excellentPersonsList1.add(excellentPersons);
                    }
                    totalSituation.setExcellentPersonslist(excellentPersonsList1);
                    JSONArray jsonArray7 = jsonObject6.getJSONArray("improvers");
                    List<Improvers> improversList = new ArrayList<>();
                    for(int d=0;d<jsonArray7.length();d++){
                        JSONObject jsonObject9 = jsonArray7.getJSONObject(d);
                        Improvers improvers = new Improvers();
                        improvers.setClassId(jsonObject9.getString("classId"));
                        improvers.setClassName(jsonObject9.getString("className"));
                        improvers.setClassOrder(jsonObject9.getString("classOrder"));
                        improvers.setClassOrderOffset(jsonObject9.getString("classOrderOffset"));
                        improvers.setGradeOrder(jsonObject9.getString("gradeOrder"));
                        improvers.setGradeOrderOffset(jsonObject9.getString("gradeOrderOffset"));
                        improvers.setLastClassOrder(jsonObject9.getString("lastClassOrder"));
                        improvers.setLastGradeOrder(jsonObject9.getString("lastGradeOrder"));
                        improvers.setOffsetOrder(jsonObject9.getString("offsetOrder"));
                        improvers.setSchoolId(jsonObject9.getString("schoolId"));
                        improvers.setScore(jsonObject9.getString("score"));
                        improvers.setStudentId(jsonObject9.getString("studentId"));
                        improvers.setStudentName(jsonObject9.getString("studentName"));
                        improversList.add(improvers);
                    }
                    totalSituation.setImproverslist(improversList);
                    totalSituation.setMeDate(jsonObject6.getString("meDate"));
                    totalSituation.setMeName(jsonObject6.getString("meName"));
                    JSONObject jsonObject7 = jsonObject6.getJSONObject("summaryInfo");
                    SummaryInfo summaryInfo = new SummaryInfo();
                    JSONArray jsonArray8 = jsonObject7.getJSONArray("subjectSummaries");
                    List<SubjectSummaries> subjectSummariesList = new ArrayList<>();
                    for(int e=0;e<jsonArray8.length();e++){
                        JSONObject jsonObject8 = jsonArray8.getJSONObject(e);
                        SubjectSummaries subjectSummaries = new SubjectSummaries();
                        subjectSummaries.setName(jsonObject8.getString("name"));
                        JSONArray jsonArray9 = jsonObject8.getJSONArray("singleExamInfos");
                        List<SingleExamInfos> singleExamInfosList = new ArrayList<>();
                        for(int f=0;f<jsonArray9.length();f++){
                            JSONObject jsonObject9 = jsonArray9.getJSONObject(f);
                            SingleExamInfos singleExamInfos = new SingleExamInfos();
                            singleExamInfos.setCourseId(jsonObject9.getInt("courseId"));
                            singleExamInfos.setCourseName(jsonObject9.getString("courseName"));
                            singleExamInfos.setFlag(jsonObject9.optString("flag"));
                            singleExamInfos.setScore((float)jsonObject9.getDouble("score"));
                            singleExamInfos.setSeFullScore(jsonObject9.getInt("seFullScore"));
                            singleExamInfosList.add(singleExamInfos);
                        }
                        subjectSummaries.setSingleExamInfoslist(singleExamInfosList);
                        subjectSummariesList.add(subjectSummaries);
                    }
                    summaryInfo.setSubjectSummarieslist(subjectSummariesList);
                    JSONArray jsonArray9 = jsonObject7.getJSONArray("summaryInfoDetails");
                    List<SummaryInfoDetails> summaryInfoDetailsList = new ArrayList<>();
                    for(int g=0;g<jsonArray9.length();g++){
                        JSONObject jsonObject = jsonArray9.getJSONObject(g);
                        SummaryInfoDetails summaryInfoDetails = new SummaryInfoDetails();
                        summaryInfoDetails.setClassId(jsonObject.getString("classId"));
                        summaryInfoDetails.setClassName(jsonObject.getString("className"));
                        summaryInfoDetails.setOffsetOrder(jsonObject.getString("offsetOrder"));
                        summaryInfoDetails.setUnitAvgScore(jsonObject.getDouble("unitAvgScore"));
                        summaryInfoDetails.setUnitOrder(jsonObject.getString("unitOrder"));
                        summaryInfoDetailsList.add(summaryInfoDetails);
                    }
                    summaryInfo.setSummaryInfoDetailslist(summaryInfoDetailsList);
                    totalSituation.setSummaryInfo(summaryInfo);
                    multiExams.setTotalSituation(totalSituation);
                    multiExamsList.add(multiExams);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        generalAll.setList(multiExamsList);
        return generalAll;
    }

    public static Notice_Message jsonNoticeInfo(String jsonString){
        Notice_Message notice_message=null;
        Notice_Info notice_info=null;
        Notice_exam_info notice_exam_info=null;
//        Notice_mark_info notice_mark_info=null;
//        List<Notice_mark_info> notice_mark_infoList;
        List<Notice_exam_info> notice_exam_infoList;
        if (jsonString!=null){
            try {

                JSONObject jsonObject=new JSONObject(jsonString);
                String resultDesc=jsonObject.getString("resultDesc");
                int resultCode=jsonObject.getInt("resultCode");

                JSONObject jsonObject_info=jsonObject.getJSONObject("messageDetail");
                int totalCount=jsonObject_info.getInt("totalCount");
                int unReadCount=jsonObject_info.getInt("unReadCount");
                notice_exam_infoList=new ArrayList<>();

                JSONArray jsonArray_exam=jsonObject_info.getJSONArray("messageInfoDetails");
                if (jsonArray_exam!=null){
                    for (int i=0;i<jsonArray_exam.length();i++){
                        JSONObject jsonObject_exam=jsonArray_exam.getJSONObject(i);
                        int meId=jsonObject_exam.getInt("meId");
                        String meName=jsonObject_exam.getString("meName");
                        int schoolId=jsonObject_exam.getInt("schoolId");
                        int seId=jsonObject_exam.getInt("seId");
                        int isReaded=jsonObject_exam.getInt("isReaded");
                        int courseId=jsonObject_exam.getInt("courseId");
                        String courseName=jsonObject_exam.getString("courseName");
                        String classId=jsonObject_exam.getString("classId");
                        String msgId=jsonObject_exam.getString("msgId");
                        String info=jsonObject_exam.getString("info");
                        int msgType=jsonObject_exam.getInt("msgType");
                        String indbtime=jsonObject_exam.getString("indbtime");
                        notice_exam_info=new Notice_exam_info(meId,meName,schoolId,seId,isReaded,courseId,courseName,classId,msgId,info,msgType,indbtime);
                        notice_exam_infoList.add(notice_exam_info);
                    }
                }
                notice_info=new Notice_Info(notice_exam_infoList,totalCount,unReadCount);
                notice_message=new Notice_Message(notice_info,resultCode,resultDesc);
            }catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return notice_message;
    }

    public static TextPager StartTextPagerJson(String json) {
        TextPager textPager = new TextPager();
        try {
            JSONObject jsonObject = new JSONObject(json);
            textPager.setResultCode(jsonObject.getString("resultCode"));
            textPager.setResultDesc(jsonObject.getString("resultDesc"));
            JSONArray jsonArray = jsonObject.getJSONArray("studentExamPapers");
            List<StudentExamPapers> studentExamPapersList = new ArrayList<StudentExamPapers>();
            for(int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                StudentExamPapers studentExamPapers = new StudentExamPapers();
                studentExamPapers.setPaperName(jsonObject1.getString("paperName"));
                studentExamPapers.setCourseId(jsonObject1.getString("courseId"));
                studentExamPapers.setPersonScore(jsonObject1.getString("personScore"));
                JSONArray jsonArray1 = jsonObject1.getJSONArray("examPaperUrls");
                List<ExamPaperUrls> examPaperUrlsList = new ArrayList<ExamPaperUrls>();
                for(int j=0;j<jsonArray1.length();j++){
                    JSONObject jsonObject2 = jsonArray1.getJSONObject(j);
                    ExamPaperUrls examPaperUrls = new ExamPaperUrls();
                    examPaperUrls.setEcPage(jsonObject2.getString("ecPage"));
                    examPaperUrls.setPageUrl(jsonObject2.getString("pageUrl"));
                    examPaperUrls.setPrefixUrl(jsonObject2.optString("prefixUrl"));
                    examPaperUrlsList.add(examPaperUrls);
                }
                studentExamPapers.setExamPaperUrlsList(examPaperUrlsList);
                JSONArray jsonArray2 = jsonObject1.getJSONArray("examQuestionPapers");
                List<ExamQuestionPapers> examQuestionPapersList = new ArrayList<ExamQuestionPapers>();
                for(int k=0;k<jsonArray2.length();k++){
                    JSONObject jsonObject2 = jsonArray2.getJSONObject(k);
                    ExamQuestionPapers examQuestionPapers = new ExamQuestionPapers();
                    examQuestionPapers.setEcPage(jsonObject2.getString("ecPage"));
                    examQuestionPapers.setAnswerIpxywh(jsonObject2.getString("answerIpxywh"));
                    examQuestionPapers.setDisplayIndex(jsonObject2.getString("displayIndex"));
                    examQuestionPapers.setDisplayName(jsonObject2.getString("displayName"));
                    examQuestionPapers.setFullScore(jsonObject2.getString("fullScore"));
                    examQuestionPapers.setScore(jsonObject2.getString("score"));
                    examQuestionPapers.setSeId(jsonObject2.getString("seId"));
                    examQuestionPapersList.add(examQuestionPapers);
                }
                studentExamPapers.setExamQuestionPapersList(examQuestionPapersList);
                studentExamPapersList.add(studentExamPapers);
            }
            textPager.setStudentExamPaperslist(studentExamPapersList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return textPager;
    }
}
