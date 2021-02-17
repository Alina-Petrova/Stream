/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stream;

import java.util.Arrays;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 *
 * @author tss
 */
public class AppMia {
    
    private static Supplier<Stream<String>> nomi = () -> Stream.of("Paola", "Mario", "Alfonso", "Giuseppe", "Stefania", "Annamaria", "Marco", "Massimo", "Paola", "Cristian", "Rosella", "Alfonso");
    
    public static void main(String[] args) {
        
        System.out.println(nomi.get().filter(v -> v.length() > 3).distinct().sorted());
          
    }
    
}
