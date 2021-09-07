package com.saferide.serve.event;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class EventHandle implements IEvent {

    IEventHandler eventHandle;

    //无界线程安全并发队列
    public ConcurrentLinkedQueue<Map<String, Object>> queue = new ConcurrentLinkedQueue<>();

    @Override
    public void setEventHandler(IEventHandler eventHandle) {
        this.eventHandle = eventHandle;
    }

    @Override
    public boolean eventTrigger(Map<String, Object> actions) {
        // 添加操作到队列
        System.out.println("push events");
        queue.add(actions);

        // 两个线程的线程池
        //创建一个单个任务的执异步任务e executor = Executors.newSingleThreadExecutor();
        // 异步处理，创建带返回值的
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            // 加锁保证事件有序执行
            synchronized (this) {
                eventHandle.consumerEvent(queue);
            }
            return "consumer event end";
        }, executor).exceptionally(e -> e.toString());
        future.whenComplete((r,e) -> {
            log.info(r);
            e.printStackTrace();
            // 事件执行结束后关闭线程池
            executor.shutdown();
        });
        return true;
    }
}
