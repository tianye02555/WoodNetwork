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
    public String company_flag;
    @Expose
    public String seller_flag;
    @Expose
    public String status;
    @Expose
    public String user_name;

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", login_name='" + login_name + '\'' +
                ", password='" + password + '\'' +
                ", company_flag='" + company_flag + '\'' +
                ", seller_flag='" + seller_flag + '\'' +
                ", status='" + status + '\'' +
                ", user_name='" + user_name + '\'' +
                '}';
    }
}
