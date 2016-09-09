package woodnetwork.hebg3.com.woodnetwork.WoDe.bean;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * Created by ty on 2016/9/5 0005.
 */

public class UserInfo_personal {
    @Expose
    public String name;
    @Expose
    public String card;
    @Expose
    public List<String> img;
}
