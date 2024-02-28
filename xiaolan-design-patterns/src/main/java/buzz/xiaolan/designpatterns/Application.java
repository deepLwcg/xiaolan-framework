package buzz.xiaolan.designpatterns;

import buzz.xiaolan.designpatterns.abstractfactorypattern.AbstractFactoryPatternDemo;
import buzz.xiaolan.designpatterns.builderpattern.BuilderPatternDemo;
import buzz.xiaolan.designpatterns.factorypattern.FactoryPatternDemo;
import buzz.xiaolan.designpatterns.singletonpattern.SingletonPatternDemo;

/**
 * @Author Wang Chenguang
 * @Email wcg.chen@foxmail.com
 * @Date 2024/2/27 22:36
 * @Description Application
 * 代理：export all_proxy=socks5://192.168.100.9:10808
 */
public class Application {

    public static void main(String[] args) {
        // 工厂模式
        System.out.println("---------- 工厂模式 -----------");
        FactoryPatternDemo.main(args);
        System.out.println("------------------------------");
        // 抽象工厂模式
        System.out.println("---------- 抽象工厂模式 -----------");
        AbstractFactoryPatternDemo.main(args);
        System.out.println("------------------------------");
        // 单例模式
        System.out.println("---------- 单例模式 -----------");
        SingletonPatternDemo.main(args);
        System.out.println("------------------------------");
        // 建造者模式
        System.out.println("---------- 建造者模式 -----------");
        BuilderPatternDemo.main(args);
        System.out.println("------------------------------");
    }

}
