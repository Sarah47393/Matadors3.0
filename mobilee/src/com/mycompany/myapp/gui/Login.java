/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.ServiceUser;
import com.mycompany.myapp.MyApplication;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;

/**
 *
 * @author HP
 */
/*public class Login extends Form{
 Form current;
public class Login (){
   
  current=this; //Back 
        setTitle("Home");
        setLayout(BoxLayout.y());
 TextField tfpass = new TextField("","Image");
        TextField tfCin= new TextField("", "Cin");
          Button loginButton = new Button("login");
 loginButton.addActionListener(e -> {
            User usc = new User(tfCin.getText(),tfpass.getText());
            
        ServiceUser.getInstance().ConnectUser(usc);
        if(MyApplication.u_c.getId()!= 0)
            {
                //Toolbar.setGlobalToolbar(true);
            new HomeForm().show();
         //   Toolbar.setGlobalToolbar(true);
            }
        else
        {
            tfCin.setText("");
            tfpass.setText("");
        }
            add(tfCin,tfpass,loginButton);
        });}}

*/


public class Login extends Form{
Form current;
    public Login() {
        current=this; //Back 
        setTitle("Home");
        setLayout(BoxLayout.y());
        
       current=this; //Back 
        setTitle("Home");
        setLayout(BoxLayout.y());
 TextField tfpass = new TextField("","password");
        TextField tfCin= new TextField("", "Cin");
          Button loginButton = new Button("login");
            Button cree = new Button("creer un compte");
           addAll(tfCin,tfpass,loginButton,cree);
           cree.addActionListener(e ->  new AddUserfront(this).show());
 loginButton.addActionListener(e -> {
            User usc = new User(Integer.parseInt(tfCin.getText()),Integer.parseInt(tfpass.getText()));
              System.out.println(usc.getId()+"+"+usc.getRole());
        ServiceUser.getInstance().ConnectUser(usc);
        System.out.println(MyApplication.u_c.getId()+"+"+MyApplication.u_c.getRole());
        if(MyApplication.u_c.getId()!= 0)
            {
                System.out.println(MyApplication.u_c.getRole());
               if(MyApplication.u_c.getRole().equalsIgnoreCase("Admin"))
            {
                //Toolbar.setGlobalToolbar(true);
            new HomeForm().show();
         //   Toolbar.setGlobalToolbar(true);
            }
               else if(MyApplication.u_c.getRole().equalsIgnoreCase("Membre")){//Toolbar.setGlobalToolbar(true);
                   int xx=MyApplication.u_c.getId();
            new Affichuser(this,MyApplication.u_c,xx).show();
         //   Toolbar.setGlobalToolbar(true);
            }}
        else
        {
            tfCin.setText("");
            tfpass.setText("");
        }
           
        });
        
        
    }
    
    
}
