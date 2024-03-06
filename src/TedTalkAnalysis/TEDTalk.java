package p3;
/**
 * 
 * @author Cathal McGuigan - 14640074
 * A class used to represent information about a TED Talk
 */
public class TEDTalk {
	
	// constants for validation
	private static final int STRING_LOWER_LIMIT = 1;
	private static final int INT_LOWER_LIMIT = 0;
	
	private String title;	
	private String presenter;	
	private Month month;	
	private int year;
	private int views;
	private int likes;
	private String url;
	private String likeViewRatio;
	
	
	/**
	 * @param title
	 * @param presenter
	 * @param month
	 * @param year
	 * @param views
	 * @param likes
	 * @param url
	 */
	public TEDTalk(String title, String presenter, Month month, int year, int views, int likes, String url) {
		this.setTitle(title);
		this.setPresenter(presenter);
		this.setMonth(month);
		this.setYear(year);
		this.setViews(views);
		this.setLikes(likes);
		this.setUrl(url);
		
		this.likeViewRatio = getLikeViewRatio();
		
	}
	
	public TEDTalk() {
	}
	
	/**
	 * @returns the title of the talk
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Sets the Title of the TED talk
	 * @param title
	 * @throws IllegalArgumentException if an invalid Title is entered
	 */
	public void setTitle(String title) throws IllegalArgumentException {
		
		if (title != null && title.length() >= STRING_LOWER_LIMIT) {
			this.title = title;
		} else {
			throw new IllegalArgumentException("Invalid Title");
		}
		
		
	}
	/**
	 * @return presenter
	 */
	public String getPresenter() {
		return presenter;
	}
	
	/**
	 * 
	 * @param Sets the Presenter of the TED talk 
	 * @throws IllegalArgumentException if invalid data is entered
	 */
	
	public void setPresenter(String presenter) throws IllegalArgumentException {
		
		if (presenter != null && presenter.length() >= STRING_LOWER_LIMIT) {
			this.presenter = presenter;
		} else {
			throw new IllegalArgumentException("Invalid Presenter");
		}
	}
	/**
	 * @return the month
	 */
	public Month getMonth() {
		return month;
	}
	
	/**
	 * Sets the Month of the talk from a list of pre-defined months
	 * @param month
	 * @throws IllegalArgumentException if invalid data is entered
	 */
	public void setMonth(Month month) throws IllegalArgumentException {
		
		if (month != null) {
			this.month = month;
		} else {
			throw new IllegalArgumentException("Invalid Month");
		}
		
		
	}
	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}
	/**
	 * Sets the year of the Ted Talk
	 * @param year
	 * @throws IllegalArgumentException if invalid data is entered
	 */
	public void setYear(int year) throws IllegalArgumentException {
		
		if (year >= INT_LOWER_LIMIT) {
			this.year = year;
		} else {
			throw new IllegalArgumentException("Invalid Year");
		}
		
		
	}
	/**
	 * @return the views
	 */
	public int getViews() {
		return views;
	}
	/**
	 * Sets the number of views of the Ted Talk
	 * @param views
	 * @throws IllegalArgumentException if invalid data is entered
	 */
	public void setViews(int views) throws IllegalArgumentException {
		
		if (views >= INT_LOWER_LIMIT) {
			this.views = views;
		} else {
			throw new IllegalArgumentException("Invalid Views");
		}
		
		
	}
	/**
	 * @return the likes
	 */
	public int getLikes() {
		return likes;
	}
	/**
	 * Sets the likes received for the Ted Talk
	 * @param likes
	 * @throws IllegalArgumentException if invalid data is entered
	 */
	public void setLikes(int likes) throws IllegalArgumentException {

		if (likes >= INT_LOWER_LIMIT) {
			this.likes = likes;
		} else {
			throw new IllegalArgumentException("Invalid Likes");
		}

	}
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * Sets the URL for the TED Talk
	 * @param url
	 * @throws IllegalArgumentException if invalid data is entered
	 */
	public void setUrl(String url) throws IllegalArgumentException {

		if (url != null && url.length() >= STRING_LOWER_LIMIT) {
			this.url = url;
		} else {
			throw new IllegalArgumentException("Invalid URL");
		}

	}

	/**
	 * INCOMPLETE - Returns ratio of Likes to Views
	 * @return
	 */
	public String getLikeViewRatio()  {
		
		findGCD(this.likes, this.views);
		
		int likesRatio = 0;
		int viewsRatio = 0;
		
		String toReturn = String.format("%d : %d", likesRatio, viewsRatio);
		
		return "To Complete";
	}
	/**
	 * INCOMPLETE - finds ratio
	 * @param likes
	 * @param views
	 * @return
	 */
	private String findGCD(int likes, int views) {
		
		String likesAsWord = String.valueOf(likes);
		String viewsAsWord = String.valueOf(views);
		
		if (likes == 0 || views == 0) {
			return likesAsWord+":"+viewsAsWord;
		}
		
		return null;
	}

	public void printDetails() {

		System.out.println(String.format("Title:      %s", this.title));
		System.out.println(String.format("Presenter:  %s", this.presenter));
		System.out.println(String.format("Date:       %s %d", this.month, this.year));
		System.out.println(String.format("Views:      %d", this.views));
		System.out.println(String.format("Likes:      %d", this.likes));
		System.out.println(String.format("L/V Ratio:  %s", this.likeViewRatio));
		System.out.println(String.format("URL:        %s", this.url));

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((likeViewRatio == null) ? 0 : likeViewRatio.hashCode());
		result = prime * result + likes;
		result = prime * result + ((month == null) ? 0 : month.hashCode());
		result = prime * result + ((presenter == null) ? 0 : presenter.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		result = prime * result + views;
		result = prime * result + year;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TEDTalk other = (TEDTalk) obj;
		if (likeViewRatio == null) {
			if (other.likeViewRatio != null)
				return false;
		} else if (!likeViewRatio.equals(other.likeViewRatio))
			return false;
		if (likes != other.likes)
			return false;
		if (month != other.month)
			return false;
		if (presenter == null) {
			if (other.presenter != null)
				return false;
		} else if (!presenter.equals(other.presenter))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		if (views != other.views)
			return false;
		if (year != other.year)
			return false;
		return true;
	}

	
	
	
}
