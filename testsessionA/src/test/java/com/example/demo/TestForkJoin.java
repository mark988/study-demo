package com.example.demo;

import org.junit.*;
import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * Created by 马晓光 on 2019/4/29.
 */
public class TestForkJoin {
    public static void main(String[] args) {

        Instant s=Instant.now();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Long> longForkJoinTask = new Cal(0L,100000000000L);
        Long result = forkJoinPool.invoke(longForkJoinTask);
        Instant e=Instant.now();
        System.out.printf("result:"+result+" 消耗时间:"+(Duration.between(s,e).toMillis())); //3499
    }

    @org.junit.Test
    public void test(){
        Long sum = 0L;
        Instant s1= Instant.now();
        for (long i = 0L; i <=100000000000L ; i++) {
            sum += i;
        }
        Instant e1=Instant.now();
        System.out.printf("sum = "+sum +" 消耗时间:"+(Duration.between(s1,e1).toMillis())); //4564
    }
    @Test
    public void testjava8(){
        Instant s2= Instant.now();
       Long sum =   LongStream.rangeClosed(0L,100000000000L).parallel().reduce(0L,Long::sum);
        Instant e2= Instant.now();
        System.out.printf("sum = "+sum +" 消耗时间:"+(Duration.between(s2,e2).toMillis()));
    }

}
class Cal extends RecursiveTask<Long> {

    private Long start;
    private Long end;
    private static final Long threshold = 100000L;

    Cal(Long start,Long end){
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long sum = 0L ;
        if( ( end - start) <= threshold){

            for (Long i = start; i <=end ; i++) {
                sum = i + sum;
            }
            return sum;
        }else{
            long middle = (end + start) / 2;
            Cal  left = new Cal(start,middle);
            Cal  right = new Cal(middle+1 , end );
            left.fork();
            right.fork();
            return left.join()+right.join();
        }
    }
}
