package kara.diamond.billing.service.logic;

import kara.diamond.billing.service.base.BaseDatabaseService;
import kara.diamond.billing.service.base.NumericHelper;
import kara.diamond.billing.service.entity.ItemEntity;
import kara.diamond.billing.service.entity.OrderDateEntity;
import kara.diamond.billing.service.iinterfaces.ItemInterfaces;
import kara.diamond.billing.service.model.request.Item;
import kara.diamond.billing.service.model.request.OrderDate;
import kara.diamond.billing.service.model.response.ExampleArray;
import kara.diamond.billing.service.model.response.ItemModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
            ItemEntity itemDataUpdate = getByPKey(ItemEntity.class, Long.parseLong(item.getPkId()));
//            ItemEntity itemDataUpdate = getByPKey(ItemEntity.class,  item.getPkId());
            itemDataUpdate.setTitle(item.getTitle());
            itemDataUpdate.setPrice(item.getPrice());
            itemDataUpdate.setQuantity(item.getQuantity());
            itemDataUpdate.setDescription(item.getDescription());
            update(itemDataUpdate);
            result = "Amjilttai";
        } catch (Exception e) {
            getDatabaseException(e);
        }
        return result;
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
            item.setDescription(obj.getDescription());
            item.setPrice(obj.getPrice());
            item.setQuantity(obj.getQuantity());
            itemList.add(item);
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
        }

//        Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse("2022-06-05");
        String jpql2 = "SELECT a FROM OrderDateEntity a";
            List<OrderDateEntity> orderdateList = getByQuery(OrderDateEntity.class, jpql2);
                for (OrderDateEntity obj : orderdateList){
                    ExampleArray exampleArray = new ExampleArray();
                    exampleArray.setDate(obj.getDate());
                    exampleArray.setId(String.valueOf(obj.getPkId()));
                    exampleArray.setList(itemList);

                    exampleArrays.add(exampleArray);

                }
        return exampleArrays;
//        ExampleArray exampleArray = new ExampleArray();



//        ExampleArray exampleArray2 = new ExampleArray();
//        exampleArray2.setDate("2022-06-06");
//        exampleArray2.setId("2");
//        exampleArray2.setList(itemList);



//        exampleArrays.add(exampleArray3);
//        exampleArrays.add(exampleArray2);
//        exampleArrays.add(exampleArray4);

    }



    @Override
    public String deleteItem(String pkId) throws Exception {

        try{
//            String jpql = "delete from ItemEntity a WHERE a.pkId = " + Long.parseLong(pkId) ;
//            executeNativeQuery(jpql);

            ItemEntity item = getByPKey(ItemEntity.class, Long.parseLong(pkId ));
            System.out.println("selected item -------------------> "  + item.getTitle());
            delete((List<?>) item);

            return "Success";
        }catch (Exception e ) {
            e.printStackTrace();
            return "Error";
        }
    }


}
