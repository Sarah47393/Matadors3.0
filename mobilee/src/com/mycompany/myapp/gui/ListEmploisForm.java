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
import com.codename1.ui.Label;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;


/**
 *
 * @author HP
 */





/**
 *
 * @author bhk
 */
public class ListEmploisForm extends Form {

    public ListEmploisForm(Form previous) {
        setTitle("List Emplois");
           setLayout(BoxLayout.y());
    //String url1 = "http://127.0.0.1/projetpi/public/Uploads/image";
 Form current;
        SpanLabel sp = new SpanLabel();
        //sp.setText(ServiceUser.getInstance().getAllUsers().toString);
         ArrayList<Emplois> list;
        list = new ArrayList<>();
        list = ServiceEmplois.getInstance().getAllEmplois();
         for ( Emplois ev : list) {
               Container c3 = new Container(BoxLayout.y());
           /*   Image placeholder = Image.createImage(200, 200);
            EncodedImage enc = EncodedImage.createFromImage(placeholder, false);*/
        /*    Label Delete1 = new Label(" ");
        Delete1.setUIID("NewsTopLine");
        Style supprmierStyle = new Style(Delete1.getUnselectedStyle());
        supprmierStyle.setFgColor(0xf21f1f);
        
        FontImage suprrimerImage = FontImage.createMaterial(FontImage.MATERIAL_DELETE, supprmierStyle);
        Delete1.setIcon(suprrimerImage);
        Delete1.setTextPosition(RIGHT);
             
          Delete1.addPointerPressedListener(l -> {
            
            Dialog dig = new Dialog("Suppression");
            
            if(dig.show("Suppression","Vous voulez supprimer ce reclamation ?","Annuler","Oui")) {
                dig.dispose();
            }
            else {
                dig.dispose();
                 }
                //n3ayto l suuprimer men service Reclamation
                if(ServiceEmplois.getInstance().deleteEmplois(ev.getId())) {
                    new ListEmploisForm(previous).show();
                }
           
        });*/
          /*  URLImage urlim = URLImage.createToStorage(enc, ev.getImage(), url1 + "/" + ev.getImage());
            ImageViewer imgV = new ImageViewer();
            imgV.setImage(urlim);*/
          
             SpanLabel sp0 = new SpanLabel("id: " + "  " + ev.getId());
              SpanLabel spl = new SpanLabel("nom: " + "  " + ev.getNom());
                SpanLabel spl2 = new SpanLabel("prenom: " + "  " + ev.getPrenom());
                SpanLabel sp7 = new SpanLabel("cin: " + "  " + ev.getCin());
                SpanLabel sp8 = new SpanLabel("Dfin: " + "  " + ev.getDfin());
                SpanLabel sp6 = new SpanLabel("Ddebut: " + "  " + ev.getDdebut());
               // SpanLabel sp4 = new SpanLabel("User: " + "  " + ev.getU());
                //SpanLabel sp5 = new SpanLabel("image: " + "  " + ev.getImage());
                //SpanLabel sp2 = new SpanLabel("password: " + "  " + ev.getPassword());
      
          addAll(sp0,spl,spl2,sp7,sp8,sp6);
                getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
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

            add(c3);
        }
            
    }

}
