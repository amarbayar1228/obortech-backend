package kara.diamond.billing.service.model.request;

import lombok.Data;

@Data
public class OrgUsers {
    private String pkId;
    private String orgId;
    private String userToken;
    private float insentive;
    private String date;
}
