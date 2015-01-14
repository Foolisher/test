package oop;

import lombok.*;

/**
 * @author wanggen on 14-7-26.
 */
public class StaticInnerClassTest {


    public static void main(String[] args){

        Person person = new Person("wg", 23, new Person.Home("杭州", "18686xxxxxx®"));

        System.out.println(person);


    }


}

@AllArgsConstructor
@Data
class Person{

//    @Getter @Setter
    private  String name;

    @Getter @Setter
    private Integer age;

    @Getter @Setter
    private Home home;

    /**<pre>
     * 内部静态类，只是作为外部内一批数据的独立封装，
     * 1. 为什么要是静态呢？因为我们有时不想把它放到外部去，因为没有其他任何一个地方会
     *    用到这个类，这个类（比如Home）只有Person与其会有强依赖关系，那么我们把它
     *    用static定义在Person中是最恰当的了，这样，这个 Home 并随每次 new Person()
     *    而生，也不随每次 Person 回收而灭，它存在于 Person.class 体中（不过准确来说
     *    也不是这样的，java编译器其实是把它编译到外部去了的），静态内部类的一个好处就是
     *    提炼外部类的成员变量，使结构更加明确，更具可读性
     * </pre>
     */
    @ToString @AllArgsConstructor
    public static class Home{

        @Getter @Setter
        private String address;

        @Getter @Setter
        private String tel;

    }

    public String getName(){
        return "";
    }


}
