package kara.diamond.billing.service.model.response;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GroupBusinessModel {

    private Long pkId;
    private String title;
    private int status;
    private String description;

    private int cnt;
    private String others;
    private int itemPriceTotal;
    private Long itemPkId;
    private String itemTitle;
    private String itemDescription;
    private int itemQuantity;
    private int itemPrice;
    private  int itemPriceD;

    private int itemCnt;

    private byte[] img;


    public GroupBusinessModel() {}


    public GroupBusinessModel(Long pkId, String title, int status, String description, int cnt, String others, int itemPriceTotal, Long itemPkId, int itemPriceD,  int itemCnt, byte[] img, String itemTitle, int itemQuantity,  String itemDescription, int itemPrice ) {
        this.pkId = pkId;
        this.title = title;
        this.status = status;
        this.description = description;
        this.cnt = cnt;
        this.others = others;
        this.itemPriceTotal = itemPriceTotal;
        this.itemPkId = itemPkId;
        this.itemPriceD = itemPriceD;
        this.itemCnt = itemCnt;
        this.img = img;
        this.itemTitle = itemTitle;
        this.itemQuantity = itemQuantity;
        this.itemDescription = itemDescription;
        this.itemPrice = itemPrice;

    }
}
