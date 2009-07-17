/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tr.org.java.web;

import org.apache.wicket.Request;
import org.apache.wicket.protocol.http.WebSession;
import tr.org.java.dao.KisiBean;
import tr.org.java.data.Kisi;

/**
 *
 * @author Administrator
 */
public class Oturum extends WebSession {

    private KisiBean kisiBean = new KisiBean();
    private Kisi kisi = new Kisi();

    public Kisi getKisi() {
        return kisi;
    }

    public void setKisi(Kisi kisi) {
        this.kisi = kisi;
    }
   

    public KisiBean getKisiBean() {
        return kisiBean;
    }

    public void setKisiBean(KisiBean kisiBean) {
        this.kisiBean = kisiBean;
    }

    public Oturum(Request request) {
		super(request);
	}

    


}
