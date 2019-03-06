package pbsToWiki;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

public class Pokemon {
	
	public int internalNum = -1;
	public int dexNum = -1;
	public String internalName = "";
	public String name = "";
	public String type1 = "";
	public String type2 = "";
	public int hp = -1;
	public int attack = -1;
	public int defense = -1;
	public int spAttack = -1;
	public int spDefense = -1;
	public int speed = -1;
	public int evolutionId = -1;
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
	String shape = "";
	ArrayList<String> movesList = new ArrayList<String>();
	ArrayList<String> eggMovesList = new ArrayList<String>();
	ArrayList<TM> tmList = new ArrayList<TM>();
	ArrayList<Evolution> evolutions = new ArrayList<Evolution>();
	String preevo = "";
	
	public File pkmFile = new File("src/input/pokemon.txt");
	public File tmFile = new File("src/input/tm.txt");
	capsHandling capsHandler = new capsHandling();
	
	public Pokemon(int num) {
		internalNum = num;
	}
	
	public void getTMs() {
		int num = 0;
		try {
			Scanner sc = new Scanner(tmFile,"UTF-8");
			while (sc.hasNextLine()) {
				String i = sc.nextLine();
				
				//Ignore comments
				if (i.startsWith("[")){
					num += 1;
					String move = i.substring(i.lastIndexOf("[")+1,i.length()-1);
					i = sc.nextLine();
					if (i.contains(name.toUpperCase())){
						TM tm = new TM();
						tm.setNum(num);
						tm.name = capsHandler.properCase(move);
						tmList.add(tm);
					}
				}
			}
		}
	    catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }
	}
	
	public ArrayList<Pokemon> getEvolutionLine(Hashtable<Integer, Pokemon> pokemonList, Hashtable<String, Integer> nameLookup) {
		
		//prevos
		ArrayList<Pokemon> family = getPreevos(pokemonList,nameLookup,true);
		
		//self
		family.add(this);

		//evos
		family.addAll(getEvos(pokemonList,nameLookup));
				
		return family;
	}
	
	public ArrayList<Pokemon> getEvos(Hashtable<Integer, Pokemon> pokemonList, Hashtable<String, Integer> nameLookup){
		ArrayList<Pokemon> evos = new ArrayList<Pokemon>();
		if (evolutions.size() > 0 && nameLookup.containsKey(evolutions.get(0).name)) {
			Pokemon currEvo = pokemonList.get(nameLookup.get(evolutions.get(0).name));
			evos.add(currEvo);
			if (nameLookup.containsKey(currEvo.internalName)){
				Integer evoID = nameLookup.get(currEvo.internalName);
				//Pokemon evoPkm = pokemonList.get(evoID);
				evos.addAll(currEvo.getEvos(pokemonList,nameLookup));
			}
		}
		return evos;
	}
	
	public ArrayList<Pokemon> getPreevos(Hashtable<Integer, Pokemon> pokemonList, Hashtable<String, Integer> nameLookup, boolean exclude) {
		ArrayList<Pokemon> preevos = new ArrayList<Pokemon>();
		 
		//Pokemon currPreevo =  pokemonList.get(nameLookup.get(preevo));
		if (preevo.length()==0) {//(!nameLookup.containsKey(currPreevo)){
			if (!exclude){
				preevos.add(this);
			}
			
		}
		else {
			Integer preevoID = nameLookup.get(preevo.toUpperCase());
			Pokemon preevoPkm = pokemonList.get(preevoID);
			preevos = preevoPkm.getPreevos(pokemonList, nameLookup, false);
			if (!exclude){
				preevos.add(this);
			}
		}

	
		return preevos;
	}

}
