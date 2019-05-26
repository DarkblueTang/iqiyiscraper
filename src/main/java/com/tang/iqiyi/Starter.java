package com.tang.iqiyi;

import com.tang.iqiyi.scrapy.*;
import com.tang.iqiyi.scrapy.parser.ArtParser;
import com.tang.iqiyi.scrapy.parser.ComicParser;
import com.tang.iqiyi.scrapy.parser.FilmParser;
import com.tang.iqiyi.scrapy.parser.SeriesParser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Starter {
    private static Properties properties = null;
    private static SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        loadProperties();
        scrapy();
    }

    private static void loadProperties() {
        properties = new Properties();
        // 使用ClassLoader加载properties配置文件生成对应的输入流

        InputStream in = Starter.class.getClassLoader().getResourceAsStream("config.properties");
        // 使用properties对象加载输入流
        try {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getProperty(String key) {
        if (Objects.isNull(properties))
            return null;
        return properties.getProperty(key);
    }

    public static void scrapy() {
        ExecutorService service = Executors.newSingleThreadExecutor();

        System.out.println("爱奇艺爬虫开始爬了");
        log("爱奇艺爬虫开始爬了");

        service.submit(() -> {
            comicScrapy();
        });
        service.submit(() -> {
            artScrapy();

        });
        service.submit(() -> {
            seriesScrapy();

        });
        service.submit(() -> {
            filmScrapy();
        });
        try {
            Thread.sleep(parseMaxtime());
            log("爬虫一波结束了");
            System.out.println("爬虫一波结束了");
            System.exit(-1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static long parseMaxtime() {
        long time = 1;
        String maxtime = getProperty("scrapy.time.maxtime");
        Matcher matcher = Pattern.compile("\\d+").matcher(maxtime);
        while (matcher.find()) {
            time *= Integer.parseInt(matcher.group());
        }
        return time;
    }

    private static void filmScrapy() {
        log("开始爬电影");
        Scrapy scrapy = new FilmScrapy();
        scrapy.setPageSize(1000);
        scrapy.setPageNum(10);
        scrapy.setTHREAD_NUM(5);
        FilmParser filmParser = new FilmParser();
        filmParser.setVideoType("电影");
        scrapy.setParser(filmParser);
        scrapy.setAwaitTime(10);
        scrapy.doScrapy();
        log("爬电影完成");

    }

    private static void comicScrapy() {
        log("开始爬动漫");
        Scrapy scrapy = new ComicScrapy();
        scrapy.setPageSize(1000);
        scrapy.setPageNum(10);
        scrapy.setTHREAD_NUM(5);
        IQIYIParser parser = new ComicParser();
        parser.setVideoType("动漫");
        scrapy.setParser(parser);
        scrapy.setAwaitTime(10);
        scrapy.doScrapy();
        log("爬动漫完成");

    }

    private static void artScrapy() {
        log("开始爬综艺");
        Scrapy scrapy = new ArtScrapy();
        scrapy.setPageSize(1000);
        scrapy.setPageNum(10);
        scrapy.setTHREAD_NUM(5);
        IQIYIParser parser = new ArtParser();
        parser.setVideoType("综艺");
        scrapy.setParser(parser);
        scrapy.setAwaitTime(10);
        scrapy.doScrapy();
        log("爬综艺完成");
    }

    private static void seriesScrapy() {
        log("开始爬电视剧");
        Scrapy scrapy = new SeriesScrapy();
        scrapy.setPageSize(1000);
        scrapy.setPageNum(10);
        scrapy.setTHREAD_NUM(5);
        IQIYIParser parser = new SeriesParser();
        parser.setVideoType("电视剧");
        scrapy.setParser(parser);
        scrapy.setAwaitTime(10);
        scrapy.doScrapy();
        log("爬电视剧完成");
    }

    private static void log(String string) {
        String date = dateFormatter.format(new Date());
        date = date + "  " + string;
        String path = getProperty("logger.file.path");
        File file = new File(path);
        try {
            FileWriter writer = new FileWriter(file, true);
            writer.write(date);
            writer.write("\n");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
