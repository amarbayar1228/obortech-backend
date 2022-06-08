package kara.diamond.billing.service.controller;

import io.swagger.annotations.ApiOperation;
import kara.diamond.billing.service.iinterfaces.OrderHistoryInterfaces;
import kara.diamond.billing.service.logic.OrderHistoryLogic;
import kara.diamond.billing.service.model.request.OrderHistory;
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
@RequestMapping("/orderHistory")
public class OrderHistoryController implements Serializable {
    @Autowired
    private OrderHistoryLogic orderHistoryLogic;

    @Autowired
    private OrderHistoryInterfaces orderHistoryInterfaces;

    private static final Logger logger = (Logger) LoggerFactory.getLogger(OrderHistoryController.class);

    @ApiOperation(value = "Энэ бол системийн док save")
    @PostMapping("/save")
    public ResponseEntity<?> saveOrderHistory(@Valid @RequestBody List<OrderHistory> orderHistory) throws Exception{
        return ResponseEntity.ok(orderHistoryInterfaces.saveOrderHistory(orderHistory));
    }
}
