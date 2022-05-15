package kara.diamond.billing.service.iinterfaces;


import kara.diamond.billing.service.entity.Users;


import java.util.List;

public interface UserService {

    public List<Users> getAllUsers() throws Exception;

}
