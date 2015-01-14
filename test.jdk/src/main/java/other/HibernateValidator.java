package other;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.junit.Test;

import javax.validation.constraints.Min;
import java.io.IOException;

/**
 * 功能描述:
 * <br>
 *
 * @author wanggen on 14/10/20.
 */
public class HibernateValidator {

    @Test
    public void test() throws IOException {

    }
    
}

@Data
class Person{

    @NotBlank(message = "姓名不能为空")
    private String name;

    @Min(value = 20, message = "最小年龄为20")
    private Integer age;

    private String address;

}
