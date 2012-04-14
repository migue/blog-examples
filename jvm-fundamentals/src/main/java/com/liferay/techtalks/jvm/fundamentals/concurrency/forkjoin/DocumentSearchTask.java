package com.liferay.techtalks.jvm.fundamentals.concurrency.forkjoin;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class DocumentSearchTask extends RecursiveTask<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6956751586943294943L;

	private final Document document;
	private final String searchedWord;

	DocumentSearchTask(Document document, String searchedWord) {
		super();
		this.document = document;
		this.searchedWord = searchedWord;
	}

	@Override
	protected Long compute() {
		return occurrencesCount(document, searchedWord);
	}

	protected Long occurrencesCount(Document document, String searchedWord) {
		List<String> documentLines = document.getLines();

		Long totalOcurrences = 0L;

		for (String line : documentLines) {
			String[] wordsInLine = line.split(" ");

			for (String word : wordsInLine) {
				if (searchedWord.equals(word)) {
					++totalOcurrences;
				}
			}
		}

		return totalOcurrences;
	}

}

class FolderSearchTask extends RecursiveTask<Long> {
	private final Folder folder;
	private final String searchedWord;

	FolderSearchTask(Folder folder, String searchedWord) {
		super();
		this.folder = folder;
		this.searchedWord = searchedWord;
	}

	@Override
	protected Long compute() {
		long count = 0L;
		List<RecursiveTask<Long>> forks = new LinkedList<>();

		for (Folder subFolder : folder.getSubFolders()) {
			FolderSearchTask task = new FolderSearchTask(subFolder,
					searchedWord);
			forks.add(task);
			task.fork();
		}

		for (Document document : folder.getDocuments()) {
			DocumentSearchTask task = new DocumentSearchTask(document,
					searchedWord);
			forks.add(task);
			task.fork();
		}

		for (RecursiveTask<Long> task : forks) {
			count = count + task.join();
		}

		return count;
	}
}
