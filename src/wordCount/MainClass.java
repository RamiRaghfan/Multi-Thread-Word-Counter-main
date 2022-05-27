package wordCount;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainClass 
{

	//on line 41 and 74 the hash map printing method is commented, uncomment to print each word frequency.
	public static void main(String[] args)
	{
		List<String> fullFileNames = FolderTools.getListOfFilesInFolder("./src/myPackage/files");
		long start, end;
		long startSingle, endSingle;

		startSingle = System.currentTimeMillis();
		countTheWordsSingleThread(fullFileNames);
		endSingle = System.currentTimeMillis();
		
		start = System.currentTimeMillis();
		int howManyThreads = countTheWordsMultiThread(fullFileNames,3);
		end = System.currentTimeMillis();
		System.out.println("MultiThreadCounter took " + (end-start) + " miliseconds with " + howManyThreads + " threads");
		System.out.println("SingleThreadCounter took " + (endSingle-startSingle) + " miliseconds");
	}

	private static void countTheWordsSingleThread(List<String> fullFileNames) 
	{
		HashMap<String, Integer> wordsCount = new HashMap<>();
		
		int n = fullFileNames.size();
		for(int i=0;i<n;i++)
		{
			Path pathToFile = Paths.get( fullFileNames.get(i) );
	
			WordCount.countHowManyTimesEachWordOccurs( pathToFile, wordsCount);
		}
//		WordCount.printHashMap(wordsCount);
	}

	private static int countTheWordsMultiThread(List<String> fullFileNames, int noOfFilesPerThread) 
	{
		List<Thread> allThreadCollection = new ArrayList <Thread> ();
		HashMap<String, Integer> wordsCount = new HashMap<>();
		List<Path> listOfPathToFile = new ArrayList<>();
		int countNoOfFilesPerThread = 0;
		int id = 1;
		int n = fullFileNames.size();
		for(int i=0;i<n;i++)
		{
			Path pathToFile = Paths.get( fullFileNames.get(i) );
			listOfPathToFile.add(pathToFile);
			countNoOfFilesPerThread++;

			if( countNoOfFilesPerThread == noOfFilesPerThread)
			{
				
				WordCounterThread wct = new WordCounterThread( id ++, listOfPathToFile, wordsCount);				
				wct.start();
				
				allThreadCollection.add(wct);
			
				countNoOfFilesPerThread = 0;
				listOfPathToFile = new ArrayList<>();
			}
			
		}
		int noOfCreatedThreads = id-1;
		
		waitForAllTheWordsToBeCounter(allThreadCollection, noOfCreatedThreads);
		
//		WordCount.printHashMap(wordsCount);
		
		return noOfCreatedThreads;
	}

	private static void waitForAllTheWordsToBeCounter(List<Thread> allThreadCollection, int noOfCreatedThreads) {
		boolean allTheFilesWereRead = false;
		while(!allTheFilesWereRead)
		{
			allTheFilesWereRead = true;
			for(int i=0;i<noOfCreatedThreads;i++)
			{
				if(allThreadCollection.get(i).isAlive())
				{
					allTheFilesWereRead = false;
				}
			}
		}
	}
}
