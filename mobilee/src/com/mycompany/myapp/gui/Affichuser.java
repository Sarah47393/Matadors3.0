/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.ServiceUser;
import java.util.ArrayList;

/**
 *
 * @author HP
 */



import com.codename1.components.SpanLabel;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.mycompany.myapp.services.ServiceUser;
import java.util.ArrayList;
import com.mycompany.myapp.entities.User;
import com.codename1.ui.Image;
import com.codename1.ui.URLImage;
import com.codename1.components.ImageViewer;
import com.codename1.components.ToastBar;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.Container;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.services.ServiceEmplois;



/**
 *
 * @author bhk
 */
public class Affichuser extends Form {

    public Affichuser(Form previous,User  t,int x) {
        setTitle("List Users");
         SpanLabel sp = new SpanLabel();
           setLayout(BoxLayout.y());
    String url1 = "http://127.0.0.1/projetpi/public/Uploads/image";
 Form current;
  Container c3 = new Container(BoxLayout.y());
              Image placeholder = Image.createImage(200, 200);
            EncodedImage enc = EncodedImage.createFromImage(placeholder, false);
            URLImage urlim = URLImage.createToStorage(enc, t.getImage(), url1 + "/" + t.getImage());
            ImageViewer imgV = new ImageViewer();
            imgV.setImage(urlim);
             
              SpanLabel spl = new SpanLabel("nom: " + "  " + t.getNom());
                SpanLabel spl2 = new SpanLabel("prenom: " + "  " + t.getPrenom());
                SpanLabel sp7 = new SpanLabel("cin: " + "  " + t.getCin());
                SpanLabel sp8 = new SpanLabel("role: " + "  " + t.getRole());
                SpanLabel sp6 = new SpanLabel("access: " + "  " + t.getAccess());
                SpanLabel sp4 = new SpanLabel("date: " + "  " + t.getDatenaissance());
                SpanLabel sp5 = new SpanLabel("image: " + "  " + t.getImage());
                SpanLabel sp2 = new SpanLabel("password: " + "  " + t.getPassword());
      
          addAll(spl,spl2,sp7,sp8,sp6,sp4,sp5,sp2,imgV);
           getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                                                    Button Delete1 = new Button("Delete");
                                     Delete1.getAllStyles().setBgColor(0xF36B08);
            Delete1.addActionListener(e -> {
                Dialog alert = new Dialog("Attention");
                SpanLabel message = new SpanLabel("Etes-vous sur de vouloir supprimer cet événement?\nCette action est irréversible!");
                alert.add(message);
                Button ok = new Button("Confirmer");
                Button cancel = new Button(new Command("Annuler"));
                //User clicks on ok to delete account
                ok.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent evt) {
                        ServiceUser.getInstance().deleteUserf((int) t.getId());
                        alert.dispose();
                        ToastBar.Status status = ToastBar.getInstance().createStatus();
                        status.setShowProgressIndicator(true);
                        //status.setIcon(res.getImage("done.png").scaledSmallerRatio(Display.getInstance().getDisplayWidth()/10, Display.getInstance().getDisplayWidth()/15));
                        status.setMessage("emplois SUPPRIME AVEC SUCCES");
                        status.setExpires(100);
                        status.show();

                        refreshTheme();
                         new ListUserForm(previous).show();
                    }

                }
                );

                alert.add(cancel);
                alert.add(ok);
                alert.showDialog();

                //new ListArticle(previous).show();
            });

                                        Button lModifier = new Button("Modifier");
                                         int exv=t.getId();
                                         
                                        lModifier.addActionListener(( e)-> new EditUserFront(this,t,exv).show() );


 //c3.add(btnDetails);
            add(Delete1);
            add(lModifier);
       
        //sp.setText(ServiceUser.getInstance().getAllUsers().toString);
     /*    ArrayList<User> list;
        list = new ArrayList<>();
        list = ServiceUser.getInstance().getAUser(t,x);
         for ( User ev : list) {
               Container c3 = new Container(BoxLayout.y());
              Image placeholder = Image.createImage(200, 200);
            EncodedImage enc = EncodedImage.createFromImage(placeholder, false);
            URLImage urlim = URLImage.createToStorage(enc, ev.getImage(), url1 + "/" + ev.getImage());
            ImageViewer imgV = new ImageViewer();
            imgV.setImage(urlim);
             
              SpanLabel spl = new SpanLabel("nom: " + "  " + ev.getNom());
                SpanLabel spl2 = new SpanLabel("prenom: " + "  " + ev.getPrenom());
                SpanLabel sp7 = new SpanLabel("cin: " + "  " + ev.getCin());
                SpanLabel sp8 = new SpanLabel("role: " + "  " + ev.getRole());
                SpanLabel sp6 = new SpanLabel("access: " + "  " + ev.getAccess());
                SpanLabel sp4 = new SpanLabel("date: " + "  " + ev.getDatenaissance());
                SpanLabel sp5 = new SpanLabel("image: " + "  " + ev.getImage());
                SpanLabel sp2 = new SpanLabel("password: " + "  " + ev.getPassword());
      
          addAll(spl,spl2,sp7,sp8,sp6,sp4,sp5,sp2,imgV);
                getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                        //Button btnDetails = new Button("Détails");
                             //   btnDetails.addActionListener(p -> new DetailsUser(current, ev).show());

                                              Button Delete1 = new Button("Delete");
                                     Delete1.getAllStyles().setBgColor(0xF36B08);
            Delete1.addActionListener(e -> {
                Dialog alert = new Dialog("Attention");
                SpanLabel message = new SpanLabel("Etes-vous sur de vouloir supprimer cet événement?\nCette action est irréversible!");
                alert.add(message);
                Button ok = new Button("Confirmer");
                Button cancel = new Button(new Command("Annuler"));
                //User clicks on ok to delete account
                ok.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent evt) {
                        ServiceUser.getInstance().deleteUserf((int) ev.getId());
                        alert.dispose();
                        ToastBar.Status status = ToastBar.getInstance().createStatus();
                        status.setShowProgressIndicator(true);
                        //status.setIcon(res.getImage("done.png").scaledSmallerRatio(Display.getInstance().getDisplayWidth()/10, Display.getInstance().getDisplayWidth()/15));
                        status.setMessage("emplois SUPPRIME AVEC SUCCES");
                        status.setExpires(100);
                        status.show();

                        refreshTheme();
                         new ListUserForm(previous).show();
                    }

                }
                );

                alert.add(cancel);
                alert.add(ok);
                alert.showDialog();

                //new ListArticle(previous).show();
            });

                                        Button lModifier = new Button("Modifier");
                                         int exv=ev.getId();
                                         
                                        lModifier.addActionListener(( e)-> new EditUserFront(this,ev,exv).show() );


 //c3.add(btnDetails);
            c3.add(Delete1);
            c3.add(lModifier);
         System.out.println("");

            add(c3);
        }
            
    }*/

}}
