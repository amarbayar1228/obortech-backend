package kara.diamond.billing.service.model.request;

import lombok.Data;

@Data
public class LoginUser {
    private String pkId;
    private  String username;
    private String firstname;
    private String lastname;
    private  int exp;
    private  int isAdmin;
    private  int isSuperAdmin;
    private  String password;
    private String email;
    private String token;
}
