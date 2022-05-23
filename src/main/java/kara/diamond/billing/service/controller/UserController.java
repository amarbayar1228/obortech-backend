package kara.diamond.billing.service.controller;


import kara.diamond.billing.service.iinterfaces.EmployeeInterfaces;
import kara.diamond.billing.service.model.request.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.Serializable;

@RestController
@RequestMapping("/users")
public class UserController implements Serializable {

    @Autowired
    private EmployeeInterfaces employeeInterfaces;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);


    @PostMapping("/save")
    public ResponseEntity<?> saveUser(@Valid @RequestBody Employee employee) throws Exception {
        return ResponseEntity.ok(employeeInterfaces.saveEmployee(employee));
    }

}
