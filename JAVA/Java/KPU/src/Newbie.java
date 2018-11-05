import java.util.ArrayList;

public class Newbie {
	final private static int noodle = 5000;
	final private static int pork = 4000;
	final private static int cuprice = 3000;
	final private static int ramun = 2000;
	
	public static void main(String args[]) {
		int charge=50000;
		int count=1;
		ArrayList<String> result=new ArrayList<String>();
		
		for(int i=1;i<11;i++) {
			charge=charge-(i*noodle);
			for( int j=1;j<13;j++) {
				charge=charge-(j*pork);
				if(charge<0) {
					charge=charge+(j*pork);
					break;
				}
				for(int k=1;k<17;k++) {
					charge=charge-(k*cuprice);
					if(charge<0) {
						charge=charge+(k*cuprice);
						break;
					}
					for(int l=1;l<26;l++) {
						charge=charge-(l*ramun);
						if(charge<0) {
							charge=charge+(l*ramun);
							break;
						}
						else if(charge==0) {
							charge=charge+(l*ramun);
							result.add(count+". ÇØ¹°¾ß³¢¿ìµ¿("+i+"°³), ÄÅ¹ä("+k+"°³), ¶ó¸é("+l+"°³), µ·±î½º("+j+"°³)");
							count++;
							break;
						}
						charge=charge+(l*ramun);
					}
					charge=charge+(k*cuprice);
				}
				charge=charge+(j*pork);
			}
			charge=charge+(i*noodle);
		}
		
		for(int i=0;i<result.size();i++)
			System.out.println(result.get(i));
		
	}
}
