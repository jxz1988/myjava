package zookeeper.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

public class NodeCache_Sample {

	static String path = "/zk-book/cache";
	static CuratorFramework client = CuratorFrameworkFactory.builder()
			.connectString("localhost:2181").sessionTimeoutMs(5000)
			.retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		client.start();
		client.create().creatingParentsIfNeeded()
				.withMode(CreateMode.EPHEMERAL)
				.forPath(path, "init".getBytes());
		final NodeCache cache = new NodeCache(client, path, false);
		cache.start(true);
		cache.getListenable().addListener(new NodeCacheListener() {
			
			public void nodeChanged() throws Exception {
				// TODO Auto-generated method stub
				System.out.println(new String(cache.getCurrentData().getData()));
			}
		});
		//client.setData().forPath(path, "init2".getBytes());
		Thread.sleep(1000);
		client.delete().deletingChildrenIfNeeded().forPath(path);
		Thread.sleep(Integer.MAX_VALUE);
	}

}
