package kara.diamond.billing.service.model.request;

import kara.diamond.billing.service.model.response.GroupBusinessModel;
import lombok.Data;

import java.util.List;

@Data
public class OrderHistory {
    private String pkId;
    private  String title;
    private String description;
    private  int price;
    private  int quantity;
    private  int cnt;
    private String orderId;
    private  String date;
    private  String userPkid;

    private String orgId;
    private int insentStatus;
    private int paymentMethod;

    private String state;
    private String itemId;
    private List<GroupBusinessModel> gbm;
}
