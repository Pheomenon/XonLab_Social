package entity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author:Gao
 * @Date:2020-03-20 10:30
 */
@Data
@NoArgsConstructor
public class Result {
    private boolean flag;
    private Integer code;
    private String message;
    private Object data;

    public Result(boolean flag, Integer code, String message, Object data) {
        this.flag = flag;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Result(boolean flag, Integer code, String message) {
        this.flag = flag;
        this.code = code;
        this.message = message;
    }
}