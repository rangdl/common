package com.example.common.utils;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;
import java.util.Random;
import java.util.concurrent.*;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * @ClassName testConcurrent
 * @Description TODO
 * @Author rdl
 * @Date 2019/12/3 14:40
 * @Version 1.0
 **/
@SpringBootTest
public class TestConcurrent {
    static Logger logger = LoggerFactory.getLogger(TestConcurrent.class);
    int i=0;
    private static final int CORE_POOL_SIZE = 5;
    private static final int MAX_POOL_SIZE = 10;
    private static final long KEEP_ALIVE = 2;
//    private static CustomizeThreadFactory factory = new CustomizeThreadFactory("CustomizeThreadFactory");
    // 创建线程池对象
    public static final ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE,
        KEEP_ALIVE, TimeUnit.SECONDS,new LinkedBlockingQueue<Runnable>());
    @Test
    public void testThreadFactory() throws InterruptedException {


        for (int i =0;i<10;i++){
            poolExecutor.execute(()->{
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Thread thread = Thread.currentThread();
                logger.info(thread.getName());
            });
        }
        logger.info("{}",poolExecutor.getPoolSize());
        Thread.sleep(3000);
    }
    @Test
    public void testCyclicBarrier() throws InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, () -> {
            logger.info("All previous tasks are completed");
        });
        Thread t1 = new Thread(new Task(cyclicBarrier), "T1");
        Thread t2 = new Thread(new Task(cyclicBarrier), "T2");
        Thread t3 = new Thread(new Task(cyclicBarrier), "T3");

        if (!cyclicBarrier.isBroken()) {
            t1.start();
            t2.start();
            t3.start();
        }
        Thread.sleep(10000);
    }
    @Test
    public void testScheduledExecutorService() {
        ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
        logger.info("{}",i);
        ScheduledFuture <Integer> future = ses.schedule(() -> {
            logger.info("{}",i);
            return i++;
        }, 10, TimeUnit.SECONDS);
        //如果此任务在正常完成之前被取消，则返回 true
//        future.isCancelled();
        //返回 true如果任务已完成
//        future.isDone();

        /**
         * @Author rdl
         * @Description scheduleAtFixedRate（Runnable命令，long initialDelay，long period，TimeUnit unit）
         * 方法创建并执行一个周期性操作，该操作在提供的初始延迟之后首先被调用，随后是给定的时间段，直到服务实例关闭。
         * @Date 2019/12/3 18:19
         * @Param []
         * @return void
         **/
        Future<?> future2 = ses.scheduleAtFixedRate(() -> {
            // ...
            LocalTime now = LocalTime.now();
            logger.info("周期执行:{}",now);
        }, 1, 10, TimeUnit.SECONDS);

        /**
         * @Author rdl
         * @Description scheduleWithFixedDelay（可运行命令，长在initialDelay，长的延迟，TIMEUNIT单元）
         * 方法与所述执行的一个的终止和的调用之间的给定的延迟创建并执行所提供的初始延迟后首先调用的周期性动作，并重复地下一个。
         * @Date 2019/12/3 18:20
         * @Param []
         * @return void
         **/
        Future<?> future3 = ses.scheduleWithFixedDelay(() -> {
            // ...
            logger.info("间隔执行:{}", LocalTime.now());
        }, 1, 5, TimeUnit.SECONDS);

        while (!future.isDone() || !future2.isDone() || !future3.isDone()){
            try {
                Thread.sleep(1000);
                if (future.isDone()){
                    Integer integer = future.get();
                    System.out.println(integer);
                }
                if (future2.isDone()){
                    Integer integer = future.get();
                    System.out.println(integer);
                }
                if (future3.isDone()){
                    Integer integer = future.get();
                    System.out.println(integer);
                }
            } catch (InterruptedException|ExecutionException e) {
                e.printStackTrace();
            }
        }
        //等待所有提交的任务完成执行
//        ses.shutdown();
        //立即终止所有未决/执行的任务
//        ses.shutdownNow();
        //（长超时，TimeUnit单元）强制阻塞，直到所有任务在触发关闭事件或执行超时发生后完成执行，或者执行线程本身被中断
//        try {
//            ses.awaitTermination( 20l, TimeUnit.NANOSECONDS );
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }


    }
    @Test
    public void testExecutorService(){
        ExecutorService executorService = Executors.newFixedThreadPool(10);
         executorService.submit(()->System.out.println(123123));
    }
    @Test
    public void testExecutor(){
        Executor executor = new Invoker();
        Random random = new Random();
        executor.execute(()->{
            random.ints(0,100).limit(10).forEach((x)->{
                System.out.println(x);
            });
        });
    }

    public static class CustomizeThreadFactory implements ThreadFactory{
        private int threadId;
        private String name;

        public CustomizeThreadFactory(String name) {
            this.threadId = 1;
            this.name = name;
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r, name + "-Thread_" + threadId);
            logger.info("Created new thread with id : {} and name : {}",threadId,thread.getName());
            return thread;
        }
    }
    public class Task implements Runnable{
        private CyclicBarrier barrier;

        public Task(CyclicBarrier barrier) {
            this.barrier = barrier;
        }
        @Override
        public void run() {
            logger.info("{} is waiting",Thread.currentThread().getName());
            logger.info("{} is released",Thread.currentThread().getName());
            try {
                barrier.await();
            } catch (InterruptedException|BrokenBarrierException e) {
                e.printStackTrace();
            }

        }
    }

    public class Invoker implements Executor{

        @Override
        public void execute(Runnable command) {
            command.run();
        }
    }
}
