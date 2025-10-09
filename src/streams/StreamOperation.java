package streams;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class StreamOperation {
    public static void main(String[] args) {
        //Operaciones intermediarias: Recap
        //1. Filter
        List<Integer> edades = Arrays.asList(45, 19, 15, 32, 20, 11, 25, 22, 35, 40, 50, 66, 12, 74);
        Predicate<Integer> mayoriaEdad = n -> n >= 18;

        Double promedioMayorEdad = edades.stream() //1.
                .filter(mayoriaEdad) //2.
                .collect(Collectors.averagingDouble(value -> value)); //3.

        ArrayList<Integer> mayoresEdad = edades.stream() //1.
                .filter(mayoriaEdad) //2.
                .collect(Collectors.toCollection(ArrayList::new));
        //3.
        //System.out.printf("El promedio para mayor de edad es: %f", promedioMayorEdad);

        //2. Map & FlatMap
        // map: Mapear entre elementos
        //flatMap: Mapear entre elementos
        List<String> frases = Arrays.asList("Hola mundo", "Programación funcional con Java");

        Function<String, Stream<String>> getPalabras = s -> Arrays.stream(s.split(" "));

        List<String> palabras = frases.stream() //1. Stream<String> = {"Hola mundo", "Programación funcional con Java"}
                .flatMap(frase -> getPalabras.apply(frase)) //2. Stream<String> = {"hola", "mundo","Programación", ...}
                .toList(); //3.

        System.out.println(palabras);

        //Collectors
        List<String> nombres = Arrays.asList("Ana", "Maria", "Juan", "Pedro", "Lucas", "Mateo","Maria");
        List<String> nombresUpper = nombres.stream()
                .map(String::toUpperCase)
                .toList();

        System.out.println("Upper: ");
        System.out.println(nombresUpper);

        //joining
        String unidos = nombres.stream()
                .collect(Collectors.joining(", "));

        System.out.println("Unidos: ");
        System.out.println(unidos);

        //grouping by
        Map<Integer, List<String>> agrupados = nombres.stream()
                .collect(Collectors.groupingBy(String::length));

        System.out.println("Agrupados: ");
        System.out.println(agrupados);

        //Combinaciones
        System.out.println("Concatenación de operaciones");
        nombres.stream()
                .filter(s -> s.length() > 3)
                .map(String::toUpperCase)
                .distinct()
                .sorted(Comparator.reverseOrder())
                .forEach(System.out::println);
    }


}
