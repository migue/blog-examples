package com.liferay.techtalks.jvm.fundamentals.nio.async;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * This class shows some of the uses of the asynchronous abilities of NIO.2
 * 
 * @author migue
 * 
 */
public class NIO2AsyncFeatures {

	protected static void customLogic() throws InterruptedException {
		System.out.println("Doing our custom logic");
		Thread.sleep(500);
	}

	/**
	 * Reading a file in an asynchronus way. This method use the {@link Future}
	 * style.
	 * <p>
	 * A different approach could use completion handlers (you can take a look
	 * to {@link CompletionHandler}
	 * </p>
	 * 
	 * @param fileName
	 */
	protected static void asyncReadFile(String fileName) {

		Path file = Paths.get(fileName);

		try {
			// 1. Open an asynchronous channel

			AsynchronousFileChannel asynchronousFileChannel = AsynchronousFileChannel
					.open(file);

			// 2. Read a number of bytes

			ByteBuffer byteBuffer = ByteBuffer.allocate(100000000);

			// 3. Use a future to hold the result
			Future<Integer> result = asynchronousFileChannel
					.read(byteBuffer, 0);

			// 4. Do our custom logic while reading the file. NON BLOCKING I/O
			while (!result.isDone()) {
				customLogic();
			}

			Integer numOfBytesRead = result.get();

			System.out.println("Bytes readed: " + numOfBytesRead);

		} catch (IOException | InterruptedException | ExecutionException e) {
			System.out.println("IO error while processing file " + fileName);
		}

	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		BufferedReader consoleInputStream = new BufferedReader(
				new InputStreamReader(System.in));

		System.out.println("Introduce the path for a file (a big file)");

		String line = consoleInputStream.readLine();

		NIO2AsyncFeatures.asyncReadFile(line);
	}
}
