package pbsToWiki;
import java.awt.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Scanner;

public class PBSWikiConverter {

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		PkmDataReader dataReader = new PkmDataReader();
		Hashtable<Integer, Pokemon> pkmList = dataReader.readData();
		Hashtable<String, Integer> nameList = dataReader.nameLookup;
	    
	    ArticlePrinter dataPrinter = new ArticlePrinter();
	    dataPrinter.printData(pkmList,nameList);
	    //dataPrinter.printDexList(pkmList);

		System.out.println("done");
	}

}
