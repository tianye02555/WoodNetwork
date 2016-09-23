package woodnetwork.hebg3.com.woodnetwork.DingDanGuanLi.bean;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ty on 2016/9/18 0018.
 */

public class ExceptionList_exceptionItem implements Serializable {
    @Expose
    public String author;
    @Expose
    public String content;
    @Expose
    public String time;
    @Expose
    public List<String> imgs;
    @Expose
    public Integer type;
}
