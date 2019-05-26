package com.tang.iqiyi.scrapy;

public interface Scrapy {
    public abstract void doScrapy();

    public abstract void setPageNum(int pageNum);

    public abstract void setPageSize(int pageSize);

    public abstract void setParser(IQIYIParser iqiyiParser);

    public abstract void setTHREAD_NUM(int THREAD_NUM);

    public abstract void setAwaitTime(long awaitTime);
}