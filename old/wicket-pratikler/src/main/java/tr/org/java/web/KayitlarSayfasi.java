/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.org.java.web;

import java.util.List;
import org.apache.wicket.Session;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.navigation.paging.AjaxPagingNavigator;
import org.apache.wicket.behavior.SimpleAttributeModifier;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import tr.org.java.data.Kisi;

/**
 *
 * @author Administrator
 */
public class KayitlarSayfasi extends WebPage {

    private WebMarkupContainer datacontainer = null;

    public KayitlarSayfasi() {
        WebMarkupContainer css = new WebMarkupContainer("css");
        add(css);

        //veri tutacagi
        datacontainer = new WebMarkupContainer("veri");
        datacontainer.setOutputMarkupId(true);
        add(datacontainer);

        final ModalWindow modal = new ModalWindow("modalyeni");
        datacontainer.add(modal);

        datacontainer.add(new AjaxLink("yeni") {

            @Override
            public void onClick(AjaxRequestTarget target) {
               
                modal.setContent(new KayitPanel(modal.getContentId(), modal, 0));
                modal.setResizable(false);
                modal.setInitialWidth(30);
                modal.setInitialHeight(15);
                modal.setWidthUnit("em");
                modal.setHeightUnit("em");

                modal.setWindowClosedCallback(new ModalWindow.WindowClosedCallback() {

                   // tabloyu guncelle
                   public void onClose(AjaxRequestTarget target) {
                        target.addComponent(datacontainer);
                    }
                });
                
                modal.show(target);
            }
        });

       


        // KisiBean icersinden kisi listesini al
        final Oturum oturum = (Oturum) Session.get();
        final List kisiler = (oturum.getKisiBean().getKisiler());

        // ekrana yapistir, 10 ar 10 ar listeleyecek
        PageableListView listview = new PageableListView("rows", kisiler, 10) {

            @Override
            protected void populateItem(ListItem item) {
                final Kisi kisi = (Kisi) item.getModelObject();
                item.add(new Label("ad", kisi.getAd()));
                item.add(new Label("soyad", kisi.getSoyad()));

                //guncelleme baglantisina(link) tiklandiginda ne olacagini buraya yaziyoruz

                final ModalWindow modal = new ModalWindow("modal");
                item.add(modal);
                oturum.setKisi(kisi);
                modal.setContent(new KayitPanel(modal.getContentId(), modal));
                modal.setResizable(false);
                modal.setInitialWidth(30);
                modal.setInitialHeight(15);
                modal.setWidthUnit("em");
                modal.setHeightUnit("em");

                modal.setWindowClosedCallback(new ModalWindow.WindowClosedCallback() {

                    public void onClose(AjaxRequestTarget target) {
                        target.addComponent(datacontainer);
                    }
                });

                item.add(new AjaxLink("guncelle") {

                    @Override
                    public void onClick(AjaxRequestTarget target) {
                        oturum.setKisi(kisi);
                        modal.show(target);
                    }
                });

                // ---


                //sil baglantisina(link) tiklandiginda ne olacagini buraya yaziyoruz
                Link silBaglantisi = new Link("sil") {

                    @Override
                    public void onClick() {
                        kisiler.remove(kisi);
                    }
                };
                silBaglantisi.add(new SimpleAttributeModifier("onclick", "return confirm('emin misin?');"));
                item.add(silBaglantisi);
            // ---


            }
        };

        datacontainer.add(listview);
        datacontainer.add(new AjaxPagingNavigator("navigator", listview));
        datacontainer.setVersioned(false);

        
    }
}
