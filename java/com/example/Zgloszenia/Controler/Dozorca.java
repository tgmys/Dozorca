/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Zgloszenia.Controler;

import com.example.Zgloszenia.DateBase.JpaUtils2;
import com.example.Zgloszenia.DateBase.JpaUtils3;
import com.example.Zgloszenia.Repozytory.RepozytoryZadania;
import com.example.Zgloszenia.Repozytory.RepozytoryZgloszenie;
import com.example.Zgloszenia.Zadania;
import com.example.Zgloszenia.Zgloszenie;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.datefield.DateTimeResolution;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.InlineDateTimeField;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.renderers.ButtonRenderer;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author siema
 */
@SpringUI(path = "/dozorca")
public class Dozorca extends UI {
    
    Grid<Zgloszenie> tabela = new Grid();
    Grid<Zadania> tabela2= new Grid();
    
    @Autowired
    RepozytoryZgloszenie repozytory;
    @Autowired
    RepozytoryZadania repozytory2;
    
    private Button deleteButton(Zgloszenie zgloszenie) {
        Button button = new Button();
        button.setCaption("Usuń");
        button.addClickListener((Button.ClickEvent e) -> {
            try {
                SessionFactory sessionFactory = JpaUtils2.getSessionFactory();
                Session session = sessionFactory.openSession();
                Transaction transaction = session.beginTransaction();
                session.delete(zgloszenie);
                transaction.commit();
                List<Zgloszenie> zgloszenia = session.createQuery("from Zgloszenie").list();
                session.close();
                sessionFactory.close();
                refreshGrid();
            } catch (Exception ex) {
                System.out.println(ex.toString());
            }
        });
        return button;
    }
    private Button deleteButton2(Zadania zadania) {
        Button button2 = new Button();
        button2.setCaption("Usuń");
        button2.addClickListener((Button.ClickEvent e) -> {
            try {
                SessionFactory sessionFactory2 = JpaUtils3.getSessionFactory();
                Session session2 = sessionFactory2.openSession();
                Transaction transaction2 = session2.beginTransaction();
                session2.delete(zadania);
                transaction2.commit();
                List<Zadania> zadanie = session2.createQuery("from Zadania").list();
                session2.close();
                sessionFactory2.close();
                refreshGrid2();
            } catch (Exception ex) {
                System.out.println(ex.toString());
            }
        });
        return button2;
    }

    private void refreshGrid() {
        SessionFactory sessionFactory = JpaUtils2.getSessionFactory();
        Session session = sessionFactory.openSession();
        List<Zgloszenie> zgloszenia = session.createQuery("from Zgloszenie").list();
        session.close();
        sessionFactory.close();
        ListDataProvider<Zgloszenie> dataPro = DataProvider.ofCollection(zgloszenia);
        dataPro.refreshAll();
        tabela.setDataProvider(dataPro);
        tabela.setSizeFull();
    }
    private void refreshGrid2() {
        SessionFactory sessionFactory2 = JpaUtils3.getSessionFactory();
        Session session2 = sessionFactory2.openSession();
        List<Zadania> zadania = session2.createQuery("from Zadania").list();
        session2.close();
        sessionFactory2.close();
        ListDataProvider<Zadania> dataPro2 = DataProvider.ofCollection(zadania);
        dataPro2.refreshAll();
        tabela2.setDataProvider(dataPro2);
        tabela2.setSizeFull();
    }

    @Override
    protected void init(VaadinRequest request) {
        String haslo = "test";
        VerticalLayout pionowo = new VerticalLayout();
        HorizontalLayout poziomo = new HorizontalLayout();
        HorizontalLayout poziomo2 = new HorizontalLayout();
        
        InlineDateTimeField kal = new InlineDateTimeField();
        kal.setValue(LocalDateTime.now());
        kal.setLocale(Locale.GERMAN);
        kal.setResolution(DateTimeResolution.MINUTE);
        kal.addValueChangeListener(event ->
        kal.getValue().toString());
        
        TextField podajNrMieszkania= new TextField();
        podajNrMieszkania.setPlaceholder("Podaj nr Mieszkania");
        TextField podajOpis= new TextField();
        podajOpis.setPlaceholder("Opis problemu");
        podajOpis.setSizeFull();
        Button przycisk3= new Button("Dodaj Zadanie do Harmonogramu");
        przycisk3.addClickListener(e -> {
            try {
                String data=kal.getValue().toString();
                String nr_mieszkania=podajNrMieszkania.getValue();
                String opis=podajOpis.getValue();
                int nrmiesz= Integer.parseInt(nr_mieszkania);
                              
                Zadania h = new Zadania();
                h.setData(data);
                h.setNr_mieszkania(nrmiesz);
                h.setOpis(opis);
                
                repozytory2.save(h);
                SessionFactory factory2 = JpaUtils3.getSessionFactory();
                Session session2 = factory2.openSession();
                session2.beginTransaction();
                session2.save(h);
                session2.getTransaction().commit();
                session2.close();
                factory2.close();
                refreshGrid2();
                Notification.show("Dodano zadanie", Notification.Type.HUMANIZED_MESSAGE);
            } catch (Exception ex) {
                Notification.show("Nie dodano zadania", Notification.Type.ERROR_MESSAGE);
            }
        });
        
        //tabela zgloszen
        SessionFactory sessionFactory = JpaUtils2.getSessionFactory();
        Session session = sessionFactory.openSession();
        List<Zgloszenie> zgloszenia = session.createQuery("from Zgloszenie").list();
        session.close();
        sessionFactory.close();

        tabela.addColumn(Zgloszenie::getId).setCaption("Nr zgloszenia").setWidth(150);
        tabela.addColumn(Zgloszenie::getNazwisko).setCaption("Nazwisko").setWidth(100);
        tabela.addColumn(Zgloszenie::getImie).setCaption("Imie").setWidth(100);
        tabela.addColumn(Zgloszenie::getNr_mieszkania).setCaption("Nr_mieszkania").setWidth(150);
        tabela.addColumn(Zgloszenie::getTel_kontaktowy).setCaption("telefon").setWidth(150);
        tabela.addColumn(Zgloszenie::getOpis_problemu).setCaption("problem").setWidthUndefined();
        tabela.addComponentColumn(this::deleteButton).setWidth(100);

        ListDataProvider<Zgloszenie> dataPro = DataProvider.ofCollection(zgloszenia);
        dataPro.refreshAll();
        tabela.setDataProvider(dataPro);
        tabela.setSizeFull();

        //tabela zadan
        SessionFactory sessionFactory2 = JpaUtils3.getSessionFactory();
        Session session2 = sessionFactory2.openSession();
        List<Zadania> zadania = session2.createQuery("from Zadania").list();
        session2.close();
        sessionFactory2.close();

        //tabela2.addColumn(Zadania::getId).setCaption("Nr zadania");
        tabela2.addColumn(Zadania::getData).setCaption("Data i godzina").setWidth(250);
        tabela2.addColumn(Zadania::getNr_mieszkania).setCaption("Nr_mieszkania").setWidth(150);
        tabela2.addColumn(Zadania::getOpis).setCaption("Opis zadania").setWidthUndefined();
        tabela2.addComponentColumn(this::deleteButton2).setWidth(100);

        ListDataProvider<Zadania> dataPro2 = DataProvider.ofCollection(zadania);
        dataPro2.refreshAll();
        tabela2.setDataProvider(dataPro2);
        tabela2.setSizeFull();

        PasswordField PodajHaslo = new PasswordField();
        Label napis = new Label("Podaj hasło dozorcy");
        Button zaloguj = new Button("Zaloguj");
        pionowo.addComponents(napis, PodajHaslo, zaloguj);

        Label napis5 = new Label("Witaj Dozorco, tabela poniżej przedstawia listę odebranych zgłoszeń");
        Label napis6 = new Label("Harmonogram zadań !");
        Label napis7 = new Label("Aby dodać zadanie do harmonogramu, wybierz dzień oraz godzinę, podaj numer mieszkania oraz opis zadania do zrobienia");
        Label napis4 = new Label("Aby sprawdzić czy odebrano nowe zgłoszenia: ");
        Button odswiez = new Button("Odswież Zgłoszenia");
        odswiez.addClickListener(f -> {
           refreshGrid();

        });

        
        zaloguj.addClickListener(e -> {
            try {
                if (PodajHaslo.getValue().equals(haslo)) {
                    Notification.show("Zalogowano", Notification.Type.HUMANIZED_MESSAGE);
                    pionowo.removeComponent(napis);
                    pionowo.removeComponent(zaloguj);
                    pionowo.removeComponent(PodajHaslo);
                    
                    poziomo2.addComponents(napis4,odswiez);
                    pionowo.addComponents(napis5,tabela, poziomo2,napis6,tabela2,napis7,kal,podajNrMieszkania,podajOpis,przycisk3);

                } else {
                    Notification.show("Zle haslo", Notification.Type.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                Notification.show("blad", Notification.Type.ERROR_MESSAGE);
            }
        });

        setContent(pionowo);
    }
}
