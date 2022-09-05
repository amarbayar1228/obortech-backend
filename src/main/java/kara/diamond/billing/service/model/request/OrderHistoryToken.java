package kara.diamond.billing.service.model.request;

import lombok.Data;

import java.util.List;
@Data
public class OrderHistoryToken {
   private String token;
   private String orgId;
   private int paymentMethod;
   private int insentStatus;
   private List<OrderHistory> product;
}
