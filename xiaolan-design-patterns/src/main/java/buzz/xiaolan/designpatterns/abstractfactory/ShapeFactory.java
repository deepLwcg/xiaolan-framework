package buzz.xiaolan.designpatterns.abstractfactory;



/**
 * @Author Wang Chenguang
 * @Email wcg.chen@foxmail.com
 * @Date 2024/2/28 00:09
 * @Description ShapeFactory
 */
public class ShapeFactory extends AbstractFactory {
    @Override
    public Color getColor(Class<? extends Color> clazz) {
        return null;
    }

    @Override
    public Shape getShape(Class<? extends Shape> clazz) {
        if (clazz == Circle.class) {
            return new Circle();
        } else if (clazz == Square.class) {
            return new Square();
        } else if (clazz == Rectangle.class) {
            return new Rectangle();
        }
        return null;
    }
}
