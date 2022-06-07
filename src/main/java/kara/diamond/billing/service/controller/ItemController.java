package kara.diamond.billing.service.controller;

import io.swagger.annotations.ApiOperation;
import kara.diamond.billing.service.entity.ItemEntity;
import kara.diamond.billing.service.iinterfaces.ItemInterfaces;
import kara.diamond.billing.service.logic.ItemLogic;
import kara.diamond.billing.service.model.request.Item;
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

    @GetMapping("get")
    public ResponseEntity<?> getAllItem() throws Exception{
        logger.info("baraa orloo ====>");
        List<Item> result = itemLogic.getAllItem();
        return ResponseEntity.ok(result);
    }


    @PostMapping("list")
    public ResponseEntity<?> getItemList() throws Exception{
        logger.info("getItemList  ====>");
        List<Item> result = itemLogic.getAllItem();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/updateItem")
    public ResponseEntity<?> update(@Valid @RequestBody Item item) throws Exception{
        return ResponseEntity.ok(itemInterfaces.updateItem(item));
    }
}
