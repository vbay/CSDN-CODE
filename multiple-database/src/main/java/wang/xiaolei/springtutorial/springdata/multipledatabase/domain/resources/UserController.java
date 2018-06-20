package wang.xiaolei.springtutorial.springdata.multipledatabase.domain.resources;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import wang.xiaolei.springtutorial.springdata.multipledatabase.domain.model.entity.first.User;
import wang.xiaolei.springtutorial.springdata.multipledatabase.domain.repository.first.FirstUserRepository;
import wang.xiaolei.springtutorial.springdata.multipledatabase.domain.repository.second.SecondUserRepository;

import java.util.Optional;

/**
 * Create by wangxiaolei on 2018/5/23 6:38 PM
 */

@RestController
public class UserController {

    protected Logger logger = LoggerFactory.getLogger(UserController.class);



    @Autowired
    FirstUserRepository firstUserRepository;

    @Autowired
    SecondUserRepository secondUserRepository;

    @RequestMapping(value = "/data/hello", method = RequestMethod.GET)
    public String hello(@RequestParam long fid, @RequestParam long sid) {

//       Optional<User> user = userRepository.findById(id);

       Optional<User> bUser = firstUserRepository.findById(sid);
        return "Result: "
                + secondUserRepository.findAll().toString()
                +bUser.toString()
                ;

    }
}
