package kara.diamond.billing.service.model.response;

import lombok.Data;

import java.util.List;

@Data
public class OrderArray {
    private String date;
    private List<OrderHistoryModel> list;
}
