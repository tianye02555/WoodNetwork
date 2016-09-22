package woodnetwork.hebg3.com.woodnetwork.WoDe.presenter;

import woodnetwork.hebg3.com.woodnetwork.Interface.OnServiceBaceInterface;
import woodnetwork.hebg3.com.woodnetwork.Utils.CommonUtils;
import woodnetwork.hebg3.com.woodnetwork.Utils.MyRequestInfo;
import woodnetwork.hebg3.com.woodnetwork.WoDe.bean.ProductSellerList;
import woodnetwork.hebg3.com.woodnetwork.WoDe.bean.QuotationList;
import woodnetwork.hebg3.com.woodnetwork.WoDe.contract.ProductSellerListContract;
import woodnetwork.hebg3.com.woodnetwork.WoDe.model.ProductSellerListModel;
import woodnetwork.hebg3.com.woodnetwork.net.ResponseBody;

/**
 * Created by ty on 2016/9/7 0007.
 */

public class ProductSellerListPrecenter implements ProductSellerListContract.ProductSellerListPresenterInterface {
    private ProductSellerListContract.ProductSellerListViewInterface productSellerListView;
    private ProductSellerListModel productSellerListModel;

    public ProductSellerListPrecenter(ProductSellerListContract.ProductSellerListViewInterface productSellerListView) {
        if (null != productSellerListView) {
            this.productSellerListView = productSellerListView;
        }
        this.productSellerListView.setPresenter(this);
        productSellerListModel = new ProductSellerListModel();
    }

    @Override
    public void getProductSellerListData(MyRequestInfo myRequestInfo, final int flag) {
        if (0 == flag) {
            productSellerListView.showProgress();
        }
        productSellerListModel.getProductSellerListData(CommonUtils.getRequestInfo(myRequestInfo.req, myRequestInfo.req_meta), new OnServiceBaceInterface() {
            @Override
            public void onSuccess(Object object) {
                if (0 == flag) {
                    productSellerListView.closeProgress();
                    productSellerListView.showProductSellerListInfo((ProductSellerList) ((ResponseBody) object).obj);
                } else if (1 == flag) {
                    productSellerListView.refresh(((ProductSellerList) ((ResponseBody) object).obj));

                } else if (2 == flag) {
                    productSellerListView.loadMore(((ProductSellerList) ((ResponseBody) object).obj).products);
                }

            }

            @Override
            public void onFailed(String string) {
                productSellerListView.closeProgress();
                productSellerListView.showMessage(string);
            }
        });
    }

    @Override
    public void start() {

    }
}
