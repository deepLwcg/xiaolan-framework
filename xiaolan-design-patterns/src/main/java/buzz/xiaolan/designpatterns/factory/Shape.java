package buzz.xiaolan.designpatterns.factory;

/**
 * @Author Wang Chenguang
 * @Email wcg.chen@foxmail.com
 * @Date 2024/2/27 22:43
 * @Description Shape
 */

public interface Shape {

    /**
     * 绘画
     *
     * @author Wang Chenguang
     */
    default void draw(){
        throw new RuntimeException("draw method is not implement");
    }

}
