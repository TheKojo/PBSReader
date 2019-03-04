package pbsToWiki;

public class TM {
	
	public String num;
	public String name;
	
	public void TM() {
		
	}
	
	public void setNum(int val) {
		if (val < 10) {
			num = "0"+val;
		}
		else {
			num = ""+val;
		}
	}

}
