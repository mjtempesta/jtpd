/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.org.java.web;

import org.apache.wicket.Session;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;
import tr.org.java.dao.KisiBean;
import tr.org.java.data.Kisi;

/**
 * @author Administrator
 */
public class KayitPanel extends Panel {

   
    public KayitPanel(String id, ModalWindow window, int yeni) {
        super(id);
        BasitForm basitForm = new BasitForm("basit-form", window, yeni);
        this.add(basitForm);
    }

    public KayitPanel(String id, ModalWindow window) {
        super(id);
        BasitForm basitForm = new BasitForm("basit-form", window);
        this.add(basitForm);
    }
}

/**
 * @author ABA
 */
class BasitForm extends Form {

    TextField ad = null;
    TextField soyad = null;
    Oturum oturum = (Oturum) Session.get();
    Kisi kisi = null;

    public BasitForm(String id, final ModalWindow window) {
        super(id);
        kisi = oturum.getKisi();
        ad = new TextField("ad", new PropertyModel(kisi, "ad"));
        soyad = new TextField("soyad", new PropertyModel(kisi, "soyad"));

        this.add(ad);
        this.add(soyad);

        this.add(new AjaxButton("submit", this) {

            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                KisiBean kisiBean = oturum.getKisiBean();


                // kisi guncelleme
                Kisi kisiGuncelle = new Kisi(kisi.getId(), ad.getModelObject().toString(),
                        soyad.getModelObject().toString());
                kisiBean.setMevcutKisi(kisiGuncelle);
                kisiBean.guncelle();


                // islem tamamlaninca pencereyi kapat
                window.close(target);

            }
        });

        this.add(new AjaxButton("iptal", this) {

            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                window.close(target);

            }
        });

    }

    public BasitForm(String id, final ModalWindow window, final int yeni) {
        super(id);


        kisi = new Kisi();
        ad = new TextField("ad", new PropertyModel(kisi, "ad"));
        soyad = new TextField("soyad", new PropertyModel(kisi, "soyad"));

        this.add(ad);
        this.add(soyad);

        this.add(new AjaxButton("submit", this) {

            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                KisiBean kisiBean = oturum.getKisiBean();

                // yeni kisi ekleme
                Kisi yeniKisi = new Kisi(0, ad.getModelObject().toString(),
                        soyad.getModelObject().toString());

                kisiBean.setMevcutKisi(yeniKisi);
                kisiBean.ekle();

                // islem tamamlaninca pencereyi kapat
                window.close(target);
            }
        });

        this.add(new AjaxButton("iptal", this) {

            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                window.close(target);

            }
        });
    }
}
