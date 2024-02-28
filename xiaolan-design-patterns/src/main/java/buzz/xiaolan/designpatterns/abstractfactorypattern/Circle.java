package buzz.xiaolan.designpatterns.abstractfactorypattern;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author Wang Chenguang
 * @Email wcg.chen@foxmail.com
 * @Date 2024/2/28 00:15
 * @Description Circle
 */
@Slf4j
public class Circle implements Shape {
    @Override
    public void draw() {
        log.info("Inside Circle::draw() method.");
    }
}
