package ua.lviv.lgs;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.TreeMap;

public class Cinema {

	private Map<Days, Schedule> schedules = new TreeMap<>();
	private List<Movie> moviesLibrary = new ArrayList<>();
	private Time open;
	private Time close;

	public Cinema(Time open, Time close) {
		super();
		this.open = open;
		this.close = close;
	}

	public Map<Days, Schedule> getSchedules() {
		return schedules;
	}

	public void setSchedules(Map<Days, Schedule> schedules) {
		this.schedules = schedules;
	}

	public List<Movie> getMoviesLibrary() {
		return moviesLibrary;
	}

	public void setMoviesLibrary(List<Movie> moviesLibrary) {
		this.moviesLibrary = moviesLibrary;
	}

	public Time getOpen() {
		return open;
	}

	public void setOpen(Time open) {
		this.open = open;
	}

	public Time getClose() {
		return close;
	}

	public void setClose(Time close) {
		this.close = close;
	}

	public void addMovie(Movie movie) {
		moviesLibrary.add(movie);
	}

	public void addSeance(Seance seance, String day) {
		if (seance.getStartTime().getHour() * 60 + seance.getStartTime().getMin() >= open.getHour() * 60 + open.getMin()
				&& (seance.getEndTime().getHour() * 60 + seance.getEndTime().getMin() <= close.getHour() * 60
						+ close.getMin())) {
			Optional<Entry<Days, Schedule>> findAny = schedules.entrySet().stream()
					.filter(e -> e.getKey().toString().equals(day)).findAny();
			if (!findAny.isPresent()) {
				schedules.put(Days.valueOf(day), new Schedule());
				schedules.entrySet().stream().filter(e -> e.getKey().toString().equals(day)).findAny().get().getValue()
						.getSeanceSet().add(seance);
			} else {
				findAny.get().getValue().addSeances(seance);
			}
		} else {
			System.out.println("Cinema is closed");
		}
	}

	public void removeMovie(Movie movie) {
		moviesLibrary.remove(movie);
	}

	public void removeSeance(Seance seance, String day) {
		schedules.entrySet().stream().forEach(e -> {
			if (e.getKey().toString().equalsIgnoreCase(day)) {
				e.getValue().removeSeances(seance);
			}
		});
	}
}
