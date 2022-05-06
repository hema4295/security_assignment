package pers.hm.security.exception;

/**
 * SecurityBizException.java
 *
 * @description: exception that may occur in the business
 * @author: Heng Ma
 * @since: 05/05/2022
 */
public class SecurityBizException extends RuntimeException{
    private String code;
    private String msg;

    public SecurityBizException(String code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public SecurityBizException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
