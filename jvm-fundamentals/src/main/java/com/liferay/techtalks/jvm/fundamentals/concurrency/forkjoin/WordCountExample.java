package com.liferay.techtalks.jvm.fundamentals.concurrency.forkjoin;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ForkJoinPool;

/**
 * Word count main class
 * 
 * Small forkjoin example based on {@linkplain http
 * ://www.oracle.com/technetwork/articles/java/fork-join-422606.html}
 * 
 * @author migue
 * 
 */
public class WordCountExample {

	String[] wordsIn(String line) {
		return line.trim().split("(\\s|\\p{Punct})+");
	}

	Long occurrencesCount(Document document, String searchedWord) {
		long count = 0;

		for (String line : document.getLines()) {
			for (String word : wordsIn(line)) {
				if (searchedWord.equals(word)) {
					count = count + 1;
				}
			}
		}
		return count;
	}

	private final ForkJoinPool forkJoinPool = new ForkJoinPool();

	Long countOccurrencesInParallel(Folder folder, String searchedWord) {
		return forkJoinPool.invoke(new FolderSearchTask(folder, searchedWord));
	}

	Long countOccurrencesOnSingleThread(Folder folder, String searchedWord) {
		long count = 0;
		for (Folder subFolder : folder.getSubFolders()) {
			count = count
					+ countOccurrencesOnSingleThread(subFolder, searchedWord);
		}
		for (Document document : folder.getDocuments()) {
			count = count + occurrencesCount(document, searchedWord);
		}
		return count;
	}

	/**
	 * @param args
	 * @throws Exception
	 */

	public static void main(String[] args) throws IOException {
		WordCountExample wordCounter = new WordCountExample();

		Folder folder = Folder.fromDirectory(new File(args[0]));
		System.out.println(wordCounter.countOccurrencesOnSingleThread(folder,
				args[1]));
	}
}
