package xyz.haodblog.future;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.*;

@Slf4j
public class TestJDKFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Future<Object> future = executorService.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                log.debug("执行计算");
                Thread.sleep(1000);
                return 10;
            }
        });
        log.debug("等待结果");
        log.debug("结果是：{}", future.get());
        executorService.shutdown();
    }
}
