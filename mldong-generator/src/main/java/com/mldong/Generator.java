package com.mldong;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Stack;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Supplier;

public class Generator {
	public int maximumPoolSize = 4;
	public ThreadPoolExecutor  executor = null;
	public static void main(String[] args) throws IOException, URISyntaxException {
		if(args.length <1) {
			System.out.println("目录不能为空");
			return;
		} else {
			long startTime=System.currentTimeMillis();   //获取开始时间   
			long c = Files.walk(Paths.get(args[0],""), 100, FileVisitOption.FOLLOW_LINKS).filter(item->{
				return item.toFile().isFile();
			}).count();
			System.err.println(c);
     	   long endTime=System.currentTimeMillis(); //获取结束时间
            System.out.println("程序运行时间： "+(endTime-startTime)+"ms"); 
            System.out.println("程序运行时间： "+new BigDecimal((endTime-startTime)).divide(new BigDecimal(1000))+"s"); 
            System.out.println("平均每秒处理： "+new BigDecimal(c).divide(new BigDecimal((endTime-startTime)).divide(new BigDecimal(1000)),4, BigDecimal.ROUND_HALF_UP)); 
			return;
		}
		// String dir = "D:\\mldong\\couse-workspace\\mldong\\mldong-admin\\src";
		// String dir = "D:\\mldong\\vue-workspace\\mole-yzq-front";
//		String dir = args[0];
//		Generator gen = new Generator();
//		if(args.length==2) {
//			gen.maximumPoolSize = Integer.parseInt(args[1]);
//		}
//		gen.executor = new ThreadPoolExecutor(1, gen.maximumPoolSize, 1000, TimeUnit.MILLISECONDS, new SynchronousQueue<Runnable>(),Executors.defaultThreadFactory(),new ThreadPoolExecutor.DiscardPolicy());
//		long startTime=System.currentTimeMillis();   //获取开始时间   
//		File file = new File(dir);
//		if(!file.exists()){
//			System.out.println("目录不存在");
//			return;
//		}
//		// gen.countFiles(file);
//		gen.doTaskAsync(file);
//		// executorService.shutdown();
//        while(true){  
//           if(gen.executor.isTerminated()){  
//        	   long endTime=System.currentTimeMillis(); //获取结束时间
//        	   System.out.println("空文件夹数：" + gen.emptyDirCountAtomic.longValue());
//       	       System.out.println("文件总数：" + gen.fileCountAtomic.longValue());
//               System.out.println("程序运行时间： "+(endTime-startTime)+"ms"); 
//               System.out.println("程序运行时间： "+new BigDecimal((endTime-startTime)).divide(new BigDecimal(1000))+"s"); 
//               System.out.println("平均每秒处理： "+new BigDecimal(gen.fileCountAtomic.longValue()).divide(new BigDecimal((endTime-startTime)).divide(new BigDecimal(1000)),4, BigDecimal.ROUND_HALF_UP)); 
//                break;  
//            }
//           try {
//        	   Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} 
//           System.out.println("线程池中线程数目：" + gen.executor.getPoolSize() + "，队列中等待执行的任务数目：" +
//        		   gen.executor.getQueue().size() + "，已执行完的任务数目：" + gen.executor.getCompletedTaskCount());
//           if(gen.executor.getPoolSize()==1&&gen.executor.getQueue().size()==0) {
//        	   gen.executor.shutdown();
//           }
//        }
		
	}
	AtomicLong fileCountAtomic = new AtomicLong(0);
	AtomicLong emptyDirCountAtomic = new AtomicLong(0);
	
	public void countFiles(File file) {
		Stack<File> fileStack = new Stack<>();
		fileStack.add(file);
		while (!fileStack.isEmpty()) {
			File f = fileStack.pop();
			if(f.isDirectory()) {
				File [] listFiles= f.listFiles();
				if(null == listFiles || listFiles.length == 0) {
					emptyDirCountAtomic.incrementAndGet();
				} else {
					for(File item: listFiles) {
						if(item.isDirectory()) {
							fileStack.add(item);
						} else if(item.isFile()) {
							// 文件数+1
							fileCountAtomic.incrementAndGet();
						}
					}
				}
			} else if(file.isFile()) {
				// 文件数+1
				fileCountAtomic.incrementAndGet();
			}
		}
	}
	public void doTaskAsync(File file){
		// 这里执行一次任务
		CompletableFuture<CountDto> future = CompletableFuture.supplyAsync(new Supplier<CountDto>() {
			@Override
			public CountDto get() {
				CountDto count = countFilesTask(file);
				return count;
			}
		},executor);
		future.thenAccept(count -> {
			// 任务完成-累计
			emptyDirCountAtomic.addAndGet(count.emptyDirCount);
			fileCountAtomic.addAndGet(count.fileCount);
			// System.out.println("当前线程："+Thread.currentThread().getId()+",统计文件数："+count.fileCount+",累计统计数："+fileCountAtomic.longValue());
		});
	}
	public CountDto countFilesTask(File file) {
		long emptyDirCount = 0L;
		long fileCount = 0L;
		Stack<File> fileStack = new Stack<>();
		fileStack.add(file);
		while(!fileStack.isEmpty()) {
			File f = fileStack.pop();
			if(f.isDirectory()) {
				File [] listFiles= f.listFiles();
				if(null == listFiles || listFiles.length == 0) {
					emptyDirCount ++;
				} else {
					int index = 0;
					int size = listFiles.length;
					if(size > 10) {// 大于0，会开一半子任务
						for(File item: listFiles) {
							if(item.isDirectory()) {
								if(index%2==0) {
									if(executor.getPoolSize()==100) {
										// 已满
										fileStack.add(item);
									} else {
										doTaskAsync(item);
									}
								} else {
									fileStack.add(item);
								}
							} else if(item.isFile()) {
								// 文件数+1
								fileCount++;
							}
							index ++;
						}
					} else {
						for(File item: listFiles) {
							if(item.isDirectory()) {
								fileStack.add(item);
							} else if(item.isFile()) {
								// 文件数+1
								fileCount++;
							}
							index ++;
						}
					}
					
				}
			} else if(file.isFile()) {
				// 文件数+1
				fileCount++;
			}
		}
		return new CountDto(emptyDirCount,fileCount);
	}
 	public class CountDto {
 		public long emptyDirCount = 0L;
 		public long fileCount = 0L;
		public CountDto(long emptyDirCount, long fileCount) {
			super();
			this.emptyDirCount = emptyDirCount;
			this.fileCount = fileCount;
		}
		
 	}
}
