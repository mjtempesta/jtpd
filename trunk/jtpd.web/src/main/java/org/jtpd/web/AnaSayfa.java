/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jtpd.web;

import org.apache.wicket.markup.html.WebPage;

/**
 *
 * @author ABA
 */
public class AnaSayfa extends WebPage {

    public AnaSayfa() {
        UstPanel ustPanel = new UstPanel("UstPanel");
        add(ustPanel);

        AltPanel altPanel = new AltPanel("AltPanel");
        add(altPanel);
    }

}
