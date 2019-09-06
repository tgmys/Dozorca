/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Zgloszenia.Repozytory;

import com.example.Zgloszenia.Zadania;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author siema
 */
public interface RepozytoryZadania extends JpaRepository<Zadania, Integer>{
 
}
