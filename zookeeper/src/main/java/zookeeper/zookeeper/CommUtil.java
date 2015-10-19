package zookeeper.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class CommUtil {
	CuratorFramework client = CuratorFrameworkFactory.builder()
			.connectString("localhost:2181").sessionTimeoutMs(5000)
			.retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();
}
