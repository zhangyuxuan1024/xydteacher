package net.iclassmate.teacherspace.bean.login;

/**
 * 教师角色
 * Created by xyd on 2016/3/2.
 */
public class Roles {
    private int roleId;
    private String roleName;
    public Roles() {
        super();
    }
    public Roles(int roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
