package com.bestsoft.common.https.exception;

/**
 * @author Administrator
 * @date 2018/10/16  15:21
 * @description 服务器运行异常
 **/
public class ServerException extends RuntimeException {
    private String responseMessage;
    private int code;

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ServerException(String responseMessage, int code) {
        this.responseMessage = responseMessage;
        this.code = code;
    }

    public ServerException(String responseMessage) {
        super(handleError(responseMessage));
    }

    private static String handleError(String responseMessage) {
        if (responseMessage == null || responseMessage.isEmpty()) {
            return "Server out:null";
        }
        return responseMessage;
    }
}
