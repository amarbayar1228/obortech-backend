package kara.diamond.billing.service.model.response;

import lombok.Data;

@Data
public class ItemModel {
    private String pkId;
    private  String title;
    private String description;
    private  int price;
    private  int quantity;
}
