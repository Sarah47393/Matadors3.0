/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.ServiceUser;
import com.mycompany.myapp.entities.Emplois;
import com.mycompany.myapp.services.ServiceEmplois;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.codename1.ui.Display;
import com.codename1.ui.spinner.Picker;

/**
 *
 * @author HP
 */

/**
 *
 * @author bhk
 */
public class AddEmploisForm extends Form{
   static    ArrayList<User>   tr;
    public AddEmploisForm(Form previous) {
        setTitle("Add a new User");
        setLayout(BoxLayout.y());
      
      /*  TextField tfNom = new TextField("","Nom");
        TextField tfPrenom= new TextField("", "Prenom");
         TextField tfPassword = new TextField("","Password");
        TextField tfDatenaissance= new TextField("", "Datenaissance");*/
         TextField tfDdebut = new TextField("","Ddebut");
        TextField tfDfin= new TextField("", "Dfin");
        // TextField tfImage = new TextField("","Image");
        TextField tfUser= new TextField("", "User");
           tr = new ArrayList<>();
    
        
        tr = ServiceUser.getInstance().getAllUsers();
        
        Picker typeReclamation = new Picker();
   typeReclamation.setType(Display.PICKER_TYPE_STRINGS);
        List<String> listuser = ServiceUser.getInstance().fillintot();
        String[] usersnames = listuser.toArray(new String [listuser.size()]);
    typeReclamation.setUIID("TextFieldBlack");
        
            typeReclamation.setStrings(usersnames);
            typeReclamation.setSelectedStringIndex(0);
        Button btnValider = new Button("Add Emplois");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfDfin.getText().length()==0)||(tfDdebut.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                         int user_id= typeReclamation.getSelectedStringIndex();
                           User tourno = new User(user_id, tr.get(user_id).getCin());
                           Integer cn=tourno.getCin();
                            float tourn = Float.parseFloat(cn.toString());
                
                        //int password, int cin, String nom, String prenom, String datenaissance, String role, String access, String image
                        Emplois t = new Emplois((int)tourn,tfDdebut.getText(),tfDfin.getText());
                        if( ServiceEmplois.getInstance().addEmplois(t))
                        {
                           Dialog.show("Success","Connection accepted",new Command("OK"));
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }

           
        });
        
        addAll(typeReclamation,tfDdebut,tfDfin,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
    
    
}
