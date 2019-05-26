package com.tang.iqiyi.scrapy;

import com.alibaba.fastjson.JSONObject;
import com.tang.iqiyi.tools.HTTPTools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public abstract class AbstractScrapy implements Scrapy {

    private int pageNum;
    private int pageSize;
    protected int THREAD_NUM = 1;
    private ExecutorService executor = null;
    protected Logger logger = Logger.getLogger(this.getClass().toString());
    private IQIYIParser filmParser = null;
    private long awaitTime = 10;

    public void doScrapy() {
        init();

        for (int i = 1; i <= pageNum; i++) {
            int finalI = i;
            executor.submit(() -> {
                try {
                    if (filmParser == null) {
                        logger.warning("初始化filmParser失败");
                        System.exit(-1);
                    }
                    System.out.println("pageNum :" + finalI);
                    String url = getUrlByPageNumPageSize(finalI, pageSize);
                    String string = HTTPTools.getString(url);
                    JSONObject json = JSONObject.parseObject(string);
                    filmParser.parse(json);
                } catch (Exception e) {
                }
            });
        }
        executor.shutdown();
        try {
            if (!executor.awaitTermination(awaitTime, TimeUnit.MINUTES)) {
                // 超时的时候向线程池中所有的线程发出中断(interrupted)。
                executor.shutdownNow();
            }
        } catch (Exception e) {
        }
        this.afterScrapy();
    }

    protected abstract String getUrlByPageNumPageSize(int pageNum, int pageSize);

    private void init() {
        executor = Executors.newFixedThreadPool(THREAD_NUM);
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public void setTHREAD_NUM(int THREAD_NUM) {
        this.THREAD_NUM = THREAD_NUM;
    }

    @Override
    public void setParser(IQIYIParser iqiyiParser) {
        this.filmParser = iqiyiParser;
    }

    public void afterScrapy() {
        System.out.println("爬取完成");
    }

    @Override
    public void setAwaitTime(long awaitTime) {
        this.awaitTime = awaitTime;
    }
}
