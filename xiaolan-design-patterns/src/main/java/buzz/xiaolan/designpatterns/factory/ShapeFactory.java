package buzz.xiaolan.designpatterns.factory;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author Wang Chenguang
 * @Email wcg.chen@foxmail.com
 * @Date 2024/2/27 22:51
 * @Description ShapeFactory
 */
@Slf4j
public class ShapeFactory {

    public Shape getShape(Class<?> shape){
        if (shape == null || shape.equals(Shape.class)){
            log.warn("Shape is null");
            return null;
        }
        if (shape.equals(Circle.class)){
            return new Circle();
        }
        if (shape.equals(Square.class)){
            return new Square();
        }
        if (shape.equals(Rectangle.class)){
            return new Rectangle();
        }
        log.warn("Shape is null");
        return null;

    }

}
