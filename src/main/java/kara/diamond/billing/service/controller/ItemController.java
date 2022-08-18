package kara.diamond.billing.service.controller;

import io.swagger.annotations.ApiOperation;
import kara.diamond.billing.service.iinterfaces.ItemInterfaces;
import kara.diamond.billing.service.logic.ItemLogic;
import kara.diamond.billing.service.model.request.GroupItemDetail;
import kara.diamond.billing.service.model.request.GroupItemHeader;
import kara.diamond.billing.service.model.request.GrouptRequest;
import kara.diamond.billing.service.model.request.Item;
import kara.diamond.billing.service.model.response.ExampleArray;
import kara.diamond.billing.service.model.response.GroupBusinessModel;
import kara.diamond.billing.service.model.response.GroupPBM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/item")
public class ItemController implements Serializable {
    @Autowired
    private ItemInterfaces itemInterfaces;
    @Autowired
    private ItemLogic itemLogic;

    private static final Logger logger = LoggerFactory.getLogger(ItemController.class);

    @ApiOperation(value = "Энэ бол системийн док save")
    @PostMapping("/save")
    public ResponseEntity<?> saveItem(@Valid @RequestBody Item item) throws  Exception{
        return  ResponseEntity.ok(itemInterfaces.saveItem(item));
    }

//    @PostMapping("/saveGroupItem")
//    public ResponseEntity<?> saveGroupItem(@Valid @RequestBody GroupItemHeader groupItemHeader) throws  Exception{
//        return  ResponseEntity.ok(itemInterfaces.saveGroupItem(groupItemHeader));
//    }

//    @ApiOperation(value = "Энэ бол системийн док save")
//    @PostMapping("/orderSave")
//    public ResponseEntity<?> orderItemSave(@Valid @RequestBody Item item) throws  Exception{
//        return  ResponseEntity.ok(itemInterfaces.orderItemSave(item));
//    }


//    @GetMapping("get")
//    public ResponseEntity<?>  getAllItem() throws Exception{
//        logger.info("baraa orloo ====>");
//        List<Item> result = itemLogic.getAllItem();
//        return ResponseEntity.ok(result);
//    }
    @PostMapping("getStatus1Item")
    public ResponseEntity<?>  getStatus1Item() throws Exception{

        List<Item> result = itemLogic.getStatus1Item();
        return ResponseEntity.ok(result);
    }


    @PostMapping("/list")
    public ResponseEntity<?> getItemList() throws Exception{
        logger.info("getItemList  ====>");
        List<Item> result = itemLogic.getAllItem();
        return ResponseEntity.ok(result);
    }
    @PostMapping("/getItemState01")
    public ResponseEntity<?> getItemState01() throws Exception{
        List<Item> result = itemLogic.getItemState01();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/updateItem")
    public ResponseEntity<?> update(@Valid @RequestBody Item item) throws Exception{
        return ResponseEntity.ok(itemInterfaces.updateItem(item));
    }

    @PostMapping("/updateStateItem")
    public ResponseEntity<?> updateStateItem(@Valid @RequestBody Item item) throws Exception{
        return ResponseEntity.ok(itemInterfaces.updateStateItem(item));
    }
    @GetMapping("/testExample")
    public ResponseEntity<?> testExample() throws Exception{

        List<ExampleArray> result = itemLogic.getTestExample();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/delete")
    public ResponseEntity<?> delete(@Valid @RequestBody Item item) throws Exception{

        String result = itemLogic.deleteItem(item.getPkId());
        return ResponseEntity.ok(result);
    }

    @PostMapping("/saveGroupItems")
    public ResponseEntity<?> saveGroupItems(@Valid @RequestBody GrouptRequest groupRequest) throws  Exception{
        String result = itemInterfaces.saveGroupItems(groupRequest);
        return ResponseEntity.ok(result);
    }
    @PostMapping("/disbaleGroupItemsInsert")
    public ResponseEntity<?> disbaleGroupItemsInsert(@Valid @RequestBody GrouptRequest groupRequest) throws  Exception{
        List<GroupPBM>  result = itemInterfaces.disbaleGroupItemsInsert(groupRequest);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/uploadStatusGroupItems")
    public ResponseEntity<?> uploadStatusGroupItems(@Valid @RequestBody GrouptRequest groupRequest) throws  Exception{
        String result = itemInterfaces.uploadStatusGroupItems(groupRequest);
        return ResponseEntity.ok(result);
    }
    @GetMapping("/getGroupItems")
    public ResponseEntity<?> getGroupItems() throws  Exception{
        List<GroupPBM> result = itemInterfaces.getGroupItems();
        return ResponseEntity.ok(result);
    }
    @GetMapping("/getGroupItemsS1")
    public ResponseEntity<?> getGroupItemsS1() throws  Exception{
        List<GroupPBM> result = itemInterfaces.getGroupItemsS1();
        return ResponseEntity.ok(result);
    }
    @PostMapping("/updateGroupItems")
    public ResponseEntity<?> updateGroupItems(@Valid @RequestBody GrouptRequest groupRequest) throws  Exception{
        String result = itemInterfaces.updateGroupItems(groupRequest);
        return ResponseEntity.ok(result);
    }

}
//aaa