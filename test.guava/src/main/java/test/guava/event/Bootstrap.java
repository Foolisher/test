package test.guava.event;

/**
 * @author wanggen on 14-7-28.
 * @Desc:
 */
public class Bootstrap {

    public static void main(String[] args){


        NewsCenter.register(new StudentListener());

        NewsCenter.register(new WorkerListener());


        NewsCenter.post(new StudentMsg("你有新的就业信息"));

        NewsCenter.post(new WorkerMsg("你有新的社会招聘信息"));

    }

}
