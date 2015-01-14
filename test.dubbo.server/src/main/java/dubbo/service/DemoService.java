package dubbo.service;


public interface DemoService {
    public void sayHello(String name) throws InterruptedException;
    void put(String msg);
    String get();
}
