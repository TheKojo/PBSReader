package pbsToWiki;
import java.awt.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class PBSWikiConverter {

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		//Settings----------------------------------
		File inputFile = new File("src/input/pokemon.txt");
		PrintWriter outputWriter = new PrintWriter("src/output/wikitext.txt", "UTF-8");
		int indexStart = 722;
		
		//Initialize variables
		String pokemonName = "";
		int dexNumber = indexStart - 721;
		String type1 = "";
		String type2 = "";
		int hp = 0;
		int attack = 0;
		int defense = 0;
		int spAttack = 0;
		int spDefense = 0;
		int speed = 0;
		int genderRatio = 0; //0 for 100% male, 31 for 87.5% male, 63 for 75% male, 127 for 50% male, 191 for 25% male, 223 for 12.5% male, 254 for female only, 256 for unknown, 255 for genderless
	    String lv100exp = ""; //1,059,860=MediumSlow/Parabolic; 600,000=Erratic; 800,000=Fast; 1,000,000=Medium Fast; 1,250,000=Slow; 1,640,000=Fluctuating
	    String baseEXP = "";
	    int evHP = 0;
	    int evAttack = 0;
	    int evDefense = 0;
	    int evSpAttack = 0;
	    int evSpDefense = 0;
	    int evSpeed = 0;
	    int evTotal = 0;
		String catchRate = ""; //rareness
		String friendship = ""; //happiness
		String ability1 = "";
		String ability2 = "";
		String hiddenAbility = "";
		String moves = "";
		String eggMoves = "";
		String eggGroup1 = "";
		String eggGroup2 = "";
		String steps = "";
		float heightM = 0;
		int heightFT = 0;
		int inches = 0;
		double weightKG = 0;
		double weightLB = 0;
		String color = "";
		String species = "";
		String dexEntry = "";
		String uncommonItem ="";
		String commonItem="";
		String rareItem="";
		ArrayList<String> movesList = null;
		ArrayList<String> eggMovesList = null;
		String prevSpecies = "";
		String prevprevSpecies = "";

		//Scan PBS and receive variable values
	    try {
	        Scanner sc = new Scanner(inputFile,"UTF-8");
	        capsHandling capsHandler = new capsHandling();
	        while (sc.hasNextLine()) {
	            String i = sc.nextLine();
	            System.out.println(i);
	            while (i.startsWith("["+indexStart)){
	            	dexNumber = Integer.parseInt(i.substring(1,4)) - 721;
	            	i =sc.nextLine();//Pokemon name line
	            	pokemonName = i.substring(i.lastIndexOf("=")+1);
	            	i =sc.nextLine(); //skip internal name
	            	
	            	if(dexNumber>1){
		        	    //Append output txt file
	            		String typeblock;
	            		if (type2.length()>0){
	            			typeblock = "type="+type1+" | type2="+type2;
	            		}
	            		else{
	            			typeblock = "type="+type1;
	            		}
	            		String prevNum;
	            		if (dexNumber<12){
	            			prevNum = "00"+(dexNumber-2);
	            		}
	            		else if (dexNumber<102){
	            			prevNum = "0"+(dexNumber-2);
	            		}
	            		else{
	            			prevNum = ""+(dexNumber-2);
	            		}
	            		
	            		String nextNum;
	            		if (dexNumber<10){
	            			nextNum = "00"+dexNumber;
	            		}
	            		else if (dexNumber<100){
	            			nextNum = "0"+dexNumber;
	            		}
	            		else{
	            			nextNum = ""+dexNumber;
	            		}
	            		
	            		String currNum;
	            		if (dexNumber<11){
	            			currNum = "00"+(dexNumber-1);
	            		}
	            		else if (dexNumber<101){
	            			currNum = "0"+(dexNumber-1);
	            		}
	            		else{
	            			currNum = ""+(dexNumber-1);
	            		}
	            		
		        		outputWriter.println("{{PokemonPrevNextHead | species="+prevSpecies+" | "+typeblock+" | prev="+prevprevSpecies+" | prevnum="+prevNum+" | next="+pokemonName+" | nextnum="+nextNum+" }}");
		        		outputWriter.println("{{Pokemon Infobox");
		        		outputWriter.println("| name          = "+prevSpecies);
		        		outputWriter.println("| ndex          = "+currNum);
		        		outputWriter.println("| species       = "+species);
		        		outputWriter.println("| image         = "+prevSpecies+".png");
		        		outputWriter.println("| type1         = "+type1);
		        		if (type2.length()>0){
		        			outputWriter.println("| type2         = "+type2);
		        		}
		        		outputWriter.println("| ability1      = "+ability1);
		        		outputWriter.println("| ability2      = "+ability2);
		        		outputWriter.println("| hiddenability = "+hiddenAbility);
		        		outputWriter.println("| gendercode    = "+genderRatio);
		        		outputWriter.println("| catchrate     = "+catchRate);
		        		outputWriter.println("| egggroup1     = "+eggGroup1);
		        		outputWriter.println("| egggroup2     = "+eggGroup2);
		        		outputWriter.println("| eggsteps      = "+steps);
		        		outputWriter.println("| egggroupn     = 1 <!-- 0 if can't legitimately obtain this as an egg -->");
		        		outputWriter.println("| height-m      = "+heightM);
		        		outputWriter.println("| height-ftin   = "+heightFT+"'"+inches+"\"");
		        		outputWriter.println("| weight-kg     = "+weightKG);
		        		outputWriter.println("| weight-lbs    = "+weightLB);
		        		outputWriter.println("| expyield      = "+baseEXP);
		        		outputWriter.println("| lv100exp      = "+lv100exp);
		        		outputWriter.println("| evtotal       = "+evTotal);
		        		outputWriter.println("| evhp          = "+evHP);
		        		outputWriter.println("| evat          = "+evAttack);
		        		outputWriter.println("| evde          = "+evDefense);
		        		outputWriter.println("| evsa          = "+evSpAttack);
		        		outputWriter.println("| evsd          = "+evSpDefense);
		        		outputWriter.println("| evsp          = "+evSpeed);
		        		outputWriter.println("| body          = ");
		        		outputWriter.println("| color         = "+color);
		        		outputWriter.println("| friendship    = "+friendship);
		        		outputWriter.println("}}");
		        		if (type2.length()>0){
		        			outputWriter.println("'''"+prevSpecies+"''' is a {{Type|"+type1+"}}/{{Type|"+type2+"}}-type Pokémon.");
		        		}
		        		else{
		        			outputWriter.println("'''"+prevSpecies+"''' is a {{Type|"+type1+"}}-type Pokémon.");
		        		}
		        		outputWriter.println("");
		        		//TODO: implement evolution info
		        		outputWriter.println("==Pokédex entry==");
		        		outputWriter.println("");
		        		if (type2.length()>0){
		        			outputWriter.println("{{Dex|type1="+type1+"|type2="+type2);
		        		}
		        		else{
		        			outputWriter.println("{{Dex|type1="+type1);
		        		}
		        		outputWriter.println("|"+dexEntry+"}}");
		        		outputWriter.println("");
		        		outputWriter.println("==Game locations==");
		        		outputWriter.println("");
		        		if (type2.length()>0){
		        			outputWriter.println("{{Availability|type1="+type1+"|type2="+type2);
		        		}
		        		else{
		        			outputWriter.println("{{Availability|type1="+type1);
		        		}
		        		locationHandling locationHandler = new locationHandling();
		        		ArrayList<String> areaList = locationHandler.getLocationsFor(prevSpecies);
		        		String listString = "";
		        		for (int h = 0; h<areaList.size(); h++){
		        			listString = listString + areaList.get(h)+", ";
		        		}
		        		if (listString.length()>0){
		        			listString = listString.substring(0,listString.length()-2);
		        		}
		        		outputWriter.println("|common= "+listString); //{{{common|}}}{{{uncommon|}}}{{{rare|}}}{{{fossil|}}}{{{one|}}}
		        		outputWriter.println("}}");
		        		outputWriter.println("");
		        		outputWriter.println("==Held items==");
		        		outputWriter.println("");
		        		if (type2.length()>0){
		        			outputWriter.println("{{HeldItems|type1="+type1+"|type2="+type2);
		        		}
		        		else{
		        			outputWriter.println("{{HeldItems|type1="+type1);
		        		}
		        		if (uncommonItem.length()>0){
		        			outputWriter.println("| uncommon = {{Item|"+capsHandler.properCase(uncommonItem)+"}} "+capsHandler.properCase(uncommonItem));
		        		}
		        		if (commonItem.length()>0){
		        			outputWriter.println("| common = {{Item|"+capsHandler.properCase(commonItem)+"}} "+capsHandler.properCase(commonItem));
		        		}
		        		if (rareItem.length()>0){
		        			outputWriter.println("| rare = {{Item|"+capsHandler.properCase(rareItem)+"}} "+capsHandler.properCase(rareItem));
		        		}
		        		outputWriter.println("}}");
		        		outputWriter.println("");
		        		outputWriter.println("==Base stats==");
		        		outputWriter.println("");
		        		outputWriter.println("{{Stats");
		        		outputWriter.println("| type    = "+type1);
		        		if (type2.length()>0){
		        			outputWriter.println("| type2    = "+type2);
		        		}
		        		outputWriter.println("| HP      = "+hp);
		        		outputWriter.println("| Attack  = "+attack);
		        		outputWriter.println("| Defense = "+defense);
		        		outputWriter.println("| SpAtk   = "+spAttack);
		        		outputWriter.println("| SpDef   = "+spDefense);
		        		outputWriter.println("| Speed   = "+speed);
		        		outputWriter.println("}}");
		        		outputWriter.println("");
		        		outputWriter.println("==Type effectiveness==");
		        		outputWriter.println("");
		        		outputWriter.println("{{TypeEffectiveness <!-- 100 is normal effectiveness, 200 is weak to that type, 50 is resistant to that type, etc. -->");
		        		outputWriter.println("| type1    = "+type1);
		        		if (type2.length()>0){
		        			outputWriter.println("| type2    = "+type2);
		        		}
		        		typeEffectivenessHandling typeHandler = new typeEffectivenessHandling();
		        		outputWriter.println("| Normal   = "+(int)(100*typeHandler.get(type1, "NORMAL")*typeHandler.get(type2, "NORMAL")));
		        		outputWriter.println("| Fighting = "+(int)(100*typeHandler.get(type1, "FIGHTING")*typeHandler.get(type2, "FIGHTING")));
		        		outputWriter.println("| Flying   = "+(int)(100*typeHandler.get(type1, "FLYING")*typeHandler.get(type2, "FLYING")));
		        		outputWriter.println("| Poison   = "+(int)(100*typeHandler.get(type1, "POISON")*typeHandler.get(type2, "POISON")));
		        		outputWriter.println("| Ground   = "+(int)(100*typeHandler.get(type1, "GROUND")*typeHandler.get(type2, "GROUND")));
		        		outputWriter.println("| Rock     = "+(int)(100*typeHandler.get(type1, "ROCK")*typeHandler.get(type2, "ROCK")));
		        		outputWriter.println("| Bug      = "+(int)(100*typeHandler.get(type1, "BUG")*typeHandler.get(type2, "BUG")));
		        		outputWriter.println("| Ghost    = "+(int)(100*typeHandler.get(type1, "GHOST")*typeHandler.get(type2, "GHOST")));
		        		outputWriter.println("| Steel    = "+(int)(100*typeHandler.get(type1, "STEEL")*typeHandler.get(type2, "STEEL")));
		        		outputWriter.println("| Fire     = "+(int)(100*typeHandler.get(type1, "FIRE")*typeHandler.get(type2, "FIRE")));
		        		outputWriter.println("| Water    = "+(int)(100*typeHandler.get(type1, "WATER")*typeHandler.get(type2, "WATER")));
		        		outputWriter.println("| Grass    = "+(int)(100*typeHandler.get(type1, "GRASS")*typeHandler.get(type2, "GRASS")));
		        		outputWriter.println("| Electric = "+(int)(100*typeHandler.get(type1, "ELECTRIC")*typeHandler.get(type2, "ELECTRIC")));
		        		outputWriter.println("| Psychic  = "+(int)(100*typeHandler.get(type1, "PSYCHIC")*typeHandler.get(type2, "PSYCHIC")));
		        		outputWriter.println("| Ice      = "+(int)(100*typeHandler.get(type1, "ICE")*typeHandler.get(type2, "ICE")));
		        		outputWriter.println("| Dragon   = "+(int)(100*typeHandler.get(type1, "DRAGON")*typeHandler.get(type2, "DRAGON")));
		        		outputWriter.println("| Dark     = "+(int)(100*typeHandler.get(type1, "DARK")*typeHandler.get(type2, "DARK")));
		        		outputWriter.println("| Fairy    = "+(int)(100*typeHandler.get(type1, "FAIRY")*typeHandler.get(type2, "FAIRY")));
		        		outputWriter.println("}}");
		        		outputWriter.println("");
		        		outputWriter.println("==Moves==");
		        		outputWriter.println("");
		        		outputWriter.println("===By leveling up===");
		        		outputWriter.println("");
		        		if (type2.length()>0){
		        			outputWriter.println("{{MoveLevelStart|"+prevSpecies+"|"+type1+"|"+type2+"}}");
		        		}
		        		else{
		        			outputWriter.println("{{MoveLevelStart|"+prevSpecies+"|"+type1+"|"+type1+"}}");
		        		}
		        		for (int j = 0; j<movesList.size()-1; j++){
		        			String moveLevel = movesList.get(j); //odd indices of list have levels
		        			if (moveLevel.equals(1)){
		        				moveLevel = "Start";
		        			}
		        			j++; //even indices have the name of the move itself
		        			moveHandling moveHandler = new moveHandling(movesList.get(j));
		        			outputWriter.println("{{MoveLevel|"+moveLevel+"|"+moveHandler.getMove()+"|"+moveHandler.getType()+"|"+moveHandler.getCategory()+"|"+moveHandler.getPower()+"|"+moveHandler.getAccuracy()+"|"+moveHandler.getPP()+"}}");
		        			
		        		}
		        		if (type2.length()>0){
		        			outputWriter.println("{{MoveLevelEnd|"+prevSpecies+"|"+type1+"|"+type2+"}}");
		        		}
		        		else{
		        			outputWriter.println("{{MoveLevelEnd|"+prevSpecies+"|"+type1+"|"+type1+"}}");
		        		}
		        		outputWriter.println("");
		        		outputWriter.println("===By TM/HM===");
		        		outputWriter.println("<!-- You can get the entries for this table from the page [[TM and HM list]] -->");
		        		if (type2.length()>0){
		        			outputWriter.println("{{MoveTMStart|"+prevSpecies+"|"+type1+"|"+type2+"}}");
		        		}
		        		else{
		        			outputWriter.println("{{MoveTMStart|"+prevSpecies+"|"+type1+"|"+type1+"}}");
		        		}
		        		outputWriter.println("{{MoveTM|TM27|Return|Normal|Physical|Varies|100|20}}");
		        		if (type2.length()>0){
		        			outputWriter.println("{{MoveTMEnd|"+prevSpecies+"|"+type1+"|"+type2+"}}");
		        		}
		        		else{
		        			outputWriter.println("{{MoveTMEnd|"+prevSpecies+"|"+type1+"|"+type1+"}}");
		        		}
		        		outputWriter.println("");
		        		outputWriter.println("===By breeding===");
		        		outputWriter.println("");
		        		if (type2.length()>0){
		        			outputWriter.println("{{MoveBreedStart|"+prevSpecies+"|"+type1+"|"+type2+"}}");
		        		}
		        		else{
		        			outputWriter.println("{{MoveBreedStart|"+prevSpecies+"|"+type1+"|"+type1+"}}");
		        		}
		        		for (int j = 0; j<eggMovesList.size(); j++){
		        			moveHandling eggMoveHandler = new moveHandling(eggMovesList.get(j));
		        			outputWriter.println("{{MoveBreed||"+eggMoveHandler.getMove()+"|"+eggMoveHandler.getType()+"|"+eggMoveHandler.getCategory()+"|"+eggMoveHandler.getPower()+"|"+eggMoveHandler.getAccuracy()+"|"+eggMoveHandler.getPP()+"}}");
		        			
		        		}
		        		if (type2.length()>0){
		        			outputWriter.println("{{MoveBreedEnd|"+prevSpecies+"|"+type1+"|"+type2+"}}");
		        		}
		        		else{
		        			outputWriter.println("{{MoveBreedEnd|"+prevSpecies+"|"+type1+"|"+type1+"}}");
		        		}
		        		outputWriter.println("");
		        		outputWriter.println("==Evolution==");
		        		outputWriter.println("");
		        		outputWriter.println("{{Evobox-3");
		        		outputWriter.println("| type1   = "+type1);
		        		if (type2.length()>0){
		        			outputWriter.println("| type2   = "+type2);
		        		}
		        		outputWriter.println("| no1     = ");
		        		outputWriter.println("| name1   = ");
		        		outputWriter.println("| image1  = ");
		        		outputWriter.println("| type1-1 = "+type1);
		        		outputWriter.println("| evo1    = ");
		        		outputWriter.println("| no2     = ");
		        		outputWriter.println("| name2   = ");
		        		outputWriter.println("| image2  = ");
		        		outputWriter.println("| type1-2 = "+type1);
		        		outputWriter.println("| type2-2 = "+type1);
		        		outputWriter.println("| evo2    = ");
		        		outputWriter.println("| no3     = ");
		        		outputWriter.println("| name3   = ");
		        		outputWriter.println("| image3  = ");
		        		outputWriter.println("| type1-3 = "+type1);
		        		outputWriter.println("| type2-3 = "+type1);
		        		outputWriter.println("}}");
		        		outputWriter.println("");
		        		outputWriter.println("==Sprites==");
		        		outputWriter.println("");
		        		if (type2.length()>0){
		        			outputWriter.println("{{Sprites|"+currNum+"|type="+type1+"|type2="+type2+"|gender=both}}");
		        		}
		        		else{
		        			outputWriter.println("{{Sprites|"+currNum+"|type="+type1+"|type2="+type2+"|gender=both}}");
		        		}
		        		outputWriter.println("");
		        		outputWriter.println("==Trivia==");
		        		outputWriter.println("");
		        		outputWriter.println("");
		        		outputWriter.println("===Design origin===");
		        		outputWriter.println("");
		        		outputWriter.println("");
		        		outputWriter.println("===Name origin===");
		        		outputWriter.println("");
		        		outputWriter.println("");
		        		outputWriter.println("{{PokemonPrevNextHead | species="+prevSpecies+" | "+typeblock+" | prev="+prevprevSpecies+" | prevnum="+prevNum+" | next="+pokemonName+" | nextnum="+nextNum+" }}");
		        		outputWriter.println("");
		        		outputWriter.println("END==============================================");
	            	}
	            	
	        		
	        		type2=""; //reset in case next pokemon only has one type
	        		ability2="";
	        		hiddenAbility="";
	        		eggGroup2="";
	        		uncommonItem ="";
	        		commonItem="";
	        		rareItem="";
	            	
	            	//Types
	            	i =sc.nextLine(); //type line
	            	type1 = i.substring(i.lastIndexOf("=")+1);
	            	type1 = capsHandler.properCase(type1);
	            	i= sc.nextLine();
	            	if (i.startsWith("Type2")){
		            	type2 = i.substring(i.lastIndexOf("=")+1);
		            	type2 = capsHandler.properCase(type2);
		            	i= sc.nextLine();
	            	}
	            	
	            	//Stats
	            	String stats = i.substring(i.lastIndexOf("=")+1);
	            	ArrayList<String> statList = new ArrayList<String>(Arrays.asList(stats.split(",")));
	            	hp = Integer.parseInt(statList.get(0));
	            	attack = Integer.parseInt(statList.get(1));
	            	defense = Integer.parseInt(statList.get(2));
	            	speed = Integer.parseInt(statList.get(3));
	            	spAttack = Integer.parseInt(statList.get(4));
	            	spDefense = Integer.parseInt(statList.get(5));
	            	
	            	//Gender
	            	i= sc.nextLine();
	            	i = i.substring(i.lastIndexOf("=")+1);
	            	switch(i){ //0 for 100% male, 31 for 87.5% male, 63 for 75% male, 127 for 50% male, 191 for 25% male, 223 for 12.5% male, 254 for female only, 256 for unknown, 255 for genderless
	            	case ("FemaleOneEighth"): 
	            		genderRatio = 31;
	            		break;
	            	case ("Female50Percent"):
	            		genderRatio = 127;
	            		break;
	            	case ("Female25Percent"):
	            		genderRatio = 63;
	            		break;
	            	case ("Female75Percent"):
	            		genderRatio = 191;
	            		break;
	            	case ("FemaleSevenEighths"):
	            		genderRatio = 223;
	            		break;
	            	case ("AlwaysFemale"):
	            		genderRatio = 254;
	            		break;
	            	case ("AlwaysMale"):
	            		genderRatio = 0;
	            		break;
	            	default:
	            		genderRatio = 255;
	            		break;
	            	}
	            	
	            	//EXP
	            	i= sc.nextLine();
	            	i = i.substring(i.lastIndexOf("=")+1);
	            	switch(i){ //1,059,860=MediumSlow/Parabolic; 600,000=Erratic; 800,000=Fast; 1,000,000=Medium Fast; 1,250,000=Slow; 1,640,000=Fluctuating
	            	case ("MediumSlow"):
	            		lv100exp = "1,059,860";
	            		break;
	            	case ("Parabolic"):
	            		lv100exp = "1,059,860";
	            		break;
	            	case ("Erratic"):
	            		lv100exp = "600,000";
	            		break;
	            	case ("Fast"):
	            		lv100exp = "800,000";
	            		break;
	            	case ("MediumFast"):
	            		lv100exp = "1,000,000";
	            		break;
	            	case ("Medium"):
	            		lv100exp = "1,000,000";
	            		break;
	            	case ("Slow"):
	            		lv100exp = "1,250,000";
	            		break;
	            	default: //fluctuating
	            		lv100exp = "1,640,000";
	            		break;
	            	}	
	            	i = sc.nextLine();
	            	baseEXP = i.substring(i.lastIndexOf("=")+1);
	            	
	            	//EVs
	            	i = sc.nextLine();
	            	String evstats = i.substring(i.lastIndexOf("=")+1);
	            	ArrayList<String> evstatList = new ArrayList<String>(Arrays.asList(evstats.split(",")));
	            	evHP = Integer.parseInt(evstatList.get(0));
	            	evAttack = Integer.parseInt(evstatList.get(1));
	            	evDefense = Integer.parseInt(evstatList.get(2));
	            	evSpeed = Integer.parseInt(evstatList.get(3));
	            	evSpAttack = Integer.parseInt(evstatList.get(4));
	            	evSpDefense = Integer.parseInt(evstatList.get(5));
	            	evTotal = evHP+evAttack+evDefense+evSpAttack+evSpDefense+evSpeed;
	            	
	            	//Rareness
	            	i = sc.nextLine();
	            	catchRate = i.substring(i.lastIndexOf("=")+1);
	            	
	            	//Friendship
	            	i = sc.nextLine();
	            	friendship = i.substring(i.lastIndexOf("=")+1);
	            	
	            	//Abilities
	            	i =sc.nextLine();
	            	String abilities = i.substring(i.lastIndexOf("=")+1);
	            	ArrayList<String> abilitiesList = new ArrayList<String>(Arrays.asList(abilities.split(",")));
	            	ability1 = capsHandler.properCase(abilitiesList.get(0));
	            	if (abilitiesList.size()>1){
		            	ability2 = capsHandler.properCase(abilitiesList.get(1));
	            	}
	            	i= sc.nextLine();
	            	if (i.startsWith("HiddenAbility")){
		            	hiddenAbility = i.substring(i.lastIndexOf("=")+1);
		            	hiddenAbility = capsHandler.properCase(hiddenAbility);
		            	i= sc.nextLine();
	            	}
	            	
	            	//Moves
	            	moves = i.substring(i.lastIndexOf("=")+1);
	            	movesList = new ArrayList<String>(Arrays.asList(moves.split(",")));
	            	
	            	i=sc.nextLine();
	            	if (i.startsWith("EggMoves")){
	            		eggMoves = i.substring(i.lastIndexOf("=")+1);
	            		eggMovesList = new ArrayList<String>(Arrays.asList(eggMoves.split(",")));
		            	i= sc.nextLine();
	            	}
	            	
	            	//Egg Groups
	            	String eggGroups = i.substring(i.lastIndexOf("=")+1);
	            	ArrayList<String> eggGroupList = new ArrayList<String>(Arrays.asList(eggGroups.split(",")));
	            	eggGroup1 = capsHandler.properCase(eggGroupList.get(0));
	            	if (eggGroupList.size()>1){
		            	eggGroup2 = capsHandler.properCase(eggGroupList.get(1));
	            	}
	            	
	            	//Steps to hatch
	            	i = sc.nextLine();
	            	steps = i.substring(i.lastIndexOf("=")+1);
	            	
	            	//Height
	            	i = sc.nextLine();
	            	heightM = Float.valueOf(i.substring(i.lastIndexOf("=")+1));
	            	double heightContainer = heightM/0.1;
	            	inches = (int) (heightContainer*4);
	            	heightContainer = inches % 12;
	            	heightFT = inches/12;
	            	inches = (int) heightContainer;
	            	
	            	//Weight
	            	i = sc.nextLine();
	            	weightKG = Double.valueOf(i.substring(i.lastIndexOf("=")+1));
	            	weightLB = weightKG * 2.2;
	            	weightLB = Math.round(weightLB);
	            	//Color
	            	i = sc.nextLine();
	            	color = i.substring(i.lastIndexOf("=")+1);
	            	
	            	i = sc.nextLine();//skip habitat
	            	if (i.startsWith("Habitat") || i.startsWith("RegionalNumb")){
	            		i = sc.nextLine();//skip regionalnumber
	            		if (i.startsWith("RegionalNumb")){
	            			i = sc.nextLine();
	            		}
	            	}
	            	
	            	//Species
	            	species = i.substring(i.lastIndexOf("=")+1);
	            	
	            	//Pokedex Entry
	            	i = sc.nextLine();
	            	dexEntry = i.substring(i.lastIndexOf("=")+1);
	            	
	            	i = sc.nextLine();
	            	if (pokemonName.equals("Pansurge")){
	            		String t1est = "";
	            	}
	            	while (i.startsWith("WildItem")){ //skip items
	            		if (i.startsWith("WildItemUncommon")){
	            			uncommonItem = i.substring(i.lastIndexOf("=")+1);
	            		}
	            		if (i.startsWith("WildItemCommon")){
	            			commonItem = i.substring(i.lastIndexOf("=")+1);
	            		}
	            		if (i.startsWith("WildItemRare")){
	            			rareItem = i.substring(i.lastIndexOf("=")+1);
	            		}
	            		i = sc.nextLine();
	            	}
	            	
	        		indexStart++;
	        		
	        		//skip battler positions and evolutions
	        		i = sc.nextLine();
	        		i = sc.nextLine();
	        		i = sc.nextLine();
	        		if (sc.hasNextLine()){
	        		i = sc.nextLine(); 
	        		}
	        		if (indexStart==827){
	        			int test = 0;
	        		}
	        		prevprevSpecies = prevSpecies;
	        		prevSpecies = pokemonName;
	            }
	        }
	        sc.close();
	        outputWriter.close();
	    } 
	    catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }
	    

		System.out.println("done");
	}

}
