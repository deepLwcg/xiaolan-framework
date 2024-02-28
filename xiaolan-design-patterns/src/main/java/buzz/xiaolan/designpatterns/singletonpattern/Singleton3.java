package buzz.xiaolan.designpatterns.singletonpattern;

/**
 * @Author Wang Chenguang
 * @Email wcg.chen@foxmail.com
 * @Date 2024/2/28 21:50
 * @Description Singleton3
 */
public class Singleton3 {

    private static Singleton3 instance = new Singleton3();

    private Singleton3() {
    }

    public static Singleton3 getInstance() {
        return instance;
    }

}
