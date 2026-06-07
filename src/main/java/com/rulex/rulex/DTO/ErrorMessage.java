package com.rulex.rulex.DTO;

import java.util.Objects;

public class ErrorMessage {
    String error;
    String message;
    String status;

    public ErrorMessage(String error, String message, String status) {
        this.error = error;
        this.message = message;
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ErrorMessage{" +
                "error='" + error + '\'' +
                ", message='" + message + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ErrorMessage that = (ErrorMessage) o;
        return Objects.equals(error, that.error) &&
                Objects.equals(message, that.message) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(error, message, status);
    }
}
