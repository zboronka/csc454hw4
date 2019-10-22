public class TotalTime implements Comparable<TotalTime> {
	public Integer real_time;
	public Integer discrete_time;

	public TotalTime(Integer rt, Integer dt) {
		real_time = rt;
		discrete_time = dt;
	}

	public TotalTime advance(TotalTime b) {
	 	return b.real_time == 0 ? new TotalTime(real_time, discrete_time + b.discrete_time) : new TotalTime(real_time + b.real_time, 0);
	}

	public int compareTo(TotalTime o) {
		return real_time == o.real_time ? discrete_time.compareTo(o.discrete_time) : real_time.compareTo(o.real_time);
	}
}
