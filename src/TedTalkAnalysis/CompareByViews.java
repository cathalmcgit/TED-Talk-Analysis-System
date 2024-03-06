package p3;

import java.util.Comparator;

/**
 * 
 * @author Cathal McGuigan 14640074
 *A comparator used to sort TED Talks according to the number of views in descending order
 *
 */
public class CompareByViews implements Comparator <TEDTalk> {

	@Override
	public int compare(TEDTalk o1, TEDTalk o2) {
		return o2.getViews() - o1.getViews();
	}

}
