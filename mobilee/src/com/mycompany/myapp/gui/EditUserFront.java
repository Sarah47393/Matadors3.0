/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.ServiceUser;
import rest.file.uploader.tn.FileUploader;

/**
 *
 * @author HP
 */


import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Emplois;
import com.mycompany.myapp.services.ServiceEmplois;

/**
 *
 * @author HP
 */



/**
 *
 * @author HP
 */
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.ServiceUser;
import java.util.ArrayList;
import rest.file.uploader.tn.FileUploader;
import com.codename1.io.File;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import static com.codename1.ui.CN.openGallery;
import static com.codename1.ui.CN1Constants.GALLERY_IMAGE;
import com.codename1.ui.Image;
import com.codename1.ui.util.ImageIO;
import rest.file.uploader.tn.FileUploader;
import com.codename1.ui.util.Resources;
import com.codename1.ext.filechooser.FileChooser;
/**
 *
 * @author oumayma
 */
public class EditUserFront extends Form {
 private String fileNameInServer = "";
     public  EditUserFront(Form previous, User ev,int ez) {
     
    
     setTitle("edit user");
        setLayout(BoxLayout.y());
   
          //  TextField tfDdebut = new TextField(ev.getDdebut());
        //TextField tfDfin= new TextField(ev.getDfin());
        
 String s= ev.getDatenaissance();
 String s1= ev.getNom();
 String s2= ev.getPrenom();
 String s11= ""+ev.getId();
 String s21= ""+ez;
 String s3= ""+ev.getPassword();
 String s4= ev.getRole();
 String s5= ev.getAccess();
 String s6= ev.getImage();
 String s7= ""+ev.getCin();
 /*int s8=Integer.parseInt(ev.getPassword().getText());
 ,Integer.parseInt(tfCin.getText())
 int CIN5 = Float.parseFloat(ev.getCin().toString());
 int Password5 = Float.parseFloat(ev.getPassword().toString());*/
   TextField tfNom1 = new TextField(s11);
        TextField tfPrenom1= new TextField(s21);
      TextField tfNom = new TextField(s1);
        TextField tfPrenom= new TextField(s2);
         TextField tfPassword = new TextField(s3);
        TextField tfDatenaissance= new TextField(s);
         TextField tfRole = new TextField(s4);
        TextField tfAccess= new TextField(s5);
         TextField tfImage = new TextField(s6);
        TextField tfCin= new TextField(s7);
        System.out.println(ev.getCin()+ev.getDatenaissance());
          Button imgBtn = new Button("parcourir");
        //addStringValue("", imgBtn);
          imgBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    FileUploader fu = new FileUploader("http://127.0.0.1/projetpi/public");

                    //Upload
                    Display.getInstance().openGallery(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent v) {
                            if (v == null || v.getSource() == null) {
                                System.out.println("choisir image fail !");
                                return;
                            }

                            String filePath = ((String) v.getSource()).substring(7);
                            System.out.println(filePath);
                          
                          
                            try {
              
                                fileNameInServer = fu.upload(filePath);
                                 System.out.println(fileNameInServer);
                               tfImage.setText(fileNameInServer);
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }

                        }
                    }, Display.GALLERY_IMAGE);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        });

      
             Button btnValider = new Button("update user"); 
         
        btnValider.addActionListener(( e)-> {
            {  
                try {
                if ((tfCin.getText().length()==0)||(tfNom.getText().length()==0)||(tfPrenom.getText().length()==0)||(tfPassword.getText().length()==0)||(tfDatenaissance.getText().length()==0)||(tfAccess.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                 
                        //SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd");
                     User t = new User(ez, tfNom.getText(),tfPrenom.getText(),Integer.parseInt(tfPassword.getText()),Integer.parseInt(tfCin.getText()),tfAccess.getText(),fileNameInServer,tfDatenaissance.getText());
//(int id, int password, int cin, String nom, String prenom, String datenaissance, String access, String image)
                    if( ServiceUser.getInstance().updateUserf(t))
                    {
                           Dialog.show("Success","Connection accepted",new Command("OK"));
                            
                        
                    }else{
                         Dialog.show("ERROR", "connn refuse", new Command("OK"));
                    }  
                    
                }
                } catch (NumberFormatException x) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                new Affichuser(this,ev,ez).show();
            }
        });
        
         
      addAll(tfNom1,tfPrenom1,tfNom,tfPrenom,tfPassword,tfDatenaissance,/*tfRole,*/tfAccess,tfImage,tfCin,imgBtn,btnValider);
         
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
}