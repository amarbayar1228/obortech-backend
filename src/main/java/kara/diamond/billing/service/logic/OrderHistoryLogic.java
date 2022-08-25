package kara.diamond.billing.service.logic;

import kara.diamond.billing.service.base.BaseDatabaseService;
import kara.diamond.billing.service.base.NumericHelper;
import kara.diamond.billing.service.entity.LoginUserEntity;
import kara.diamond.billing.service.entity.OrderHistoryEntity;
import kara.diamond.billing.service.entity.OrgUsersEntity;
import kara.diamond.billing.service.entity.PayInsentiveEntity;
import kara.diamond.billing.service.iinterfaces.OrderHistoryInterfaces;
import kara.diamond.billing.service.model.request.*;
import kara.diamond.billing.service.model.response.GroupBusinessModel;
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
            loginUserEntities= getByQuery(LoginUserEntity.class, jpql);
            List<LoginUser> loginUserList = new ArrayList<>();

            List<OrderHistory> orderHistory = orderHistoryToken.getProduct();
            try {
                long orderId2 = NumericHelper.generateKey();

                int sumGroup = 0;
                int sum = 0;
                for (int i = 0; i < orderHistory.size(); i++) {
                    OrderHistoryEntity order1 = new OrderHistoryEntity();


                    order1.setDate(java.time.LocalDate.now().toString());

                    order1.setOrderId(orderId2);

                    order1.setPkId(NumericHelper.generateKey());
                    order1.setItemId(Long.parseLong(orderHistory.get(i).getPkId()));
                    order1.setTitle(orderHistory.get(i).getTitle());
                    order1.setState(orderHistory.get(i).getState());
                    order1.setCnt(orderHistory.get(i).getCnt());
                    //OrgId
                    order1.setOrgId(orderHistoryToken.getOrgId());
                    order1.setPaymentMethod(orderHistoryToken.getPaymentMethod());
//                  order1.setInsentStatus(orderHistory.get(i).getInsentStatus());

                    order1.setUserPkid(loginUserEntities.get(0).getPkId().toString());
                    order1.setDescription(orderHistory.get(i).getDescription());
                    order1.setPrice(orderHistory.get(i).getPrice());
                    order1.setQuantity(orderHistory.get(i).getQuantity());

                    if(orderHistory.get(i).getState() != null && orderHistory.get(i).getState().equals("group")){

                        System.out.println("is group: "+orderHistory.get(i).getState());
//                        if(orderHistory.get(i).getState() != null && obj.getState().equals("group")){


                        jpql = "SELECT new kara.diamond.billing.service.model.response.GroupBusinessModel(A.pkId, A.title, A.status, A.description, A.cnt, A.others, A.itemPriceTotal, B.itemPkId, B.itemPriceD, B.itemCnt, C.title as itemTitle, C.quantity as itemQuantity, C.description as itemDescription, C.price as itemPrice)   "
                                + "FROM GroupItemHeaderEntity A  "
                                + "LEFT JOIN GroupItemDetailEntity B ON A.pkId = B.groupItemHeaderPkId  "
                                + "LEFT JOIN ItemEntity C ON C.pkId = B.itemPkId where B.groupItemHeaderPkId = '"+orderHistory.get(i).getPkId()+"'";
                        List<GroupBusinessModel> result3 = getByQuery(GroupBusinessModel.class, jpql.toString(), null);
                        int totalPirce2 = 0;
                        for (int ki = 0; ki < result3.size(); ki++) {

//                            System.out.println("getItemPriceD ===>>>> " + result3.get(ki).getItemPriceD());
//                            System.out.println("getItemCnt() ===>>>> " + result3.get(ki).getItemCnt());
                            totalPirce2 += result3.get(ki).getItemCnt() * result3.get(ki).getItemPriceD();

                        }


//                        System.out.println("GrouptotalPirce: " + totalPirce2);
//                        System.out.println("group Cnt: " + orderHistory.get(i).getCnt());

                        sumGroup += orderHistory.get(i).getCnt() * totalPirce2;
//                        System.out.println("Sum===>> " + sumGroup);
//                        sumGroup +=
                    }
                    sum += orderHistory.get(i).getCnt() * orderHistory.get(i).getPrice();
                    insert(order1);
                }
//                System.out.println("final total: "+ (sum + sumGroup));
                PayInsentiveEntity payIns = new PayInsentiveEntity();
                String jpql2 = "SELECT a FROM  OrgUsersEntity a where a.userToken = '"+loginUserEntities.get(0).getPkId().toString()+"'";
                List<OrgUsersEntity> orgUsersEntities;
                orgUsersEntities = getByQuery(OrgUsersEntity.class, jpql2);
//                System.out.println("orgUsersEntities Insentive%%%%%: " + orgUsersEntities.get(0).getInsentive());

                for(OrgUsersEntity obj : orgUsersEntities){
                    if(obj.getOrgId().equals(orderHistoryToken.getOrgId())){
                        System.out.println("orgId: " + obj.getInsentive());
                        Float huvi = Float.parseFloat(String.valueOf((sum + sumGroup))) / 100 * obj.getInsentive();
                    System.out.println("huvi: "+huvi);
                    payIns.setPkId(NumericHelper.generateKey());
                    payIns.setUserPkId(Long.valueOf(loginUserEntities.get(0).getPkId().toString()));
                    payIns.setDate(java.time.LocalDate.now().toString());
                    payIns.setFee(huvi);
                    payIns.setOrgId(orderHistoryToken.getOrgId());
                    payIns.setStatus(1);
                    payIns.setPayMethod(orderHistoryToken.getPaymentMethod());
                    insert(payIns);
                    }
                }

                result = "Амжилттай хадгалалаа.";


            } catch (Exception e) {
                throw getDatabaseException(e);
            }
        return result;
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public List<PayInsentive> getPayInsentive(PayInsentive payInsentive) throws Exception{

        List<PayInsentiveEntity> payInsentiveEntities;
        String jpql = "SELECT a FROM PayInsentiveEntity a where a.userPkId = '"+payInsentive.getUserPkId()+"'";

        List<PayInsentiveEntity> payInsentiveEntities1 = getByQuery(PayInsentiveEntity.class, jpql);

        List<PayInsentive> payList = new ArrayList<>();
        for (PayInsentiveEntity obj: payInsentiveEntities1){
            PayInsentive payInsentive1 = new PayInsentive();

            payInsentive1.setPayMethod(obj.getPayMethod());
            payInsentive1.setDate(obj.getDate());
            payInsentive1.setFee(obj.getFee());
            payInsentive1.setOrgId(obj.getOrgId());
            payInsentive1.setStatus(obj.getStatus());
            payInsentive1.setPkId(String.valueOf(obj.getPkId()));

            payList.add(payInsentive1);
        }


        return  payList;

    }
//    @Override
//    @Transactional(propagation = Propagation.REQUIRED)
//    public String saveOrderHistory(OrderHistoryToken orderHistoryToken) throws Exception {
//        List<LoginUserEntity> loginUserEntities;
//        String result = "";
//        String jpql = "SELECT a FROM  LoginUserEntity a where a.token = '"+orderHistoryToken.getToken()+"'";
//
////            System.out.println("query: "+orderHistoryToken.getToken());
//        loginUserEntities= getByQuery(LoginUserEntity.class, jpql);
//        List<LoginUser> loginUserList = new ArrayList<>();
//
////        System.out.println(orderHistoryToken.getToken());
////        System.out.println(
////                "\n products: "+orderHistoryToken.getProduct().get(0));
//        List<OrderHistory> orderHistory = orderHistoryToken.getProduct();
//        try {
//
//            long orderId2 = NumericHelper.generateKey();
//
//            for (int i = 0; i < orderHistory.size(); i++) {
//                OrderHistoryEntity order1 = new OrderHistoryEntity();
//
//
//                order1.setDate(java.time.LocalDate.now().toString());
//
//                order1.setOrderId(orderId2);
//
//                order1.setPkId(NumericHelper.generateKey());
//                order1.setItemId(Long.parseLong(orderHistory.get(i).getPkId()));
//                order1.setTitle(orderHistory.get(i).getTitle());
//                order1.setState(orderHistory.get(i).getState());
//                order1.setCnt(orderHistory.get(i).getCnt());
//                order1.setUserPkid(loginUserEntities.get(0).getPkId().toString());
//                order1.setDescription(orderHistory.get(i).getDescription());
//                order1.setPrice(orderHistory.get(i).getPrice());
//                order1.setQuantity(orderHistory.get(i).getQuantity());
//
//                insert(order1);
//            }
//            result = "Амжилттай хадгалалаа.";
//
//        } catch (Exception e) {
//            throw getDatabaseException(e);
//        }
//        return result;
//    }



    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public String saveOrderHistoryNoId(OrderHistoryToken orderHistoryToken) throws Exception {

        String result = "amjiltggui";
        try {
            long orderId2 = NumericHelper.generateKey();
            List<OrderHistory> orderHistory = orderHistoryToken.getProduct();
            for (int i = 0; i < orderHistory.size(); i++) {
                OrderHistoryEntity order1 = new OrderHistoryEntity();
                order1.setOrderId(orderId2);

                order1.setDate(java.time.LocalDate.now().toString());

                order1.setPkId(NumericHelper.generateKey());
                order1.setItemId(Long.parseLong(orderHistory.get(i).getPkId()));
                order1.setState(orderHistory.get(i).getState());
                order1.setOrgId(orderHistoryToken.getOrgId());
//                order1.setPkId(NumericHelper.generateKey());

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
//        System.out.println(" ==================> ene history req"+ orderHistory.getUserPkid());

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

            OrderHistory order = new OrderHistory();

            order.setPkId(String.valueOf(obj.getPkId()));
            order.setTitle(obj.getTitle());
            order.setDescription(obj.getDescription());
            order.setCnt(obj.getCnt());
            order.setPrice(obj.getPrice());
            order.setOrgId(obj.getOrgId());
            order.setQuantity(obj.getQuantity());
            order.setDate(obj.getDate());
            order.setOrderId(obj.getOrderId().toString());

            if(obj.getState() != null && obj.getState().equals("group")){
                jpql = "SELECT new kara.diamond.billing.service.model.response.GroupBusinessModel(A.pkId, A.title, A.status, A.description, A.cnt, A.others, A.itemPriceTotal, B.itemPkId, B.itemPriceD, B.itemCnt, C.title as itemTitle, C.quantity as itemQuantity, C.description as itemDescription, C.price as itemPrice)   "
                        + "FROM GroupItemHeaderEntity A  "
                        + "LEFT JOIN GroupItemDetailEntity B ON A.pkId = B.groupItemHeaderPkId  "
                        + "LEFT JOIN ItemEntity C ON C.pkId = B.itemPkId where B.groupItemHeaderPkId = '"+obj.getItemId()+"'";
                List<GroupBusinessModel> result = getByQuery(GroupBusinessModel.class, jpql.toString(), null);
                int totalPirce2 = 0;
                for (int i = 0; i < result.size(); i++) {

//                    System.out.println("getItemPriceD ===>>>> " + result.get(i).getItemPriceD());
//                    System.out.println("getItemCnt() ===>>>> " + result.get(i).getItemCnt());
                    totalPirce2 += result.get(i).getItemCnt() * result.get(i).getItemPriceD();

                }
//                System.out.println("totalPirce2" + totalPirce2);
                order.setPrice(totalPirce2);

                order.setGbm(result);
            }

//            System.err.println("sizeoid: "+obj.getOrderId());

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
//        System.err.println("size: "+byOrder.size());
        for(Map.Entry<String, List<OrderHistory>> row : byOrder.entrySet()){
//            System.out.println(row.getValue().get(0).toString());
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
//        System.err.println("size: "+byDate.size());

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

//        for (OrderHistoryEntity obj : orderHistoryEntity) {
//            System.out.println("orderid: "+obj.getOrderId()+", pkid: "+obj.getPkId()+" itemid: "+obj.getItemId());
//        }

        for (OrderHistoryEntity obj : orderHistoryEntity) {
            //System.err.println("passs");
            OrderHistory order = new OrderHistory();

            order.setPkId(String.valueOf(obj.getPkId()));
            order.setTitle(obj.getTitle());
            order.setDescription(obj.getDescription());
            order.setCnt(obj.getCnt());
            order.setOrgId(obj.getOrgId());
            System.out.println("cnt: =========>  "+obj.getCnt());
            order.setPrice(obj.getPrice());
            order.setQuantity(obj.getQuantity());
            order.setDate(obj.getDate());
            order.setUserPkid(obj.getUserPkid());
            order.setOrderId(obj.getOrderId().toString());
            //System.err.println("sizeoid: "+obj);

            if(obj.getState() != null && obj.getState().equals("group")){
                //System.out.println("pk id: "+obj.getPkId());
                jpql = "SELECT new kara.diamond.billing.service.model.response.GroupBusinessModel(A.pkId, A.title, A.status, A.description, A.cnt, A.others, A.itemPriceTotal, B.itemPkId, B.itemPriceD, B.itemCnt, C.title as itemTitle, C.quantity as itemQuantity, C.description as itemDescription, C.price as itemPrice)   "
                        + "FROM GroupItemHeaderEntity A  "
                        + "LEFT JOIN GroupItemDetailEntity B ON A.pkId = B.groupItemHeaderPkId  "
                        + "LEFT JOIN ItemEntity C ON C.pkId = B.itemPkId where B.groupItemHeaderPkId = '"+obj.getItemId()+"'";
                List<GroupBusinessModel> result = getByQuery(GroupBusinessModel.class, jpql.toString(), null);
                int totalPirce2 = 0;
                for (int i = 0; i < result.size(); i++) {

                    System.out.println("getItemPriceD ===>>>> " + result.get(i).getItemPriceD());
                    System.out.println("getItemCnt() ===>>>> " + result.get(i).getItemCnt());
                    totalPirce2 += result.get(i).getItemCnt() * result.get(i).getItemPriceD();

                }
                System.out.println("totalPirce2" + totalPirce2);
                    order.setPrice(totalPirce2);
//
                order.setGbm(result);

            }


//            System.out.println(obj.getOrderId());

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
//        System.err.println("size: "+byOrder.size());

        for(Map.Entry<String, List<OrderHistory>> row : byOrder.entrySet()){
//            System.out.println(row.getValue().get(0).toString());
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
//        System.err.println("size: "+byDate.size());

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