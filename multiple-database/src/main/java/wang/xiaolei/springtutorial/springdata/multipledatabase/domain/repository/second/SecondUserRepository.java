package wang.xiaolei.springtutorial.springdata.multipledatabase.domain.repository.second;

import org.springframework.data.repository.CrudRepository;
import wang.xiaolei.springtutorial.springdata.multipledatabase.domain.model.entity.second.User;

/**
 * Create by wangxiaolei on 2018/5/25 6:02 PM
 */
public interface SecondUserRepository extends CrudRepository<User, Long> {

}
