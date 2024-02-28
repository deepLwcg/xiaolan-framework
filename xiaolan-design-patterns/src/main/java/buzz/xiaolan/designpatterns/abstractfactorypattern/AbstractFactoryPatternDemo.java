package buzz.xiaolan.designpatterns.abstractfactorypattern;

/**
 * @Author Wang Chenguang
 * @Email wcg.chen@foxmail.com
 * @Date 2024/2/28 00:08
 * @Description AbstractFactoryDemo
 */
public class AbstractFactoryPatternDemo {

    public static void main(String[] args) {

        AbstractFactory colorFactory = FactoryProducer.getFactory(ColorFactory.class);
        assert colorFactory != null;
        colorFactory.getColor(Blue.class).fill();
        colorFactory.getColor(Red.class).fill();
        colorFactory.getColor(Green.class).fill();

        AbstractFactory shapeFactory = FactoryProducer.getFactory(ShapeFactory.class);
        assert shapeFactory != null;
        shapeFactory.getShape(Square.class).draw();
        shapeFactory.getShape(Circle.class).draw();
        shapeFactory.getShape(Rectangle.class).draw();
    }
}
