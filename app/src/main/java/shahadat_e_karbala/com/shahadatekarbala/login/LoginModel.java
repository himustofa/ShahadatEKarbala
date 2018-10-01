package shahadat_e_karbala.com.shahadatekarbala.login;

public class LoginModel {

    private String loginId;
    private String loginName;
    private String loginMobile;

    private String deviceName;
    private String locationName;
    private String latitude;
    private String longitude;

    private String createdById;
    private String updatedById;
    private String createdAt;

    public LoginModel(String loginId, String loginName, String loginMobile, String createdById, String updatedById, String createdAt) {
        this.loginId = loginId;
        this.loginName = loginName;
        this.loginMobile = loginMobile;
        this.createdById = createdById;
        this.updatedById = updatedById;
        this.createdAt = createdAt;
    }

    public LoginModel(String loginName, String loginMobile) {
        this.loginName = loginName;
        this.loginMobile = loginMobile;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginMobile() {
        return loginMobile;
    }

    public void setLoginMobile(String loginMobile) {
        this.loginMobile = loginMobile;
    }

    public String getCreatedById() {
        return createdById;
    }

    public void setCreatedById(String createdById) {
        this.createdById = createdById;
    }

    public String getUpdatedById() {
        return updatedById;
    }

    public void setUpdatedById(String updatedById) {
        this.updatedById = updatedById;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
