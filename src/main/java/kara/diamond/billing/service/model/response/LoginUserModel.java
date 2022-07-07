package kara.diamond.billing.service.model.response;

import lombok.Data;

@Data
public class LoginUserModel {
    private String pkId;
    private  String username;
    private String firstname;
    private String lastname;
    private  long exp;
    private String email;
    private String token;

}
