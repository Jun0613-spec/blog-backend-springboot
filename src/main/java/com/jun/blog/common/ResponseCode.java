package com.jun.blog.common;

public interface ResponseCode {
    
     //HTTP Status 200
     public static final String SUCCESS = "SU";
    
     //HTTP Status 400
     public static final String VALIDATION_FAILED = "VF";
     public static final String DUPLICATE_EMAIL = "DE";
     public static final String DUPLICATE_USERNAME = "DU";
     public static final String NOT_EXISTED_USER = "NEU";
     public static final String NOT_EXISTED_POST = "NEP";
     public static final String NOT_EXISTED_COMMENT = "NEC";
 
     //HTTP Status 401
     public static final String SIGN_IN_FAIL = "SF";
     public static final String AUTHORIZATION_FAIL = "AF";
 
     //HTTP Status 403
     public static final String NO_PERMISSION = "NP";
 
     //HTTP Status 500
     public static final String DATABASE_ERROR = "DBE";

}
