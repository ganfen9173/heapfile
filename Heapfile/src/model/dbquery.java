package model;

public class dbquery {

	static String searchString = "";
	static int pageSize = 0;
	
	public static void searchFile() {
		
	}
	
	public static boolean isInteger(String s)
	   {
	      boolean isValidInt = false;
	      try
	      {
	         Integer.parseInt(s);
	         isValidInt = true;
	      }
	      catch (NumberFormatException e)
	      {
	    	 System.err.println("Exception when trying to canvert page size");
	         e.printStackTrace();
	      }
	      return isValidInt;
	   }
	
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		Boolean aborting =false;
		// parse args
		for (int i =0; i< args.length && !aborting; i++) {
			//
			if (args.length < 2) {
				System.exit(-1);
			} else if (i == 0 && searchString.isEmpty()) {
				searchString = args[i];
			}else if( i == 1 && pageSize == 0){
				if (isInteger(args[i])) {
				pageSize = Integer.parseInt(args[i]);
				}
			}
		}
		if (searchString.isEmpty()) {
			System.err.println("ERROR: No argument found to specify text search");
			aborting =true;
		}
		if (pageSize==0) {
			System.err.println("ERROR: No argument found to specify page size");
			aborting =true;
		}
		if(!aborting) {
			searchFile();
		}
	}

}
