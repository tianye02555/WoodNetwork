package woodnetwork.hebg3.com.woodnetwork.ShoppingMall.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ty on 2016/8/27 0027.
 */

public class OrderAdd implements Serializable {
    @Expose
    public String number;
    @Expose
    public String creat_time;
    @Expose
    public Integer type;
    @Expose
    public Integer status;
    @Expose
    public String buyer;
    @Expose
    public String seller;
    @Expose
    public String delivery_area;
    @Expose
    public String receive_area;
    @Expose
    public Integer receive_type;
    @Expose
    public Double total;
    @Expose
    public ArrayList <OrderAdd_productsItem> products;


}
