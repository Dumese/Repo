import com.alibaba.druid.sql.visitor.functions.Isnull;

import java.lang.reflect.Field;
import java.util.*;

/**
 * 字段比较工具类
 * @param <T>
 */
public class CompareUtils<T> {
    /**
     *
     * @param source    被比较类
     * @param target    比较类
     * @return  相异结果
     */
    public LinkedList<Map> compare(T source, T target) throws Exception{
        return compare(source, target, null);
    }

    /**
     *
     * @param source    被比较类
     * @param target    比较类
     * @param ignoreFields  忽略字段
     * @return  相异结果
     */
    public LinkedList<Map> compare(T source, T target, List<String> ignoreFields) throws Exception{
        Map<String, String> sourceMap = getValueMap(source);
        Map<String, String> targetMap = getValueMap(target);
        return doCompare(sourceMap, targetMap, ignoreFields);
    }

    private LinkedList<Map> doCompare(Map sourceMap, Map targetMap, List<String> ignoreFields){
        LinkedList<Map> mapList = new LinkedList<Map>();
        Set<String> keys = sourceMap.keySet();
        for (String key : keys) {
            if (Objects.nonNull(ignoreFields) && ignoreFields.contains(key)) {
                continue;
            }
            String sourceValue = sourceMap.get(key).toString();
            String targetValue = targetMap.get(key).toString();

            if(!sourceValue.equals(targetValue)){
                Map<String, String> map = new HashMap<String, String>();
                map.put("fieldName", key);
                map.put("sourceData", sourceValue);
                map.put("targetData", targetValue);
                mapList.add(map);
            }
        }
        return mapList;
    }

    private Map<String, String> getValueMap(T data) throws Exception {
        if (Objects.isNull(data)) {
            return Collections.emptyMap();
        }
        Field[] fields = data.getClass().getDeclaredFields();
        if (fields.length == 0) {
            return Collections.emptyMap();
        }
        Map<String, String> map = new LinkedHashMap<String, String>();
        for(Field field : fields){
            field.setAccessible(true);
            map.put(field.getName(), String.valueOf(field.get(data)));
        }
        return map;
    }
}
