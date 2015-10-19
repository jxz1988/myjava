package zookeeper.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListener;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class MasterSelect {
	static String path = "/master_path";
	static CuratorFramework client = CuratorFrameworkFactory.builder()
			.connectString("localhost:2181").sessionTimeoutMs(5000)
			.retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		client.start();
		LeaderSelector selector=new LeaderSelector(client, path, new LeaderSelectorListenerAdapter() {
			
			public void takeLeadership(CuratorFramework client) throws Exception {
				// TODO Auto-generated method stub
				System.out.println("Is master");
				Thread.sleep(3000);
				System.out.println("释放master权利");
			}
		});
		selector.autoRequeue();
		selector.start();
		Thread.sleep(Integer.MAX_VALUE);
	}
}
