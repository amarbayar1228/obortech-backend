package kara.diamond.billing.service.controller;


import io.swagger.annotations.ApiOperation;
import kara.diamond.billing.service.entity.EmployeeEntity;
import kara.diamond.billing.service.entity.Users;
import kara.diamond.billing.service.iinterfaces.EmployeeInterfaces;
import kara.diamond.billing.service.logic.EmployeeLogic;
import kara.diamond.billing.service.logic.UserLogic;
import kara.diamond.billing.service.model.request.Employee;
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
@RequestMapping("/users")
public class UserController implements Serializable {

    @Autowired
    private EmployeeInterfaces employeeInterfaces;

    @Autowired
    private EmployeeLogic employeeLogic;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);


    @PostMapping("/save")
    public ResponseEntity<?> saveUser(@Valid @RequestBody Employee employee) throws Exception {
        return ResponseEntity.ok(employeeInterfaces.saveEmployee(employee));
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@Valid @RequestBody Employee employeeEntity) throws Exception {
        return ResponseEntity.ok(employeeInterfaces.updateEmployee(employeeEntity));
    }

    @ApiOperation(value = "Энэ бол системийн док ")
    @GetMapping("/get")
    public ResponseEntity<?> getAllEmployee() throws Exception {
        logger.info("orlo====>");
        List<EmployeeEntity> result = employeeLogic.getAllEmployee();
        return ResponseEntity.ok(result);
    }
}
