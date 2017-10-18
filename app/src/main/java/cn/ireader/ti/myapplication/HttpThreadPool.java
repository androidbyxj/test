package cn.ireader.ti.myapplication;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 供http使用的线程池
 * 默认5线程，最大不做限制，10分钟没有新的请求，就释放预留的线程
 * 使用SynchronousQueue确保线程按照调用execute的顺序启动
 */
public class HttpThreadPool {
	public static final int DEFAULT_MAX_NUM_THREADS = 2 * Runtime.getRuntime().availableProcessors() + 1;
	private static ThreadPoolExecutor mThreadPoolExecutor = 
			new ThreadPoolExecutor(3, DEFAULT_MAX_NUM_THREADS,
                    60*10, TimeUnit.SECONDS,
                    new SynchronousQueue<Runnable>(),new CusRejectedExecutionHandler());
	
	private HttpThreadPool() {
	}
	
	public static void execute(Runnable runnable) {
		mThreadPoolExecutor.execute(runnable);
    }
}
