package co.anbora.labs.engvid.jobs;

import javax.inject.Singleton;
import io.micronaut.scheduling.annotation.Scheduled;

@Singleton
public class CrawlerJob {

    @Scheduled(fixedRate = "5m")
    public void process() {}
}