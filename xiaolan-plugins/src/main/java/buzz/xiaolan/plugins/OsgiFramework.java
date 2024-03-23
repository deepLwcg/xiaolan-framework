package buzz.xiaolan.plugins;

import org.apache.felix.framework.Felix;
import org.apache.felix.framework.util.FelixConstants;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Wang Chenguang
 * @Email wcg.chen@foxmail.com
 * @Date 2024/3/5 23:54
 * @Description OsgiFramework
 */
public class OsgiFramework {

    private final Felix felix;

    private static class OsgiFrameworkHolder {
        private static final OsgiFramework INSTANCE = new OsgiFramework();
    }

    private OsgiFramework() {
        Map<String, String> configMap = new HashMap<>();
        configMap.put(FelixConstants.LOG_LEVEL_PROP, "4");
        felix = new Felix(configMap);
    }

    public static OsgiFramework getInstance() {
        return OsgiFrameworkHolder.INSTANCE;
    }

    public Felix getFelix() {
        return felix;
    }
}
