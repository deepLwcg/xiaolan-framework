package buzz.xiaolan.designpatterns.singletonpattern;

/**
 * @Author Wang Chenguang
 * @Email wcg.chen@foxmail.com
 * @Date 2024/2/29 00:00
 * @Description SingletonPatternDemo
 */
public class SingletonPatternDemo {
    public static void main(String[] args) {

        Singleton1 singleton1 = Singleton1.getInstance();
        System.out.println("懒汉式，线程不安全: "+singleton1.toString());

        Singleton2 singleton2 = Singleton2.getInstance();
        System.out.println("懒汉式，线程安全: "+singleton2.toString());

        Singleton3 singleton3 = Singleton3.getInstance();
        System.out.println("饿汉式: "+singleton3.toString());

        Singleton4 singleton4 = Singleton4.getInstance();
        System.out.println("双检锁/双重校验锁: "+singleton4.toString());

        Singleton5 singleton5 = Singleton5.getInstance();
        System.out.println("登记式/静态内部: "+singleton5.toString());

        Singleton6 singleton6 = Singleton6.INSTANCE;
        System.out.println("枚举: "+singleton6.toString());


    }
}
