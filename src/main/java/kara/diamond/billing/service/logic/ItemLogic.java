package kara.diamond.billing.service.logic;

import kara.diamond.billing.service.base.BaseDatabaseService;
import kara.diamond.billing.service.base.NumericHelper;
import kara.diamond.billing.service.entity.GroupItemDetailEntity;
import kara.diamond.billing.service.entity.GroupItemHeaderEntity;
import kara.diamond.billing.service.entity.ItemEntity;
import kara.diamond.billing.service.iinterfaces.ItemInterfaces;
import kara.diamond.billing.service.model.request.GroupItemDetail;
import kara.diamond.billing.service.model.request.GroupItemHeader;
import kara.diamond.billing.service.model.request.GrouptRequest;
import kara.diamond.billing.service.model.request.Item;
import kara.diamond.billing.service.model.response.ExampleArray;
import kara.diamond.billing.service.model.response.GroupBusinessModel;
import kara.diamond.billing.service.model.response.GroupPBM;
import kara.diamond.billing.service.model.response.ItemModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.json.*;
import java.util.*;

@Service
public class ItemLogic extends BaseDatabaseService implements ItemInterfaces {
    private static final Logger logger = LoggerFactory.getLogger(ItemLogic.class);

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public String saveItem(Item item) throws Exception {
        String result = "";
        try {
            ItemEntity item1 = new ItemEntity();
            item1.setPkId(NumericHelper.generateKey());
            item1.setTitle(item.getTitle());
            item1.setCnt(item.getCnt());
            item1.setStatus(0);
            item1.setDescription(item.getDescription());
            item1.setPrice(item.getPrice());
            item1.setQuantity(item.getQuantity());
            insert(item1);
            result = "Амжилттай хадгалалаа.";

        } catch (Exception e) {
            throw getDatabaseException(e);
        }
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public String updateItem(Item item) throws Exception {
        String result = "amjiltgui";
        try {
            ItemEntity itemDataUpdate = getByPKey(ItemEntity.class, Long.parseLong(item.getPkId().toString()));
            itemDataUpdate.setTitle(item.getTitle());
            itemDataUpdate.setPrice(item.getPrice());
            itemDataUpdate.setCnt(item.getCnt());
            itemDataUpdate.setQuantity(item.getQuantity());
            itemDataUpdate.setDescription(item.getDescription());
            update(itemDataUpdate);
            result = "Amjilttai";

        } catch (Exception e) {
            getDatabaseException(e);
        }
        return result;
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public String updateStateItem(Item item) throws Exception {
        String result = "Error";
        try {
            ItemEntity itemDataUpdate = getByPKey(ItemEntity.class, Long.parseLong(item.getPkId().toString()));

            itemDataUpdate.setStatus(item.getStatus());
            itemDataUpdate.setOthers(item.getOthers());
            update(itemDataUpdate);
            result = "Success";

        } catch (Exception e) {
            getDatabaseException(e);
        }
        return result;
    }
public List<Item> getStatus1Item() throws Exception {

    List<ItemEntity> itemEntity;
    String jpql = "SELECT a FROM ItemEntity a";
    List<Item> itemList = new ArrayList<>();
    itemEntity = getByQuery(ItemEntity.class, jpql);

    for (ItemEntity obj : itemEntity) {
        if(obj.getStatus() == 1){
            Item item = new Item();
            item.setPkId(String.valueOf(obj.getPkId()));
            item.setTitle(obj.getTitle());
            item.setCnt(obj.getCnt());
            item.setStatus(obj.getStatus());
            item.setDescription(obj.getDescription());
            item.setPrice(obj.getPrice());
            item.setQuantity(obj.getQuantity());
            itemList.add(item);
        }

    }
    return itemList;
}

    public List<Item> getAllItem() throws Exception {

        List<ItemEntity> itemEntity;
        String jpql = "SELECT a FROM ItemEntity a";
        List<Item> itemList = new ArrayList<>();
        itemEntity = getByQuery(ItemEntity.class, jpql);

        for (ItemEntity obj : itemEntity) {
            Item item = new Item();
            item.setPkId(String.valueOf(obj.getPkId()));
            item.setTitle(obj.getTitle());
            item.setCnt(obj.getCnt());
            item.setStatus(obj.getStatus());
            item.setDescription(obj.getDescription());
            item.setPrice(obj.getPrice());
            item.setQuantity(obj.getQuantity());
            item.setOthers(obj.getOthers());
            itemList.add(item);
        }
        return itemList;
    }

    public List<Item> getItemState01() throws Exception {

        List<ItemEntity> itemEntity;
        String jpql = "SELECT a FROM ItemEntity a";
        List<Item> itemList = new ArrayList<>();
        itemEntity = getByQuery(ItemEntity.class, jpql);

        for (ItemEntity obj : itemEntity) {
            if(obj.getStatus() == 0 || obj.getStatus() == 1){
            Item item = new Item();
            item.setPkId(String.valueOf(obj.getPkId()));
            item.setTitle(obj.getTitle());
            item.setCnt(obj.getCnt());
            item.setStatus(obj.getStatus());
            item.setDescription(obj.getDescription());
            item.setPrice(obj.getPrice());
            item.setQuantity(obj.getQuantity());
            item.setOthers(obj.getOthers());
            itemList.add(item);
            }
        }
        return itemList;
    }

    public List<ExampleArray> getTestExample() throws Exception {
        List<ExampleArray> exampleArrays = new ArrayList<>();
        List<ItemModel> itemList = new ArrayList<>();
        String jpql = "SELECT a FROM ItemEntity a";
        List<ItemEntity> itemEntityList = getByQuery(ItemEntity.class, jpql);
        for (ItemEntity obj : itemEntityList) {
            ItemModel item = new ItemModel();

            item.setPkId(String.valueOf(obj.getPkId()));
            item.setTitle(obj.getTitle());
            item.setDescription(obj.getDescription());
            item.setPrice(obj.getPrice());
            item.setQuantity(obj.getQuantity());
            itemList.add(item);

            ExampleArray exampleArray = new ExampleArray();
            exampleArray.setDate(obj.getDate());
            exampleArrays.add(exampleArray);
            exampleArray.setList(itemList);
        }
        return exampleArrays;

    }



    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public String deleteItem(String pkId) throws Exception {

        try{
//            String jpql = "delete from ItemEntity a WHERE a.pkId = " + Long.parseLong(pkId) ;
//            executeNativeQuery(jpql);

            ItemEntity item = getByPKey(ItemEntity.class, Long.parseLong(pkId));
            System.out.println("selected item -------------------> "  + item.getTitle());
            delete((List<?>) item);

            return "Success";
        }catch (Exception e ) {
            e.printStackTrace();
            return "Error";
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public String saveGroupItems(GrouptRequest groupRequest) throws Exception {
        String result = "amjiltgui";

        try {
            GroupItemHeaderEntity groupItemHeader = new GroupItemHeaderEntity();
            groupItemHeader.setPkId(NumericHelper.generateKey());
            groupItemHeader.setTitle(groupRequest.getTitle());
            groupItemHeader.setDescription(groupRequest.getDescription());
            groupItemHeader.setCnt(1);

            List<GroupItemDetail> groupItemDtl = groupRequest.getGroupDetail();

            int sum = 0;
            for (int i = 0; i < groupItemDtl.size(); i++){
                GroupItemDetailEntity groupItemDetail = new GroupItemDetailEntity();
                groupItemDetail.setPkId(NumericHelper.generateKey());
                groupItemDetail.setGroupItemHeaderPkId(groupItemHeader.getPkId());
                groupItemDetail.setItemCnt(groupItemDtl.get(i).getItemCnt());

//                groupItemDetail.setItemPriceD(groupItemDtl.get(i).getItemPriceD());
                System.out.println("===========>>>>> price: "+groupItemDetail.getItemPriceD() + groupItemDetail.getItemPkId());

//                groupItemDetail.setGroupItemHeaderPkId(groupItemHeaderEntities.get(0).getPkId().toString());
                groupItemDetail.setItemPkId(Long.parseLong(groupItemDtl.get(i).getItemPkId()));
                groupItemDetail.setItemPriceD(groupItemDtl.get(i).getItemPriceD());
                List<ItemEntity> itemEntity;
                String jpql = "SELECT a FROM ItemEntity a where a.pkId = '"+ groupItemDtl.get(i).getItemPkId() +"'";

                itemEntity = getByQuery(ItemEntity.class, jpql);

//                for (ItemEntity obj : itemEntity) {
//                    groupItemDetail.setItemPriceD(obj.getPrice());
//
//                    System.out.println("items price: "+ obj.getPrice());
//                        int values = groupItemDetail.getItemPriceD();
//                         sum  = values + sum;
//                                System.out.println("sum1 : " +  sum);
//
////                    itemList.add(item);
//                }
                System.out.println("sum222 : " +  sum);
//                groupItemDetail.setItemTotalPrice(sum);

                insert(groupItemDetail);
                result = "amjilttai";
            }


            System.out.println("sum333: " + sum);
            groupItemHeader.setItemPriceTotal(sum);
            insert(groupItemHeader);

        }catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<GroupPBM> disbaleGroupItemsInsert(GrouptRequest groupRequest) throws Exception {
        System.out.println("groupRequest: " + groupRequest.getPkId());
        List<GroupBusinessModel> result = new ArrayList<>();
        List<GroupPBM> rs = new ArrayList<>();
        List<GroupItemHeader> groupItemHeaderList = new ArrayList<>();
        try {
            System.out.println("groupRequest: " + groupRequest.getPkId());

            String jpql = "SELECT new kara.diamond.billing.service.model.response.GroupBusinessModel(A.pkId, A.title, A.status, A.description, A.cnt, A.itemPriceTotal, B.itemPkId, B.itemPriceD, B.itemCnt, C.title as itemTitle, C.quantity as itemQuantity, C.description as itemDescription, C.price as itemPrice)   "
                    + "FROM GroupItemHeaderEntity A  "
                    + "LEFT JOIN GroupItemDetailEntity B ON A.pkId = B.groupItemHeaderPkId  "
                    + "LEFT JOIN ItemEntity C ON C.pkId = B.itemPkId  where B.groupItemHeaderPkId = '"+groupRequest.getPkId().toString()+"'";;


            result = getByQuery(GroupBusinessModel.class, jpql.toString(), null);

            GroupItemHeaderEntity groupItemHeader = new GroupItemHeaderEntity();
            groupItemHeader.setPkId(NumericHelper.generateKey());
            groupItemHeader.setTitle(result.get(0).getTitle());
            groupItemHeader.setDescription(result.get(0).getDescription());
            groupItemHeader.setCnt(1);
             insert(groupItemHeader);
            for (int i = 0 ; i < result.size(); i++) {
                System.out.println("result: " + result);

                System.out.println("setTitle: " + result.get(i).getTitle());
                List<GroupItemDetail> groupItemDtl;
                GroupItemDetailEntity groupItemDetail = new GroupItemDetailEntity();
                groupItemDetail.setPkId(NumericHelper.generateKey());
                groupItemDetail.setGroupItemHeaderPkId(groupItemHeader.getPkId());
                groupItemDetail.setItemCnt(result.get(i).getItemCnt());
                System.out.println("itemCnt: " + result.get(i).getItemCnt());
                groupItemDetail.setItemPkId(result.get(i).getItemPkId());
                groupItemDetail.setItemPriceD(result.get(i).getItemPriceD());
                List<ItemEntity> itemEntity;
                    String jpql2 = "SELECT a FROM ItemEntity a where a.pkId = '" + result.get(i).getItemPkId() + "'";
                    itemEntity = getByQuery(ItemEntity.class, jpql2);
                    insert(groupItemDetail);

            }

        }catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public String uploadStatusGroupItems(GrouptRequest groupRequest) throws Exception {
        String result = "error";
        try {
            GroupItemHeaderEntity groupItemHeaderEntity =  getByPKey(GroupItemHeaderEntity.class, Long.parseLong(groupRequest.getPkId().toString()));
                groupItemHeaderEntity.setStatus(groupRequest.getStatus());
                update(groupItemHeaderEntity);
                result = "Success";
        }catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    /// All group items
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<GroupPBM> getGroupItems() throws Exception {
        List<GroupBusinessModel> result = new ArrayList<>();
        List<GroupPBM> rs = new ArrayList<>();
        List<GroupItemHeader> groupItemHeaderList = new ArrayList<>();
        try {

            String jpql = "SELECT new kara.diamond.billing.service.model.response.GroupBusinessModel(A.pkId, A.title, A.status, A.description, A.cnt, A.others, A.itemPriceTotal, B.itemPkId, B.itemPriceD, B.itemCnt, C.title as itemTitle, C.quantity as itemQuantity, C.description as itemDescription, C.price as itemPrice)   "
                    + "FROM GroupItemHeaderEntity A  "
                    + "LEFT JOIN GroupItemDetailEntity B ON A.pkId = B.groupItemHeaderPkId  "
                    + "LEFT JOIN ItemEntity C ON C.pkId = B.itemPkId  ";


            result = getByQuery(GroupBusinessModel.class, jpql.toString(), null);
            List<GroupBusinessModel> groupBusinessModels = new ArrayList<>();
            GroupPBM temp = new GroupPBM();

            List<String> pkId = new ArrayList<>();
            int sum = 0;
            int gbmPrice = 0;
            for (int i = 0 ; i < result.size(); i++){

//                int sum = 0;

                gbmPrice += result.get(i).getItemCnt() * result.get(i).getItemPrice();
                boolean is_arived = false;
                for(int j = 0; j < pkId.size(); j++){
                    //System.out.println("pkid: "+ pkId.get(j)+"\nres: "+result.get(i).getPkId());
                    if(pkId.get(j).toString().equals(result.get(i).getPkId().toString())) {

//                        System.out.println("arrived");
                        is_arived = true;
                    }else{

                    }
                }
                if(!is_arived){
//                    System.out.println("pk size"+pkId.size());
                    if(pkId.size() > 0){
//                        System.out.println("inserting...");
                        temp.setGbm(groupBusinessModels);

//                        for (int ik = 0; ik<groupBusinessModels.size(); ik++){
//                            System.out.println("groupBusinessModels.ItemPriceD: " + groupBusinessModels.get(ik).getItemPriceD());
//                            int values= groupBusinessModels.get(ik).getItemPriceD();
//                            sum = values + sum;
//                        }

                        groupBusinessModels = new ArrayList<>();

                        rs.add(temp);
                    }

                    temp = new GroupPBM();
//                    System.out.println("sum1: "+ sum);
                    temp.setPkId(result.get(i).getPkId().toString());
                    temp.setCnt(result.get(i).getCnt());
                     System.out.println("temp ItemCnt: " + result.get(i).getItemCnt());
                     System.out.println("temp itemPriceD: " + result.get(i).getItemPriceD());


                    temp.setItemPriceTotal(gbmPrice);
//                    temp.setItemPriceTotal(result.get(i).getItemCnt());
//                    temp.setItemPriceD(result.get(i).getItemPriceD());
                    temp.setTitle(result.get(i).getTitle().toString());
                    temp.setDescription(result.get(i).getDescription().toString());
                    temp.setStatus(result.get(i).getStatus());
                    temp.setOthers(result.get(i).getOthers());
                    pkId.add(temp.getPkId());

                }
                groupBusinessModels.add(result.get(i));


            }
            int testTotal = 0;
            for (int ik = 0; ik<groupBusinessModels.size(); ik++){

                int values = groupBusinessModels.get(ik).getItemPriceD();
                sum = values + sum;
            }
            temp.setItemPriceTotal(sum);
            temp.setGbm(groupBusinessModels);
            rs.add(temp);

//                    +"WHERE A.pkId = 123"


        }catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

//    @Override
//    @Transactional(propagation = Propagation.REQUIRED)
//    public List<GroupPBM> getGroupItems() throws Exception {
//        List<GroupBusinessModel> result = new ArrayList<>();
//        List<GroupPBM> rs = new ArrayList<>();
//        List<GroupItemHeader> groupItemHeaderList = new ArrayList<>();
//        try {
//
//            String jpql = "SELECT new kara.diamond.billing.service.model.response.GroupBusinessModel(A.pkId, A.title, A.status, A.description, A.cnt, B.itemPkId, B.itemPriceD, C.title as itemTitle, C.quantity as itemQuantity, C.description as itemDescription, C.price as itemPrice)   "
//                    + "FROM GroupItemHeaderEntity A  "
//                    + "LEFT JOIN GroupItemDetailEntity B ON A.pkId = B.groupItemHeaderPkId  "
//                    + "LEFT JOIN ItemEntity C ON C.pkId = B.itemPkId  ";
//
//
//            result = getByQuery(GroupBusinessModel.class, jpql.toString(), null);
//            List<GroupBusinessModel> groupBusinessModels = new ArrayList<>();
//            GroupPBM temp = new GroupPBM();
//
//            List<String> pkId = new ArrayList<>();
//            for (int i = 0 ; i < result.size(); i++){
//                int sum = 0;
//                boolean is_arived = false;
//                for(int j = 0; j < pkId.size(); j++){
//                    //System.out.println("pkid: "+ pkId.get(j)+"\nres: "+result.get(i).getPkId());
//                    if(pkId.get(j).toString().equals(result.get(i).getPkId().toString())) {
////                        System.out.println("arrived");
//                        is_arived = true;
//                    }else{
//
//                    }
//                }
//
//                if(!is_arived){
//                    if(pkId.size() > 0){
////                        System.out.println("inserting...");
//                        temp.setGbm(groupBusinessModels);
//                        for (int ik = 0; ik<groupBusinessModels.size(); ik++){
//                            System.out.println("groupBusinessModels.ItemPriceD: " + groupBusinessModels.get(ik).getItemPriceD());
//                            int values= groupBusinessModels.get(ik).getItemPriceD();
//                            sum = values + sum;
//                        }
//
//                        groupBusinessModels = new ArrayList<>();
//
//                        rs.add(temp);
//                    }
//                    temp = new GroupPBM();
//                    System.out.println("sum1: "+ sum);
//                    temp.setPkId(result.get(i).getPkId().toString());
//                    temp.setCnt(result.get(i).getCnt());
//
////                    temp.setItemPriceD(result.get(i).getItemPriceD());
//                    temp.setTitle(result.get(i).getTitle().toString());
//                    temp.setDescription(result.get(i).getDescription().toString());
//                    temp.setStatus(result.get(i).getStatus());
//                    pkId.add(temp.getPkId());
//
//                }
//                groupBusinessModels.add(result.get(i));
//
//            }
//            temp.setGbm(groupBusinessModels);
//            rs.add(temp);
//
////                    +"WHERE A.pkId = 123"
//
//
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
//        return rs;
//    }
//

    // status 1 group items
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<GroupPBM> getGroupItemsS1() throws Exception {
        List<GroupBusinessModel> result = new ArrayList<>();
        List<GroupPBM> rs = new ArrayList<>();
        List<GroupItemHeader> groupItemHeaderList = new ArrayList<>();
        try {

            String jpql = "SELECT new kara.diamond.billing.service.model.response.GroupBusinessModel(A.pkId, A.title, A.status, A.description, A.cnt, A.others, A.itemPriceTotal, B.itemPkId, B.itemPriceD, B.itemCnt, C.title as itemTitle, C.quantity as itemQuantity, C.description as itemDescription, C.price as itemPrice)   "
                    + "FROM GroupItemHeaderEntity A  "
                    + "LEFT JOIN GroupItemDetailEntity B ON A.pkId = B.groupItemHeaderPkId  "
                    + "LEFT JOIN ItemEntity C ON C.pkId = B.itemPkId  ";
            result = getByQuery(GroupBusinessModel.class, jpql.toString(), null);

            List<GroupBusinessModel> groupBusinessModels = new ArrayList<>();
            GroupPBM temp = new GroupPBM();

            List<String> pkId = new ArrayList<>();

            for (int i = 0 ; i < result.size(); i++){
                int totalPirce2 = 0;
                boolean is_arived = false;
                if(result.get(i).getStatus() == 1){
                    totalPirce2 += result.get(i).getItemCnt() * result.get(i).getItemPriceD();
                    for(int j = 0; j < pkId.size(); j++){

                        //System.out.println("pkid: "+ pkId.get(j)+"\nres: "+result.get(i).getPkId());
                        if(pkId.get(j).toString().equals(result.get(i).getPkId().toString())) {
                            System.out.println("arrived");
                            is_arived = true;
                        }else{
                            System.out.println("not arrived");
                        }

                    }

                    if(!is_arived){
                        if(pkId.size() > 0){
//                        System.out.println("inserting...");
                            temp.setGbm(groupBusinessModels);
                            groupBusinessModels = new ArrayList<>();
                            rs.add(temp);
                        }
                        temp = new GroupPBM();
                        temp.setPkId(result.get(i).getPkId().toString());
                        temp.setCnt(result.get(i).getCnt());
                        temp.setItemPriceD(result.get(i).getItemPriceD());

                            temp.setItemPriceTotal(totalPirce2);


                        temp.setTitle(result.get(i).getTitle().toString());
                        temp.setDescription(result.get(i).getDescription().toString());
                        temp.setStatus(result.get(i).getStatus());
                        temp.setOthers(result.get(i).getOthers());
                        pkId.add(temp.getPkId());

                    }
                    groupBusinessModels.add(result.get(i));
                }
                else {
                    System.out.println("bolohq bn =======> ");
                }



            }
            temp.setGbm(groupBusinessModels);
            rs.add(temp);

//                    +"WHERE A.pkId = 123"


        }catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public String updateGroupItems(GrouptRequest groupRequest) throws Exception {
        String result = "amjiltgui";
        try {
            GroupItemHeaderEntity groupItemHeaderEntity =  getByPKey(GroupItemHeaderEntity.class, Long.parseLong(groupRequest.getPkId().toString()));

            groupItemHeaderEntity.setTitle(groupRequest.getTitle());
            groupItemHeaderEntity.setDescription(groupRequest.getDescription());
            groupItemHeaderEntity.setStatus(groupRequest.getStatus());
            update(groupItemHeaderEntity);
            System.out.println("req title: " + groupRequest.getTitle());
            System.out.println("req desc: " + groupRequest.getDescription());
            System.out.println("req status: " + groupRequest.getStatus());

            List<GroupItemDetail> groupItemDtl = groupRequest.getGroupDetail();
//            System.out.println("headerPkd: " + groupRequest.getPkId());
            for (int i = 0; i < groupItemDtl.size(); i++){
//                System.out.println("headerPkID items: "+groupItemDtl.get(i).getGroupItemHeaderPkId());
//               System.out.println("REQ: getItemPkId: "+groupItemDtl.get(i).getItemPkId());
                List<GroupItemDetailEntity> groupItemDetail;
                String jpql = "SELECT a FROM GroupItemDetailEntity a where a.groupItemHeaderPkId = '"+ groupItemDtl.get(i).getGroupItemHeaderPkId().toString() +"'";

                groupItemDetail = getByQuery(GroupItemDetailEntity.class, jpql);

                System.out.println("itemPkIdEntity: "+groupItemDetail.get(i).getItemPkId());
                System.out.println("req: "+ Long.parseLong(groupItemDtl.get(i).getItemPkId()));

//                    if(groupItemDetail.get(i).getItemPkId().toString().equals(groupItemDtl.get(i).getItemPkId().toString())){
                        groupItemDetail.get(i).setItemPriceD(groupItemDtl.get(i).getItemPriceD());
                        update(groupItemDetail);

//                        System.out.println("tentsvvv");
//                    }else{
//                        System.out.println("Hooson");
//                    }


//

//                groupItemDetail.setPkId(NumericHelper.generateKey());
//                groupItemDetail.setGroupItemHeaderPkId(groupItemHeaderEntity.getPkId());

//                groupItemDetail.setGroupItemHeaderPkId(groupItemHeaderEntities.get(0).getPkId().toString());
//                groupItemDetail.setItemPkId(Long.parseLong(groupItemDtl.get(i).getItemPkId()));


//                update(groupItemDetail);
                result = "amjilttai";
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}













