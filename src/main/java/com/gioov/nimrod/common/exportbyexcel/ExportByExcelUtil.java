package com.gioov.nimrod.common.exportbyexcel;

import com.gioov.common.util.JsonUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author godcheese
 * @date 2018/2/22
 */
public class ExportByExcelUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExportByExcelUtil.class);

    private static final String DEFAULT_KEY = "default";

    private ExportByExcelUtil() {
    }

    @SuppressWarnings("unchecked")
    public static void exportFieldValue(Row row, Object entity) throws IllegalAccessException, IOException {
        Object value;
        Field[] fields = entity.getClass().getDeclaredFields();
        int fieldIndex = 0;
        for (Field field : fields) {
            ExportByExcel annotation = field.getAnnotation(ExportByExcel.class);
            if (annotation != null) {
                field.setAccessible(true);
                Cell cell = row.createCell(fieldIndex);


                Class type = field.getType();
                String pattern = annotation.pattern();
                if (type == Date.class && !"".equals(pattern)) {
                    value = parseDatePattern(pattern, entity, field);
                } else {
                    if (type == Integer.class && !"".equals(pattern)) {
                        value = parseIntegerPattern(pattern, entity, field);
                    } else {
                        value = field.get(entity);
                    }
                }
                if (value == null) {
                    value = field.get(entity);
                }
                cell.setCellValue(String.valueOf(value));
                fieldIndex++;
            }
        }
    }

    private static Object parseDatePattern(String pattern, Object entity, Field field) throws IllegalAccessException {
        Object value;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        value = simpleDateFormat.format(field.get(entity));
        return value;
    }

    /**
     * 将实例化的实体遍历赋值
     *
     * @param entityClass
     * @param entity
     * @param map
     * @param <T>
     * @return
     */
    public static <T> T entityAssignValue(Class entityClass, T entity, Map<Integer, Object> map) {
        Field[] fields = entityClass.getDeclaredFields();
        int index = 0;
        for (Field field : fields) {
            field.setAccessible(true);
            ExportByExcel exportByExcel;
            if ((exportByExcel = field.getAnnotation(ExportByExcel.class)) != null) {
                String pattern = exportByExcel.pattern();
                try {
                    Class fieldTypeClass = field.getType();
                    Cell mapCell = (Cell) map.get(index);
                    Object methodValue = null;
                    if (field.get(entity) == null && mapCell != null) {
                        String mapValue = mapCell.getStringCellValue();
                        if (fieldTypeClass.equals(Long.class)) {
                            methodValue = Long.valueOf(mapValue);
                            field.set(entity, methodValue);
                        } else if (fieldTypeClass.equals(Integer.class)) {
                            if (!"".equals(pattern)) {
                                try {
                                    methodValue = entityPatternParseToHashMapByValue(pattern, mapValue);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                    methodValue = mapValue;
                                }
                            } else {
                                methodValue = Integer.valueOf(mapValue);
                            }
                            field.set(entity, methodValue);
                        } else if (fieldTypeClass.equals(String.class)) {
                            field.set(entity, mapValue);
                        } else if (fieldTypeClass.equals(Date.class)) {
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                            try {
                                methodValue = simpleDateFormat.parse(mapValue);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }

                            // 给实体属性赋值
                            field.set(entity, methodValue);
                        }
                    }

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

                // 给实体属性对应的方法赋值
//                String fieldName = field.getName();

//                String methodName =  fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

//                String getMethodName = "get" + methodName;
//                String setMethodName = "set" + methodName;

//                LOGGER.info("setMethodName={}", setMethodName);
//                LOGGER.info("map.get(index)={}", map.get(index));
//                try {
//                    Class fieldClass = field.getType();
//                    Object mapValue = map.get(index);
//                    Object methodValue = null;
//
//                    Method setMethod = entityClass.getMethod(setMethodName, fieldClass);
//                    LOGGER.info("{}", setMethod);
//                    if (fieldClass.equals(Long.class)) {
//                        if(mapValue instanceof Integer) {
//                            methodValue = ((Integer) mapValue).longValue();
//                        } else if(mapValue instanceof Long ){
//                            methodValue = mapValue;
//                        }
//                    } else if (fieldClass.equals(String.class)) {
//                        methodValue = String.valueOf(mapValue);
//                    } else if (fieldClass.equals(Integer.class)) {
//                        if(mapValue instanceof Integer) {
//                            methodValue = mapValue;
//                        } else if(mapValue instanceof Long ){
//                            methodValue = ((Long) mapValue).intValue();
//                        }
//                    } else if (fieldClass.equals(Date.class)) {
//                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(exportByExcel.pattern());
//                        try {
//                            methodValue = simpleDateFormat.parse((String) mapValue);
//                        } catch (ParseException e) {
//                            e.printStackTrace();
//                            methodValue = new Date();
//                        }
//                    }
//
//                    setMethod.invoke(entity, methodValue);
//
//                } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e1) {
//                    e1.printStackTrace();
//                }
                index++;
            }
        }
        return entity;
    }

    /**
     * 将实体的 ExportByExcel annotation 的 pattern 值转成 HashMap
     *
     * @param pattern
     * @param compareValue
     * @return
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
    public static Integer entityPatternParseToHashMapByValue(String pattern, String compareValue) throws IOException {
        Map<Object, Object> map = JsonUtil.toObject(pattern, Map.class);
        Integer index = Integer.valueOf(map.get(DEFAULT_KEY).toString());
        for (Map.Entry entry : map.entrySet()) {
            Object key = entry.getKey();
            Object value = entry.getValue();
            if (value.equals(compareValue) && !DEFAULT_KEY.equalsIgnoreCase((String) key)) {
                index = Integer.valueOf(key.toString());
                break;
            }
        }
        return index;
    }

    /**
     * @param pattern
     * @param entity
     * @param field
     * @return
     * @throws IOException
     * @throws IllegalAccessException
     */
    @SuppressWarnings("unchecked")
    private static Object parseIntegerPattern(String pattern, Object entity, Field field) throws IOException, IllegalAccessException {
        Object value = null;
        Map<Object, Object> map = JsonUtil.toObject(pattern, HashMap.class);
        for (Map.Entry<Object, Object> entry : map.entrySet()) {
            if ((String.valueOf(entry.getKey())).equals(String.valueOf(field.get(entity)))) {
                value = entry.getValue();
                break;
            } else if (DEFAULT_KEY.equals(String.valueOf(entry.getKey()))) {
                value = map.get(map.get(entry.getValue()));
                break;
            }
        }
        return value;
    }

}
