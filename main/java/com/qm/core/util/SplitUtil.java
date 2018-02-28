package com.qm.core.util;

public class SplitUtil {
	
//	private int position1;
	
	private int length;
	
	private int posStart;
	
	private int posEnd;
	
	private int index;
	
	private String[] arr=new String[100];
	
	public SplitUtil(){
		arr=new String[100];
	}
	
	public SplitUtil(int initLength){
		arr=new String[initLength];
	}
	
	/*public String[] splitChar(String line, char splitChar) {
		posStart=0;
		index=0;
		for(int i=0;i<line.length();i++){
			if(line.charAt(i)==splitChar){
				if(index>arr.length-1){
					addCapacity(20);
				}
				posEnd=i;
				arr[index++]=line.substring(posStart, posEnd);
				posStart=i+1;
			}
		}
		arr[index++]=line.substring(posStart);
		return arr;
	}*/
	
	public String[] splitText(String line, String splitText) {
		posEnd = line.indexOf(splitText);
		length = splitText.length();
		posStart = -length;
//		posEnd = position1;
		index=0;
		while (posEnd >= 0) {
			posEnd = line.indexOf(splitText, posStart + length);
			if (posEnd == -1) {
				break;
			}
			if(index>arr.length-1){
				addCapacity(index-arr.length+2);
			}
			arr[index++]=line.substring(posStart + length, posEnd);
			posStart = posEnd;
		}
		arr[index++]=line.substring(posStart + length);
		return arr;
	}
	
	//扩容
	protected void addCapacity(int addLen){
		if(addLen>0){
			String[] newBuffer=new String[addLen+arr.length];
			System.arraycopy(this.arr,0,newBuffer,0,arr.length);
			this.arr=newBuffer;
		}
	}
	
	public int getLength(){
		return index;
	}
}
