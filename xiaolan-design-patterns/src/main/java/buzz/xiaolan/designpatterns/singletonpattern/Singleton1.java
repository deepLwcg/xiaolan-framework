package buzz.xiaolan.designpatterns.singletonpattern;

/**
 * @Author Wang Chenguang
 * @Email wcg.chen@foxmail.com
 * @Date 2024/2/28 20:58
 * @Description SingleObject
 */

public class Singleton1 {

    private static Singleton1 instance;
    private Singleton1() {
    }


    public static Singleton1 getInstance() {
        if (instance == null) {
            instance = new Singleton1();
        }
        return instance;
    }
}
