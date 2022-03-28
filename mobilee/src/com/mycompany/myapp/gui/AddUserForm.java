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
import com.codename1.ui.Display;
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
 * @author bhk
 */
public class AddUserForm extends Form{
private String fileNameInServer = "";

    public AddUserForm(Form previous) {
        setTitle("Add a new User");
        setLayout(BoxLayout.y());
        
        TextField tfNom = new TextField("","Nom");
        TextField tfPrenom= new TextField("", "Prenom");
         TextField tfPassword = new TextField("","Password");
        TextField tfDatenaissance= new TextField("", "Datenaissance");
         TextField tfRole = new TextField("","Role");
        TextField tfAccess= new TextField("", "Access");
         TextField tfImage = new TextField("","Image");
        TextField tfCin= new TextField("", "Cin");
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

        Button btnValider = new Button("Add User");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfNom.getText().length()==0)||(tfPrenom.getText().length()==0))
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                else
                {
                    try {
                        //int password, int cin, String nom, String prenom, String datenaissance, String role, String access, String image
                        User t = new User(Integer.parseInt(tfPassword.getText()),Integer.parseInt(tfCin.getText()), tfNom.getText(),tfPrenom.getText(),tfDatenaissance.getText(),tfRole.getText(),tfAccess.getText(),fileNameInServer);
                        if( ServiceUser.getInstance().addUser(t))
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
        
        addAll(tfNom,tfPrenom,tfPassword,tfDatenaissance,tfRole,tfAccess,tfImage,tfCin,imgBtn,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                
    }
    
    
}
