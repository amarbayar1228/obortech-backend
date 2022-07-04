package kara.diamond.billing.service.controller;

import io.swagger.annotations.ApiOperation;
import kara.diamond.billing.service.entity.Users;
import kara.diamond.billing.service.logic.UserLogic;
import kara.diamond.billing.service.model.request.User;
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
@RequestMapping("/v1")
public class TestController implements Serializable {

    @Autowired
    private UserLogic userLogic;
//    private UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @PostMapping("/all")
    public ResponseEntity<?> all(@Valid @RequestBody User user) throws Exception {
        logger.info("info logger---------------->" + user.getName() + "   " + user.getPassword());
        return ResponseEntity.ok("Amjilltai ");
    }

    @ApiOperation(value = "Энэ бол системийн док")
    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers () throws Exception {
        logger.info("orlo====>");
        List<Users> result = userLogic.getAllUsers();
        return ResponseEntity.ok(result);
    }

}
