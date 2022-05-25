package kara.diamond.billing.service.logic;

import kara.diamond.billing.service.base.BaseDatabaseService;
import kara.diamond.billing.service.entity.Users;
import kara.diamond.billing.service.iinterfaces.UserService;
import kara.diamond.billing.service.model.response.UserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserLogic extends BaseDatabaseService implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserLogic.class);


    @Override
    public List<Users> getAllUsers() throws Exception {
        List<Users> users;
        String jpql = "SELECT a FROM Users a";
        users = getByQuery(Users.class, jpql);
        return users;
    }

}
