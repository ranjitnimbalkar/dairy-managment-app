package com.dairy.managment.app.rest;

import java.util.Date;

import lombok.Data;

@Data
public class ErrorResponse {
   private Date timestamp;
   private int status;
   private String error;
   private String path;   
}
