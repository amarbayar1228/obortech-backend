package kara.diamond.billing.service.controller;

import kara.diamond.billing.service.entity.LoginUserEntity;
import kara.diamond.billing.service.iinterfaces.LoginUserInterfaces;
import kara.diamond.billing.service.logic.LoginUserLogic;
import kara.diamond.billing.service.model.request.Item;
import kara.diamond.billing.service.model.request.LoginUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/user")
public class LoginUserController implements Serializable {

    @Autowired
    private LoginUserInterfaces loginUserInterfaces;

    @Autowired
    private  LoginUserLogic loginUserLogic;

    private static final Logger logger = LoggerFactory.getLogger(LoginUserController.class);

    @PostMapping("/signUp")
    public ResponseEntity<?> saveItem(@Valid @RequestBody LoginUser loginUser) throws  Exception{
        return  ResponseEntity.ok(loginUserInterfaces.saveUser(loginUser));
    }

    @PostMapping("/signIn")
    public ResponseEntity<?> signInUser2(@Valid @RequestBody LoginUser loginUser) throws Exception{
        logger.info("getItemList  ====>");

        Map<String, String> result = loginUserLogic.signInUser(loginUser);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/updateUser")
    public ResponseEntity<?> update(@Valid @RequestBody LoginUser loginUser) throws Exception{
        return ResponseEntity.ok(loginUserInterfaces.updateUser2(loginUser));
    }

    @PostMapping("/getUsers")
    public ResponseEntity<?> getUser() throws Exception{
        List<LoginUser> result = loginUserLogic.getUser();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/getUserTest")
    public ResponseEntity<?> getUserTest() throws Exception {
        logger.info("orlo====>");
        List<LoginUser> result = loginUserLogic.getUserTest();
        return ResponseEntity.ok(result);
    }
}
