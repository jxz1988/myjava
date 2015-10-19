package zookeeper.zookeeper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class RecipesLock {
	static String path = "/lock_path";
	static CuratorFramework client = CuratorFrameworkFactory.builder()
			.connectString("localhost:2181").sessionTimeoutMs(5000)
			.retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		client.start();
		final InterProcessMutex lock = new InterProcessMutex(client, path);
		final CountDownLatch down = new CountDownLatch(1);
		long a = System.currentTimeMillis();
		for (int i = 0; i < 10; i++) {
			new Thread(new Runnable() {

				public void run() {
					// TODO Auto-generated method stub
					try {
						down.await();
						lock.acquire();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println(e);
					}
					SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss|SSS");
					String no = sdf.format(new Date());
					System.out.println("id:" + no);
					try {
						lock.release();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}).start();
		}
		down.countDown();
		down.await();
		System.out.println(System.currentTimeMillis()-a);
	}

}
