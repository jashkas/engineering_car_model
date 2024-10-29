package objectOrientedProgramming.polymorphism;

import objectOrientedProgramming.Inheritance.Series;
import objectOrientedProgramming.encapsulation.Movie;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Morf {
    public static void main(String[] args) {
        Movie movie = new Movie("Кунг-фу Панда 4", 2024, new Date());
        Series rick_and_morty = new Series("Рик и Морти", 2023, new Date(), 7, 3);

        List<Movie> movies = Arrays.asList(movie, rick_and_morty);
        for (Movie m : movies) {
            System.out.println(m.toString());  // полиморфный вызов метода
        }
    }
}
