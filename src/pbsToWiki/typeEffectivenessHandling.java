package pbsToWiki;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class typeEffectivenessHandling {
	public File inputFile = new File("src/input/types.txt");
	
	public typeEffectivenessHandling(){
		
	}
	
	public double get(String defendingType, String offendingType) throws FileNotFoundException{
		Scanner sc = new Scanner(inputFile,"UTF-8");
		//offendingType = offendingType.toUpperCase();
		if (defendingType.equals("")){
			return 1;
		}
		while (sc.hasNextLine()) {
			String i = sc.nextLine();
			if (defendingType.equals(i.substring(i.lastIndexOf("=")+1))){
				i = sc.nextLine();
				i = sc.nextLine();
				if (i.startsWith("IsSpecialType")){
					i = sc.nextLine();
				}
            	String types = i.substring(i.lastIndexOf("=")+1);
            	ArrayList<String> weaknessList = new ArrayList<String>(Arrays.asList(types.split(",")));
				for (int j=0; j<weaknessList.size(); j++){
					if (offendingType.equals(weaknessList.get(j))){
						return 2;
					}
				}
				i = sc.nextLine();
				if (i.startsWith("Resistances")){
	            	types = i.substring(i.lastIndexOf("=")+1);
	            	ArrayList<String> resistanceList = new ArrayList<String>(Arrays.asList(types.split(",")));
					for (int j=0; j<resistanceList.size(); j++){
						if (offendingType.equals(resistanceList.get(j))){
							return 0.5;
						}
					}
				}
				i = sc.nextLine();
				if (i.startsWith("Immunities")){
	            	types = i.substring(i.lastIndexOf("=")+1);
	            	ArrayList<String> immuneList = new ArrayList<String>(Arrays.asList(types.split(",")));
					for (int j=0; j<immuneList.size(); j++){
						if (offendingType.equals(immuneList.get(j))){
							return 0;
						}
					}
				}
				
			}
		}
		return 1; 
	}
	
}
