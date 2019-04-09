package model;

public class Record {
	long pos;
	
	Field[] fields;
	
	byte[] content;
	
	public Record() {
		pos = 0;
		fields = new Field[13];
		this.content = new byte[2];
	}
	
	public Record(String[] tokens) {
		fields = new Field[9];
	}
}
