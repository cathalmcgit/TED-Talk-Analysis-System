package p3;

import java.util.Comparator;

/*
 * Cathal McGuigan 14640074
 * A comparator used to sort TED Talks by presenter name (alphabetically)
 */
public class CompareByPresenterName implements Comparator<TEDTalk>{

	@Override
	public int compare(TEDTalk o1, TEDTalk o2) {
		return o1.getPresenter().compareTo(o2.getPresenter());
	}

}
