package kara.diamond.billing.service.model.request;

import lombok.Data;

@Data
public class PayInsentive {
    private String pkId;
    private String userPkId;
    private String date;
    private  int status;
    private float fee;
    private  int payMethod;
    private String orgId;
}
