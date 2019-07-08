package com.cxd.cool.util;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCache.StartMode;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessMultiLock;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

import java.util.Arrays;

public class ZookUtil {

    // zookeeper地址
    static final String CONNECT_ADDR = "127.0.0.1:2181";
    // session超时时间 *
    static final int SESSION_OUTTIME = 5000;

    public static void main(String[] args) {

        // 1 重试策略：初试时间为1s 重试10次
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 10);
        // 2 通过工厂创建连接
        CuratorFramework client = CuratorFrameworkFactory.builder().connectString(CONNECT_ADDR).sessionTimeoutMs(SESSION_OUTTIME).retryPolicy(retryPolicy).build();

        // 3 开启连接
        client.start();

        try {
            //持久顺序节点--c10000000001 加后缀0000000001
            //System.out.println(client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT_SEQUENTIAL).forPath("/cxd/c1"));

            //临时节点
            //System.out.println(client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath("/cxd/ep1"));

            //获取值
            System.out.println(client.getChildren().forPath("/cxd"));

            //顺序节点名称不是原始的，要加后缀
            System.out.println(new String(client.getData().forPath("/cxd/c10000000004")));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void create(CuratorFramework client) {
        // 删除
        try {
            client.delete().deletingChildrenIfNeeded().forPath("/chen");
            client.delete().deletingChildrenIfNeeded().forPath("/cxd");
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        // 4 建立节点 指定节点类型（不加withMode默认为持久类型节点）、路径、数据内容
        try {
            client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath("/cxd/c1", "test01".getBytes());
            client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath("/cxd/c2", "test02".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            String ret1 = new String(client.getData().forPath("/cxd/c1"));
            System.out.println("ret1=" + ret1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 5 建立一个PathChildrenCache缓存,第三个参数为是否接受节点数据内容 如果为false则不接受
        try {
            final PathChildrenCache cache = new PathChildrenCache(client, "/chen", true);
            cache.start(StartMode.POST_INITIALIZED_EVENT);
            cache.getListenable().addListener(new PathChildrenCacheListener() {
                @Override
                public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
                    switch (event.getType()) {
                        case CHILD_ADDED:
                            System.out.println("CHILD_ADDED :" + event.getData().getPath());
                            break;
                        case CHILD_UPDATED:
                            System.out.println("CHILD_UPDATED :" + event.getData().getPath());
                            break;
                        case CHILD_REMOVED:
                            System.out.println("CHILD_REMOVED :" + event.getData().getPath());
                            break;
                        default:
                            break;
                    }
                }
            });

            client.create().forPath("/chen", "init".getBytes());

            // 添加子节点
            Thread.sleep(1000);
            client.create().forPath("/chen/c1", "c1内容".getBytes());
            Thread.sleep(1000);
            client.create().forPath("/chen/c2", "c2内容".getBytes());

            // 修改子节点
            Thread.sleep(1000);
            client.setData().forPath("/chen/c1", "c1更新内容".getBytes());

            // 删除子节点
            Thread.sleep(1000);
            client.delete().forPath("/chen/c2");

            // 删除本身节点
            Thread.sleep(1000);
            //client.delete().deletingChildrenIfNeeded().forPath("/chen");

        } catch (Exception e) {
            e.printStackTrace();
        }

        final NodeCache nodeCache = new NodeCache(client, "/chenxd");
        nodeCache.getListenable().addListener(new NodeCacheListener() {

            @Override
            public void nodeChanged() throws Exception {
                System.out.println("NodeCache changed, data is: " + new String(nodeCache.getCurrentData().getData()));
            }
        });

        //子节点
        PathChildrenCache pathChildrenCache = new PathChildrenCache(client, "/chenxd", true);
        pathChildrenCache.getListenable().addListener((c1, c2) -> {
        });

        InterProcessLock lock = new InterProcessMultiLock(client, Arrays.asList("/chen"));
        try {
            lock.acquire();
            lock.release();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
