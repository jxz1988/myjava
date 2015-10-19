package zookeeper.zookeeper;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

public class AsyncClient {

	static String path = "/zk-book/c2";
	static CuratorFramework client = CuratorFrameworkFactory.builder()
			.connectString("localhost:2181").sessionTimeoutMs(5000)
			.retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();
	static CountDownLatch semaphore = new CountDownLatch(1);
	static ExecutorService tp = Executors.newFixedThreadPool(2);

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		client.start();
		System.out.println("Main thread" + Thread.currentThread().getName());
		client.create().creatingParentsIfNeeded()
				.withMode(CreateMode.EPHEMERAL)
				.inBackground(new BackgroundCallback() {

					public void processResult(CuratorFramework client,
							CuratorEvent event) throws Exception {
						// TODO Auto-generated method stub
						System.out.println("event1[code: "
								+ event.getResultCode() + " type: "
								+ event.getType()+"]");
						System.out.println("ProcessResult:"+Thread.currentThread().getName());
						semaphore.countDown();
					}
				},tp).forPath(path,"init".getBytes());
		
		client.create().creatingParentsIfNeeded()
		.withMode(CreateMode.EPHEMERAL)
		.inBackground(new BackgroundCallback() {

			public void processResult(CuratorFramework client,
					CuratorEvent event) throws Exception {
				// TODO Auto-generated method stub
				System.out.println("event2[code: "
						+ event.getResultCode() + " type: "
						+ event.getType()+"]");
				System.out.println("ProcessResult:"+Thread.currentThread().getName());
				semaphore.countDown();
			}
		}).forPath(path,"init".getBytes());
		semaphore.await();
		tp.shutdown();
	}

}
