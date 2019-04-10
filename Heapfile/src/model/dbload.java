package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class dbload {
	static boolean printing = false;
	static String csvFilename = "";
	static int pageSize = 0;

	public static void loadFile() {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(csvFilename));
			
			String columns[]; //contains column names
			int num_cols = br.readLine().split(",").length;
			//skip first line
			String firstLine = "";
			int byte_cnt = 0;
			while ((firstLine = br.readLine()) != null){ //loop will run from 2nd line
				int record_byte_cnt = 0;
				String[] tokens = firstLine.split(",");
				if(tokens.length == num_cols) {
					for (int i=0;i<num_cols;i++) {
						if(i==0) {
							
						}
					}
				}
			}
			System.out.println(br.readLine());
			System.out.println(num_cols);
		} catch (IOException e) {
			System.out.println("Exception when trying to read file ");
			e.printStackTrace();		
		} finally {
			try {
				if (br != null) br.close();
			} catch (IOException ex) {
				System.out.println("Exception when trying to close BufferedReader:");
				ex.printStackTrace();
			}
		}
	}
	
	public void loadRecords() {
		
	}
	
	// check if pagesize is a valid integer
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
			if (args.length < 3) {
				System.exit(-1);
			} else if (args[i].compareTo("-p") ==0 ) {
				printing = true;
			} else if( i == 1 && pageSize == 0){
				if (isInteger(args[i])) {
				pageSize = Integer.parseInt(args[i]);
				}
			} else if (i == 2 && csvFilename.isEmpty()) {
				csvFilename = args[i];
			}
		}
		if (csvFilename.isEmpty()) {
			System.err.println("ERROR: No argument found to specify csv file");
			aborting =true;
		}
		if (pageSize==0) {
			System.err.println("ERROR: No argument found to specify page size");
			aborting =true;
		}
		if(!aborting) {
			loadFile();
		}
	}

}
