package kara.diamond.billing.service.iinterfaces;

import kara.diamond.billing.service.model.request.OrderHistory;

import java.util.List;

public interface OrderHistoryInterfaces {
    public String saveOrderHistory(List<OrderHistory> orderHistory) throws Exception;
}
