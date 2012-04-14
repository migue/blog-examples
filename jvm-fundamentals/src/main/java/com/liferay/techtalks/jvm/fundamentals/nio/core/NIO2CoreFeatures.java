package com.liferay.techtalks.jvm.fundamentals.nio.core;

import static java.nio.file.StandardCopyOption.ATOMIC_MOVE;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFileAttributeView;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermission;
import java.util.Set;

/**
 * This class shows some of the new features included in Project Coin
 * 
 * @author migue
 * 
 */
public class NIO2CoreFeatures {

	protected static void copyingFiles(String sourceFile, String destinationFile) {

		Path source = Paths.get(sourceFile);
		Path destination = Paths.get(destinationFile);

		try {
			Files.copy(source, destination, ATOMIC_MOVE);
		} catch (IOException e) {
			System.err.println("IO problem while copying " + sourceFile
					+ " to " + destinationFile);
		}
	}

	protected static void movingFiles(String sourceFile, String destinationFile) {

		Path source = Paths.get(sourceFile);
		Path destination = Paths.get(destinationFile);

		try {
			Files.move(source, destination, ATOMIC_MOVE, REPLACE_EXISTING);
		} catch (IOException e) {
			System.err.println("IO problem while moving " + sourceFile + " to "
					+ destinationFile);
		}
	}

	protected static void managingFilePermissions(String fileName)
			throws IOException {

		Path file = Paths.get(fileName);

		PosixFileAttributeView fileAttributeView = Files.getFileAttributeView(
				file, PosixFileAttributeView.class);

		if (fileAttributeView != null) {
			PosixFileAttributes readAttributes = fileAttributeView
					.readAttributes();

			String owner = readAttributes.owner().getName();

			Set<PosixFilePermission> permissions = readAttributes.permissions();

			System.out.format("%s %s%n", owner, permissions);

			permissions.add(PosixFilePermission.OWNER_EXECUTE);

			fileAttributeView.setPermissions(permissions);

			System.out.format("%s %s%n", owner, permissions);
		}
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		BufferedReader consoleInputStream = new BufferedReader(
				new InputStreamReader(System.in));

		System.out
				.println("Introduce the path for a file to add the execute permission");

		String line = consoleInputStream.readLine();

		NIO2CoreFeatures.managingFilePermissions(line);

	}
}
