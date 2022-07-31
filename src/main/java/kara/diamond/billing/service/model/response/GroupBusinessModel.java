package kara.diamond.billing.service.model.response;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GroupBusinessModel {

    private String pkId;
    private String title;
    private int status;
    private String description;
    private Long itemPkId;


    public GroupBusinessModel() {}


    public GroupBusinessModel(String pkId, String title, int status, String description, Long itemPkId) {
        this.pkId = pkId;
        this.title = title;
        this.status = status;
        this.description = description;
        this.itemPkId = itemPkId;
    }
}
