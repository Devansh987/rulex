package com.rulex.rulex.Exception;

import java.util.Objects;

public class ErrorResponse {
    String Message;
    int code;

    public ErrorResponse(String Message, int code) {
        this.Message = Message;
        this.code = code;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "Message='" + Message + '\'' +
                ", code=" + code +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ErrorResponse that = (ErrorResponse) o;
        return code == that.code &&
                Objects.equals(Message, that.Message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Message, code);
    }
}
