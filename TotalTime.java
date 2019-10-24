public class TotalTime implements Comparable<TotalTime> {
	private Integer real_time;
	private Integer discrete_time;

	public TotalTime(Integer rt, Integer dt) {
		real_time = rt;
		discrete_time = dt;
	}

	public int compareTo(TotalTime o) {
		return real_time == o.real_time ? discrete_time.compareTo(o.discrete_time) : real_time.compareTo(o.real_time);
	}

	public TotalTime advance(TotalTime b) {
	 	return b.real_time == 0 ? new TotalTime(real_time, discrete_time + b.discrete_time) : new TotalTime(real_time + b.real_time, 0);
	}

	public Integer length(TotalTime b) {
		return b.real_time - real_time;
	}
}
