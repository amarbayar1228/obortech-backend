package kara.diamond.billing.service.iinterfaces;

import kara.diamond.billing.service.model.request.OrderHistory;
import kara.diamond.billing.service.model.request.OrderHistoryToken;

import java.util.List;
import java.util.Map;

public interface OrderHistoryInterfaces {
    public String saveOrderHistory(OrderHistoryToken orderHistory) throws Exception;
    public String saveOrderHistoryNoId(OrderHistoryToken orderHistoryToken) throws Exception;
//    public  String getUserTokenOrderList(OrderHistory orderHistory) throws  Exception;
}
