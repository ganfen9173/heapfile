package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class dbload {
	static boolean printing = false;
	static String csvFilename = "";
	static int pageSize = 0;

	public static void main(String[] args) {
		Boolean aborting =false;
		// parse args
		for (int i =0; i< args.length && !aborting; i++) {
			//
			if (args.length < 3) {
				System.exit(-1);
			}else if (args[i].compareTo("-p") ==0 ) {
				printing = true;
/*				BufferedReader br = null;
				try {
					br = new BufferedReader(new FileReader("sample.csv"));
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
				}*/
			} else if( i == 1 && pageSize == 0){
				pageSize = Integer.parseInt(args[i]);
			} else if (i == 2  && csvFilename.isEmpty()) {
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
			
		}
	}

}
