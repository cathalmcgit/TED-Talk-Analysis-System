package p3;

import java.util.Comparator;

/**
 * 
 * @author Cathal McGuigan 14640074
 * A comparator used to sort TED Talks according to the number of likes in ascending order
 */
public class CompareByLikes implements Comparator<TEDTalk>{

	@Override
	public int compare(TEDTalk o1, TEDTalk o2) {
		return o1.getLikes() - o2.getLikes();
	}

}
