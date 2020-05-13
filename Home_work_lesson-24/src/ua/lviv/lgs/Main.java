package ua.lviv.lgs;

import java.util.Map.Entry;
import java.util.Optional;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws TimeException {

		Cinema cinema = new Cinema(new Time(11, 30), new Time(20, 00));

		Scanner sc = new Scanner(System.in);

		while (true) {
			menu();
			String s = sc.next();
			switch (s) {
			case "1": {
				System.out.println("Enter name of movie: ");
				String nameMovie = sc.next();
				System.out.println("Enter time duration of this movie(hour): ");
				int hour = sc.nextInt();
				System.out.println("Enter time duration of this movie(min): ");
				int min = sc.nextInt();

				cinema.addMovie(new Movie(nameMovie, new Time(hour, min)));
				System.out.println(nameMovie + " is adding");
				break;
			}
			case "2": {
				System.out.println("Enter name of movie");
				String nameM = sc.next();
				Movie movie = cinema.getMoviesLibrary().stream().filter(e -> e.getTitle().equalsIgnoreCase(nameM))
						.findAny().get();
				cinema.removeMovie(movie);
				System.out.println(nameM + " is removed");
				break;
			}
			case "3": {
				System.out.println("Enter name of movie");
				String title = sc.next();
				System.out.println("Enter to starts seance(hour): ");
				int hour = sc.nextInt();
				System.out.println("Enter to starts seance(min): ");
				int min = sc.nextInt();
				System.out.println("Enter day of starts seance: ");
				String day = sc.next().toUpperCase();

				Time time = new Time(hour, min);
				Movie movie = cinema.getMoviesLibrary().stream().filter(e -> e.getTitle().equalsIgnoreCase(title))
						.findAny().get();
				Seance seance = new Seance(movie, time);
				cinema.addSeance(seance, day);
				System.out.println("Seance is adding");
				break;
			}
			case "4": {
				System.out.println("Enter name of movie");
				String name = sc.next();
				System.out.println("Enter day of week: ");
				String day = sc.next().toUpperCase();

				Optional<Entry<Days, Schedule>> findAny = cinema.getSchedules().entrySet().stream()
						.filter(e -> e.getKey().toString().equalsIgnoreCase(day)).findAny();
				if (findAny.isPresent()) {
					Seance seance = findAny.get().getValue().getSeanceSet().stream()
							.filter(x -> x.getMovie().getTitle().equalsIgnoreCase(name)).findAny().get();
					cinema.removeSeance(seance, day);
				}
				System.out.println("Seance is remover");
				break;
			}
			case "5": {
				cinema.getMoviesLibrary().forEach(System.out::println);
				break;
			}
			case "6": {
				System.out.println(cinema.getSchedules());
				break;
			}
			}
		}
	}

	public static void menu() {
		System.out.println();
		System.out.println("Enter 1, to add movie");
		System.out.println("Enter 2, to remove movie");
		System.out.println("Enter 3, to add seance");
		System.out.println("Enter 4, to remove seance");
		System.out.println("Enter 5, to print all movies");
		System.out.println("Enter 6, to print all schedule");
	}
}
