import java.util.ArrayList;

@SuppressWarnings("serial")
public class SegmentList extends ArrayList<Segment> {
	public boolean add(Segment s) {
		super.add(s);
		
		return false;
	}
}
