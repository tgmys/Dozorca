/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Zgloszenia.Service;

import com.example.Zgloszenia.DateBase.JpaUtils3;
import com.example.Zgloszenia.Repozytory.RepozytoryZadania;
import com.example.Zgloszenia.Zadania;
import java.util.List;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author siema
 */
@Slf4j
@Service
public class ZadaniaService {
    @Autowired
    RepozytoryZadania repozytory2;
    @PostConstruct
    public void metoda()
    {
        SessionFactory factory2 = JpaUtils3.getSessionFactory();
        Session session2 = factory2.openSession();
        
        List<Zadania>lista=session2.createQuery("from Zadania").list();
        session2.close();
        factory2.close();
        for(Zadania i :lista)
        {
            repozytory2.save(i);
        }
    }
}
