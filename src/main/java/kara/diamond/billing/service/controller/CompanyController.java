package kara.diamond.billing.service.controller;

import kara.diamond.billing.service.entity.CompanyEntity;
import kara.diamond.billing.service.iinterfaces.CompanyInterfaces;
import kara.diamond.billing.service.logic.CompanyLogic;
import kara.diamond.billing.service.model.request.*;
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

    @PostMapping("companySentIncentive")
    public ResponseEntity<?> companyUpdateIncentive(@Valid @RequestBody OrgUsers orgUsers) throws Exception{
        return ResponseEntity.ok(companyInterfaces.companySentIncentive(orgUsers));
    }
//    @PostMapping("getIncentivePercent")
//    public ResponseEntity<?> getIncentivePercent(@Valid @RequestBody IncentivePercent incentivePercent) throws Exception{
//        return ResponseEntity.ok(companyInterfaces.getIncentivePercent(incentivePercent));
//    }
    @PostMapping("getIncentivePercent")
    public ResponseEntity<?>  getIncentivePercent() throws Exception{

        List<IncentivePercent> result = companyLogic.getIncentivePercent();
        return ResponseEntity.ok(result);
    }
    @PostMapping("/updateIncentivePercent")
    public ResponseEntity<?> updateIncentivePercent(@Valid @RequestBody IncentivePercent incentivePercent) throws Exception{
        return ResponseEntity.ok(companyInterfaces.updateIncentivePercent(incentivePercent));
    }
    @PostMapping("IncentivePercentSent")
    public ResponseEntity<?> IncentivePercentSent(@Valid @RequestBody IncentivePercent incentivePercent) throws Exception{
        return ResponseEntity.ok(companyInterfaces.IncentivePercentSent(incentivePercent));
    }
    @PostMapping("get")
    public ResponseEntity<?> getCompany(@Valid @RequestBody Company company) throws Exception{
        List<Company> result = companyLogic.getCompany(company);
        return ResponseEntity.ok(result);
    }
    @PostMapping("/userGet")
    public ResponseEntity<?> userGet(@Valid @RequestBody Company company) throws Exception{
        List<Company> result = companyLogic.userGet(company);
        return ResponseEntity.ok(result);
    }
    @PostMapping("/confirmCompanyAdminList")
    public ResponseEntity<?> companyConfirmList(@Valid @RequestBody Company company) throws Exception{
        List<Company> result = companyLogic.companyConfirmList(company);
        return ResponseEntity.ok(result);
    }
    @PostMapping("/getCompanyUser")
    public ResponseEntity<?> getCompany() throws Exception{
        List<Company> result = companyLogic.getCompany();
        return ResponseEntity.ok(result);
    }
    @PostMapping("/getCompanyAcceptAll")
    public ResponseEntity<?> getCompanyAcceptAll() throws Exception{
        List<Company> result = companyLogic.getCompanyAcceptAll();
        return ResponseEntity.ok(result);
    }
    @PostMapping("/companyUpdateReq")
    public ResponseEntity<?> companyUpdateReq(@Valid @RequestBody Company company) throws  Exception{
        return ResponseEntity.ok(companyInterfaces.companyUpdateReq(company));
    }

    @PostMapping("/companyUpdateOrgId")
    public ResponseEntity<?> companyUpdateOrgId(@Valid @RequestBody Company company) throws  Exception{
        return ResponseEntity.ok(companyInterfaces.companyUpdateOrgId(company));
    }

    @PostMapping("/incentive")
    public ResponseEntity<?> incentive(@Valid @RequestBody Company company) throws  Exception{
        return ResponseEntity.ok(companyInterfaces.incentive(company));
    }
    @PostMapping("/companyUpdateUserEdit")
    public ResponseEntity<?> companyUpdateUserEdit(@Valid @RequestBody Company company) throws  Exception{
        return ResponseEntity.ok(companyInterfaces.companyUpdateUserEdit(company));
    }
}
