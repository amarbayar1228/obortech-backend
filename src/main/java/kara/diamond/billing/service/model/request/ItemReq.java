package kara.diamond.billing.service.model.request;

import lombok.Data;

import java.util.List;
@Data
public class ItemReq {
    private String date;
    private List<Item> list;
}
