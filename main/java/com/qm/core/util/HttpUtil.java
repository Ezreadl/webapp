package com.qm.core.util;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import net.sf.jmimemagic.Magic;
import net.sf.jmimemagic.MagicMatch;

public class HttpUtil {
	
	public static void main(String[] args) throws Exception{  
		String saveName="aaaaaaaa";
		Map<String,String> params=new HashMap<String,String>();
		params.put("wd",saveName);
		String ret=HttpUtil.post("https://www.baidu.com/s?rsv_spt=1&rsv_iqid=0xc3e25b27000181aa&issp=1&f=8&rsv_bp=1&rsv_idx=2&ie=utf-8&rqlang=cn&tn=baiduhome_pg&rsv_enter=0&oq=aaa&rsv_t=dda4G94zhhkTcGaTcEeg12Jih9oJSaX%2BT2gPL8ZqwNt%2Fws7RLBBN4mJ8XH5vKxGyEjj6&inputT=1673&rsv_sug3=9&rsv_pq=d5302f650001cae8&rsv_sug1=7&rsv_sug7=100&rsv_sug4=1933", params);
		System.out.println(ret);
		
    }
	
	public static String post(String urlAddress,Map<String,String> param) throws Exception{
		URL url = new URL(urlAddress);
		HttpURLConnection conn = ( HttpURLConnection )url.openConnection();
		conn.setRequestMethod( "POST" );
		conn.setDoOutput( true );
		conn.setDoInput( true );
		PrintWriter writer = new PrintWriter( conn.getOutputStream());
		StringBuilder sb=new StringBuilder();
		for(String key:param.keySet()){
			sb.append(key+"="+param.get(key)+"&");
		}
		if(sb.length()>0){
			sb.delete(sb.length()-1,sb.length()-1);
		}
		writer.print(sb.toString());
		writer.flush();
     	writer.close();
     	StringBuilder retSb=new StringBuilder();
     	BufferedReader read = new BufferedReader( new InputStreamReader( conn.getInputStream() ) );
     	String line=read.readLine();
     	while(line!=null){
     		retSb.append(line+"\r\n");
     		line=read.readLine();
     	}
     	read.close();
     	conn.disconnect();
     	return retSb.toString();
	}
	
	public static String formUpload(String urlStr, Map<String, String> paramMap, Map<String, String> fileMap) {  
        String res = "";  
        HttpURLConnection conn = null;  
        String BOUNDARY = "---------------------------123821742118716"; //boundary就是request头和上传文件内容的分隔符    
        try {  
            URL url = new URL(urlStr);  
            conn = (HttpURLConnection) url.openConnection();  
            conn.setConnectTimeout(5000);  
            conn.setReadTimeout(30000);  
            conn.setDoOutput(true);  
            conn.setDoInput(true);  
            conn.setUseCaches(false);  
            conn.setRequestMethod("POST");  
            conn.setRequestProperty("Connection", "Keep-Alive");  
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.6)");  
            conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);  
  
            OutputStream out = new DataOutputStream(conn.getOutputStream());  
            // text    
            if (paramMap != null) {  
                StringBuffer strBuf = new StringBuffer();  
                Iterator<Map.Entry<String, String>> iter = paramMap.entrySet().iterator();  
                while (iter.hasNext()) {  
                    Map.Entry<String, String> entry = iter.next();  
                    String inputName = (String) entry.getKey();  
                    String inputValue = (String) entry.getValue();  
                    if (inputValue == null) {  
                        continue;  
                    }  
                    strBuf.append("\r\n").append("--").append(BOUNDARY).append("\r\n");  
                    strBuf.append("Content-Disposition: form-data; name=\"" + inputName + "\"\r\n\r\n");  
                    strBuf.append(inputValue);  
                }  
                out.write(strBuf.toString().getBytes());  
            }  
  
            // file    
            if (fileMap != null) {  
                Iterator<Map.Entry<String, String>> iter = fileMap.entrySet().iterator();  
                while (iter.hasNext()) {  
                    Map.Entry<String, String> entry = iter.next();  
                    String inputName = (String) entry.getKey();  
                    String inputValue = (String) entry.getValue();  
                    if (inputValue == null) {  
                        continue;  
                    }  
                    File file = new File(inputValue);  
                    String filename = file.getName();  
                    MagicMatch match = Magic.getMagicMatch(file, false, true);  
                    String contentType = match.getMimeType();  
  
                    StringBuffer strBuf = new StringBuffer();  
                    strBuf.append("\r\n").append("--").append(BOUNDARY).append("\r\n");  
                    strBuf.append("Content-Disposition: form-data; name=\"" + inputName + "\"; filename=\"" + filename + "\"\r\n");  
                    strBuf.append("Content-Type:" + contentType + "\r\n\r\n");  
  
                    out.write(strBuf.toString().getBytes());  
  
                    DataInputStream in = new DataInputStream(new FileInputStream(file));  
                    int bytes = 0;  
                    byte[] bufferOut = new byte[1024];  
                    while ((bytes = in.read(bufferOut)) != -1) {  
                        out.write(bufferOut, 0, bytes);  
                    }  
                    in.close();  
                }  
            }  
  
            byte[] endData = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();  
            out.write(endData);  
            out.flush();  
            out.close();  
  
            // 读取返回数据    
            StringBuffer strBuf = new StringBuffer();  
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));  
            String line = null;  
            while ((line = reader.readLine()) != null) {  
                strBuf.append(line).append("\n");  
            }  
            res = strBuf.toString();  
            reader.close();  
            reader = null;  
        } catch (Exception e) {  
            System.out.println("发送POST请求出错。" + urlStr);  
            e.printStackTrace();  
        } finally {  
            if (conn != null) {  
                conn.disconnect();  
                conn = null;  
            }  
        }  
        return res;  
    }  
}
