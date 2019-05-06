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
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

public class ZookUtil {

    // zookeeper地址
    static final String CONNECT_ADDR = "127.0.0.1:2181";
    // session超时时间 *
    static final int SESSION_OUTTIME = 5000;

    public static void main(String[] args) {

        // 1 重试策略：初试时间为1s 重试10次
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 10);
        // 2 通过工厂创建连接
        CuratorFramework cf = CuratorFrameworkFactory.builder().connectString(CONNECT_ADDR).sessionTimeoutMs(SESSION_OUTTIME).retryPolicy(retryPolicy).build();

        // 3 开启连接
        cf.start();

        // 删除
        try {
            cf.delete().deletingChildrenIfNeeded().forPath("/chen");
            cf.delete().deletingChildrenIfNeeded().forPath("/cxd");
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        // 4 建立节点 指定节点类型（不加withMode默认为持久类型节点）、路径、数据内容
        try {
            cf.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath("/cxd/c1", "test01".getBytes());
            cf.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath("/cxd/c2", "test02".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String ret1 = new String(cf.getData().forPath("/cxd/c1"));
            System.out.println("ret1=" + ret1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 5 建立一个PathChildrenCache缓存,第三个参数为是否接受节点数据内容 如果为false则不接受
        try {
            final PathChildrenCache cache = new PathChildrenCache(cf, "/chen", true);
            cache.start(StartMode.POST_INITIALIZED_EVENT);
            cache.getListenable().addListener(new PathChildrenCacheListener() {
                @Override
                public void childEvent(CuratorFramework cf, PathChildrenCacheEvent event) throws Exception {
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

            cf.create().forPath("/chen", "init".getBytes());

            // 添加子节点
            Thread.sleep(1000);
            cf.create().forPath("/chen/c1", "c1内容".getBytes());
            Thread.sleep(1000);
            cf.create().forPath("/chen/c2", "c2内容".getBytes());

            // 修改子节点
            Thread.sleep(1000);
            cf.setData().forPath("/chen/c1", "c1更新内容".getBytes());

            // 删除子节点
            Thread.sleep(1000);
            cf.delete().forPath("/chen/c2");

            // 删除本身节点
            Thread.sleep(1000);
            cf.delete().deletingChildrenIfNeeded().forPath("/chen");

        } catch (Exception e) {
            e.printStackTrace();
        }

        final NodeCache nodeCache = new NodeCache(cf, "/chenxd");
        nodeCache.getListenable().addListener(new NodeCacheListener() {

            @Override
            public void nodeChanged() throws Exception {
                System.out.println("NodeCache changed, data is: " + new String(nodeCache.getCurrentData().getData()));
            }
        });
        
        //nodeCache.close();

        // TreeCache treeCache=new TreeCache();

        // ----------------------------
    }

}
