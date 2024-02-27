package buzz.xiaolan.designpatterns.abstractfactory;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author Wang Chenguang
 * @Email wcg.chen@foxmail.com
 * @Date 2024/2/28 00:17
 * @Description Rectangle
 */
@Slf4j
public class Rectangle implements Shape {
    @Override
    public void draw() {
        log.info("Inside Rectangle::draw() method.");
    }
}
