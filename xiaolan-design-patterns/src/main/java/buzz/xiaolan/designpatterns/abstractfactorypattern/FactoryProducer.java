package buzz.xiaolan.designpatterns.abstractfactorypattern;

/**
 * @Author Wang Chenguang
 * @Email wcg.chen@foxmail.com
 * @Date 2024/2/28 00:19
 * @Description FactoryProducer
 */
public class FactoryProducer {

    public static AbstractFactory getFactory(Class<? extends AbstractFactory> clazz) {
        if (ShapeFactory.class.equals(clazz)) {
            return new ShapeFactory();
        } else if (ColorFactory.class.equals(clazz)) {
            return new ColorFactory();
        }
        return null;
    }

}
