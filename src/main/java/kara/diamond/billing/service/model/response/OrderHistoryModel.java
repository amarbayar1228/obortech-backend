package kara.diamond.billing.service.model.response;

import lombok.Data;

@Data
public class OrderHistoryModel {
    private String pkId;
    private  String title;
    private String description;
    private  int price;
    private  int quantity;
    private  int cnt;
}
