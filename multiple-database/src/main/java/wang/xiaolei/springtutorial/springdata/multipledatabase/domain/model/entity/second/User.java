package wang.xiaolei.springtutorial.springdata.multipledatabase.domain.model.entity.second;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Create by wangxiaolei on 2018/5/25 6:01 PM
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
