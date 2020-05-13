package ua.lviv.lgs;

public class Time {

	private int hour;
	private int min;

	public Time(int hour, int min) throws TimeException {
		if (min < 0 || min > 59) {
			throw new TimeException("Wrong time");
		} else {
			this.min = min;
		}
		if (hour < 0 || hour > 23) {
			throw new TimeException("Wrong time");
		} else {
			this.hour = hour;
		}
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	@Override
	public String toString() {
		return hour + "h" + " " + min + "min";
	}

	public Time addTime(Time time) throws TimeException {
		int hourRes = this.hour + time.hour;
		int minRes = this.min + time.min;
		if (minRes > 59) {
			minRes -= 60;
			hourRes++;
		}
		if (hourRes > 23) {
			hourRes -= 24;
		}
		return new Time(hourRes, minRes);
	}
}
