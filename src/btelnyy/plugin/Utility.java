package btelnyy.plugin;

public class Utility {
	   public static int ArrayCounter(Object[] o)   
	   {  
		   int count = 0;
	        for(@SuppressWarnings("unused") Object i:o)
	        {
	            count++;
	        }
	        return count;
	   }
}
