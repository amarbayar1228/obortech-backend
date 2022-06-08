package kara.diamond.billing.service.logic;

import kara.diamond.billing.service.base.BaseDatabaseService;
import kara.diamond.billing.service.base.NumericHelper;
import kara.diamond.billing.service.entity.OrderHistoryEntity;
import kara.diamond.billing.service.iinterfaces.OrderHistoryInterfaces;
import kara.diamond.billing.service.model.request.OrderHistory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderHistoryLogic  extends BaseDatabaseService implements OrderHistoryInterfaces {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(OrderHistoryLogic.class);

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public String saveOrderHistory(List<OrderHistory> orderHistory) throws Exception{
        String result = "";
        try {
            for (int i = 0; i < orderHistory.size(); i++){
                OrderHistoryEntity order1 = new OrderHistoryEntity();

                order1.setPkId(NumericHelper.generateKey());
                order1.setTitle(orderHistory.get(i).getTitle());
                order1.setDescription(orderHistory.get(i).getDescription());
                order1.setPrice(orderHistory.get(i).getPrice());
                order1.setQuantity(orderHistory.get(i).getQuantity());
                insert(order1);
            }

            result = "Амжилттай хадгалалаа.";
        }catch(Exception e){
            throw getDatabaseException(e);
        }
        return result;
    }

    public List<OrderHistory> getOrderList()throws Exception{

        List<OrderHistoryEntity> orderHistoryEntity;
        String jpql = "SELECT a FROM OrderHistoryEntity a";
        List<OrderHistory> orderList = new ArrayList<>();
        orderHistoryEntity = getByQuery(OrderHistoryEntity.class, jpql);
        for (OrderHistoryEntity obj : orderHistoryEntity){
            OrderHistory order = new OrderHistory();
            order.setPkId(String.valueOf(obj.getPkId()));
            order.setTitle(obj.getTitle());
            order.setDescription(obj.getDescription());
            order.setPrice(obj.getPrice());
            order.setQuantity(obj.getQuantity());
            orderList.add(order);
        }
        return orderList;
    }

}
