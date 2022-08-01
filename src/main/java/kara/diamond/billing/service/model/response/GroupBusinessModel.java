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
    private Long itemPkId;
    private String itemTitle;
    private String itemDescription;
    private int itemQuantity;
    private int itemPrice;



    public GroupBusinessModel() {}


    public GroupBusinessModel(Long pkId, String title, int status, String description, Long itemPkId, String itemTitle, int itemQuantity,  String itemDescription, int itemPrice ) {
        this.pkId = pkId;
        this.title = title;
        this.status = status;
        this.description = description;
        this.itemPkId = itemPkId;
        this.itemTitle = itemTitle;
        this.itemQuantity = itemQuantity;
        this.itemDescription = itemDescription;
        this.itemPrice = itemPrice;
    }
}
