/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Zgloszenia.Service;


import com.example.Zgloszenia.DateBase.JpaUtils2;
import com.example.Zgloszenia.Repozytory.RepozytoryZgloszenie;
import com.example.Zgloszenia.Zgloszenie;
import java.util.List;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Tomasz
 */
@Slf4j
@Service
public class ZamowieniaService {
@Autowired
    RepozytoryZgloszenie repozytory;
    @PostConstruct
    public void metoda()
    {
        SessionFactory factory = JpaUtils2.getSessionFactory();
        Session session = factory.openSession();
        
        List<Zgloszenie>lista=session.createQuery("from Zgloszenie").list();
        session.close();
        factory.close();
        for(Zgloszenie i :lista)
        {
            repozytory.save(i);
        }
    }
}
