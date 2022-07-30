package kara.diamond.billing.service.iinterfaces;

import kara.diamond.billing.service.entity.CompanyEntity;
import kara.diamond.billing.service.model.request.Company;
import kara.diamond.billing.service.model.request.LoginUser;

import java.util.List;

public interface CompanyInterfaces {
    public String sendCompany(Company company) throws Exception;
    public List<Company> getCompany(Company company) throws  Exception;
    public List<Company> userGet(Company company) throws Exception;
    public List<Company> companyConfirmList(Company company) throws Exception;
    public String  companyUpdateReq(Company company) throws Exception;
    public String  companyUpdateUserEdit(Company company) throws Exception;

}
