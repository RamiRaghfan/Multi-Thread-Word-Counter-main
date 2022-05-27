package wordCount;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FolderTools {

	public static List<String> getListOfFilesInFolder(String folderPath)
	{
		List < String > fileNames = new ArrayList < > ();
        try 
        {
            DirectoryStream < Path > directoryStream = Files.newDirectoryStream(Paths.get(folderPath));
            for (Path path: directoryStream) 
            {
                fileNames.add(path.toString());
            }
        } 
        
        catch (IOException ex) 
        {
            ex.printStackTrace();
        }
		return fileNames;
	}

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub

	}

}
