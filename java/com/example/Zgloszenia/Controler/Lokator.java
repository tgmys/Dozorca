/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Zgloszenia.Controler;

import com.example.Zgloszenia.DateBase.JpaUtils2;
import com.example.Zgloszenia.Repozytory.RepozytoryZgloszenie;
import com.example.Zgloszenia.Zgloszenie;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author siema
 */
@SpringUI(path="/lokator")
public class Lokator extends UI{
    @Autowired
    RepozytoryZgloszenie repozytory;
    
    @Override
    protected void init(VaadinRequest request)
    {
        VerticalLayout pionowo = new VerticalLayout();
        Label label3 = new Label("Witaj w formularzu zgłoszeniowym problemów, wypełnij wszystkie pola i wyślij zgłoszenie.");
        Label label4 = new Label("Dozorca skontakuje się z Tobą aby ustalić dzień wizyty oraz dopytać o ewentualne szczegóły związane ze zgłoszonym problemem.");
        Label label5 = new Label("W przypadku problemów, skontakuj się z Dozorcą pod numerem tel. 700123321");
        TextField podajNazwisko= new TextField();
        podajNazwisko.setPlaceholder("Podaj Nazwisko");
        TextField podajImie= new TextField();
        podajImie.setPlaceholder("Podaj Imie");
        TextField podajNrMieszkania= new TextField();
        podajNrMieszkania.setPlaceholder("Podaj nr Mieszkania");
        TextField podajTelKontaktowy= new TextField();
        podajTelKontaktowy.setPlaceholder("Podaj Tel. Kont.");
        Label label1 = new Label("Numer telefonu podajemy bez kierunkowego, cyfr nie odzielamy spacją, np: 500500500");
        TextArea podajProblem= new TextArea();    
        podajProblem.setPlaceholder("Opisz problem");
        podajProblem.setSizeFull();
        Label label2 = new Label("Jeżeli masz więcej niż 1 problem, opisz je wszystkie w jednym zgłoszeniu, nie twórz kolejnego");
        Button przycisk= new Button("Dodaj Zgloszenie");
        przycisk.addClickListener(e -> {
            try {
                String nazwisko=podajNazwisko.getValue();
                String imie=podajImie.getValue();
                String nrMieszkania=podajNrMieszkania.getValue();
                int nrmiesz= Integer.parseInt(nrMieszkania);
                String telKontaktowy=podajTelKontaktowy.getValue();
               
                int telkon= Integer.parseInt(telKontaktowy);
                String opisProblemu=podajProblem.getValue();
                              
                Zgloszenie s = new Zgloszenie();
                s.setNazwisko(nazwisko);
                s.setImie(imie);
                s.setNr_mieszkania(nrmiesz);
                s.setTel_kontaktowy(telkon);
                s.setOpis_problemu(opisProblemu);
                
                repozytory.save(s);
                SessionFactory factory = JpaUtils2.getSessionFactory();
                Session session = factory.openSession();
                session.beginTransaction();
                session.save(s);
                session.getTransaction().commit();
                session.close();
                factory.close();
                Notification.show("Dodano zgloszenie", Notification.Type.HUMANIZED_MESSAGE);
            } catch (Exception ex) {
                Notification.show("Nie zgloszono, sprawdz poprawnosc wpisanych danych", Notification.Type.ERROR_MESSAGE);
            }
        });
        pionowo.addComponents(label3,label4,podajNazwisko,podajImie,podajNrMieszkania,podajTelKontaktowy,label1,podajProblem,label2,przycisk,label5);
        setContent(pionowo);
        
        
    }
    
    
}
