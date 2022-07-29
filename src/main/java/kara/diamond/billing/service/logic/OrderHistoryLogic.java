package kara.diamond.billing.service.logic;

import kara.diamond.billing.service.base.BaseDatabaseService;
import kara.diamond.billing.service.base.NumericHelper;
import kara.diamond.billing.service.entity.LoginUserEntity;
import kara.diamond.billing.service.entity.OrderHistoryEntity;
import kara.diamond.billing.service.iinterfaces.OrderHistoryInterfaces;
import kara.diamond.billing.service.model.request.LoginUser;
import kara.diamond.billing.service.model.request.OrderHistory;
import kara.diamond.billing.service.model.request.OrderHistoryToken;
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

@Service
public class OrderHistoryLogic  extends BaseDatabaseService implements OrderHistoryInterfaces {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(OrderHistoryLogic.class);

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public String saveOrderHistory(OrderHistoryToken orderHistoryToken) throws Exception {
        List<LoginUserEntity> loginUserEntities;
        String result = "";
        String jpql = "SELECT a FROM  LoginUserEntity a where a.token = '"+orderHistoryToken.getToken()+"'";

//            System.out.println("query: "+orderHistoryToken.getToken());
            loginUserEntities= getByQuery(LoginUserEntity.class, jpql);
            List<LoginUser> loginUserList = new ArrayList<>();

//        System.out.println(orderHistoryToken.getToken());
//        System.out.println(
//                "\n products: "+orderHistoryToken.getProduct().get(0));
            List<OrderHistory> orderHistory = orderHistoryToken.getProduct();
            try {
                long orderId2 = NumericHelper.generateKey();

                for (int i = 0; i < orderHistory.size(); i++) {
                    OrderHistoryEntity order1 = new OrderHistoryEntity();
                    order1.setOrderId(orderId2);
                    order1.setDate(java.time.LocalDate.now().toString());
                    order1.setPkId(NumericHelper.generateKey());
                    order1.setTitle(orderHistory.get(i).getTitle());
                    order1.setCnt(orderHistory.get(i).getCnt());
                    order1.setUserPkid(loginUserEntities.get(0).getPkId().toString());
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

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public String saveOrderHistoryNoId(List<OrderHistory> orderHistory) throws Exception {

        String result = "amjiltggui";
        try {
            long orderId2 = NumericHelper.generateKey();
            for (int i = 0; i < orderHistory.size(); i++) {
                OrderHistoryEntity order1 = new OrderHistoryEntity();
                order1.setOrderId(orderId2);
                order1.setDate(java.time.LocalDate.now().toString());
                order1.setPkId(NumericHelper.generateKey());
                order1.setTitle(orderHistory.get(i).getTitle());
                order1.setCnt(orderHistory.get(i).getCnt());
//                order1.setUserPkid(orderHistory.getUser);
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
    // user iDgaar n shalgaj awchirj bga Order list
    @Transactional(propagation = Propagation.REQUIRED)
    public Map<String, List<List<OrderHistory>>> getUserTokenOrderList(OrderHistory orderHistory ) throws Exception {
        System.out.println(" ==================> ene history req"+ orderHistory.getUserPkid());


        List<OrderHistoryEntity> orderHistoryEntity;
//        String jpql = "SELECT a FROM OrderHistoryEntity a";
        String jpql = "SELECT a FROM  OrderHistoryEntity a where a.userPkid = '"+orderHistory.getUserPkid()+"'";
        List<OrderHistory> orderList = new ArrayList<>();
        List<List<OrderHistory>> orderListList = new ArrayList<>();
        orderHistoryEntity = getByQuery(OrderHistoryEntity.class, jpql);

        Map<String, List<List<OrderHistory>>> byDate = new HashMap<>();
        Map<String, List<OrderHistory>> byOrder = new HashMap<>();
        List<OrderHistory> byOrderId = new ArrayList<>();

        for (OrderHistoryEntity obj : orderHistoryEntity) {
            System.err.println("passs");
            OrderHistory order = new OrderHistory();

            order.setPkId(String.valueOf(obj.getPkId()));
            order.setTitle(obj.getTitle());
            order.setDescription(obj.getDescription());
            order.setCnt(obj.getCnt());
            order.setPrice(obj.getPrice());
            order.setQuantity(obj.getQuantity());
            order.setDate(obj.getDate());
            order.setOrderId(obj.getOrderId().toString());

            System.err.println("sizeoid: "+obj.getOrderId());

            if(obj.getOrderId() != null){
                if(!byOrder.containsKey(obj.getOrderId().toString())){
                    orderList = new ArrayList<>();
                    orderList.add(order);
                    byOrder.put(obj.getOrderId().toString(), orderList);
                }else{
                    orderList = byOrder.get(obj.getOrderId().toString());
                    orderList.add(order);
                    byOrder.put(obj.getOrderId().toString(), orderList);
                }
            }

        }
        System.err.println("size: "+byOrder.size());
        for(Map.Entry<String, List<OrderHistory>> row : byOrder.entrySet()){
            System.out.println(row.getValue().get(0).toString());
            if(row.getValue().get(0).getDate() != null){
                if(!byDate.containsKey(row.getValue().get(0).getDate())){
                    orderListList = new ArrayList<>();
                    orderListList.add(row.getValue());
                    byDate.put(row.getValue().get(0).getDate(), orderListList);
                }else{
                    orderListList = byDate.get(row.getValue().get(0).getDate());
                    orderListList.add(row.getValue());
                    byDate.put(row.getValue().get(0).getDate(), orderListList);
                }
            }
        }
        System.err.println("size: "+byDate.size());

        return byDate;
    }

    // user order history-iig bvgdiin awchirj bga
    public Map<String, List<List<OrderHistory>>> getOrderList() throws Exception {
        List<OrderHistoryEntity> orderHistoryEntity;
        String jpql = "SELECT a FROM OrderHistoryEntity a";
        List<OrderHistory> orderList = new ArrayList<>();
        List<List<OrderHistory>> orderListList = new ArrayList<>();
        orderHistoryEntity = getByQuery(OrderHistoryEntity.class, jpql);

        Map<String, List<List<OrderHistory>>> byDate = new HashMap<>();
        Map<String, List<OrderHistory>> byOrder = new HashMap<>();
        List<OrderHistory> byOrderId = new ArrayList<>();

        for (OrderHistoryEntity obj : orderHistoryEntity) {
            System.err.println("passs");
            OrderHistory order = new OrderHistory();

            order.setPkId(String.valueOf(obj.getPkId()));
            order.setTitle(obj.getTitle());
            order.setDescription(obj.getDescription());
            order.setCnt(obj.getCnt());
            order.setPrice(obj.getPrice());
            order.setQuantity(obj.getQuantity());
            order.setDate(obj.getDate());
            order.setUserPkid(obj.getUserPkid());
            order.setOrderId(obj.getOrderId().toString());
            System.err.println("sizeoid: "+obj.getOrderId());
            if(obj.getOrderId() != null){
                if(!byOrder.containsKey(obj.getOrderId().toString())){
                    orderList = new ArrayList<>();
                    orderList.add(order);
                    byOrder.put(obj.getOrderId().toString(), orderList);
                }else{
                    orderList = byOrder.get(obj.getOrderId().toString());
                    orderList.add(order);
                    byOrder.put(obj.getOrderId().toString(), orderList);
                }
            }

        }
        System.err.println("size: "+byOrder.size());
        for(Map.Entry<String, List<OrderHistory>> row : byOrder.entrySet()){
            System.out.println(row.getValue().get(0).toString());
            if(row.getValue().get(0).getDate() != null){
                if(!byDate.containsKey(row.getValue().get(0).getDate())){
                    orderListList = new ArrayList<>();
                    orderListList.add(row.getValue());
                    byDate.put(row.getValue().get(0).getDate(), orderListList);
                }else{
                    orderListList = byDate.get(row.getValue().get(0).getDate());
                    orderListList.add(row.getValue());
                    byDate.put(row.getValue().get(0).getDate(), orderListList);
                }
            }
        }
        System.err.println("size: "+byDate.size());

        return byDate;
    }

//    public Map<String, List<List<OrderHistory>>> getOrderList() throws Exception {
//
//        List<OrderHistoryEntity> orderHistoryEntity;
//        String jpql = "SELECT a FROM OrderHistoryEntity a";
//        List<OrderHistory> orderList = new ArrayList<>();
//        List<List<OrderHistory>> orderListList = new ArrayList<>();
//        orderHistoryEntity = getByQuery(OrderHistoryEntity.class, jpql);
//        Map<String, List<List<OrderHistory>>> byDate = new HashMap<>();
//        Map<String, List<OrderHistory>> byOrder = new HashMap<>();
//        List<OrderHistory> byOrderId = new ArrayList<>();
//
//        for (OrderHistoryEntity obj : orderHistoryEntity) {
//            System.err.println("passs");
//            OrderHistory order = new OrderHistory();
//
//
//            order.setPkId(String.valueOf(obj.getPkId()));
//            order.setTitle(obj.getTitle());
//            order.setDescription(obj.getDescription());
//            order.setCnt(obj.getCnt());
//            order.setPrice(obj.getPrice());
//            order.setQuantity(obj.getQuantity());
//            order.setDate(obj.getDate());
//            order.setOrderId(obj.getOrderId().toString());
//            System.err.println("sizeoid: "+obj.getOrderId());
//            if(obj.getOrderId() != null){
//                if(!byOrder.containsKey(obj.getOrderId().toString())){
//                    orderList = new ArrayList<>();
//                    orderList.add(order);
//                    byOrder.put(obj.getOrderId().toString(), orderList);
//                }else{
//                    orderList = byOrder.get(obj.getOrderId().toString());
//                    orderList.add(order);
//                    byOrder.put(obj.getOrderId().toString(), orderList);
//                }
//            }
//
//        }
//        System.err.println("size: "+byOrder.size());
//        for(Map.Entry<String, List<OrderHistory>> row : byOrder.entrySet()){
//            System.out.println(row.getValue().get(0).toString());
//            if(row.getValue().get(0).getDate() != null){
//                if(!byDate.containsKey(row.getValue().get(0).getDate())){
//                    orderListList = new ArrayList<>();
//                    orderListList.add(row.getValue());
//                    byDate.put(row.getValue().get(0).getDate(), orderListList);
//                }else{
//                    orderListList = byDate.get(row.getValue().get(0).getDate());
//                    orderListList.add(row.getValue());
//                    byDate.put(row.getValue().get(0).getDate(), orderListList);
//                }
//            }
//        }
//        System.err.println("size: "+byDate.size());
//
//        return byDate;
//    }

//    public List<OrderArray> getOrderArray() throws Exception {
//        List<OrderArray> orderArrays = new ArrayList<>();
//        List<OrderHistoryModel> orderItemList = new ArrayList<>();
//        String jpql = "SELECT a FROM OrderHistoryEntity a";
//        List<OrderHistoryEntity> itemOrderList = getByQuery(OrderHistoryEntity.class, jpql);
//
//        return orderArrays;
//    }
}