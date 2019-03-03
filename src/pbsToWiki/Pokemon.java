package pbsToWiki;

import java.util.ArrayList;

public class Pokemon {
	
	public int internalNum = -1;
	public int dexNum = -1;
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
	ArrayList<String> movesList = new ArrayList<String>();
	ArrayList<String> eggMovesList = new ArrayList<String>();
	
	public Pokemon(int num) {
		internalNum = num;
	}

}
