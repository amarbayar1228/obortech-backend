package kara.diamond.billing.service.logic;

import kara.diamond.billing.service.base.BaseDatabaseService;
import kara.diamond.billing.service.base.NumericHelper;
import kara.diamond.billing.service.entity.ItemEntity;
import kara.diamond.billing.service.entity.OrderHistoryEntity;
import kara.diamond.billing.service.iinterfaces.OrderHistoryInterfaces;
import kara.diamond.billing.service.model.request.OrderHistory;
import kara.diamond.billing.service.model.response.ExampleArray;
import kara.diamond.billing.service.model.response.ItemModel;
import kara.diamond.billing.service.model.response.OrderArray;
import kara.diamond.billing.service.model.response.OrderHistoryModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderHistoryLogic  extends BaseDatabaseService implements OrderHistoryInterfaces {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(OrderHistoryLogic.class);

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public String saveOrderHistory(List<OrderHistory> orderHistory) throws Exception {
        String result = "";
        try {
            for (int i = 0; i < orderHistory.size(); i++) {

                OrderHistoryEntity order1 = new OrderHistoryEntity();
                order1.setDate(java.time.LocalDate.now().toString());
                order1.setPkId(NumericHelper.generateKey());
                order1.setTitle(orderHistory.get(i).getTitle());
                order1.setCnt(orderHistory.get(i).getCnt());
                order1.setDescription(orderHistory.get(i).getDescription());
                order1.setPrice(orderHistory.get(i).getPrice());
                order1.setQuantity(orderHistory.get(i).getQuantity());
                insert(order1);
            }

            result = "Амжилттай хадгалалаа.";
        } catch (Exception e) {
            throw getDatabaseException(e);
        }
        return result;
    }

    public Map<String, List<OrderHistory>> getOrderList() throws Exception {

        List<OrderHistoryEntity> orderHistoryEntity;
        String jpql = "SELECT a FROM OrderHistoryEntity a";
        List<OrderHistory> orderList = new ArrayList<>();
        orderHistoryEntity = getByQuery(OrderHistoryEntity.class, jpql);
        Map<String, List<OrderHistory>> byDate = new HashMap<>();
        for (OrderHistoryEntity obj : orderHistoryEntity) {
            System.err.println("passs");
            OrderHistory order = new OrderHistory();
            order.setPkId(String.valueOf(obj.getPkId()));
            order.setTitle(obj.getTitle());
            order.setDescription(obj.getDescription());
            order.setCnt(obj.getCnt());
            order.setPrice(obj.getPrice());
            order.setQuantity(obj.getQuantity());
            //orderList.add(order);
            if(!obj.getDate().equals(null)){
                if(!byDate.containsKey(obj.getDate())){
                    orderList = new ArrayList<>();
                    orderList.add(order);
                    byDate.put(obj.getDate(), orderList);
                }else{
                    orderList = byDate.get(obj.getDate());
                    orderList.add(order);
                    byDate.put(obj.getDate(), orderList);
                }
            }

        }
        System.err.println("size: "+byDate.size());
//        for (OrderHistoryEntity obj : orderHistoryEntity) {
//            OrderHistory order = new OrderHistory();
//            order.setPkId(String.valueOf(obj.getPkId()));
//            order.setTitle(obj.getTitle());
//            order.setDescription(obj.getDescription());
//            order.setCnt(obj.getCnt());
//            order.setPrice(obj.getPrice());
//            order.setQuantity(obj.getQuantity());
//            orderList.add(order);
//        }
        return byDate;
    }

    public List<OrderArray> getOrderArray() throws Exception {
        List<OrderArray> orderArrays = new ArrayList<>();
        List<OrderHistoryModel> orderItemList = new ArrayList<>();
        String jpql = "SELECT a FROM OrderHistoryEntity a";
        List<OrderHistoryEntity> itemOrderList = getByQuery(OrderHistoryEntity.class, jpql);

//        for (OrderHistoryEntity obj : itemOrderList) {
//            OrderHistoryModel order = new OrderHistoryModel();
//
//            order.setPkId(String.valueOf(obj.getPkId()));
//            order.setTitle(obj.getTitle());
//            order.setDescription(obj.getDescription());
//            order.setCnt(obj.getCnt());
//            order.setPrice(obj.getPrice());
//            order.setQuantity(obj.getQuantity());
////            order.setDate(obj.getDate());
//            orderItemList.add(order);
//
//            OrderArray orderArray = new OrderArray();
//            orderArray.setDate(obj.getDate());
//            orderArrays.add(orderArray);
//            orderArray.setList(orderItemList);
////            for(int i = 0; i < orderItemList.size(); i++){
////                if(orderItemList.get(i).getDate().equals(obj.getDate())){
////
////                }
////            }
////            orderArray.setList(orderItemList.stream().filter().collect(Collectors.));
//        }
        return orderArrays;
    }
}