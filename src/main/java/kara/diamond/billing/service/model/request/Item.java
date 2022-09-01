package kara.diamond.billing.service.model.request;

import lombok.Data;

@Data
public class Item {
    private String pkId;
    private  String title;
    private String description;
    private  int price;
    private  int quantity;
    private int cnt;
    private int status;
    private String others;
    private String img;
}
