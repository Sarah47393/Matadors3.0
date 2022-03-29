/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

/**
 *
 * @author HP
 */
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Emplois;
import com.mycompany.myapp.services.ServiceEmplois;
import java.util.ArrayList;
/**
 *
 * @author oumayma
 */
public class EditEmplois extends Form {
 
     public  EditEmplois(Form previous, Emplois ev) {
     
    
     setTitle("Add a new tournoi");
        setLayout(BoxLayout.y());
      
            TextField tfDdebut = new TextField(ev.getDdebut());
        TextField tfDfin= new TextField(ev.getDfin());
  
     
      
             Button btnValider = new Button("update emplois"); 
         
        btnValider.addActionListener(( e)-> {
            {  
                try {
                if ((tfDdebut.getText().length()==0)||(tfDfin.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                 
                        //SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd");
                        Emplois t = new Emplois( ev.getId(),tfDdebut.getText().toString(),tfDfin.getText());
                    if( ServiceEmplois.getInstance().updateEmplois(t))
                    {
                           Dialog.show("Success","Connection accepted",new Command("OK"));
                        
                    }else{
                         Dialog.show("ERROR", "connn refuse", new Command("OK"));
                    }  
                    
                }
                } catch (NumberFormatException x) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                new ListEmploisForm(this).show();
            }
        });
        
         
        addAll(tfDdebut,tfDfin,btnValider);
         
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
}