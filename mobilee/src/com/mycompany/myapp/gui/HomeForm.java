/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author bhk
 */
public class HomeForm extends Form{
Form current;
    public HomeForm() {
        current=this; //Back 
        setTitle("Home");
        setLayout(BoxLayout.y());
        
        add(new Label("Choose an option"));
        Button btnAddUser = new Button("Add User");
        Button log = new Button("Add log");
        Button btnListUsers = new Button("List Users");
         Button btnAddEmplois = new Button("Add Emplois");
        Button btnListEmplois = new Button("List Emplois");
                Button btnrech = new Button("rech emplois");
         Button btnrechu = new Button("rech user");
          Button btnrechu1 = new Button("add front user");
        btnAddUser.addActionListener(e-> new AddUserForm(current).show());
         log.addActionListener(e-> new Login().show());
        btnListUsers.addActionListener(e-> new ListUserForm(current).show());
        btnAddEmplois.addActionListener(e-> new AddEmploisForm(current).show());
        btnListEmplois.addActionListener(e-> new ListEmploisForm(current).show());
         btnrech.addActionListener(e-> new RechercheEmplois(current).show());
         btnrechu.addActionListener(e-> new RechercheUser(current).show());
            btnrechu1.addActionListener(e-> new AddUserfront(current).show());
        addAll(btnrech,btnrechu,btnrechu1,btnAddUser,btnListUsers,btnAddEmplois,btnListEmplois,log);
        
        
    }
    
    
}
