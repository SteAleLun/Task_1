package com.example.task_1.exception;

public class FileAlreadyUploadedException extends  Exception{

    public FileAlreadyUploadedException(String message){
        super(message);
    }
}
