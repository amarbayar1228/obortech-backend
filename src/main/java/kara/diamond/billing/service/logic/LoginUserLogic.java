package kara.diamond.billing.service.logic;

import kara.diamond.billing.service.base.BaseDatabaseService;
import kara.diamond.billing.service.base.NumericHelper;
import kara.diamond.billing.service.entity.ItemEntity;
import kara.diamond.billing.service.entity.LoginUserEntity;
import kara.diamond.billing.service.iinterfaces.LoginUserInterfaces;
import kara.diamond.billing.service.model.request.Item;
import kara.diamond.billing.service.model.request.LoginUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class LoginUserLogic extends BaseDatabaseService implements LoginUserInterfaces {
    private static final Logger logger = LoggerFactory.getLogger(LoginUserLogic.class);

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public String saveUser(LoginUser loginUser) throws Exception {
        String result = "";
        UUID uuid = UUID.randomUUID();
        long unixTime = Instant.now().getEpochSecond();
        try {
            LoginUserEntity loginUserEntity=  new LoginUserEntity();
            loginUserEntity.setPkId(NumericHelper.generateKey());
            loginUserEntity.setFirstname(loginUser.getFirstname());
            loginUserEntity.setLastname(loginUser.getLastname());
            loginUserEntity.setUsername(loginUser.getUsername());
            loginUserEntity.setExp(unixTime + 60 * 60 * 24 * 30);
            loginUserEntity.setEmail(loginUser.getEmail());
            loginUserEntity.setToken(uuid.toString());
            loginUserEntity.setPassword(loginUser.getPassword());
            insert(loginUserEntity);
            result = "Амжилттай хадгалалаа.";

        } catch (Exception e) {

            throw getDatabaseException(e);
        }
        return result;
    }
//        public List<LoginUser> getUser(LoginUser loginUser) throws Exception {
//
//            List<LoginUserEntity> loginUserEntitie;
//            String jpql = "SELECT a FROM LoginUser a";
//            List<LoginUser> loginList = new ArrayList<>();
//            loginUserEntitie = getByQuery(LoginUserEntity.class, jpql);
//
//            for (LoginUserEntity obj : loginUserEntitie) {
//                LoginUser loginUser2 = new LoginUser();
//                loginUser2.setPkId(String.valueOf(obj.getPkId()));
//                loginUser2.setEmail(obj.getEmail());
//                loginUser2.setFirstname(obj.getFirstname());
//                loginUser2.setLastname(obj.getLastname());
//                loginUser2.setUsername(obj.getUsername());
//                loginUser2.setToken(obj.getToken());
//                loginList.add(loginUser2);
//            }
//            return loginList;
//        }
}
