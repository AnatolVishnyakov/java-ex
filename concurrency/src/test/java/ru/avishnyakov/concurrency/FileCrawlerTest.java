package ru.avishnyakov.concurrency;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileFilter;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class FileCrawlerTest {
    @SneakyThrows
    @Test
    void fileCrawler() {
        Indexer.startIndexing(Objects.requireNonNull(new File("D:/").listFiles()));
        Thread.sleep(100_000);
    }
}

@Slf4j
@AllArgsConstructor
class FileCrawler implements Runnable {
    private final BlockingQueue<File> queue;
    private final FileFilter filter;
    private final File root;

    @Override
    public void run() {
        try {
            crawl(root);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void crawl(File root) throws InterruptedException {
        log.info("Folder " + root.getAbsolutePath());

        File[] entries = root.listFiles(filter);
        if (entries != null) {
            for (File entry : entries) {
                if (entry.isDirectory()) {
                    crawl(entry);
                } else if (!alreadyIndexed(entry)) {
                    queue.put(entry);
                }
            }
        }
    }

    private boolean alreadyIndexed(File file) {
        return false;
    }
}

@AllArgsConstructor
class Indexer implements Runnable {
    private final BlockingQueue<File> queue;

    @Override
    public void run() {
        try {
            while (true) {
                indexFile(queue.take());
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void indexFile(File file) {

    }

    public static void startIndexing(File[] roots) {
        BlockingQueue<File> queue = new LinkedBlockingQueue<>(10_000);
        FileFilter filter = pathname -> true;

        for (File root : roots) {
            new Thread(new FileCrawler(queue, filter, root)).start();
        }

        for (int i = 0; i < 4; i++) {
            new Thread(new Indexer(queue)).start();
        }
    }
}