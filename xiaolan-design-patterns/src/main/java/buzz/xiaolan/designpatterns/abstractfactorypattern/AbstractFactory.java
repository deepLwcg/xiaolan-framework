package buzz.xiaolan.designpatterns.abstractfactorypattern;

/**
 * @Author Wang Chenguang
 * @Email wcg.chen@foxmail.com
 * @Date 2024/2/28 00:11
 * @Description AbstractFactory
 */
public abstract class AbstractFactory {

    /**
     * get color
     *
     * @param clazz class
     * @return buzz.xiaolan.designpatterns.abstractfactory.Color
     * @author Wang Chenguang
     */
    public abstract Color getColor(Class<? extends Color> clazz);

    /**
     * get shape
     *
     * @param clazz class
     * @return buzz.xiaolan.designpatterns.factory.Shape
     * @author Wang Chenguang
     */
    public abstract Shape getShape(Class<? extends Shape> clazz);
}
