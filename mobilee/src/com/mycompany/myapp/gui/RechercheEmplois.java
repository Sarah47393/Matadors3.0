/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Emplois;
import com.mycompany.myapp.services.ServiceEmplois;
import java.util.ArrayList;

/**
 *
 * @author HP
 */

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.entities.Emplois;
import com.mycompany.myapp.services.ServiceEmplois;
import java.util.ArrayList;
import com.codename1.ui.Dialog;
import com.codename1.ui.Font;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.UIManager;
import com.mycompany.myapp.services.ServiceUser;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;


/**
 *
 * @author HP
 */





/**
 *
 * @author bhk
 */
public class RechercheEmplois extends Form {
ArrayList<Emplois> listp;
 String t="";
  // Button view = new Button("View");
    
   Container list = new Container(BoxLayout.y());
    Container c = new Container(BoxLayout.x());
    public RechercheEmplois(Form previous) {
        setTitle("List Emplois");
           setLayout(BoxLayout.y());
    //String url1 = "http://127.0.0.1/projetpi/public/Uploads/image";
    
       Toolbar.setGlobalToolbar(true);
Style s = UIManager.getInstance().getComponentStyle("Title");
 Container mainc = new Container(BoxLayout.y());
TextField searchField = new TextField("", "Search"); // <1>
searchField.getHintLabel().setUIID("Title");
searchField.setUIID("Title");
searchField.getAllStyles().setAlignment(Component.LEFT);

getToolbar().setTitleComponent(searchField);

FontImage searchIcon = FontImage.createMaterial(FontImage.MATERIAL_SEARCH, s);




searchField.addDataChangeListener((i1, i2) -> { // <2>
     t = searchField.getText();
    //System.out.println("t here "+t);
    mainc.removeAll();
     removeAll();
     if(t.length()>0)
    {
                //System.out.println("t xxxxx "+t);
               
               
                  mainc.removeAll();
                   System.out.println(t);
             //  listp = new ArrayList<>();
                 listp=ServiceEmplois.getInstance().RechercheE(t);
                 for(Emplois ev : listp) {
                              Container c3 = new Container(BoxLayout.y());
         
         
             SpanLabel sp0 = new SpanLabel("id: " + "  " + ev.getId());
              SpanLabel spl = new SpanLabel("nom: " + "  " + ev.getNom());
                SpanLabel spl2 = new SpanLabel("prenom: " + "  " + ev.getPrenom());
                SpanLabel sp7 = new SpanLabel("cin: " + "  " + ev.getCin());
                SpanLabel sp8 = new SpanLabel("Dfin: " + "  " + ev.getDfin());
                SpanLabel sp6 = new SpanLabel("Ddebut: " + "  " + ev.getDdebut());
    
      
          c3.addAll(sp0,spl,spl2,sp7,sp8,sp6);
               // getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                        Button btnDetails = new Button("Détails");
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
                        ServiceEmplois.getInstance().deleteEmplois((int) ev.getId());
                        alert.dispose();
                        ToastBar.Status status = ToastBar.getInstance().createStatus();
                        status.setShowProgressIndicator(true);
                        //status.setIcon(res.getImage("done.png").scaledSmallerRatio(Display.getInstance().getDisplayWidth()/10, Display.getInstance().getDisplayWidth()/15));
                        status.setMessage("emplois SUPPRIME AVEC SUCCES");
                        status.setExpires(100);
                        status.show();

                        refreshTheme();
                         new ListEmploisForm(previous).show();
                    }

                }
               
                );
                alert.add(cancel);
                alert.add(ok);
                alert.showDialog();

                //new ListArticle(previous).show();
            });
 //Delete1.addActionListener(p -> new DetailsUser(current, ev).show());
 // Delete1.addActionListener(e-> new ServiceEmplois.deleteEmplois(ev.getId()));
                                        Button lModifier = new Button("Modifier");
                                         int exv=ev.getId();
                                         
                                        lModifier.addActionListener(( e)-> new EditEmplois(this,ev,exv).show() );
       


 c3.add(btnDetails);
            c3.add(Delete1);
            c3.add(lModifier);
         System.out.println("");

            mainc.add(c3);
          
 
                      }
                    add(mainc);
  
    }
    else if(t.length()==0)
            {
               //System.out.println("t xxxxx "+t);
               t="";
             
               
                  mainc.removeAll();
                  
             //  listp = new ArrayList<>();
                 listp=ServiceEmplois.getInstance().getAllEmplois();
                 for(Emplois ev : listp) {
                              Container c3 = new Container(BoxLayout.y());
         
         
             SpanLabel sp0 = new SpanLabel("id: " + "  " + ev.getId());
              SpanLabel spl = new SpanLabel("nom: " + "  " + ev.getNom());
                SpanLabel spl2 = new SpanLabel("prenom: " + "  " + ev.getPrenom());
                SpanLabel sp7 = new SpanLabel("cin: " + "  " + ev.getCin());
                SpanLabel sp8 = new SpanLabel("Dfin: " + "  " + ev.getDfin());
                SpanLabel sp6 = new SpanLabel("Ddebut: " + "  " + ev.getDdebut());
    
      
          c3.addAll(sp0,spl,spl2,sp7,sp8,sp6);
               // getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                        Button btnDetails = new Button("Détails");
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
                        ServiceEmplois.getInstance().deleteEmplois((int) ev.getId());
                        alert.dispose();
                        ToastBar.Status status = ToastBar.getInstance().createStatus();
                        status.setShowProgressIndicator(true);
                        //status.setIcon(res.getImage("done.png").scaledSmallerRatio(Display.getInstance().getDisplayWidth()/10, Display.getInstance().getDisplayWidth()/15));
                        status.setMessage("emplois SUPPRIME AVEC SUCCES");
                        status.setExpires(100);
                        status.show();

                        refreshTheme();
                         new ListEmploisForm(previous).show();
                    }

                }
                                );

                alert.add(cancel);
                alert.add(ok);
                alert.showDialog();

                //new ListArticle(previous).show();
            });
 //Delete1.addActionListener(p -> new DetailsUser(current, ev).show());
 // Delete1.addActionListener(e-> new ServiceEmplois.deleteEmplois(ev.getId()));
                                        Button lModifier = new Button("Modifier");
                                         int exv=ev.getId();
                                         
                                        lModifier.addActionListener(( e)-> new EditEmplois(this,ev,exv).show() );
       


 c3.add(btnDetails);
            c3.add(Delete1);
            c3.add(lModifier);
         System.out.println("");

            mainc.add(c3);
          
 
                      }
                    add(mainc);
            }
         
    
    getContentPane().animateLayout(250);
});
getToolbar().addCommandToRightBar("", searchIcon, (e) -> {
    searchField.startEditingAsync(); // <4>
});

      t="";
             
               
                  mainc.removeAll();
                  
             //  listp = new ArrayList<>();
                 listp=ServiceEmplois.getInstance().getAllEmplois();
                 for(Emplois ev : listp) {
                              Container c3 = new Container(BoxLayout.y());
         
         
             SpanLabel sp0 = new SpanLabel("id: " + "  " + ev.getId());
              SpanLabel spl = new SpanLabel("nom: " + "  " + ev.getNom());
                SpanLabel spl2 = new SpanLabel("prenom: " + "  " + ev.getPrenom());
                SpanLabel sp7 = new SpanLabel("cin: " + "  " + ev.getCin());
                SpanLabel sp8 = new SpanLabel("Dfin: " + "  " + ev.getDfin());
                SpanLabel sp6 = new SpanLabel("Ddebut: " + "  " + ev.getDdebut());
    
      
          c3.addAll(sp0,spl,spl2,sp7,sp8,sp6);
               // getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
                        Button btnDetails = new Button("Détails");
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
                        ServiceEmplois.getInstance().deleteEmplois((int) ev.getId());
                        alert.dispose();
                        ToastBar.Status status = ToastBar.getInstance().createStatus();
                        status.setShowProgressIndicator(true);
                        //status.setIcon(res.getImage("done.png").scaledSmallerRatio(Display.getInstance().getDisplayWidth()/10, Display.getInstance().getDisplayWidth()/15));
                        status.setMessage("emplois SUPPRIME AVEC SUCCES");
                        status.setExpires(100);
                        status.show();

                        refreshTheme();
                         new ListEmploisForm(previous).show();
                    }

                }
                                );

                alert.add(cancel);
                alert.add(ok);
                alert.showDialog();

                //new ListArticle(previous).show();
            });
 //Delete1.addActionListener(p -> new DetailsUser(current, ev).show());
 // Delete1.addActionListener(e-> new ServiceEmplois.deleteEmplois(ev.getId()));
                                        Button lModifier = new Button("Modifier");
                                         int exv=ev.getId();
                                         
                                        lModifier.addActionListener(( e)-> new EditEmplois(this,ev,exv).show() );
       


 c3.add(btnDetails);
            c3.add(Delete1);
            c3.add(lModifier);
         System.out.println("");

            mainc.add(c3);
          
 
                      }
                    add(mainc);
            }
}


