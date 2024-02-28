package buzz.xiaolan.designpatterns.builderpattern;

/**
 * @Author Wang Chenguang
 * @Email wcg.chen@foxmail.com
 * @Date 2024/2/28 22:17
 * @Description VegBurger
 */
public class VegBurger extends Burger {
    @Override
    public float price() {
        return 20f;
    }

    @Override
    public String name() {
        return "Veg Burger";
    }
}
