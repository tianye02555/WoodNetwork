package woodnetwork.hebg3.com.woodnetwork.sysfunction.bean;

import com.google.gson.annotations.Expose;

/**
 * Created by Administrator on 2016/8/16 0016.
 */

public class User {
    @Expose
    public String id;
    @Expose
    public String login_name;
    @Expose
    public String password;
    @Expose
    public Integer company_flag;
    @Expose
    public Integer seller_flag;
    @Expose
    public Integer status;
    @Expose
    public String user_name;

}
