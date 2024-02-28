package buzz.xiaolan.designpatterns.factorypattern;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author Wang Chenguang
 * @Email wcg.chen@foxmail.com
 * @Date 2024/2/27 22:48
 * @Description Circle
 */
@Slf4j
public class Circle implements Shape{
    @Override
    public void draw() {
        log.info("Inside Circle::draw() method");
    }
}
