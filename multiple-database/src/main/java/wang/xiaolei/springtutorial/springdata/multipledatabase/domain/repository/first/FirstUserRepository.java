package wang.xiaolei.springtutorial.springdata.multipledatabase.domain.repository.first;


import org.springframework.data.repository.CrudRepository;
import wang.xiaolei.springtutorial.springdata.multipledatabase.domain.model.entity.first.User;

/**
 * Create by wangxiaolei on 2018/5/25 6:47 PM
 */
public interface FirstUserRepository extends CrudRepository<User,Long> {
}
