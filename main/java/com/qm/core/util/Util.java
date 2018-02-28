package com.qm.core.util;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.File;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

public class Util
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  
  public static String getRealPath(String path)
  {
    return new File(Util.class.getClassLoader().getResource("").getFile()).getParentFile().getParentFile().getPath() + path;
  }
  
  public static String getBasePath(HttpServletRequest request)
  {
    String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    return path;
  }
  
  public static boolean isEmpty(Object o)
  {
    return toString(o).equals("");
  }
  
  public static String toString(Object o)
  {
    return toString(o, "");
  }
  
  public static String toString(Object o, String defaultValue)
  {
    String str = "";
    if (o == null) {
      str = defaultValue;
    } else {
      str = o.toString();
    }
    return str;
  }
  
  public static Integer toInt(Object o)
  {
    return toInt(o, 0);
  }
  
  public static Integer toInt(Object o, int defaultValue)
  {
    int i = defaultValue;
    try
    {
      if (o != null) {
        if (o.equals("false")) {
          i = 0;
        } else if (o.equals("true")) {
          i = 1;
        } else {
          i = Integer.parseInt(o.toString());
        }
      }
    }
    catch (NumberFormatException e) {}
    return Integer.valueOf(i);
  }
  
  public static Double toDbl(Object o)
  {
    return toDbl(o, Double.valueOf(0.0D));
  }
  
  public static Double toDbl(Object o, Double defaultValue)
  {
    Double i = defaultValue;
    try
    {
      if ((o != null) && (!toString(o).equals(""))) {
        i = Double.valueOf(Double.parseDouble(o.toString()));
      }
    }
    catch (NumberFormatException e) {}
    return i;
  }
  
  public static Float toFloat(Object o)
  {
    return toFloat(o, Float.valueOf(0.0F));
  }
  
  public static Float toFloat(Object o, Float defaultValue)
  {
    Float i = defaultValue;
    try
    {
      if ((o != null) && (!toString(o).equals(""))) {
        i = Float.valueOf(Float.parseFloat(o.toString()));
      }
    }
    catch (NumberFormatException e) {}
    return i;
  }
  
  public static Short toShort(Object o)
  {
    return toShort(o, Short.valueOf(Short.parseShort("0")));
  }
  
  public static Short toShort(Object o, Short defaultValue)
  {
    Short i = defaultValue;
    try
    {
      if (o != null) {
        i = Short.valueOf(Short.parseShort(o.toString()));
      }
    }
    catch (NumberFormatException e) {}
    return i;
  }
  
  public static Long toLong(Object o)
  {
    return toLong(o, Long.valueOf(Long.parseLong("0")));
  }
  
  public static Long toLong(Object o, Long defaultValue)
  {
    Long i = defaultValue;
    try
    {
      if (o != null) {
        i = Long.valueOf(Long.parseLong(o.toString()));
      }
    }
    catch (NumberFormatException e) {}
    return i;
  }
  
  public static float divide(Object a, Object b, int scale)
  {
    try
    {
      BigDecimal b1 = new BigDecimal(a.toString());
      BigDecimal b2 = new BigDecimal(b.toString());
      return b1.divide(b2, scale, 4).floatValue();
    }
    catch (Exception e) {}
    return 0.0F;
  }
  
  public static String toDateStr(Timestamp o)
  {
    return toDateStr(o, "yyyy-MM-dd HH:mm:ss");
  }
  
  public static String toShortDateStr(Timestamp o)
  {
    return toDateStr(o, "yyyy-MM-dd");
  }
  
  public static String toDateStr(Timestamp ts, String formatStr)
  {
    String str = "";
    SimpleDateFormat formatter = new SimpleDateFormat(formatStr);
    formatter.setTimeZone(TimeZone.getTimeZone("GMT+8"));
    ParsePosition pos = new ParsePosition(0);
    Date strtodate = formatter.parse(toString(ts), pos);
    if (strtodate != null) {
      str = formatter.format(strtodate);
    }
    return str;
  }
  
  public static boolean toBoolean(Object o)
  {
    return toBoolean(o, false);
  }
  
  public static boolean toBoolean(Object o, boolean bool)
  {
    try
    {
      if ((toString(o).equals("1")) || (toString(o).equals("true"))) {
        return true;
      }
      return false;
    }
    catch (NumberFormatException e) {}
    return bool;
  }
  
  public static String substring(String str, int length)
  {
    return substring(str, length, "");
  }
  
  public static String substring(String str, int length, String suffix)
  {
    return substring(str, 0, length, suffix);
  }
  
  public static String subLaststring(String str, int length)
  {
    str = toString(str);
    int beginIndex = str.length() - length;
    return substring(str, beginIndex, length, "");
  }
  
  public static String substring(String str, int beginIndex, int length, String suffix)
  {
    str = toString(str);
    if ((str.length() >= length) && (length > 0)) {
      if (beginIndex == 0) {
        str = str.substring(beginIndex, length - suffix.length()) + suffix;
      } else {
        str = str.substring(beginIndex);
      }
    }
    return str;
  }
  
  public static String keepLen(String str, int len)
  {
    return keepLen(str, len, "��");
  }
  
  public static String keepLen(String str, int len, String prefix)
  {
    str = toString(str);
    int fill = len - str.length();
    if (fill <= 0) {
      str = substring(str, len);
    } else {
      for (int i = 0; i < fill; i++) {
        str = prefix + str;
      }
    }
    return str;
  }
  
  public static int getTimestamp_int()
  {
    return toInt(Long.valueOf(new Timestamp(new Date().getTime()).getTime())).intValue();
  }
  
  public static Timestamp getTimestamp()
  {
    return new Timestamp(new Date().getTime());
  }
  
  public static String trimIn(String str, String begin, String end)
  {
    if (str == null) {
      return str;
    }
    int beginIndex = str.indexOf(begin);
    int endIndex = str.indexOf(end);
    if ((beginIndex >= 0) && (endIndex >= 0) && (endIndex > beginIndex))
    {
      str = str.substring(0, beginIndex) + str.substring(endIndex + 1, str.length());
      return trimIn(str, begin, end);
    }
    return str;
  }
  
  public static String trim(String str, String begin, String end)
  {
    if (str == null) {
      return str;
    }
    int beginIndex = str.indexOf(begin);
    int endIndex = str.indexOf(end);
    if ((beginIndex >= 0) && (endIndex >= 0)) {
      str = str.substring(beginIndex + 1, endIndex);
    }
    return str;
  }
  
  public static String getRandom(int size)
  {
    Random random = new Random();
    StringBuilder sb = new StringBuilder(size);
    for (int i = 0; i < size; i++) {
      sb.append(random.nextInt(9));
    }
    return sb.toString();
  }
  
  public static void drawRandomPicture(Graphics g, int width, int height, String randomCode)
  {
    g.setColor(randColor(200, 250));
    g.fillRect(0, 0, width, height);
    g.setFont(new Font("Times?New?Roman", 0, 18));
    g.setColor(randColor(160, 200));
    
    Random random = new Random(System.currentTimeMillis());
    for (int i = 0; i < 155; i++)
    {
      int x = random.nextInt(width);
      int y = random.nextInt(height);
      int xl = random.nextInt(12);
      int yl = random.nextInt(12);
      g.drawLine(x, y, x + xl, y + yl);
    }
    for (int i = 0; i < 4; i++)
    {
      g.setColor(randColor(20, 130));
      g.drawString(randomCode.substring(i, i + 1), 13 * i + 6, 16);
    }
  }
  
  private static Color randColor(int fc, int bc)
  {
    Random random = new Random(System.currentTimeMillis());
    if (fc > 255) {
      fc = 255;
    }
    if (bc > 255) {
      bc = 255;
    }
    int r = fc + random.nextInt(bc - fc);
    int g = fc + random.nextInt(bc - fc);
    int b = fc + random.nextInt(bc - fc);
    return new Color(r, g, b);
  }
  
  public static String Url_replace(String str)
  {
    str = str.replace("%", "%25");
    str = str.replace("+", "%2B");
    str = str.replace("/", "%2F");
    str = str.replace("?", "%3F");
    str = str.replace("#", "%23");
    str = str.replace("&", "%26");
    str = str.replace("-", "%2D");
    str = str.replace(" ", "%20");
    return str;
  }
  
  public static byte[] encode2bytes(String source)
  {
    byte[] result = null;
    try
    {
      MessageDigest md = MessageDigest.getInstance("MD5");
      md.reset();
      md.update(source.getBytes("UTF-8"));
      result = md.digest();
    }
    catch (NoSuchAlgorithmException e)
    {
      e.printStackTrace();
    }
    catch (UnsupportedEncodingException e)
    {
      e.printStackTrace();
    }
    return result;
  }
  
  public static String encode2hex(String source)
  {
    byte[] data = encode2bytes(source);
    
    StringBuffer hexString = new StringBuffer();
    for (int i = 0; i < data.length; i++)
    {
      String hex = Integer.toHexString(0xFF & data[i]);
      if (hex.length() == 1) {
        hexString.append('0');
      }
      hexString.append(hex);
    }
    return hexString.toString();
  }
  
  public static boolean validate(String unknown, String okHex)
  {
    return okHex.equals(encode2hex(unknown));
  }
  
  public static List<String> string2List(String str, String split)
  {
    List<String> list = new ArrayList();
    if (!isEmpty(str)) {
      for (String s : toString(str).split(split)) {
        list.add(s);
      }
    }
    return list;
  }
  
  public static boolean isRegEx(String str, String regEx)
  {
    regEx = "[" + regEx + "]";
    Pattern p = Pattern.compile(regEx);
    Matcher m = p.matcher(str);
    return m.find();
  }
  
  public static List<String> string2List(String str)
  {
    return string2List(str, ",");
  }
}

