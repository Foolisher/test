package xml;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import org.junit.Test;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 功能描述:
 * <p/>
 *
 * @author wanggen on 14-9-11.
 */
public class XMLStream {

    @Test
    public void parse() {

        XStream xStream = new XStream();

        xStream.autodetectAnnotations(true);
        xStream.setMode(XStream.NO_REFERENCES);

//        xStream.aliasType("Person", Person.class);

//        xStream.alias("Person", Person.class);
        xStream.processAnnotations(Person.class);
        Person person = new Person();
        person = (Person) xStream.fromXML(
                "<Person>\n" +
                "   <name>王根</name>\n" +
                "   <age>24</age>\n" +
                "</Person>");

        System.out.println(person);

    }

}


@Data
@XStreamAlias("Person")
@XmlRootElement
class Person {

    @XStreamAlias("name")
    private String name;

    @XStreamAlias("age")
    private int age;

}
