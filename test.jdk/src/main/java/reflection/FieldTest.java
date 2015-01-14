package reflection;

import com.google.common.base.Stopwatch;
import lombok.Data;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.concurrent.TimeUnit;

/**
 * @author wanggen on 14-7-30.
 */
public class FieldTest {

    @Test
    public void test(){

        Field[] personFields = Person.class.getFields();

        for(Field field: personFields)
            System.out.println(field.getName());


        personFields = Person.class.getDeclaredFields();

        for(Field field: personFields){
            System.out.println(field.getName());
            System.out.println("是否是 Integer "+field.getType().isAssignableFrom(Integer.class));
        }

    }


    @Test
    public void testGetFields(){

        Stopwatch stopwatch = Stopwatch.createStarted();
        for(int i=1;i<=1000000;i++){
            Person.class.getDeclaredFields();
        }
        System.err.println("100W 次获取所有 Filed 耗时"+stopwatch.elapsed(TimeUnit.MILLISECONDS));

    }


    @Test
    public void testModifier() throws NoSuchFieldException {

        System.out.println(Modifier.FINAL);

        System.out.println(Person.class.getDeclaredField("versionUID").getModifiers());

//        System.out.println(Person.class.getDeclaredField("versionUID").isEnumConstant());


        System.out.println(Modifier.isFinal(Person.class.getDeclaredField("versionUID").getModifiers()));
    }

    @Test
    public void testSupperField(){

        System.out.println("-----------for current------------");
        for(Field field: Father.class.getDeclaredFields()){
            System.out.println(field.getName());
        }

        System.out.println("------------for supper---------------");
        for(Field field: Father.class.getSuperclass().getDeclaredFields()){
            System.err.println("Supper "+field.getName());
        }

    }

}


@Data
class Person{
    private static final long versionUID=7898678908098798l;
    private String name;

    private Integer age;

    private String address;

}

@Data
class Father extends Person{

    private String job;


}
