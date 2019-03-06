package pbsToWiki;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Scanner;

public class PkmDataReader {
	
	public Hashtable<Integer, Pokemon> pokemonList = new Hashtable<Integer, Pokemon>();
	public Hashtable<String, Integer> nameLookup = new Hashtable<String, Integer>();
	
	public Hashtable<Integer, Pokemon> readData() {
		File inputFile = new File("src/input/pokemon.txt");
		//Hashtable<Integer, Pokemon> pokemonList = new Hashtable<Integer, Pokemon>();
		//Hashtable<String, Integer> nameLookup = new Hashtable<String, Integer>();
		int indexStart = 722;
		boolean v17 = true;
		
		try {
			Scanner sc = new Scanner(inputFile,"UTF-8");
	        capsHandling capsHandler = new capsHandling();
	        while (sc.hasNextLine()) {
	            String i = sc.nextLine();
	            
	            //Loop
	            while (i.startsWith("["+indexStart)){
	            	int dexNumber = Integer.parseInt(i.substring(1,4)) - 721;
	            	Pokemon currPokemon = new Pokemon(dexNumber + 721);
	            	currPokemon.dexNum = dexNumber;
	            	
	            	//Pokemon name line
	            	i = sc.nextLine();
	            	currPokemon.name = i.substring(i.lastIndexOf("=")+1);
	            	
	            	//Internal name
	            	i = sc.nextLine();
	            	String internalName = i.substring(i.lastIndexOf("=")+1);
	            	currPokemon.internalName = internalName;
	            	
	            	//Types 1
	            	i = sc.nextLine(); 
	            	String type1 = i.substring(i.lastIndexOf("=")+1);
	            	type1 = capsHandler.properCase(type1);
	            	currPokemon.type1 = type1;
	            	
	            	//Type 2
	            	i = sc.nextLine();
	            	if (i.startsWith("Type2")){
		            	String type2 = i.substring(i.lastIndexOf("=")+1);
		            	type2 = capsHandler.properCase(type2);
		            	currPokemon.type2 = type2;
		            	i = sc.nextLine();
	            	}
	            	
	            	//Stats
	            	String stats = i.substring(i.lastIndexOf("=")+1);
	            	ArrayList<String> statList = new ArrayList<String>(Arrays.asList(stats.split(",")));
	            	currPokemon.hp = Integer.parseInt(statList.get(0));
	            	currPokemon.attack = Integer.parseInt(statList.get(1));
	            	currPokemon.defense = Integer.parseInt(statList.get(2));
	            	currPokemon.speed = Integer.parseInt(statList.get(3));
	            	currPokemon.spAttack = Integer.parseInt(statList.get(4));
	            	currPokemon.spDefense = Integer.parseInt(statList.get(5));
	            	
	            	
	            	//Gender
	            	i = sc.nextLine();
	            	i = i.substring(i.lastIndexOf("=")+1);
	            	switch(i){ //0 for 100% male, 31 for 87.5% male, 63 for 75% male, 127 for 50% male, 191 for 25% male, 223 for 12.5% male, 254 for female only, 256 for unknown, 255 for genderless
	            	case ("FemaleOneEighth"): 
	            		currPokemon.genderRatio = 31;
	            		break;
	            	case ("Female50Percent"):
	            		currPokemon.genderRatio = 127;
	            		break;
	            	case ("Female25Percent"):
	            		currPokemon.genderRatio = 63;
	            		break;
	            	case ("Female75Percent"):
	            		currPokemon.genderRatio = 191;
	            		break;
	            	case ("FemaleSevenEighths"):
	            		currPokemon.genderRatio = 223;
	            		break;
	            	case ("AlwaysFemale"):
	            		currPokemon.genderRatio = 254;
	            		break;
	            	case ("AlwaysMale"):
	            		currPokemon.genderRatio = 0;
	            		break;
	            	default:
	            		currPokemon.genderRatio = 255;
	            		break;
	            	}
	            	
	            	//Growth
	            	i= sc.nextLine();
	            	i = i.substring(i.lastIndexOf("=")+1);
	            	switch(i){ //1,059,860=MediumSlow/Parabolic; 600,000=Erratic; 800,000=Fast; 1,000,000=Medium Fast; 1,250,000=Slow; 1,640,000=Fluctuating
	            	case ("MediumSlow"):
	            		currPokemon.lv100exp = "1,059,860";
	            		break;
	            	case ("Parabolic"):
	            		currPokemon.lv100exp = "1,059,860";
	            		break;
	            	case ("Erratic"):
	            		currPokemon.lv100exp = "600,000";
	            		break;
	            	case ("Fast"):
	            		currPokemon.lv100exp = "800,000";
	            		break;
	            	case ("MediumFast"):
	            		currPokemon.lv100exp = "1,000,000";
	            		break;
	            	case ("Medium"):
	            		currPokemon.lv100exp = "1,000,000";
	            		break;
	            	case ("Slow"):
	            		currPokemon.lv100exp = "1,250,000";
	            		break;
	            	default: //fluctuating
	            		currPokemon.lv100exp = "1,640,000";
	            		break;
	            	}	
	            	
	            	//Base EXP
	            	i = sc.nextLine();
	            	currPokemon.baseEXP = i.substring(i.lastIndexOf("=")+1);
	            	
	            	
	            	//EVs
	            	i = sc.nextLine();
	            	String evstats = i.substring(i.lastIndexOf("=")+1);
	            	ArrayList<String> evstatList = new ArrayList<String>(Arrays.asList(evstats.split(",")));
	            	currPokemon.evHP = Integer.parseInt(evstatList.get(0));
	            	currPokemon.evAttack = Integer.parseInt(evstatList.get(1));
	            	currPokemon.evDefense = Integer.parseInt(evstatList.get(2));
	            	currPokemon.evSpeed = Integer.parseInt(evstatList.get(3));
	            	currPokemon.evSpAttack = Integer.parseInt(evstatList.get(4));
	            	currPokemon.evSpDefense = Integer.parseInt(evstatList.get(5));
	            	currPokemon.evTotal = currPokemon.evHP+currPokemon.evAttack+currPokemon.evDefense+currPokemon.evSpAttack+currPokemon.evSpDefense+currPokemon.evSpeed;
	            	
	            	//Rareness
	            	i = sc.nextLine();
	            	currPokemon.catchRate = i.substring(i.lastIndexOf("=")+1);
	            	
	            	//Friendship
	            	i = sc.nextLine();
	            	currPokemon.friendship = i.substring(i.lastIndexOf("=")+1);
	            	
	            	//Ability
	            	i =sc.nextLine();
	            	String abilities = i.substring(i.lastIndexOf("=")+1);
	            	ArrayList<String> abilitiesList = new ArrayList<String>(Arrays.asList(abilities.split(",")));
	            	currPokemon.ability1 = capsHandler.properCase(abilitiesList.get(0));
	            	if (abilitiesList.size()>1){
	            		currPokemon.ability2 = capsHandler.properCase(abilitiesList.get(1));
	            	}
	            	
	            	//Hidden Ability
	            	i= sc.nextLine();
	            	if (i.startsWith("HiddenAbility")){
	            		String hiddenAbility = i.substring(i.lastIndexOf("=")+1);
	            		currPokemon.hiddenAbility = capsHandler.properCase(hiddenAbility);
		            	i= sc.nextLine();
	            	}
	            	
	            	//Moves
	            	currPokemon.moves = i.substring(i.lastIndexOf("=")+1);
	            	currPokemon.movesList = new ArrayList<String>(Arrays.asList(currPokemon.moves.split(",")));
	            	
	            	i=sc.nextLine();
	            	if (i.startsWith("EggMoves")){
	            		currPokemon.eggMoves = i.substring(i.lastIndexOf("=")+1);
	            		currPokemon.eggMovesList = new ArrayList<String>(Arrays.asList(currPokemon.eggMoves.split(",")));
		            	i= sc.nextLine();
	            	}
	            	
	            	//Egg Groups
	            	String eggGroups = i.substring(i.lastIndexOf("=")+1);
	            	ArrayList<String> eggGroupList = new ArrayList<String>(Arrays.asList(eggGroups.split(",")));
	            	currPokemon.eggGroup1 = capsHandler.properCase(eggGroupList.get(0));
	            	if (eggGroupList.size()>1){
	            		currPokemon.eggGroup2 = capsHandler.properCase(eggGroupList.get(1));
	            	}
	            	
	            	//Steps to hatch
	            	i = sc.nextLine();
	            	currPokemon.steps = i.substring(i.lastIndexOf("=")+1);
	            	
	            	//Height
	            	i = sc.nextLine();
	            	currPokemon.heightM = Float.valueOf(i.substring(i.lastIndexOf("=")+1));
	            	double heightContainer = currPokemon.heightM/0.1;
	            	currPokemon.inches = (int) (heightContainer*4);
	            	heightContainer = currPokemon.inches % 12;
	            	currPokemon.heightFT = currPokemon.inches/12;
	            	currPokemon.inches = (int) heightContainer;
	            	
	            	//Weight
	            	i = sc.nextLine();
	            	currPokemon.weightKG = Double.valueOf(i.substring(i.lastIndexOf("=")+1));
	            	currPokemon.weightLB = currPokemon.weightKG * 2.2;
	            	currPokemon.weightLB = Math.round(currPokemon.weightLB);
	            	
	            	//Color
	            	i = sc.nextLine();
	            	currPokemon.color = i.substring(i.lastIndexOf("=")+1);
	            	
	            	if (v17){
		            	//Shape
		            	i = sc.nextLine();
		            	currPokemon.shape = i.substring(i.lastIndexOf("=")+1);
	            	}

	            			
	            	//Skip Habitat
	            	i = sc.nextLine();
	            	if (i.startsWith("Habitat") || i.startsWith("RegionalNumb")){
	            		//Skip Regional Number
	            		i = sc.nextLine();
	            		if (i.startsWith("RegionalNumb")){
	            			i = sc.nextLine();
	            		}
	            	}
	            	
	            	//Species
	            	currPokemon.species = i.substring(i.lastIndexOf("=")+1);
	            	
	            	//Pokedex Entry
	            	i = sc.nextLine();
	            	currPokemon.dexEntry = i.substring(i.lastIndexOf("=")+1);
	            	
	            	//Items
	            	i = sc.nextLine();
	            	while (i.startsWith("WildItem")){
	            		if (i.startsWith("WildItemUncommon")){
	            			currPokemon.uncommonItem = i.substring(i.lastIndexOf("=")+1);
	            		}
	            		if (i.startsWith("WildItemCommon")){
	            			currPokemon.commonItem = i.substring(i.lastIndexOf("=")+1);
	            		}
	            		if (i.startsWith("WildItemRare")){
	            			currPokemon.rareItem = i.substring(i.lastIndexOf("=")+1);
	            		}
	            		i = sc.nextLine();
	            	}
	            	
	        		//Skip BattlerPlayerY
	        		i = sc.nextLine();
	        		
	        		//Skip BattlerEnemyY
	        		i = sc.nextLine();
	        		
	        		//Skip BattlerAltitude
	        		i = sc.nextLine();
	        		
	        		//Skip Evolution
	        		String evos = i.substring(i.lastIndexOf("=")+1);
	        		ArrayList<String>evoList = new ArrayList<String>(Arrays.asList(evos.split(",")));
	        		if (evoList.size()>1) {
	        			if (evoList.size() == 2) {
	        				evoList.add("");
	        			}
		        		for (int j = 0; j<evoList.size(); j+=3) {
		        			Evolution evolution = new Evolution();
		        			evolution.name = evoList.get(j);
		        			evolution.method = evoList.get(j+1);
		        			evolution.req = evoList.get(j+2);
		        			
		        			currPokemon.evolutions.add(evolution);
		        		}
	        		}

	        		
	        		if (sc.hasNextLine()){
	        			i = sc.nextLine(); 
	        		}
	        		
	        		//if (indexStart==827){
	        			//int test = 0;
	        		//}
	        		//prevprevSpecies = prevSpecies;
	        		//prevSpecies = pokemonName;
	            	
	        		pokemonList.put(currPokemon.internalNum, currPokemon);
	        		nameLookup.put(internalName,currPokemon.internalNum);
	        		indexStart++;
	            }
	            	
	        }
	        sc.close();
	    } 
	    catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }
		
		//Find pre-evos
		for (int i = 0; i<pokemonList.size(); i++) {
			Pokemon currPokemon = pokemonList.get(i+722);
			for (int j = 0; j<currPokemon.evolutions.size(); j++) {
				Evolution currEvo = currPokemon.evolutions.get(j);
				if (nameLookup.containsKey(currEvo.name)){
					pokemonList.get(nameLookup.get(currEvo.name)).preevo = currPokemon.internalName;
				}
				
			}
		}
		
		
		return pokemonList;
	}
}
