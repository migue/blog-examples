package com.liferay.techtalks.jvm.fundamentals.nio.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

/**
 * The new {@link FileVisitor} interfaz available in Java 7
 * 
 * @author migue
 * 
 */
public class NIO2FileVisitor {

	private static class FileFinderByExtensionVisitor extends
			SimpleFileVisitor<Path> {
		private String extensionToSearch;
		private List<Path> foundFiles = new ArrayList<Path>();

		public FileFinderByExtensionVisitor(String extension) {
			this.extensionToSearch = extension;
		}

		@Override
		public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
				throws IOException {
			super.visitFile(file, attrs);

			if (file.toFile().getName().endsWith(this.extensionToSearch)) {
				foundFiles.add(file);
			}

			return FileVisitResult.CONTINUE;
		}

		public List<Path> getFoundFiles() {
			return this.foundFiles;
		}
	}

	private static class SimpleTreeVisitor extends SimpleFileVisitor<Path> {

		@Override
		public FileVisitResult preVisitDirectory(Path dir,
				BasicFileAttributes attrs) throws IOException {
			super.preVisitDirectory(dir, attrs);
			System.out
					.println("Include here your logic todo before visiting the directories");
			return FileVisitResult.CONTINUE;
		}

		@Override
		public FileVisitResult postVisitDirectory(Path dir,
				IOException exception) throws IOException {

			super.postVisitDirectory(dir, exception);

			System.out
					.println("Include here your logic todo after visiting the directories");

			return FileVisitResult.CONTINUE;
		}
	}

	protected static List<Path> findFilesByExtension(String folderName,
			String extension) {
		Path folder = Paths.get(folderName);

		if (!folder.toFile().isDirectory()) {
			throw new IllegalArgumentException(folderName
					+ "is not a valid path for a folder");
		}
		FileFinderByExtensionVisitor fileFinderByExtensionVisitor = new FileFinderByExtensionVisitor(
				extension);

		try {
			Files.walkFileTree(folder, fileFinderByExtensionVisitor);
		} catch (IOException e) {
			System.err.println("Error while searching " + extension
					+ " files on hierarchy " + folderName);
		}

		return fileFinderByExtensionVisitor.getFoundFiles();

	}

	protected static void visitHierarchy(String pathName) {
		Path path = Paths.get(pathName);

		try {
			Files.walkFileTree(path, new SimpleTreeVisitor());
		} catch (IOException e) {
			System.err.println("Error while traversing the hierarchy");
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

		NIO2FileVisitor.visitHierarchy(line);

		System.out.println("Introduce a path file: ");
		line = consoleInputStream.readLine();
		System.out.println("Introduce a file extension: ");
		String extension = consoleInputStream.readLine();

		List<Path> foundFiles = NIO2FileVisitor.findFilesByExtension(line,
				extension);

		System.out.println("Found " + foundFiles.size() + " files");
		for (Path path : foundFiles) {
			System.out.println(path.toFile().getAbsolutePath());
		}

	}
}
