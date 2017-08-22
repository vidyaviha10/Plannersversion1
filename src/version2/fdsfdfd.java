package version2;

import java.util.ArrayList;
import java.util.Random;

public class fdsfdfd {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	      ArrayList<Integer> tobe1 = new ArrayList();
	        
	        for(int l = 0;l<15;l++)
	        {
	        	
	        	 Random r = new Random();
	        	int number =  r.nextInt((9 - 0) + 1) + 0;
	         	tobe1.add(number);
	         	System.out.println(number);
	        }
/*		StringBuilder info = new StringBuilder(10);
		for(int i =0;i<10;i++)
		{
			info.append("1");
		}
		info.insert(7, "a");
System.out.println(info.toString());*/
	}

}
