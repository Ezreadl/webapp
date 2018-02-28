package com.qm.core.base;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
public class UrlEncoder
{
  public static RequestResult getOrderList(String paramString1, String paramString2, String paramString3)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("userName", paramString1);
    localHashMap.put("password", paramString2);
    RequestResult localRequestResult = requestByPost("GetOrderList", localHashMap, paramString3);
    localRequestResult.isSuccess().booleanValue();
    return localRequestResult;
  }
  
  public static RequestResult login(String paramString1, String paramString2, String paramString3)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("userName", paramString1);
    localHashMap.put("password", paramString2);
    return requestByPost("doLogin", localHashMap, paramString3);
  }
  
  private static RequestResult requestByPost(String paramString1, HashMap paramHashMap, String paramString2)
  {
    RequestResult localRequestResult = new RequestResult();
    HttpPost localHttpPost = new HttpPost(paramString2 + "/common/" + paramString1 + ".do");
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(new BasicNameValuePair("userName", paramHashMap.get("userName").toString()));
    localArrayList.add(new BasicNameValuePair("userPwd", paramHashMap.get("password").toString()));
    try
    {
      localHttpPost.getParams().setParameter("http.connection.timeout", Integer.valueOf(20000));
      localHttpPost.setEntity(new UrlEncodedFormEntity(localArrayList, "UTF-8"));
      HttpResponse localHttpResponse = new DefaultHttpClient().execute(localHttpPost);
      if (localHttpResponse.getStatusLine().getStatusCode() == 200)
      {
        localRequestResult.setSuccess(Boolean.valueOf(true));
        localRequestResult.setResult(EntityUtils.toString(localHttpResponse.getEntity(), "UTF-8"));
      }
      return localRequestResult;
    }
    catch (ClientProtocolException localClientProtocolException)
    {
      localClientProtocolException.printStackTrace();
      localRequestResult.setResult(localClientProtocolException.getMessage());
      return localRequestResult;
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
      localRequestResult.setResult(localIOException.getMessage());
    }
    return localRequestResult;
  }
}
