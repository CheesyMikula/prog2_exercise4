package at.ac.fhcampuswien.newsanalyzer.downloader;

import at.ac.fhcampuswien.newsapi.enums.Category;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ParallelDownloader extends Downloader {

    @Override
    public int process(List<String> urls) {

        ExecutorService pool = Executors.newFixedThreadPool(10);

        int count = 0;
        Future<String> future = null;
        for(String url : urls){
            future = pool.submit(()->saveUrl2File(url));
            count++;
            //System.out.println(count);
        }
         if (future != null) {
            while (!future.isDone()){
                try {

                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }


        pool.shutdown();
        return count;


    }


}
