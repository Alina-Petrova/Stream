/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stream;

import java.lang.module.ModuleFinder;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.function.Supplier;
import java.util.stream.Stream;
import javax.sound.sampled.BooleanControl;

/**
 *
 * @author alfonso
 */
public class App {

    private static Supplier<Stream<String>> nomi = () -> Stream.of("Paola", "Mario", "Alfonso", "Giuseppe", "Stefania", "Annamaria", "Marco", "Massimo", "Paola", "Cristian", "Rosella", "Alfonso");

    public static void main(String[] args) {
        


        nomi.get().distinct()
                .sorted()
                .filter(v -> v.startsWith("A"))
                .forEach(System.out::println);

        Object[] ris1 = nomi.get().filter(v -> v.length() > 3).distinct().sorted().toArray();

        System.out.println(Arrays.toString(ris1));

        Object[] ris2 = nomi.get().filter(v -> v.length() > 5).distinct().sorted(Comparator.reverseOrder()).toArray();

        System.out.println(Arrays.toString(ris2));

        long ris3 = nomi.get().filter(v -> v.length() > 5).distinct().count();

        System.out.println(ris3);

        Optional<String> max = nomi.get().filter(v -> v.length() > 5).distinct().max((v1, v2) -> v1.compareTo(v2));

        System.out.println(max.orElse("non presente"));

        nomi.get().distinct().map(v -> v.length()).max((v1, v2) -> v1.compareTo(v2)).ifPresent(System.out::println);

        nomi.get().distinct().mapToInt(v -> v.length()).map(v -> v * v).max().ifPresent(System.out::println);
        
        List<String> colori = Arrays.asList("bianco","nero","blu","arancio");
        
        System.out.println(colori.stream().filter(v -> v.equals("giallo")).findFirst().orElse("non presente"));
        
        int ris4 = nomi.get().distinct().mapToInt(v -> v.length()).sum();
        
         System.out.println(ris4);
         
        OptionalDouble average = nomi.get().distinct().mapToInt(v -> v.length()).average();
        
         System.out.println(average.getAsDouble());
         
        
    }

}
