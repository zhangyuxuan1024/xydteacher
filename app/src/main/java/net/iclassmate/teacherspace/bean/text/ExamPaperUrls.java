package net.iclassmate.teacherspace.bean.text;

/**
 * Created by xydbj on 2016.3.23.
 */
public class ExamPaperUrls {
    private String ecPage;
    private String pageUrl;
    private String prefixUrl;

    public String getEcPage() {
        return ecPage;
    }

    public void setEcPage(String ecPage) {
        this.ecPage = ecPage;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public String getPrefixUrl() {
        return prefixUrl;
    }

    public void setPrefixUrl(String prefixUrl) {
        this.prefixUrl = prefixUrl;
    }

    @Override
    public String toString() {
        return "ExamPaperUrls{" +
                "ecPage='" + ecPage + '\'' +
                ", pageUrl='" + pageUrl + '\'' +
                ", prefixUrl='" + prefixUrl + '\'' +
                '}';
    }
}
