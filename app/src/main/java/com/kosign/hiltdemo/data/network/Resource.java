package com.kosign.hiltdemo.data.network;

public class Resource<T> {

    Status status;
    T data;
    int code;
    String message;

    public static class Success<T> extends Resource<T>{

        public Success(T response){
            setStatus(Status.SUCCESS);
            data = response;
        }
    }

    public static class Error<T> extends Resource<T>{

        public Error(int code, String error){
            setStatus(Status.ERROR);
            this.code = code;
            message = error;
        }

    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
//
//    public String getCode() {
//        return code;
//    }
//
//    public void setCode(String code) {
//        this.code = code;
//    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
