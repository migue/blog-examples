package com.liferay.techtalks.jvm.fundamentals.concurrency.forkjoin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Simple class to represent the lines availables on a document
 * 
 * @author migue
 * 
 */
public final class Document {

	public Document(List<String> lines) {
		this.lines = lines;
	}

	public List<String> getLines() {
		return this.lines;
	}
	
	public static Document fromFile(File file) throws FileNotFoundException,
			IOException {
		// Java 7 diamond syntax
		List<String> lines = new LinkedList<>();

		// Note we are using the TWR ability of Java 7
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String line = reader.readLine();
			while (line != null) {
				lines.add(line);
				line = reader.readLine();
			}
		}
		return new Document(lines);
	}

	private List<String> lines;

}

/**
 * Simple class used to represent a folder in the filesystem hierarchy
 * 
 * @author migue
 * 
 */
class Folder {
	private final List<Folder> subFolders;
	private final List<Document> documents;

	Folder(List<Folder> subFolders, List<Document> documents) {
		this.subFolders = subFolders;
		this.documents = documents;
	}

	List<Folder> getSubFolders() {
		return this.subFolders;
	}

	List<Document> getDocuments() {
		return this.documents;
	}

	static Folder fromDirectory(File dir) throws IOException {
		List<Document> documents = new LinkedList<>();
		List<Folder> subFolders = new LinkedList<>();

		for (File entry : dir.listFiles()) {
			if (entry.isDirectory()) {
				subFolders.add(Folder.fromDirectory(entry));
			} else {
				documents.add(Document.fromFile(entry));
			}
		}
		return new Folder(subFolders, documents);
	}
}
