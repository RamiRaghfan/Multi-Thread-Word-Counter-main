package wordCount;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.BlockingQueue;

public class WordCount 
{
	public static HashMap<String, Integer> countHowManyTimesEachWordOccurs(Path file) 
	{
		HashMap < String, Integer > map = new HashMap < String, Integer > ();
		countHowManyTimesEachWordOccurs(file, map);
		return map;
	}

	
	public static void putLinesInQueue(Path file, BlockingQueue <String> lineQueue) 
	{
		try(Scanner in = new Scanner(file)) //try with resources
		{
			boolean inError = false;
		    while ( in .hasNextLine() ) 
		    {
		        String line = in .nextLine();
		        
		        inError = true;
		        while(inError)
		        {
			       try 
			       {
			    	   lineQueue.put( line );
			    	   inError = false;
			       } 
			       catch (InterruptedException e) 
			       {
						
			       }
		        }
		    }
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	public static void countHowManyTimesEachWordOccurs(Path file, HashMap<String, Integer> map) 
	{
		try(Scanner in = new Scanner(file)) //try with resources
		{
		    while ( in .hasNextLine() ) 
		    {
		        String line = in .nextLine();
		        
		      
		        wordsFrequencyOnLine(line, map);
		    }
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	public static void wordsFrequencyOnLine(String line, HashMap<String, Integer> map) 
	{
		String[] words = line.split(" ");

		for (String w: words) 
		{
			
				if (map.containsKey(w)) 
				{
				
					map.put(w, map.get(w) + 1);
						
				} 
				
				else 
				{ 	
				   map.put(w, 1);
				}
				
			
			
		}
	}
	
	public static void wordsFrequencyOnLineSynchronized(String line, HashMap<String, Integer> map) 
	{
		String[] words = line.split(" ");

		for (String w: words) 
		{
			synchronized(map)
	    	{
			    if (map.containsKey(w)) 
			    {
			        map.put(w, map.get(w) + 1);
			    } 
			    else 
			    {
			    	
			    		map.put(w, 1);
			    }
	    	}
		}
	}
	
	public static void printHashMap(HashMap<String, Integer> hp)
	{
		Set<String> countys = hp.keySet();
		Iterator<String> icountys = countys.iterator();
		while(icountys.hasNext())
		{
			String key = icountys.next();
			
			System.out.println(key + " --> " + hp.get(key).toString());
		}

	}
	
	public void printList(List<String>list)
	{
		for(int i=0;i<list.size();i++)
		{
			System.out.println(list.get(i));
		}
	}
	
}
