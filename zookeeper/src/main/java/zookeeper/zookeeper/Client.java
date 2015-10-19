package zookeeper.zookeeper;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.ZKPaths;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class Client {

	static String path = "/database5/0";
	static CuratorFramework client = CuratorFrameworkFactory.builder()
			.connectString("localhost:2181").sessionTimeoutMs(5000)
			.retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		client.start();
		for(int i=0 ; i<10;i++){
			System.out.println(client.create().creatingParentsIfNeeded()
				.withMode(CreateMode.PERSISTENT_SEQUENTIAL)
				.forPath(path, "init".getBytes()).split("/")[2]);
		}
		
		//Stat stat = new Stat();
		//System.out.println(new String(client.getData().storingStatIn(stat)
		//		.forPath(path)));
		ZooKeeper zoo = client.getZookeeperClient().getZooKeeper();
		System.out.println(ZKPaths.getSortedChildren(zoo, "/database5").size());
		// client.delete().guaranteed().deletingChildrenIfNeeded()
		// .withVersion(stat.getVersion()).forPath(path);
		// System.out.println(client.setData().withVersion(stat.getVersion())
		// .forPath(path).getVersion());
		// System.out.println(stat.getVersion());
		// try {
		// client.setData().withVersion(stat.getVersion()).forPath(path);
		// System.out.println(stat.getVersion());
		// } catch (Exception e) {
		// // TODO: handle exception
		// System.out.println(e.getMessage());
		// }
		// Thread.sleep(Integer.MAX_VALUE);
		new BufferedReader(new InputStreamReader(System.in)).readLine();
	}

}
