package woodnetwork.hebg3.com.woodnetwork.WoDe.bean;

import com.google.gson.annotations.Expose;

/**
 * Created by ty on 2016/9/5 0005.
 */

public class UserInfo {
    @Expose
    public String login_name;
    @Expose
    public String phone;
    @Expose
    public String mail;
    @Expose
    public int company_flag;
    @Expose
    public UserInfo_company company;
    @Expose
    public UserInfo_personal personal;

}
