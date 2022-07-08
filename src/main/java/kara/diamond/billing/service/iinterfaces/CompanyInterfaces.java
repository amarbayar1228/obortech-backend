package kara.diamond.billing.service.iinterfaces;

import kara.diamond.billing.service.model.request.Company;

public interface CompanyInterfaces {
    public String sendCompany(Company company) throws Exception;
    public String getCompany(Company company) throws  Exception;
}
