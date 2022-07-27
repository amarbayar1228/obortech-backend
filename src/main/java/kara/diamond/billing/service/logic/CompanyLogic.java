package kara.diamond.billing.service.logic;

import kara.diamond.billing.service.base.BaseDatabaseService;
import kara.diamond.billing.service.base.NumericHelper;
import kara.diamond.billing.service.entity.CompanyEntity;
import kara.diamond.billing.service.entity.LoginUserEntity;
import kara.diamond.billing.service.iinterfaces.CompanyInterfaces;
import kara.diamond.billing.service.model.request.Company;
import kara.diamond.billing.service.model.request.LoginUser;
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

            List<LoginUserEntity> loginUserEntities;
            String jpql = "SELECT a FROM  LoginUserEntity a where a.token = '"+company.getUserToken()+"'";
            System.out.println("query=======================: "+jpql);
            loginUserEntities= getByQuery(LoginUserEntity.class, jpql);
            List<LoginUser> loginUserList = new ArrayList<>();
//        System.out.println("\n\ndjsopajfopsda: "+loginUserEntities.get(0).getPkId());
            try{
                CompanyEntity com = new CompanyEntity();
                com.setPkId(NumericHelper.generateKey());
                com.setAddress(company.getAddress());
                com.setCompanyName(company.getCompanyName());
                com.setDateCompany(company.getDateCompany());
                com.setState(company.getState());
                com.setUserToken(loginUserEntities.get(0).getPkId().toString());
//            com.setAdminToken(company.getAdminToken());
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
            com2.setRegister(obj.getRegister());
            com2.setDateCompany(obj.getDateCompany());
            com2.setUserToken(obj.getUserToken());
            com2.setAdminToken(obj.getAdminToken());
            com2.setTelephone(obj.getTelephone());
            com2.setAddress(obj.getAddress());
            com2.setAreasOfActivity(obj.getAreasOfActivity());
            com2.setState(obj.getState());
            com2.setCompanyName(obj.getCompanyName());
            companiesList.add(com2);
            System.out.println("addresss ================> "+obj.getAddress());
        }

        return companiesList;
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Company> userGet(Company company) throws Exception{

        String jpql = "SELECT a FROM CompanyEntity a where a.userToken = '"+company.getUserToken()+"'";
        System.out.println("orooooqqq ");
        List<CompanyEntity> companyEntities;
        companyEntities = getByQuery(CompanyEntity.class, jpql);
         List<Company> comList = new ArrayList<>();
        for (CompanyEntity obj : companyEntities){
                Company company2 = new Company();
                company2.setPkId(String.valueOf(obj.getPkId()));
                company2.setAddress(obj.getAddress());
                company2.setCompanyName(obj.getCompanyName());
                company2.setDateCompany(obj.getDateCompany());
                company2.setState(obj.getState());
                company2.setTelephone(obj.getTelephone());
                company2.setRegister(obj.getRegister());
                company2.setUserToken(obj.getUserToken());
                company2.setAreasOfActivity(obj.getAreasOfActivity());
                comList.add(company2);
        }


        return comList;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Company> companyConfirmList(Company company) throws Exception{

        String jpql = "SELECT a FROM CompanyEntity a where a.adminToken = '"+company.getPkId()+"'";

        List<CompanyEntity> companyEntities;
        companyEntities = getByQuery(CompanyEntity.class, jpql);
        List<Company> comList = new ArrayList<>();
        for (CompanyEntity obj : companyEntities){
            Company company2 = new Company();
            company2.setPkId(String.valueOf(obj.getPkId()));
            company2.setAddress(obj.getAddress());
            company2.setCompanyName(obj.getCompanyName());
            company2.setDateCompany(obj.getDateCompany());
            company2.setState(obj.getState());
            company2.setTelephone(obj.getTelephone());
            company2.setRegister(obj.getRegister());
            company2.setUserToken(obj.getUserToken());
            company2.setAreasOfActivity(obj.getAreasOfActivity());
            company2.setOrgId(obj.getOrgId());
            comList.add(company2);
        }
        System.out.println("iiiishee orson=========>"+ company.getUserToken());

        return comList;
    }
    public List<Company> getCompany() throws  Exception{
        List<CompanyEntity> companyEntities;
        String jpql = "SELECT a FROM CompanyEntity a";
        List<Company> companyList = new ArrayList<>();
        companyEntities = getByQuery(CompanyEntity.class, jpql);
        for(CompanyEntity obj2 : companyEntities){
            if(obj2.getState() == 1) {
                Company comp = new Company();
                comp.setPkId(String.valueOf(obj2.getPkId()));
                comp.setDateCompany(obj2.getDateCompany());
                comp.setCompanyName(obj2.getCompanyName());
                comp.setTelephone(obj2.getTelephone());
                comp.setRegister(obj2.getRegister());
                comp.setAddress(obj2.getAddress());
                comp.setUserToken(obj2.getUserToken());
                comp.setAreasOfActivity(obj2.getAreasOfActivity());
//                comp.setAdminToken(obj2.getAdminToken());
                comp.setState(obj2.getState());
                companyList.add(comp);
            }
        }
        return companyList;
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public String companyUpdateReq(Company company) throws Exception{
        String result = "amjiltgui";
            try {
                CompanyEntity companyEntity = getByPKey(CompanyEntity.class, Long.parseLong(company.getPkId().toString()));
                companyEntity.setState(company.getState());
                companyEntity.setAdminToken(company.getAdminToken());
                companyEntity.setOrgId(company.getOrgId());
                update(companyEntity);
            }catch (Exception e){
                System.out.println("Ex : "+e);
                getDatabaseException(e);
            }
        return result;
    }
}
