package model;

public class Record {
	long pos;
	
	String[] record;
	
	byte[] content;
	
	public Record() {
		pos = 0;
		record = new String[13];
		this.content = new byte[2];
	}
	
	public Record(String[] tokens) {
		int record_byte_cnt = 0;
		int num_cols = tokens.length;
		if(tokens.length == num_cols) {
			for (int i=0;i<num_cols;i++) {
				if(i==0) {
					record_byte_cnt = record_byte_cnt + 4;
				} else if(i == 1) {
					record_byte_cnt = record_byte_cnt + 8;
				} else if(i == 2) {
					record_byte_cnt = record_byte_cnt + 8;
				} else if(i == 3) {
					record_byte_cnt = record_byte_cnt + 8;
				} else if(i == 4) {
					record_byte_cnt = record_byte_cnt + tokens[i].length() +4;
				} else if(i == 5) {
					if (tokens[i].length() == 0) {
						tokens[i] ="########";
					}
					record_byte_cnt = record_byte_cnt + tokens[i].length() +4;
				} else if(i == 6) {
					record_byte_cnt = record_byte_cnt + tokens[i].length() +4;
				} else if(i == 7) {
					record_byte_cnt = record_byte_cnt + 4;
				} else if(i == 8) {
					record_byte_cnt = record_byte_cnt + tokens[i].length() +4;
				} else if(i == 9) {
					record_byte_cnt = record_byte_cnt + tokens[i].length() +4;
				} else if(i == 10) {
					if (tokens[i].length() == 0) {
						tokens[i] ="########";
					}
					record_byte_cnt = record_byte_cnt + tokens[i].length() +4;
				} else if(i == 11) { 
					record_byte_cnt = record_byte_cnt +4;
				} else if(i == 12) {
					record_byte_cnt = record_byte_cnt + tokens[i].length() +4;
				}
				record[i] = tokens[i];
			}
		}
	}
}
