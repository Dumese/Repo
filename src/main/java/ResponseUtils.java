import lombok.Data;

import java.util.Date;

/**
 * 响应工具类
 * @param <T>
 */
@Data
public class ResponseUtils<T> {
    /**
     * 返回码
     */
    private Integer code;
    /**
     * 返回信息
     */
    private String msg;
    /**
     * 响应时间
     */
    private Date timestamp = new Date();
    /**
     * 数据
     */
    private T data;

    public ResponseUtils(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
