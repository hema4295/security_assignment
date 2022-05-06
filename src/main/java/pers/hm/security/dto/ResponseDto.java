package pers.hm.security.dto;

import java.io.Serializable;

/**
 * ResponseDto.java
 *
 * @description: the result that responds to the front-end
 * @author: Heng Ma
 * @since: 05/05/2022
 */
public class ResponseDto implements Serializable{
    private String resultCode;

    private String resultMsg;

    private Object resultData;

    public ResponseDto resultCode(String resultCode) {
        this.resultCode = resultCode;
        return this;
    }

    public ResponseDto resultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
        return this;
    }

    public ResponseDto resultData(Object resultData) {
        this.resultData = resultData;
        return this;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public Object getResultData() {
        return resultData;
    }

    public void setResultData(Object resultData) {
        this.resultData = resultData;
    }
}
