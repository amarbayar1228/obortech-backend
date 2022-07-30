package kara.diamond.billing.service.entity;

import io.swagger.models.Operation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "company")
public class CompanyEntity extends Operation {

    @Id
    @Column(name = "pkId")
    private Long pkId;

    @Column(name = "companyName")
    private  String companyName;

    @Column(name = "register")
    private  String register;

    @Column(name = "areasOfActivity")
    private  String areasOfActivity;

    @Column(name = "telephone")
    private  String telephone;

    @Column(name = "address")
    private  String address;

    @Column(name = "dateCompany")
    private  String dateCompany;

    @Column(name = "userToken")
    private  String userToken;

    @Column(name = "adminToken")
    private  String adminToken;

    @Column(name = "state")
    private  int state;
    @Column(name = "orgId")
    private  String orgId;
    @Column(name = "others")
    private  String others;

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }

    public Long getPkId() {
        return pkId;
    }

    public void setPkId(Long pkId) {
        this.pkId = pkId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getRegister() {
        return register;
    }

    public void setRegister(String register) {
        this.register = register;
    }

    public String getAreasOfActivity() {
        return areasOfActivity;
    }

    public void setAreasOfActivity(String areasOfActivity) {
        this.areasOfActivity = areasOfActivity;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDateCompany() {
        return dateCompany;
    }

    public void setDateCompany(String dateCompany) {
        this.dateCompany = dateCompany;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public String getAdminToken() {
        return adminToken;
    }

    public void setAdminToken(String adminToken) {
        this.adminToken = adminToken;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
}
