package buzz.xiaolan.designpatterns.factory;

/**
 * @Author Wang Chenguang
 * @Email wcg.chen@foxmail.com
 * @Date 2024/2/27 22:41
 * @Description 工程模式
 */
public class FactoryModeDemo {

    public static void main(String[] args) {

        ShapeFactory shapeFactory = new ShapeFactory();

        Shape shape2 = shapeFactory.getShape(Circle.class);
        shape2.draw();

        Shape shape3 = shapeFactory.getShape(Square.class);
        shape3.draw();

        Shape shape4 = shapeFactory.getShape(Rectangle.class);
        shape4.draw();

    }
}