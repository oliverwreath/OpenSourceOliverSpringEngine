package com.oli.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * Author: Oliver
 * Singleton + Lay Initialization + Double-checked locking
 */
public final class JsonUtil {
    // Lay Initialization
    private static volatile ObjectMapper mapper = null;

    // Prevent multiple instances
    private JsonUtil() {}

    /**
     * Double-checked locking
     * @return
     */
    public static synchronized ObjectMapper getMapper() {
        if (mapper == null) {
            synchronized (JsonUtil.class) {
                if (mapper == null) {
                    mapper = new ObjectMapper()
                            .registerModule(new Hibernate5Module().configure(Hibernate5Module.Feature.FORCE_LAZY_LOADING, true))
                            .registerModule(new JavaTimeModule())
                            .registerModule(new Jdk8Module());
                }
            }
        }
        return mapper;
    }

//    // Grab the singleton instance multiple times and check HashCode.
//    public static void main(String[] args) {
//        final int size = 10;
//        for (int i = 0; i < size; i++) {
//            System.out.println("#" + i + " Grab singleton instance: " + getMapper().hashCode());
//        }
//    }

//    // This Runnable will keep Grabbing singleton instance and check the hashCode.
//    static class Task implements Runnable {
//        private String name;
//
//        Task(String name) {
//            this.name = name;
//        }
//
//        public String getName() {
//            return name;
//        }
//
//        @Override
//        public void run() {
//            try {
//                while (true) {
//                    long duration = (long) (Math.random() * 10);
//                    System.out.println(getName() + " Grab singleton instance: " + getMapper().hashCode());
//                    TimeUnit.SECONDS.sleep(duration);
//                }
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    // Use ThreadPoolExecutor to execute 10s of thousands of Tasks concurrently.
//    public static void main(String[] args) throws InterruptedException {
//        final int size = 10;
//        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(size);
//        for (int i = 0; i < 10; i++) {
//            Task task = new Task("Task #" + i);
//            executor.execute(task);
//        }
//
//        TimeUnit.SECONDS.sleep(100L);
//        executor.shutdown();
//    }
}
