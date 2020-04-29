package cn.zq.test;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

public class QgGoods {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis(); //获取开始时间
        ExecutorService pool = Executors.newFixedThreadPool(200);
        for (int i = 0; i < 1000; i++) {
//            AtomicReference<Integer> ar = new AtomicReference<>(i);
            pool.execute(() -> {
                try {
                    Connection conn = Jsoup.connect("http://localhost:8084/api/v/getGoods");
                    conn.timeout(100000);
                    conn.header("token", "11001");
                    conn.data("goodsId", "1");
                    conn.ignoreContentType(true);
                    Document doc = conn.post();
                    String body = doc.select("body").text();
                    System.out.println(body);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        //停止所有完成任务的线程
        long endTime = 0;
        pool.shutdown();
        while (true) {
            if (pool.isTerminated()) {
                endTime = System.currentTimeMillis(); //获取结束时间
                break;
            }
        }
        System.out.println("测试完成用时：" + (endTime - startTime) + "毫秒");

    }
}
