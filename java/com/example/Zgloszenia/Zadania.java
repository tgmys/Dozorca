/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.Zgloszenia;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author siema
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Zadania {
    @Id
   @GeneratedValue(strategy = GenerationType.AUTO)  
   private int id;
   private String data;
   private  int Nr_mieszkania; 
   private String Opis;
    
}
