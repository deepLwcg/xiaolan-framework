package buzz.xiaolan.designpatterns.builderpattern;

/**
 * @Author Wang Chenguang
 * @Email wcg.chen@foxmail.com
 * @Date 2024/2/28 22:09
 * @Description Bottle
 */
public class Bottle implements Packing {
    @Override
    public String pack() {
        return "Bottle";
    }
}
