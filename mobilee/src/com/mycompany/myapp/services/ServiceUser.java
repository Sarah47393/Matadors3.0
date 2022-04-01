/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.mycompany.myapp.MyApplication;
/**
 *
 * @author bhk
 */
public class ServiceUser {

    public ArrayList<User> Users;
    
    public static ServiceUser instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceUser() {
         req = new ConnectionRequest();
    }

    public static ServiceUser getInstance() {
        if (instance == null) {
            instance = new ServiceUser();
        }
        return instance;
    }

    public boolean addUser(User t) {
        System.out.println(t);
        System.out.println("********");
     //  String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus()+ "&status=" + t.getStatus()+ "&status=" + t.getStatus()+ "&status=" + t.getStatus()+ "&status=" + t.getStatus()+ "&status=" + t.getStatus()+ "&status=" + t.getStatus();
       String url = Statics.BASE_URL + "user/addUserj";
    
       req.setUrl(url);
       //tfNom,tfPrenom,tfPassword,tfDatenaissance,tfRole,tfAccess,tfImage,tfCin
       req.addArgument("Nom", t.getNom());
       req.addArgument("Prenom", t.getPrenom()+"");
        req.addArgument("CIN", t.getCin()+"");
       req.addArgument("Password", t.getPassword()+"");
        req.addArgument("Role", t.getRole());
       req.addArgument("Access", t.getAccess()+"");
        req.addArgument("image", t.getImage());
       req.addArgument("datenaissance", t.getDatenaissance()+"");
       
       req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
      public boolean addUserf(User t) {
        System.out.println(t);
        System.out.println("********");
     //  String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus()+ "&status=" + t.getStatus()+ "&status=" + t.getStatus()+ "&status=" + t.getStatus()+ "&status=" + t.getStatus()+ "&status=" + t.getStatus()+ "&status=" + t.getStatus();
       String url = Statics.BASE_URL + "user/addUserjf";
    
       req.setUrl(url);
       //tfNom,tfPrenom,tfPassword,tfDatenaissance,tfRole,tfAccess,tfImage,tfCin
       req.addArgument("Nom", t.getNom());
       req.addArgument("Prenom", t.getPrenom()+"");
        req.addArgument("CIN", t.getCin()+"");
       req.addArgument("Password", t.getPassword()+"");
        //req.addArgument("Role", t.getRole());
       req.addArgument("Access", t.getAccess()+"");
        req.addArgument("image", t.getImage());
       req.addArgument("datenaissance", t.getDatenaissance()+"");
       
       req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public ArrayList<User> parseUsers(String jsonText){
        try {
            Users=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> UsersListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)UsersListJson.get("root");
            for(Map<String,Object> obj : list){
                User t = new User();
                float id = Float.parseFloat(obj.get("id").toString());
                float CIN = Float.parseFloat(obj.get("CIN").toString());
                float Password = Float.parseFloat(obj.get("Password").toString());
                t.setId((int)id);
                t.setNom((obj.get("Nom").toString()));
                    t.setPrenom(obj.get("Prenom").toString());
                     t.setCin((int)CIN);
                      t.setPassword((int)Password);
                     t.setAccess((obj.get("Access").toString()));
                    t.setRole(obj.get("Role").toString());
                     t.setImage((obj.get("image").toString()));
                    t.setDatenaissance(obj.get("datenaissance").toString());
                Users.add(t);
            }
            /*
            
             Students=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> StudentsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)StudentsListJson.get("root");
            for(Map<String,Object> obj : list){
                Student t = new Student();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId((int)id);
                t.setEmail((obj.get("email").toString()));
                    t.setNsc(obj.get("nsc").toString());
                Students.add(t);
            }
            */
            
        } catch (IOException ex) {
            
        }
        return Users;
    }
    public List<String>fillintot()
    {
        List<String> userss = new ArrayList<String>();
        Users = getAllUsers();
        for(User tr:Users)
        {
             Integer cn=tr.getCin();
                   float tourn = Float.parseFloat(cn.toString());
                   String s=String.valueOf(cn);
                   String y= s +","+ tr.getNom() +","+ tr.getPrenom();
           // userss.add(tr.getNom());
             userss.add(y);
          //  tournoiss.add("");
        }
     
      //  System.out.println(tournoiss);
        return userss;
    }
    public ArrayList<User> getAllUsers(){
        //String url = Statics.BASE_URL+"/Users/";
        String url = Statics.BASE_URL +"user/AllUsers";
        req.setUrl(url);
     
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Users = parseUsers(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Users;
    }
public ArrayList<User> getAUser(User i,int t){
        //String url = Statics.BASE_URL+"/Users/";
        String url = Statics.BASE_URL +"user/AUser?id="+i.getId();
        req.setUrl(url);
     
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Users = parseUsers(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Users;
    }
      public boolean deleteUser(int t) {
        System.out.println(t);
        System.out.println("******");
     
       String url = Statics.BASE_URL + "user/deletey?id="+t;
       //String url = Statics.BASE_URL + "addTournoij";
       req.setUrl(url);
      req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
    
        NetworkManager.getInstance().addToQueueAndWait(req);
      return resultOK;
    }
    public boolean deleteUserf(int t) {
        System.out.println(t);
        System.out.println("******");
     
       String url = Statics.BASE_URL + "user/deleteyff?id="+t;
       //String url = Statics.BASE_URL + "addTournoij";
       req.setUrl(url);
      req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
    
        NetworkManager.getInstance().addToQueueAndWait(req);
      return resultOK;
    }
         public ArrayList<User> RechercheU(String t){
    
        //String url = Statics.BASE_URL+"/Students/";
       String url = Statics.BASE_URL +"user/search1/"+t;
        //Tournoi t=new Tournoi();
        req.setUrl(url);
   
       req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Users = parseUsers(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Users;
    }
       public ArrayList<User> getAllUserstri(String t){
        //String url = Statics.BASE_URL+"/Users/";
        String url = Statics.BASE_URL +"user/order1/"+t;
        req.setUrl(url);
     
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Users = parseUsers(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Users;
    }
        public boolean updateUser(User t) {
        System.out.println(t);
        System.out.println("******");
         String url = Statics.BASE_URL + "user/updateUserj?id="+t.getId();
       req.setUrl(url);
       //tfNom,tfPrenom,tfPassword,tfDatenaissance,tfRole,tfAccess,tfImage,tfCin
        
       req.addArgument("Nom", t.getNom());
       req.addArgument("Prenom", t.getPrenom()+"");
        req.addArgument("CIN", t.getCin()+"");
       req.addArgument("Password", t.getPassword()+"");
        req.addArgument("Role", t.getRole());
       req.addArgument("Access", t.getAccess()+"");
        req.addArgument("image", t.getImage());
       req.addArgument("datenaissance", t.getDatenaissance()+"");
      req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
    
        NetworkManager.getInstance().addToQueueAndWait(req);
      return resultOK;
    }
         public boolean updateUserf(User t) {
        System.out.println(t);
        System.out.println("******");
         String url = Statics.BASE_URL + "user/updateUserjfff?id="+t.getId();
       req.setUrl(url);
       //tfNom,tfPrenom,tfPassword,tfDatenaissance,tfRole,tfAccess,tfImage,tfCin
        
       req.addArgument("Nom", t.getNom());
       req.addArgument("Prenom", t.getPrenom()+"");
        req.addArgument("CIN", t.getCin()+"");
       req.addArgument("Password", t.getPassword()+"");
        //req.addArgument("Role", t.getRole());
       req.addArgument("Access", t.getAccess()+"");
        req.addArgument("image", t.getImage());
       req.addArgument("datenaissance", t.getDatenaissance()+"");
      req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
    
        NetworkManager.getInstance().addToQueueAndWait(req);
      return resultOK;
    }
         public User ConnectUser(User u){
      

         String url = Statics.BASE_URL+"user/cnJson?username="+u.getCin()+"&mdp="+u.getPassword();
        req.setUrl(url);
       //req.setPost(false);
         req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    MyApplication.u_c = new User(getUser(new String(req.getResponseData())));
                      System.out.println(MyApplication.u_c.getId()+"+"+MyApplication.u_c.getRole());
                } catch (IOException ex) {
                  //  Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
                }
               
            System.out.println(MyApplication.u_c.getId()+"+"+MyApplication.u_c.getRole());
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return MyApplication.u_c;
        
    }
     public User getUser(String jsontext) throws IOException{
  
             if(jsontext.length()>4)
             {
               
                 JSONParser j = new JSONParser();
              JSONParser j2 = new JSONParser();
            Map<String,Object> obj = j.parseJSON(new CharArrayReader(jsontext.toCharArray()));
                
             
               String nom = obj.get("Nom").toString();
                 String prenom = obj.get("Prenom").toString();
//String password = obj.get("Password").toString();
String role = obj.get("Role").toString();
String img = obj.get("image").toString();
String access = obj.get("Access").toString();
String datenaissance = obj.get("datenaissance").toString();
//Badge badge = (Badge)obj.get("badge");
float cin =  Float.parseFloat(obj.get("CIN").toString());
float password =  Float.parseFloat(obj.get("Password").toString());
float id = Float.parseFloat(obj.get("id").toString());
//Badge bad = new Badge();
User b = new User(nom,prenom,(int)password,(int)cin,role,access,img,datenaissance);
b.setId((int)id);
              System.out.println(b);
          
                     return b;
             }
             else
             {
                 User uc = new User();
            
                  return uc;
             }
                    
                  

    }
}


