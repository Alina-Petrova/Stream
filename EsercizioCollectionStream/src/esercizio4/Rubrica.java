/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esercizio4;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author alfonso
 */
public class Rubrica implements Iterable<Map.Entry<Voce,Set<Contatto>>>{

    private final Map<Voce, Set<Contatto>> elenco = new HashMap<>();

    public void add(Voce.Tipologia tipoVoce, String nomeVoce, Contatto.Tipo tipoContatto, String contatto) {
        add(new Voce(tipoVoce, nomeVoce), new Contatto(tipoContatto, contatto));
    }

    public void add(Voce v, Contatto c) {
        Objects.requireNonNull(v, "La voce non può essere null");
        Objects.requireNonNull(c, "Il contatto non può essere null");

        BiFunction<Voce, Set<Contatto>, Set<Contatto>> calcolaNuovoValore = (key, valore) -> Stream.concat(valore.stream(), Stream.of(c)).collect(Collectors.toSet());

        elenco.computeIfPresent(v, calcolaNuovoValore);
        elenco.computeIfAbsent(v, k -> Stream.of(c).collect(Collectors.toSet()));
    }

    /**
     * se la voce non esiste, aggiunge la voce e i contatti se la voce esiste
     * aggiunge i contatti alla voce esistente
     *
     * @param v
     * @param contatti
     */
    public void addOld(Voce v, Set<Contatto> contatti) {
        /*
        for (Contatto contatto : contatti) {
            addOld(v, contatto);
        }
         */

        //contatti.forEach(contatto -> addOld(v, contatto));
        contatti.stream().forEach(contatto -> addOld(v, contatto));

    }

    /**
     * se la voce non esiste, aggiunge la voce e il contatto se la voce esiste
     * aggiunge il contatto alla voce esistente
     *
     * @param v
     * @param c
     */
    public void addOld(Voce v, Contatto c) {
        Objects.requireNonNull(v, "La voce non può essere null");
        Objects.requireNonNull(c, "Il contatto non può essere null");
        boolean esiste = elenco.containsKey(v);
        if (esiste) {
            elenco.get(v).add(c);
        } else {
            Set<Contatto> contatti = new HashSet<>();
            contatti.add(c);
            elenco.put(v, contatti);
        }
    }

    public void elimina(Voce.Tipologia tipoVoce, String nomeVoce) {
        elimina(new Voce(tipoVoce, nomeVoce));
    }

    public void elimina(Voce v) {
        elenco.remove(v);
    }

    public void elimina(Voce.Tipologia tipoVoce, String nomeVoce, Contatto.Tipo tipoContatto, String contatto) {
        elimina(new Voce(tipoVoce, nomeVoce), new Contatto(tipoContatto, contatto));
    }

    public void elimina(Voce v, Contatto c) {
        elenco.computeIfPresent(v, (k, value) -> value.stream().filter(item -> !item.equals(c)).collect(Collectors.toSet()));
    }

    public Optional<Map.Entry<Voce, Set<Contatto>>> find(Contatto.Tipo tipoContatto, String contatto) {
        return find(new Contatto(tipoContatto, contatto));
    }

    public Optional<Map.Entry<Voce, Set<Contatto>>> find(Contatto c) {
        return elenco.entrySet().stream().filter(v -> v.getValue().contains(c)).findAny();
    }

    public void stampa() {
        System.out.println("--------------- Rubrica -------------------");
        elenco.keySet().stream().forEach(this::stampaVoce);
    }

    private void stampaVoce(Voce v) {
        System.out.println("\t\t----------- Voce -------------");
        System.out.println("\t\t" + v.getNome() + "\t\t" + v.getT().name());
        elenco.get(v).stream().forEach(System.out::println);
    }

    @Override
    public Iterator<Map.Entry<Voce, Set<Contatto>>> iterator() {
        return elenco.entrySet().iterator();
    }
}
