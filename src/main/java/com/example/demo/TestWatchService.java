package com.example.demo;

import org.springframework.stereotype.Component;

import java.nio.file.*;

import static java.nio.file.StandardWatchEventKinds.*;

@Component
public class TestWatchService {

    @lombok.SneakyThrows
    public void test() {

        WatchService watchService = FileSystems.getDefault().newWatchService();

        Path path = Paths.get("E:\\photos\\saved");

        path.register(watchService, ENTRY_CREATE, ENTRY_MODIFY, ENTRY_DELETE);

        boolean poll = true;

        while (poll) {

            WatchKey key = watchService.take();

            for (WatchEvent<?> event : key.pollEvents()) {

                if (event.kind() == ENTRY_CREATE && event.context().toString().contains(".png")) {
                    System.out.println(event.context().toString().length());
                }

            }

            poll = key.reset();

        }
    }

}


