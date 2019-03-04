package pbsToWiki;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Hashtable;

public class ArticlePrinter {

	public void printData(Hashtable<Integer, Pokemon> pkmList) throws FileNotFoundException, UnsupportedEncodingException {
		PrintWriter outputWriter = new PrintWriter("src/output/wikitext.txt", "UTF-8");
		capsHandling capsHandler = new capsHandling();
		int startIndex = 722;
		
		//Loop
		for (int i = 0; i<pkmList.size(); i++) {
			
			int currIndex = i+startIndex;
			Pokemon currPokemon = pkmList.get(currIndex);
		    
			//Type String
			String typeblock;
			if (currPokemon.type2.length()>0){
				typeblock = "type="+currPokemon.type1+" | type2="+currPokemon.type2;
			}
			else{
				typeblock = "type="+currPokemon.type1;
			}
			
			//Dex Number String
			String prevNum;
			if ((currPokemon.dexNum-1)<10){
				prevNum = "00"+(currPokemon.dexNum-1);
			}
			else if ((currPokemon.dexNum-1)<100){
				prevNum = "0"+(currPokemon.dexNum-1);
			}
			else{
				prevNum = ""+(currPokemon.dexNum-1);
			}
			
			String nextNum;
			if ((currPokemon.dexNum+1)<10){
				nextNum = "00"+(currPokemon.dexNum+1);
			}
			else if ((currPokemon.dexNum+1)<100){
				nextNum = "0"+(currPokemon.dexNum+1);
			}
			else{
				nextNum = ""+(currPokemon.dexNum+1);
			}
			
			String currNum;
			if (currPokemon.dexNum<10){
				currNum = "00"+(currPokemon.dexNum);
			}
			else if (currPokemon.dexNum<100){
				currNum = "0"+(currPokemon.dexNum);
			}
			else{
				currNum = ""+(currPokemon.dexNum);
			}
			
			
			String prevSpecies = "";
			if (currIndex != startIndex){
				prevSpecies = pkmList.get(currIndex-1).name;
			}

			String nextSpecies = "";
			int test = pkmList.size();
			if (currIndex-startIndex < pkmList.size()-1){
				nextSpecies = pkmList.get(currIndex+1).name;
			}

			outputWriter.println("{{PokemonPrevNextHead | species="+currPokemon.name+" | "+typeblock+" | prev="+prevSpecies+" | prevnum="+prevNum+" | next="+nextSpecies+" | nextnum="+nextNum+" }}");
			outputWriter.println("{{Pokemon Infobox");
			outputWriter.println("| name          = "+currPokemon.name);
			outputWriter.println("| ndex          = "+currNum);
			outputWriter.println("| species       = "+currPokemon.species);
			outputWriter.println("| image         = "+currPokemon.name+".png");
			outputWriter.println("| type1         = "+currPokemon.type1);
			if (currPokemon.type2.length()>0){
				outputWriter.println("| type2         = "+currPokemon.type2);
			}
			outputWriter.println("| ability1      = "+currPokemon.ability1);
			outputWriter.println("| ability2      = "+currPokemon.ability2);
			outputWriter.println("| hiddenability = "+currPokemon.hiddenAbility);
			outputWriter.println("| gendercode    = "+currPokemon.genderRatio);
			outputWriter.println("| catchrate     = "+currPokemon.catchRate);
			outputWriter.println("| egggroup1     = "+currPokemon.eggGroup1);
			outputWriter.println("| egggroup2     = "+currPokemon.eggGroup2);
			outputWriter.println("| eggsteps      = "+currPokemon.steps);
			outputWriter.println("| egggroupn     = 1 <!-- 0 if can't legitimately obtain this as an egg -->");
			outputWriter.println("| height-m      = "+currPokemon.heightM);
			outputWriter.println("| height-ftin   = "+currPokemon.heightFT+"'"+currPokemon.inches+"\"");
			outputWriter.println("| weight-kg     = "+currPokemon.weightKG);
			outputWriter.println("| weight-lbs    = "+currPokemon.weightLB);
			outputWriter.println("| expyield      = "+currPokemon.baseEXP);
			outputWriter.println("| lv100exp      = "+currPokemon.lv100exp);
			outputWriter.println("| evtotal       = "+currPokemon.evTotal);
			outputWriter.println("| evhp          = "+currPokemon.evHP);
			outputWriter.println("| evat          = "+currPokemon.evAttack);
			outputWriter.println("| evde          = "+currPokemon.evDefense);
			outputWriter.println("| evsa          = "+currPokemon.evSpAttack);
			outputWriter.println("| evsd          = "+currPokemon.evSpDefense);
			outputWriter.println("| evsp          = "+currPokemon.evSpeed);
			outputWriter.println("| body          = ");
			outputWriter.println("| color         = "+currPokemon.color);
			outputWriter.println("| friendship    = "+currPokemon.friendship);
			outputWriter.println("}}");
			if (currPokemon.type2.length()>0){
				outputWriter.println("'''"+currPokemon.name+"''' is a {{Type|"+currPokemon.type1+"}}/{{Type|"+currPokemon.type2+"}}-type Pokémon.");
			}
			else{
				outputWriter.println("'''"+currPokemon.name+"''' is a {{Type|"+currPokemon.type1+"}}-type Pokémon.");
			}
			outputWriter.println("");
			//TODO: implement evolution info
			outputWriter.println("==Pokédex entry==");
			outputWriter.println("");
			if (currPokemon.type2.length()>0){
				outputWriter.println("{{Dex|type1="+currPokemon.type1+"|type2="+currPokemon.type2);
			}
			else{
				outputWriter.println("{{Dex|type1="+currPokemon.type1);
			}
			outputWriter.println("|"+currPokemon.dexEntry+"}}");
			outputWriter.println("");
			outputWriter.println("==Game locations==");
			outputWriter.println("");
			if (currPokemon.type2.length()>0){
				outputWriter.println("{{Availability|type1="+currPokemon.type1+"|type2="+currPokemon.type2);
			}
			else{
				outputWriter.println("{{Availability|type1="+currPokemon.type1);
			}
			locationHandling locationHandler = new locationHandling();
			ArrayList<String> areaList = locationHandler.getLocationsFor(currPokemon.name);
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
			if (currPokemon.type2.length()>0){
				outputWriter.println("{{HeldItems|type1="+currPokemon.type1+"|type2="+currPokemon.type2);
			}
			else{
				outputWriter.println("{{HeldItems|type1="+currPokemon.type1);
			}
			if (currPokemon.uncommonItem.length()>0){
				outputWriter.println("| uncommon = {{Item|"+capsHandler.properCase(currPokemon.uncommonItem)+"}} "+capsHandler.properCase(currPokemon.uncommonItem));
			}
			if (currPokemon.commonItem.length()>0){
				outputWriter.println("| common = {{Item|"+capsHandler.properCase(currPokemon.commonItem)+"}} "+capsHandler.properCase(currPokemon.commonItem));
			}
			if (currPokemon.rareItem.length()>0){
				outputWriter.println("| rare = {{Item|"+capsHandler.properCase(currPokemon.rareItem)+"}} "+capsHandler.properCase(currPokemon.rareItem));
			}
			outputWriter.println("}}");
			outputWriter.println("");
			outputWriter.println("==Base stats==");
			outputWriter.println("");
			outputWriter.println("{{Stats");
			outputWriter.println("| type    = "+currPokemon.type1);
			if (currPokemon.type2.length()>0){
				outputWriter.println("| type2    = "+currPokemon.type2);
			}
			outputWriter.println("| HP      = "+currPokemon.hp);
			outputWriter.println("| Attack  = "+currPokemon.attack);
			outputWriter.println("| Defense = "+currPokemon.defense);
			outputWriter.println("| SpAtk   = "+currPokemon.spAttack);
			outputWriter.println("| SpDef   = "+currPokemon.spDefense);
			outputWriter.println("| Speed   = "+currPokemon.speed);
			outputWriter.println("}}");
			outputWriter.println("");
			outputWriter.println("==Type effectiveness==");
			outputWriter.println("");
			outputWriter.println("{{TypeEffectiveness <!-- 100 is normal effectiveness, 200 is weak to that type, 50 is resistant to that type, etc. -->");
			outputWriter.println("| type1    = "+currPokemon.type1);
			if (currPokemon.type2.length()>0){
				outputWriter.println("| type2    = "+currPokemon.type2);
			}
			typeEffectivenessHandling typeHandler = new typeEffectivenessHandling();
			outputWriter.println("| Normal   = "+(int)(100*typeHandler.get(currPokemon.type1, "NORMAL")*typeHandler.get(currPokemon.type2, "NORMAL")));
			outputWriter.println("| Fighting = "+(int)(100*typeHandler.get(currPokemon.type1, "FIGHTING")*typeHandler.get(currPokemon.type2, "FIGHTING")));
			outputWriter.println("| Flying   = "+(int)(100*typeHandler.get(currPokemon.type1, "FLYING")*typeHandler.get(currPokemon.type2, "FLYING")));
			outputWriter.println("| Poison   = "+(int)(100*typeHandler.get(currPokemon.type1, "POISON")*typeHandler.get(currPokemon.type2, "POISON")));
			outputWriter.println("| Ground   = "+(int)(100*typeHandler.get(currPokemon.type1, "GROUND")*typeHandler.get(currPokemon.type2, "GROUND")));
			outputWriter.println("| Rock     = "+(int)(100*typeHandler.get(currPokemon.type1, "ROCK")*typeHandler.get(currPokemon.type2, "ROCK")));
			outputWriter.println("| Bug      = "+(int)(100*typeHandler.get(currPokemon.type1, "BUG")*typeHandler.get(currPokemon.type2, "BUG")));
			outputWriter.println("| Ghost    = "+(int)(100*typeHandler.get(currPokemon.type1, "GHOST")*typeHandler.get(currPokemon.type2, "GHOST")));
			outputWriter.println("| Steel    = "+(int)(100*typeHandler.get(currPokemon.type1, "STEEL")*typeHandler.get(currPokemon.type2, "STEEL")));
			outputWriter.println("| Fire     = "+(int)(100*typeHandler.get(currPokemon.type1, "FIRE")*typeHandler.get(currPokemon.type2, "FIRE")));
			outputWriter.println("| Water    = "+(int)(100*typeHandler.get(currPokemon.type1, "WATER")*typeHandler.get(currPokemon.type2, "WATER")));
			outputWriter.println("| Grass    = "+(int)(100*typeHandler.get(currPokemon.type1, "GRASS")*typeHandler.get(currPokemon.type2, "GRASS")));
			outputWriter.println("| Electric = "+(int)(100*typeHandler.get(currPokemon.type1, "ELECTRIC")*typeHandler.get(currPokemon.type2, "ELECTRIC")));
			outputWriter.println("| Psychic  = "+(int)(100*typeHandler.get(currPokemon.type1, "PSYCHIC")*typeHandler.get(currPokemon.type2, "PSYCHIC")));
			outputWriter.println("| Ice      = "+(int)(100*typeHandler.get(currPokemon.type1, "ICE")*typeHandler.get(currPokemon.type2, "ICE")));
			outputWriter.println("| Dragon   = "+(int)(100*typeHandler.get(currPokemon.type1, "DRAGON")*typeHandler.get(currPokemon.type2, "DRAGON")));
			outputWriter.println("| Dark     = "+(int)(100*typeHandler.get(currPokemon.type1, "DARK")*typeHandler.get(currPokemon.type2, "DARK")));
			outputWriter.println("| Fairy    = "+(int)(100*typeHandler.get(currPokemon.type1, "FAIRY")*typeHandler.get(currPokemon.type2, "FAIRY")));
			outputWriter.println("}}");
			outputWriter.println("");
			outputWriter.println("==Moves==");
			outputWriter.println("");
			outputWriter.println("===By leveling up===");
			outputWriter.println("");
			if (currPokemon.type2.length()>0){
				outputWriter.println("{{MoveLevelStart|"+currPokemon.name+"|"+currPokemon.type1+"|"+currPokemon.type2+"}}");
			}
			else{
				outputWriter.println("{{MoveLevelStart|"+currPokemon.name+"|"+currPokemon.type1+"|"+currPokemon.type1+"}}");
			}
			for (int j = 0; j<currPokemon.movesList.size()-1; j++){
				String moveLevel = currPokemon.movesList.get(j); //odd indices of list have levels
				if (moveLevel.equals(1)){
					moveLevel = "Start";
				}
				j++; //even indices have the name of the move itself
				moveHandling moveHandler = new moveHandling(currPokemon.movesList.get(j));
				outputWriter.println("{{MoveLevel|"+moveLevel+"|"+moveHandler.getMove()+"|"+moveHandler.getType()+"|"+moveHandler.getCategory()+"|"+moveHandler.getPower()+"|"+moveHandler.getAccuracy()+"|"+moveHandler.getPP()+"}}");
				
			}
			if (currPokemon.type2.length()>0){
				outputWriter.println("{{MoveLevelEnd|"+currPokemon.name+"|"+currPokemon.type1+"|"+currPokemon.type2+"}}");
			}
			else{
				outputWriter.println("{{MoveLevelEnd|"+currPokemon.name+"|"+currPokemon.type1+"|"+currPokemon.type1+"}}");
			}
			outputWriter.println("");
			outputWriter.println("===By TM/HM===");
			outputWriter.println("<!-- You can get the entries for this table from the page [[TM and HM list]] -->");
			if (currPokemon.type2.length()>0){
				outputWriter.println("{{MoveTMStart|"+currPokemon.name+"|"+currPokemon.type1+"|"+currPokemon.type2+"}}");
			}
			else{
				outputWriter.println("{{MoveTMStart|"+currPokemon.name+"|"+currPokemon.type1+"|"+currPokemon.type1+"}}");
			}
			//TMHandler tmHandler  = new TMHandler(currPokemon);
			currPokemon.getTMs();
			for (int j = 0; j<currPokemon.tmList.size(); j++) {
				moveHandling tmMoveInfo = new moveHandling(currPokemon.tmList.get(j).name.toUpperCase());
				outputWriter.println("{{MoveTM|TM"+currPokemon.tmList.get(j).num+"|"+tmMoveInfo.getMove()+"|"+tmMoveInfo.getType()+"|"+tmMoveInfo.getCategory()+"|"+tmMoveInfo.getPower()+"|"+tmMoveInfo.getAccuracy()+"|"+tmMoveInfo.getPP()+"}}");
			}
			if (currPokemon.type2.length()>0){
				outputWriter.println("{{MoveTMEnd|"+currPokemon.name+"|"+currPokemon.type1+"|"+currPokemon.type2+"}}");
			}
			else{
				outputWriter.println("{{MoveTMEnd|"+currPokemon.name+"|"+currPokemon.type1+"|"+currPokemon.type1+"}}");
			}
			outputWriter.println("");
			outputWriter.println("===By breeding===");
			outputWriter.println("");
			if (currPokemon.type2.length()>0){
				outputWriter.println("{{MoveBreedStart|"+currPokemon.name+"|"+currPokemon.type1+"|"+currPokemon.type2+"}}");
			}
			else{
				outputWriter.println("{{MoveBreedStart|"+currPokemon.name+"|"+currPokemon.type1+"|"+currPokemon.type1+"}}");
			}
			for (int j = 0; j<currPokemon.eggMovesList.size(); j++){
				moveHandling eggMoveHandler = new moveHandling(currPokemon.eggMovesList.get(j));
				outputWriter.println("{{MoveBreed||"+eggMoveHandler.getMove()+"|"+eggMoveHandler.getType()+"|"+eggMoveHandler.getCategory()+"|"+eggMoveHandler.getPower()+"|"+eggMoveHandler.getAccuracy()+"|"+eggMoveHandler.getPP()+"}}");
				
			}
			if (currPokemon.type2.length()>0){
				outputWriter.println("{{MoveBreedEnd|"+currPokemon.name+"|"+currPokemon.type1+"|"+currPokemon.type2+"}}");
			}
			else{
				outputWriter.println("{{MoveBreedEnd|"+currPokemon.name+"|"+currPokemon.type1+"|"+currPokemon.type1+"}}");
			}
			outputWriter.println("");
			outputWriter.println("==Evolution==");
			outputWriter.println("");
			outputWriter.println("{{Evobox-3");
			outputWriter.println("| type1   = "+currPokemon.type1);
			if (currPokemon.type2.length()>0){
				outputWriter.println("| type2   = "+currPokemon.type2);
			}
			outputWriter.println("| no1     = ");
			outputWriter.println("| name1   = ");
			outputWriter.println("| image1  = ");
			outputWriter.println("| type1-1 = "+currPokemon.type1);
			outputWriter.println("| evo1    = ");
			outputWriter.println("| no2     = ");
			outputWriter.println("| name2   = ");
			outputWriter.println("| image2  = ");
			outputWriter.println("| type1-2 = "+currPokemon.type1);
			outputWriter.println("| type2-2 = "+currPokemon.type1);
			outputWriter.println("| evo2    = ");
			outputWriter.println("| no3     = ");
			outputWriter.println("| name3   = ");
			outputWriter.println("| image3  = ");
			outputWriter.println("| type1-3 = "+currPokemon.type1);
			outputWriter.println("| type2-3 = "+currPokemon.type1);
			outputWriter.println("}}");
			outputWriter.println("");
			outputWriter.println("==Sprites==");
			outputWriter.println("");
			if (currPokemon.type2.length()>0){
				outputWriter.println("{{Sprites|"+currNum+"|type="+currPokemon.type1+"|type2="+currPokemon.type2+"|gender=both}}");
			}
			else{
				outputWriter.println("{{Sprites|"+currNum+"|type="+currPokemon.type1+"|type2="+currPokemon.type2+"|gender=both}}");
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
			outputWriter.println("{{PokemonPrevNextHead | species="+currPokemon.name+" | "+typeblock+" | prev="+prevSpecies+" | prevnum="+prevNum+" | next="+nextSpecies+" | nextnum="+nextNum+" }}");
			outputWriter.println("");
			outputWriter.println("END=============================================="); 
		}
		outputWriter.close();
	}
	
	public void printDexList(Hashtable<Integer, Pokemon> pkmList) throws FileNotFoundException, UnsupportedEncodingException {
		PrintWriter outputWriter = new PrintWriter("src/output/pktext.txt", "UTF-8");
		int startIndex = 722;
		
		//Loop
		for (int i = 0; i<pkmList.size(); i++) {
			int currIndex = i+startIndex;
			Pokemon currPokemon = pkmList.get(currIndex);
		    
			//Type String
			String typeblock;
			if (currPokemon.type2.length()>0){
				typeblock = "type="+currPokemon.type1+" | type2="+currPokemon.type2;
			}
			else{
				typeblock = "type="+currPokemon.type1;
			}
			
			//Dex Number String
			String prevNum;
			if ((currPokemon.dexNum-1)<10){
				prevNum = "00"+(currPokemon.dexNum-1);
			}
			else if ((currPokemon.dexNum-1)<100){
				prevNum = "0"+(currPokemon.dexNum-1);
			}
			else{
				prevNum = ""+(currPokemon.dexNum-1);
			}
			
			String nextNum;
			if ((currPokemon.dexNum+1)<10){
				nextNum = "00"+(currPokemon.dexNum+1);
			}
			else if ((currPokemon.dexNum+1)<100){
				nextNum = "0"+(currPokemon.dexNum+1);
			}
			else{
				nextNum = ""+(currPokemon.dexNum+1);
			}
			
			String currNum;
			if (currPokemon.dexNum<10){
				currNum = "00"+(currPokemon.dexNum);
			}
			else if (currPokemon.dexNum<100){
				currNum = "0"+(currPokemon.dexNum);
			}
			else{
				currNum = ""+(currPokemon.dexNum);
			}
			
    		if (currPokemon.type2.length()>0){
    			outputWriter.println("{{DexListEntry|"+currNum+"|"+currPokemon.name+"|"+currPokemon.type1+"|"+currPokemon.type2+"}}");
    		}
    		else{
    			outputWriter.println("{{DexListEntry|"+currNum+"|"+currPokemon.name+"|"+currPokemon.type1+"}}");
    		}
		}
		
		outputWriter.close();
	}
	
}
