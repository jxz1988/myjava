package zookeeper.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.framework.recipes.cache.PathChildrenCache.StartMode;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

public class PathChildrenCache_Sample {
	static String path = "/mzk-book";
	static CuratorFramework client = CuratorFrameworkFactory.builder()
			.connectString("localhost:2181").sessionTimeoutMs(5000)
			.retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		client.start();
		PathChildrenCache cache = new PathChildrenCache(client, path, false);
		cache.start(StartMode.POST_INITIALIZED_EVENT);
		cache.getListenable().addListener(new PathChildrenCacheListener() {
			
			public void childEvent(CuratorFramework client, PathChildrenCacheEvent event)
					throws Exception {
				// TODO Auto-generated method stub
				switch(event.getType()){
				case CHILD_ADDED:
				System.out.println("child_add:"+event.getData().getPath());
				break;
				case CHILD_UPDATED:
				System.out.println("child_update:"+event.getData().getPath());
				break;
				case CHILD_REMOVED:
				break;
				default:
					break;
				}
				
			}
		});
		//client.create().withMode(CreateMode.PERSISTENT).forPath(path);
		Thread.sleep(1000);
		try {
			client.create().withMode(CreateMode.EPHEMERAL).forPath(path+"/c1");
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		Thread.sleep(1000);
		//client.setData().forPath(path+"/c1", "init".getBytes());
		Thread.sleep(1000);
		//client.delete().forPath(path+"/c1");
		Thread.sleep(1000);
		//client.delete().forPath(path);
		Thread.sleep(Integer.MAX_VALUE);
	}

}
