package kara.diamond.billing.service.model.request;

import lombok.Data;

import javax.persistence.Column;
import java.util.List;

@Data
public class Company {
    private String pkId;
    private String companyName;
    private  String register;

    private  String areasOfActivity;
    private int state;
    private  String telephone;

    private  String address;

    private  String dateCompany;

    private  String userToken;

    private  String adminToken;

    private  String orgId;

    private  String others;

    private List<OrgUsers> orgUserList;

}
