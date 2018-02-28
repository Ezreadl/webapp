package com.qm.protocols;



public class ReadByteBuffer {
	
	private int capacity=5120;
	
	private byte[] buffer=new byte[2048];
	
	private int length=0;
	
	private int startRow=0;
	
	private String charset;
	
	private String lastLine="";
	
	private String firstLine="";
	
	private String[] dataArr=null;
	
	private int dataLengthTemp=0;
	
	public ReadByteBuffer(String charset){
		this.charset=charset;
	}
	
	public void addByte(byte b){
		if(this.length>=this.buffer.length){
			addCapacity(this.capacity);
		}
		buffer[this.length++]=b;
	}
	
	public void addByteArray(byte[] array){
		addByteArray(array,0,array.length);
	}
	//加入数组
	public void addByteArray(byte[] array,int pos,int len){
		if(len>0){
			if(this.buffer.length-this.length<len){
				int addLen=this.capacity>len?this.capacity:len+this.capacity;
				addCapacity(addLen);
			}
			System.arraycopy(array,pos,this.buffer,this.length,len);
			this.length=this.length+len;
		}
	}
	//扩容
	private void addCapacity(int addLen){
		if(addLen>0){
			byte[] newBuffer=new byte[addLen+this.length];
			System.arraycopy(this.buffer,0,newBuffer,0,this.length);
			this.buffer=newBuffer;
		}
	}
	//返回
	public String getEndSubstring(int endLength){
		int a=endLength%6;//确保长度为6的倍数，防止截断编码错误
		if(a!=0){
			endLength=endLength+(6-a);
		}
		int startPos=this.length-endLength;
		if(startPos<0){
			startPos=0;
		}
		byte[] temp=new byte[this.length-startPos];
		System.arraycopy(this.buffer,startPos,temp,0,temp.length);
		try {
			return new String(temp,charset).trim();
		} catch (Exception e) {
			return new String(temp).trim();
		}
	}
	public int length(){
		return this.length;
	}
	
	public static String filterTerminalControlFea(String line){
		try {
			if(line==null || "".equals(line)){
				return line;
			}
			line=line.replace((char)0+"","");
			line=line.replaceAll("(?i)\\[\\d+d|","");
			return line;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return line;
	}
	
	//获取数据行数
	public int getRowCount(){
		splitData();
		return dataArr.length;
	}
	
	//获取数据数组
	public String[] getDataArr(){
		splitData();
		return dataArr;
	}
	
	//获取最后一行不为空的数据
	public String getLastLine(){
		splitData();
	    return lastLine;
	}
	
	//获取最后读取的数据
	public String[] getLastDataArr(){
		splitData();
//		if(startRow==0){
//			return dataArr;
//		}else{
//			int len=getRowCount()-startRow;
//			String[] lastDataArr=new String[len];
//			System.arraycopy(this.dataArr,startRow,lastDataArr,0,len);
//			return lastDataArr;
//		}
		//返回最后10行
		int len=getRowCount();
		if(len>10){
			len=10;
		}
		int start=getRowCount()-len;
		if(start<0){
			start=0;
		}
		String[] lastDataArr=new String[len];
		System.arraycopy(this.dataArr,start,lastDataArr,0,len);
		return lastDataArr;
	}
	
	public static String getLastLine(String[] arr){
		String lastLine="";
		if(arr!=null){
			for(int i=arr.length-1;i>=0;i--){
		    	//倒数不为null或空的行
		    	if(!"".equals(arr[i].trim())){
		    		lastLine=arr[i].trim();
		    		break;
		    	}
	    	}
		}
		return lastLine;
	}
	
	//获取第一行不为空的数据
	public String getFirstLine(){
		splitData();
	    return firstLine;
	}
	
	private void splitData(){
		//如果长度变化，重新划分
		if(dataLengthTemp!=length){
			dataLengthTemp=length;
			String s=toString();
			if(s!=null && !"".equals(s)){
				dataArr=s.split("[\\r\\n]+");
		    	for(int i=dataArr.length-1;i>=0;i--){
			    	//倒数不为null或空的行
			    	if(!"".equals(dataArr[i].trim())){
			    		lastLine=dataArr[i].trim();
			    		break;
			    	}
		    	}
		    	for(int i=0;i<dataArr.length;i++){
			    	//顺数不为null或空的行
			    	if(!"".equals(dataArr[i].trim())){
			    		firstLine=dataArr[i].trim();
			    		break;
			    	}
		    	}
			}else{
				dataArr=new String[0];
				lastLine="";
				firstLine="";
			}
		}
	}
	//重新定位数据开始行,用于再次加入数据判断
	public void reSetStartRow(){
		this.startRow=this.getRowCount();
	}

	public int getStartRow() {
		return startRow;
	}

	@Override
	public String toString() {
		try {
			String ret=new String(buffer,0,length,charset);
			ret=filterTerminalControlFea(ret);
			return ret.trim();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new String(buffer,0,length).replace((char)0+"", "").trim();
	}
	
	public static void main(String[] args) {
		int endLength=14;
		int a=endLength%6;
		if(a!=0){
			endLength=endLength+(6-a);
		}
		System.out.println(endLength);
	}
}
