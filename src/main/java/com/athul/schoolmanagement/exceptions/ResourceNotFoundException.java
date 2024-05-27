package com.athul.schoolmanagement.exceptions;

import lombok.Data;


@Data
public class ResourceNotFoundException extends RuntimeException{

    String resourceName;
    Long numberData;
    String textData;

    public ResourceNotFoundException(Long numberData){
        super(String.format("Student not found with  %d ",numberData));
        this.numberData=numberData;
    }

//    public ResourceNotFoundException(String textData){
//        super(String.format("Student(s) not found with %s",textData));
//        this.textData=textData;
//    }


}
