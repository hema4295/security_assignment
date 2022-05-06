package pers.hm.security.enums;

/**
 * Created by maheng on 5/5/22.
 */
public enum ResponseCode{
    SUCCESS("0", "Success"),
    FAIL("1001", "Fail"),
    USER_EXISTS_ERROR("1002", "The user has already existed"),
    ENCRYPT_ERROR("1003", "some errors when encrypting"),
    UNKNOW_ERRORS("9999", "Unknown errors"),
    ;

    private String CODE;
    private String DESC;

    private ResponseCode(String code, String desc) {
        this.CODE = code;
        this.DESC = desc;
    }

    public String code() {
        return this.CODE;
    }

    public String desc() {
        return this.DESC;
    }
}
