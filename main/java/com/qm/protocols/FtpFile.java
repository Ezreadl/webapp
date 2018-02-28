package com.qm.protocols;

import java.util.Calendar;

public class FtpFile {
	private String name;
	
	private long size;
	
	private boolean isFile;
	
	private Calendar mTime;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public boolean isFile() {
		return isFile;
	}

	public void setFile(boolean isFile) {
		this.isFile = isFile;
	}

	public Calendar getmTime() {
		return mTime;
	}

	public void setmTime(Calendar mTime) {
		this.mTime = mTime;
	}

}
