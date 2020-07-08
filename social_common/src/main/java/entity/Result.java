package entity;

/**
 * @author lanwei
 * @email 335747949@qq.com
 */
public class Result {

    private boolean falg;
    private Integer code;
    private String message;
    private Object data;

    public Result() {
    }

    public Result(boolean falg, Integer code, String message) {
        this.falg = falg;
        this.code = code;
        this.message = message;
    }

    public Result(boolean falg, Integer code, String message, Object data) {
        this.falg = falg;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public boolean isFalg() {
        return falg;
    }

    public void setFalg(boolean falg) {
        this.falg = falg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "falg=" + falg +
                ", code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
