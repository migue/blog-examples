package com.liferay.techtalks.jvm.fundamentals.nio.watchs;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

/**
 * Some uses of the new {@link WatchService} ability included in Java 7.
 * 
 * <p>
 * Using this class we will be able to monitor files/folders
 * </p>
 * 
 * @author migue
 * 
 */
public class NIO2WatchService {

	protected static void monitorPath(String pathName) {
		Path path = Paths.get(pathName);

		WatchService watchService;
		try {
			watchService = FileSystems.getDefault().newWatchService();

			WatchKey key = path.register(watchService, ENTRY_CREATE,
					ENTRY_DELETE, ENTRY_MODIFY);

			while (true) { // loop forever
				key = watchService.take();

				for (WatchEvent<?> watchEvent : key.pollEvents()) {
					Kind<?> eventKind = watchEvent.kind();

					if (eventKind == ENTRY_CREATE) {
						System.out.println("The file "
								+ ((Path) (watchEvent.context())).getFileName()
								+ " has been created");
					} else if (eventKind == ENTRY_DELETE) {
						System.out.println("The file "
								+ ((Path) (watchEvent.context())).getFileName()
								+ " has been deleted");
					} else if (eventKind == ENTRY_MODIFY) {
						System.out.println("The file "
								+ ((Path) (watchEvent.context())).getFileName()
								+ " has been modified");
					}

				}

				key.reset();

			}
		} catch (IOException | InterruptedException e) {
			System.out.println("No expected error");
		}
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		BufferedReader consoleInputStream = new BufferedReader(
				new InputStreamReader(System.in));

		System.out.println("Introduce a path file: ");
		String line = consoleInputStream.readLine();

		NIO2WatchService.monitorPath(line);
	}
}
