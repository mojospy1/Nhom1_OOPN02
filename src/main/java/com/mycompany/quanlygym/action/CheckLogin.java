/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
//<<<<<<< HEAD:src/main/java/com/mycompany/quanlygym/action/CheckLogin.java
package com.mycompany.quanlygym.action;
import java.util.List;
import java.util.ArrayList;
import com.mycompany.quanlygym.entity.User;
//=======
//package com.mycompany.quanlydoituongdacbiet.action;
//import java.util.List;
//import java.util.ArrayList;
//import com.mycompany.quanlydoituongdacbiet.entity.User;
//>>>>>>> ee5768bb1962bb638945a58b6fc3284afdade289:src/main/java/com/mycompany/quanlydoituongdacbiet/action/CheckLogin.java

/**
 *
 * @author PC
 */
public class CheckLogin {
//<<<<<<< HEAD
//    public boolean checkUser(User user) {
//        if (user != null) {
//            if ("admin".equals(user.getUserName()) 
//                    && "admin".equals(user.getPassword())) {
//                return true;
//            }
//        }
//        return false;
////            return true ;
//=======
   private static List<User> users = new ArrayList<>();
   
   static{
       users.add(new User("admin", "123456"));
         users.add(new User("user1", "09092005"));
           users.add(new User("user2", "1234"));
   }
   public static boolean checkUser(User user){
       for(User u :users){
           if(u.getUserName().equals(user.getUserName()) && user.getPassword().equals(user.getPassword())){
               return true;
           }
       }
          return false ;
//>>>>>>> a5551f4 (login)
    }
}
//cc
// ccc
