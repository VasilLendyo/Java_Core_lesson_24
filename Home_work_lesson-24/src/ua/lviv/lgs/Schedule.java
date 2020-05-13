package ua.lviv.lgs;

import java.util.Set;
import java.util.TreeSet;

public class Schedule {

	private Set<Seance> seanceSet = new TreeSet<>();

	public Set<Seance> getSeanceSet() {
		return seanceSet;
	}

	public void setSeanceSet(Set<Seance> seanceSet) {
		this.seanceSet = seanceSet;
	}

	public void addSeances(Seance seance) {
		seanceSet.add(seance);
	}

	public void removeSeances(Seance seance) {
		seanceSet.remove(seance);
	}

	@Override
	public String toString() {
		return "\n" + seanceSet + "\n";
	}
}
