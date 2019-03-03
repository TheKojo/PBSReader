package pbsToWiki;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class moveHandling  {
	public String move ="";
	public String power ="";
	public String category ="";
	public String type ="";
	public String accuracy ="";
	public String pp ="";
	public File inputFile = new File("src/input/moves.txt");
	capsHandling capsHandler = new capsHandling();
	
	
	public moveHandling(String initMove) throws FileNotFoundException, UnsupportedEncodingException{
		move = initMove;
		Scanner sc = new Scanner(inputFile,"UTF-8");
		while (sc.hasNextLine()) {
            String i = sc.nextLine();
            String test = i.substring(i.indexOf(",")+1);
            if ((i.substring(i.indexOf(",")+1)).startsWith(move)){
            	ArrayList<String> moveInfo = new ArrayList<String>(Arrays.asList(i.split(",")));
            	move = moveInfo.get(2);
            	power = moveInfo.get(4);
            	type = capsHandler.properCase(moveInfo.get(5));
            	category = moveInfo.get(6);
            	accuracy = moveInfo.get(7);
            	pp = moveInfo.get(8);
            }            
        }
	}
	
	public String getMove(){
		return move;
	}
	public String getPower(){
		if (power.equals("0")){
			return "&mdash;";
		}
		else{
			return power;
		}
	}
	public String getType(){
		return type;
	}
	public String getCategory(){
		return category;
	}
	public String getAccuracy(){
		return accuracy;
	}
	public String getPP(){
		return pp;
	}
}
