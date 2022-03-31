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
import com.mycompany.myapp.entities.Emplois;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author HP
 */



/**
 *
 * @author bhk
 */
public class ServiceEmplois {

    public ArrayList<Emplois> Emplois;
    
    public static ServiceEmplois instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceEmplois() {
         req = new ConnectionRequest();
    }

    public static ServiceEmplois getInstance() {
        if (instance == null) {
            instance = new ServiceEmplois();
        }
        return instance;
    }

    public boolean addEmplois(Emplois t) {
        System.out.println(t);
        System.out.println("********");
     //  String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus()+ "&status=" + t.getStatus()+ "&status=" + t.getStatus()+ "&status=" + t.getStatus()+ "&status=" + t.getStatus()+ "&status=" + t.getStatus()+ "&status=" + t.getStatus();
       String url = Statics.BASE_URL + "emplois/AddEmploisj";
    
       req.setUrl(url);
       //tfNom,tfPrenom,tfPassword,tfDatenaissance,tfRole,tfAccess,tfImage,tfCin
        req.addArgument("User", t.getCin()+"");
       req.addArgument("Ddebut", t.getDdebut()+"");
        req.addArgument("Dfin", t.getDfin()+"");
       //req.addArgument("Access", t.getAccess()+"");
       // req.addArgument("image", t.getImage());
      
       
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

    public ArrayList<Emplois> parseEmplois(String jsonText){
        try {
            Emplois=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> EmploisListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)EmploisListJson.get("root");
            for(Map<String,Object> obj : list){
                Emplois t = new Emplois();
                float id = Float.parseFloat(obj.get("id").toString());
                float CIN = Float.parseFloat(obj.get("CIN").toString());
                 
              //  float Password = Float.parseFloat(obj.get("Password").toString());
                t.setId((int)id);
                t.setNom((obj.get("Nom").toString()));
                    t.setPrenom(obj.get("Prenom").toString());
                     t.setCin((int)CIN);
                      t.setDfin((obj.get("Dfin").toString()));
                     t.setDdebut((obj.get("Ddebut").toString()));
                    //t.setRole(obj.get("Role").toString());
                    // t.setImage((obj.get("image").toString()));
                 /*    Map<String,Object> userh = (Map<String,Object>) obj.get("user");
                   float user_id=Float.parseFloat(userh.get("id").toString());
                     float user_cin = Float.parseFloat(userh.get("cin").toString());
                User us = new User((int)user_id,(int)user_cin);
                                        t.setU(us);*/
                                        
                Emplois.add(t);
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
        return Emplois;
    }
    
    public ArrayList<Emplois> getAllEmplois(){
        //String url = Statics.BASE_URL+"/Emplois/";
        String url = Statics.BASE_URL +"emplois/Allemp";
        req.setUrl(url);
     
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Emplois = parseEmplois(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Emplois;
    }

    public boolean deleteEmplois(int t) {
        System.out.println(t);
        System.out.println("******");
     
       String url = Statics.BASE_URL + "emplois/deletej?id="+t;
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
    public boolean updateEmplois(Emplois t) {
        System.out.println(t);
        System.out.println("******");
         String url = Statics.BASE_URL + "emplois/updateEmploisj?id="+t.getId();
       req.setUrl(url);
       //tfNom,tfPrenom,tfPassword,tfDatenaissance,tfRole,tfAccess,tfImage,tfCin
        
       req.addArgument("Ddebut", t.getDdebut()+"");
        req.addArgument("Dfin", t.getDfin()+"");
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
     public ArrayList<Emplois> RechercheE(String t){
    
        //String url = Statics.BASE_URL+"/Students/";
        String url = Statics.BASE_URL +"emplois/searchemp/"+t;
        //Tournoi t=new Tournoi();
        req.setUrl(url);
   
       req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Emplois = parseEmplois(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Emplois;
    }
}
