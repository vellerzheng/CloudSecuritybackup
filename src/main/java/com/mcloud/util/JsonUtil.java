package com.mcloud.util;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;


import com.fasterxml.jackson.core.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
//import com.sf.module.bspcommon.util.JsonException;

/**
 * 描述：JsonUtil工具类
 *
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2015年3月7日      sfit0250         Create
 * ****************************************************************************
 * </pre>
 *
 * @author sfit0250
 * @since 1.0
 */
public class JsonUtil {

    private static Logger logger = LoggerFactory.getLogger(JsonUtil.class);

    private static final ObjectMapper objectMapper;
    /** 格式化时间的string */
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    static {
        objectMapper = new ObjectMapper();
        // 去掉默认的时间戳格式
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        // 设置为中国北京时区
        objectMapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        objectMapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
        // 空值不序列化
        objectMapper.setSerializationInclusion(Include.NON_NULL);
        // 反序列化时，属性不存在的兼容处理
        objectMapper.getDeserializationConfig().withoutFeatures(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        // 序列化时，日期的统一格式
        objectMapper.setDateFormat(new SimpleDateFormat(DATE_TIME_FORMAT));

        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 单引号处理
        objectMapper.configure(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
    }

    /**
     * json 转换成 Object
     *
     * @param json
     * @param clazz
     * @return
     */
    public static <T> T json2Object(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (IOException e) {
            logger.error("", e);
            throw new RuntimeException("解析json错误");
        }
    }

    public static <T> T json2Object(String json, TypeReference<T> tr) {
        try {
            return (T)objectMapper.readValue(json, (TypeReference) tr.getType());
        } catch (IOException e) {
            logger.error("", e);
            throw new RuntimeException("解析json错误", e);
        }
    }

    /**
     * obj 转换成json
     *
     * @param entity
     * @return
     */
    public static <T> String object2Json(T entity) {
        try {
            return objectMapper.writeValueAsString(entity);
        } catch (IOException e) {
            logger.error("", e);
            throw new RuntimeException("转换json错误");
        }
    }

    /**
     * obj对象 转换成树型JSON
     *
     * @param obj
     * @return
     */
    public static JsonNode object2TreeJson(Object obj) {
        try {
            return objectMapper.valueToTree(obj);
        } catch (Exception e) {
            logger.error("", e);
            throw new RuntimeException("转换json错误");
        }
    }


    /**
     * 解码json串成对象
     *
     * @param <T>
     * @param json
     * @param valueType
     * @return T
     */
    @SuppressWarnings("unchecked")
    public static <T> T decode(String json, Class<?> valueType) {
        try {
            return (T) objectMapper.readValue(json, valueType);
        } catch (Exception e) {
          //  throw new JsonException(json, e);
            e.printStackTrace();
            return  null;
        }

    }
    // /**
    // * 对象转换JSON 忽略指定属性
    // * @param obj
    // * @param pros 忽略属性名
    // * @return
    // */
    // public static String object2JsonIgnoreProperties(Object obj, String...
    // pros) {
    // try {
    // FilterProvider filters = new SimpleFilterProvider().addFilter(obj
    // .getClass().getName(), SimpleBeanPropertyFilter
    // .filterOutAllExcept(pros));
    // objectMapper.setFilters(filters);
    // objectMapper.setAnnotationIntrospector(new
    // JacksonAnnotationIntrospector() {
    // private static final long serialVersionUID = 1L;
    //
    // public Object findFilterId(AnnotatedClass ac) {
    // return ac.getName();
    // }
    // });
    // return objectMapper.writeValueAsString(obj);
    // } catch (Exception e) {
    // log.error(e);
    // throw new RuntimeException("转换json错误");
    // }
    // }

}
