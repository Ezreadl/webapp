package com.qm.core.base;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * 将java.util.Date对象转换为HH:mm:ss格式字符串
 * @author wangchong
 *
 */
public class TimeSerializer extends JsonSerializer<Date>{
	
	@Override
    public void serialize(Date date, JsonGenerator gen, SerializerProvider provider) throws IOException, JsonProcessingException
    {
        SimpleDateFormat format =new SimpleDateFormat("HH:mm:ss");
        String formattedDate = format.format(date);
        gen.writeString(formattedDate);
    }
}
