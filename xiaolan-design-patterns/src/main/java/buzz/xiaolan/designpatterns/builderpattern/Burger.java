package buzz.xiaolan.designpatterns.builderpattern;

/**
 * @Author Wang Chenguang
 * @Email wcg.chen@foxmail.com
 * @Date 2024/2/28 22:12
 * @Description Burger
 */
public abstract class Burger implements Item {


    @Override
    public Packing packing() {
        return new Wrapper();
    }


    @Override
    public abstract float price();
}
