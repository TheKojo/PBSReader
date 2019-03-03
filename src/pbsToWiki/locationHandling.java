package pbsToWiki;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class locationHandling {
	public File inputFile = new File("src/input/encounters.txt");
	public boolean foundit = false; 
	public locationHandling(){
		
	}
	
	public ArrayList<String> getLocationsFor(String name) throws FileNotFoundException{
		foundit = false;
		ArrayList<String> areaList = new ArrayList<String>();
		name = name.toUpperCase();
		if (name.equals("PANSURGE")){
			String test = "";
		}
		Scanner sc = new Scanner(inputFile,"UTF-8");
		String i = sc.nextLine();
		while (sc.hasNextLine()) {
			if (i.startsWith("#")){
				i = sc.nextLine();
				String location = i.substring(i.lastIndexOf("#")+2);
				while (!i.startsWith("#") && sc.hasNextLine()){	
					if (i.startsWith(name)){
						foundit = true;
					}
					i = sc.nextLine();
				}
				if (foundit == true){
					areaList.add(location);
					foundit = false;
				}
			}
		}
		return areaList;
	}
}
