package kara.diamond.billing.service.model.response;

import lombok.Data;

import java.util.List;

@Data
public class ExampleArray {

    private String date;
    private String id;
    private List<ItemModel> list;

}
