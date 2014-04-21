package fr.insalyon.telecom.joofluxtest.parallelization;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

public class ParallelSqrt {

    public static List<Double> parallel_sqrt(int n) throws Throwable {
        ExecutorService pool = Executors.newFixedThreadPool(2);
        List<Future<Double>> futures = new ArrayList<Future<Double>>();
        List<Double> list = new ArrayList<Double>();

        for (int i = 0; i < n; i += 1) {
            final int base = i;
            futures.add(pool.submit(new Callable<Double>() {
                public Double call() {
                    return Math.sqrt(base);
                }
            }));
        }

        for (Future<Double> future : futures){
            list.add(future.get());
        }       

        pool.shutdown();
        
        return list;
    }

    public static void main(String... args) throws Throwable {
        //System.out.println(parallel_sqrt(new Integer(args[0])));
        long computation_time = System.nanoTime();
        parallel_sqrt(new Integer(args[0]));
        computation_time = System.nanoTime() - computation_time;
        System.out.println(computation_time);
    }
}
