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
        Button btnListUsers = new Button("List Users");
         Button btnAddEmplois = new Button("Add Emplois");
        Button btnListEmplois = new Button("List Emplois");
        
        btnAddUser.addActionListener(e-> new AddUserForm(current).show());
        btnListUsers.addActionListener(e-> new ListUserForm(current).show());
        btnAddEmplois.addActionListener(e-> new AddEmploisForm(current).show());
        btnListEmplois.addActionListener(e-> new ListEmploisForm(current).show());
        addAll(btnAddUser,btnListUsers,btnAddEmplois,btnListEmplois);
        
        
    }
    
    
}
