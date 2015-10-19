package zookeeper.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.atomic.AtomicValue;
import org.apache.curator.framework.recipes.atomic.DistributedAtomicInteger;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.retry.RetryNTimes;

public class DisAtomicInt {

	static String path = "/atomicint_path";
	static CuratorFramework client = CuratorFrameworkFactory.builder()
			.connectString("localhost:2181").sessionTimeoutMs(5000)
			.retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		client.start();
		DistributedAtomicInteger atomicInteger = new DistributedAtomicInteger(
				client, path, new RetryNTimes(3, 1000));
		AtomicValue<Integer> rc = atomicInteger.add(8);
		atomicInteger.forceSet(0);
		System.out.println(rc.succeeded()+","+rc.postValue().toString()+","+rc.preValue());
	}

}
