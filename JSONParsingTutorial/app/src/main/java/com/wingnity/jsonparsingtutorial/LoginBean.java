package com.wingnity.jsonparsingtutorial;


import java.io.Serializable;

/**
 * Created by lenovo on 4/17/2015.
 */

/**
 * Created by lenovo on 4/16/2015.
 */
public class LoginBean {

    public User user = new User();


    public class User implements Serializable {
        public String userId = "";
        public  String firstName="";
        public String  lastName="";
        public String   userName="";
    }
}
