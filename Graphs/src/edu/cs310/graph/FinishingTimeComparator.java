import java.util.Comparator;

/*
 *  Comparator that will facilitate sorting the vertices container based on the finishing time of the vertices.
 *  Note:It sorts the Vertices container in Descending order based on the Finishing Time of the vertices.
 */
public class FinishingTimeComparator implements Comparator<Vertex> {

	@Override
	public int compare(Vertex o1, Vertex o2) {

		int fTime1=o1.getFinishingTime();
		int fTime2=o2.getFinishingTime();
		
		return fTime1>fTime2?-1:1;
	}

}
