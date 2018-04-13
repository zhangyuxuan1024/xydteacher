package net.iclassmate.teacherspace.bean.login;

/**
 * Created by xyd on 2016/2/23.
 */
public class Login_use {
    private String usercode;
    private String password;
    private String urlPath;
    public Login_use() {
        super();
    }

    public Login_use(String usercode, String password, String urlPath) {
        this.usercode = usercode;
        this.password = password;
        this.urlPath = urlPath;
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrlPath() {
        return urlPath;
    }

    public void setUrlPath(String urlPath) {
        this.urlPath = urlPath;
    }
}
