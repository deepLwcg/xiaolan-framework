package buzz.xiaolan.bundle;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

/**
 * @Author Wang Chenguang
 * @Email wcg.chen@foxmail.com
 * @Date 2024/3/6 00:32
 * @Description HelloActivator
 */
public class HelloActivator implements BundleActivator {

    private ServiceRegistration registration;
    @Override
    public void start(BundleContext bundleContext) throws Exception {
        registration = bundleContext.registerService(
                HelloWorldService.class.getName(),
                new HelloWorldServiceImpl(),
                null);
    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        registration.unregister();
    }
}
