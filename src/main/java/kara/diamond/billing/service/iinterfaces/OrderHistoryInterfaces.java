package kara.diamond.billing.service.iinterfaces;

import kara.diamond.billing.service.model.request.OrderHistory;

import java.util.List;
import java.util.Map;

public interface OrderHistoryInterfaces {
    public String saveOrderHistory(List<OrderHistory> orderHistory) throws Exception;

//    public  String getUserTokenOrderList(OrderHistory orderHistory) throws  Exception;
}
