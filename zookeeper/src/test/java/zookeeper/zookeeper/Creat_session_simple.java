package zookeeper.zookeeper;

import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;

public class Creat_session_simple {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ZkClient zkClient = new ZkClient("localhost:2181",5000);
		//zkClient.create("/app2",null, CreateMode.PERSISTENT);
		zkClient.create("/app2/", null, CreateMode.PERSISTENT_SEQUENTIAL);
		for(String node : zkClient.getChildren("/app2")){
			System.out.println(node);
		}
	}

}
