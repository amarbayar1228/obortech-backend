package kara.diamond.billing.service.model.response;

import lombok.Data;

@Data
public class IncentiveModel {
    private Long pkId;
    private  String title;
    private String description;
    private  int price;
    private  int quantity;
    private  int cnt;
    private String date;
    private String orderId;
    private String userPkid;
    private String itemId;
    private String state;
    private String orgId;
    private int insentStatus;
    private int paymentMethod;


    public IncentiveModel() {}


    public IncentiveModel(Long pkId, String title,  String description, int price, int cnt, int quantity, String date, String orderId, String userPkid, String itemId, String state, String orgId, int insentStatus, int paymentMethod) {
        this.pkId = pkId;
        this.title = title;
        this.state = state;
        this.description = description;
        this.cnt = cnt;
        this.date = date;
        this.orderId = orderId;
        this.userPkid = userPkid;
        this.itemId = itemId;
        this.orgId = orgId;
        this.insentStatus = insentStatus;
        this.paymentMethod = paymentMethod;

    }
}
