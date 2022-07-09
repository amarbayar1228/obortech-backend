package kara.diamond.billing.service.logic;

import kara.diamond.billing.service.base.BaseDatabaseService;
import kara.diamond.billing.service.base.NumericHelper;
import kara.diamond.billing.service.entity.CompanyEntity;
import kara.diamond.billing.service.iinterfaces.CompanyInterfaces;
import kara.diamond.billing.service.model.request.Company;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyLogic extends BaseDatabaseService implements CompanyInterfaces {
    private  static  final Logger logger = LoggerFactory.getLogger(CompanyLogic.class);

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public String sendCompany(Company company) throws Exception{
        String result = "error";
        try{
            CompanyEntity com = new CompanyEntity();
            com.setPkId(NumericHelper.generateKey());
            com.setAddress(company.getAddress());
            com.setCompanyName(company.getCompanyName());
            com.setDateCompany(company.getDateCompany());
            com.setState(company.getState());
            com.setUserToken(company.getUserToken());
            com.setAdminToken(company.getAdminToken());
            com.setAreasOfActivity(company.getAreasOfActivity());
            com.setRegister(company.getRegister());
            com.setTelephone(company.getTelephone());
            insert(com);
            result= "Success";
        } catch (Exception e){
            throw getDatabaseException(e);
        }
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public  List<Company> getCompany(Company company) throws  Exception {
    String result = "amjiltq";
        List<CompanyEntity> companyEntities;
        String jpql = "SELECT a FROM CompanyEntity a where a.userToken = '"+company.getUserToken()+"'";
        System.out.println("iiiishee orson=========>");
        companyEntities = getByQuery(CompanyEntity.class, jpql);

        List<Company> companiesList = new ArrayList<>();
        for (CompanyEntity obj:companyEntities){
            Company com2  = new Company();
            com2.setPkId(String.valueOf(obj.getPkId()));
            com2.setCompanyName(obj.getCompanyName());
            companiesList.add(com2);
            System.out.println("addresss ================> "+obj.getAddress());
        }

        return companiesList;
    }
//            for (CompanyEntity obj:companyEntity ){
//                if(obj.getUserToken().equals(company.getUserToken())){
//                    Company com2  = new Company();
//                    com2.setPkId(String.valueOf(obj.getPkId()));
//                    com2.setCompanyName(obj.getCompanyName());
//                    com2.setDateCompany(obj.getDateCompany());
//                    com2.setAreasOfActivity(obj.getAreasOfActivity());
//                    com2.setAddress(obj.getAddress());
//                    com2.setRegister(obj.getRegister());
//                    com2.setTelephone(obj.getTelephone());
//                    com2.setState(obj.getState());
//                    com2.setAdminToken(obj.getAdminToken());
//                    com2.setUserToken(obj.getUserToken());
//                    comList.add(com2);
//                    System.out.println("bosl===========>" + obj.getCompanyName());
//                }
}
