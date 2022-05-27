package wordCount;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;

public class WordCounterThread extends Thread 
{
	public boolean working;
	
	int id;
	List<Path> files;
	HashMap<String, Integer> wordsCount;
	public WordCounterThread(int id, List<Path> files, HashMap<String, Integer> wordsCount)
	{
		this.id = id;
		this.files = files;
		this.wordsCount = wordsCount;
	}
	
	public void run()
	{		
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		working = true;
		int n = files.size();
		for(int i=0;i<n;i++)
		{
			Path pathToFile = files.get(i);
//			System.out.println(id + " " + pathToFile);
			
			WordCount.countHowManyTimesEachWordOccurs( pathToFile, wordsCount);
			
		}
		
		working = false;
	}
}
