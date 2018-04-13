package net.iclassmate.teacherspace.constant;

/**
 * Created by xydbj on 2016/1/27.
 */
public class Constants {
    //        public static String ADDRESS = "http://app.iclassmate.cn:9000/";
    //    public static String ADDRESS = "http://app.iclassmate.cn:10080/";
    public static final String SHARED_PREFERENCES = "xyd";
    public static final String SHARED_WEIKE = "xyd_weike";

    public static String FIRST_LOGIN = "first_login";

    public static String ADDRESS = "http://app.ruilongjiaoyu.com:10080/";
//    public static String ADDRESS = "http://app.iclassmate.cn:10080/";
    //public static String ADDRESS = "http://192.168.1.120:10080/";
    //public static String ADDRESS = "http://192.168.1.201:10080/";

    public static String BASE_URL_DM = "http://base.iclassmate.cn:8586/";
    public static String BASE_URL = "http://app.ruilongjiaoyu.com:10080/";

    //    public static String BASE_LOGIN_URL = "http://192.168.1.135:10080/teacherlogin/";

    public static String BASE_LOGIN_URL = ADDRESS + "teacherlogin/";
    //public static String BASE_LOGIN_URL = "http://192.168.1.201:10080/teacherlogin/";
    //    public static String BASE_LOGIN_URL = "http://192.168.1.120:10080/teacherlogin/";

    //    public static String BASE_LOGIN_URL = ADDRESS + "teacherlogin/";

    public static String LOGIN_SEARCHSCHOOL_DM_URL = BASE_URL_DM + "BaseCenter/rest/service/school/searchpy/";
    public static String GET_SCHOOL_SERVERLIST_DM = BASE_URL_DM + "BaseCenter/rest/service/appServer";
    public static String LOGIN_SEARCHSCHOOLCONFIG_URL = BASE_URL + "sac/getschoolconfig";

    //单科
    public static String SINGLE_LESSON = ADDRESS + "querysingleexam/";
    public static String SINGLE_LESSON2 = ADDRESS + "querysingleexam/%s/%s/%s/%s";
    //public static String EXAM=ADDRESS+"queryexam";
    //考试
    public static String EXAM = ADDRESS + "queryexam";
    //试卷  http:// x.x.x.x:port/getexampaper/{schoolId}/{meId}/{seId}/{studentId}
    public static String TEST_PAPER_BASE = ADDRESS + "getexampaper";
    public static String TEST_PAPER = TEST_PAPER_BASE + "/%s/%s/%s/%s";
    public static String APP_CACHE_NAME = "xyd";

    /**
     * 全科
     */
    // public static String GENERAL = "http://192.168.1.201:10080/querymultiexam/";
    public static String GENERAL = ADDRESS + "querymultiexam/";
    //请求试卷的url
    public static String GENERAL_TEXT = ADDRESS + "getexampaper/";
    //试卷url前缀（默认地址）
    public static String DEFAULT_PREFIXURL = "http://qdfile1.iclassmate.cn";
    /**
     * 通知
     */
    public static String NOTICE_URL = ADDRESS + "getmessage";
    /**
     * 短信验证
     */
    public static String SMS_URL = ADDRESS + "getverifycode";
    public static String SMS_VERIFY_URL = ADDRESS + "validateverifycode";

    /**
     * 修改密码
     */
    public static String CHANGE_PWD_URL = ADDRESS + "changeorresetpwd";
    /**
     * 版本更新
     */
    public static String UPDATE_URL = ADDRESS + "getversion/android";

    //http:// x.x.x.x:port/getverifycode/{phoneNum}
    public static String SENDUSERCODE_URL = ADDRESS + "getphonenum";
    /**
     * 收集用户登录信息
     */
    public static String COLLECT_USERINFO_URL = ADDRESS + "userloginfo";
}
