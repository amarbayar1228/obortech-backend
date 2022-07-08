package kara.diamond.billing.service.controller;

import kara.diamond.billing.service.iinterfaces.CompanyInterfaces;
import kara.diamond.billing.service.logic.CompanyLogic;
import kara.diamond.billing.service.model.request.Company;
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
@RequestMapping("/company")
public class CompanyController implements Serializable {

    @Autowired
    private CompanyInterfaces companyInterfaces;
    @Autowired
    private CompanyLogic companyLogic;
    private static final Logger logger = LoggerFactory.getLogger(CompanyController.class);

    @PostMapping("send")
    public ResponseEntity<?> sendCompany(@Valid @RequestBody Company company) throws Exception{
        return ResponseEntity.ok(companyInterfaces.sendCompany(company));
    }

    @PostMapping("get")
    public ResponseEntity<?> getCompany(@Valid @RequestBody Company company) throws Exception{
        return ResponseEntity.ok(companyInterfaces.getCompany(company));


    }
}
