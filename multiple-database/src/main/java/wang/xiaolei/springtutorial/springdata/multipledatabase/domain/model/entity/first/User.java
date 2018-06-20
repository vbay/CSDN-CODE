package wang.xiaolei.springtutorial.springdata.multipledatabase.domain.model.entity.first;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;


/**
 * Create by wangxiaolei on 2018/5/23 6:41 PM
 */

@Entity
@Data
@Table(name = "test_a")
public class User {
    private @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    @Column(name = "id")
    long id;


    @Column(name = "name")
    private String name;
}
