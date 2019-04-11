package model;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class dbload {
	static boolean printing = false;
	static String csvFilename = "";
	static int pageSize = 0;

	public static void loadFile() {
		BufferedReader br = null;
		DataOutputStream ds = null;
		try {
			br = new BufferedReader(new FileReader(csvFilename));		
			ds = new DataOutputStream(new FileOutputStream("heap."+pageSize));
			String columns[]; //contains column names
			int num_cols = br.readLine().split(",").length;
			//skip first line
			String firstLine = "";
			int page_byte_cnt = 0;
			int page_cnt = 1;
			int record_cnt = 0;
			while ((firstLine = br.readLine()) != null){ //loop will run from 2nd line
				record_cnt++;
				int record_byte_cnt = 0;
				String[] tokens = firstLine.split(",");
				if(tokens.length == num_cols) {
					for (int i=0;i<num_cols;i++) {
						if(i==0) {
							record_byte_cnt = record_byte_cnt + 4;
							ds.writeInt((Integer.parseInt(tokens[i])));
						} else if(i == 1) {
							record_byte_cnt = record_byte_cnt + tokens[i].length();
							ds.writeBytes(tokens[i]);
						} else if(i == 2) {
							record_byte_cnt = record_byte_cnt + tokens[i].length();
							ds.writeBytes(tokens[i]);
						} else if(i == 3) {
							record_byte_cnt = record_byte_cnt + 8;
							ds.writeLong(Long.parseLong(tokens[i]));
						} else if(i == 4) {
							record_byte_cnt = record_byte_cnt + tokens[i].length();
							ds.writeBytes(tokens[i]);
						} else if(i == 5) {
							if (tokens[i].length() == 0) {
								tokens[i] ="########";
							}
							record_byte_cnt = record_byte_cnt + tokens[i].length();
							ds.writeBytes(tokens[i]);
						} else if(i == 6) {
							record_byte_cnt = record_byte_cnt + tokens[i].length();
							ds.writeBytes(tokens[i]);
						} else if(i == 7) {
							record_byte_cnt = record_byte_cnt + 4;
							ds.writeInt((Integer.parseInt(tokens[i])));
						} else if(i == 8) {
							record_byte_cnt = record_byte_cnt + tokens[i].length();
							ds.writeBytes(tokens[i]);
						} else if(i == 9) {
							record_byte_cnt = record_byte_cnt + tokens[i].length();
							ds.writeBytes(tokens[i]);
						} else if(i == 10) {
							if (tokens[i].length() == 0) {
								tokens[i] ="########";
							}
							record_byte_cnt = record_byte_cnt + tokens[i].length();
							ds.writeBytes(tokens[i]);
						} else if(i == 11) { 
							record_byte_cnt = record_byte_cnt +4;
							ds.writeInt((Integer.parseInt(tokens[i])));
						} else if(i == 12) {
							record_byte_cnt = record_byte_cnt + tokens[i].length();
							ds.writeBytes(tokens[i]);
						}
						
					}
				}
				if((page_byte_cnt+record_byte_cnt)> 4096) {
					page_byte_cnt = 0;
					page_cnt ++;
					
				}else {
					page_byte_cnt = page_byte_cnt+record_byte_cnt;
				}
				
			}
			System.out.println(record_cnt+" of records loaded.");
			System.out.println(page_cnt+" of pages used to load");
		} catch (IOException e) {
			System.out.println("Exception when trying to read file ");
			e.printStackTrace();		
		} finally {
			try {
				if (br != null) br.close();
				if (ds != null) ds.close();
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
			startTime = System.currentTimeMillis() - startTime;
			System.out.println(startTime+ "ms has been used to load");
		}
	}

}
