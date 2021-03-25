package com.ruhul.ractive.netty.common;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Component
public class FileReaderReactive {

	public Flux<String> getFile() {
		// input file

		URL resource = getClass().getResource("/file/large-text.txt");
		Path ipPath = Paths.get(resource.getPath());


		Flux<String> stringFlux = Flux.using(
				() -> Files.lines(ipPath),
				Flux::fromStream,
				Stream::close
		);
		return stringFlux;
	}

// private methods to handle checked exceptions

	private void close(Closeable closeable) {
		try {
			closeable.close();
			System.out.println("Closed the resource");
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}

	private void write(BufferedWriter bw, String string) {
		try {
			bw.write(string);
			bw.newLine();
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}

}
