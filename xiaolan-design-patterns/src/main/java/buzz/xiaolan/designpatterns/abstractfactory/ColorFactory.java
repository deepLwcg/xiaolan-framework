package buzz.xiaolan.designpatterns.abstractfactory;

/**
 * @Author Wang Chenguang
 * @Email wcg.chen@foxmail.com
 * @Date 2024/2/28 00:10
 * @Description ColorFactory
 */
public class ColorFactory extends AbstractFactory {


    @Override
    public Color getColor(Class<? extends Color> clazz) {
        if (clazz == Red.class) {
            return new Red();
        } else if (clazz == Green.class) {
            return new Green();
        } else if (clazz == Blue.class) {
            return new Blue();
        }
        return null;
    }

    @Override
    public Shape getShape(Class<? extends Shape> clazz) {
        return null;
    }
}
