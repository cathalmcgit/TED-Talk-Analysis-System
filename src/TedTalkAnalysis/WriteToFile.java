package p3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author Cathal McGuigan 14640074
 * A Thread used to write details from an ArrayList to a CSV file
 */
public class WriteToFile implements Runnable {
	
	List <TEDTalk> talks = new ArrayList<TEDTalk>();
	String filename;

	public WriteToFile (List <TEDTalk> talks) {
		this.talks = talks;
		this.filename = " JLinTalks.csv";
	}
	
	public void writeFile () {
		
		try {
			File file = new File (filename);
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			
			if (!file.exists()) {
				file.createNewFile();
			}
			
			String headings = String.format("Title, Presenter, Month, Year, Views, Likes, Url, Like To View Ratio");
			bw.write(headings);
			bw.newLine();
			
			for (TEDTalk talk : talks) {
				  
				//views and likes must be formatted so that the final three numbers are
				//replaced by K representing thousands and the remaining numbers printed
				
				String formatViews = String.valueOf(talk.getViews());
				if (formatViews.length() == 4) {
					formatViews = formatViews.substring(0)+"K";
				} else if (formatViews.length() == 5) {
					formatViews = formatViews.substring(0,2)+"K";
				} else if (formatViews.length() == 6) {
					formatViews = formatViews.substring(0,3)+"K";
				} else if (formatViews.length() == 7) {
					formatViews = formatViews.substring(0,4)+"K";
				}
				
				String formatLikes = String.valueOf(talk.getLikes());
				
				if (formatLikes.length() == 4) {
					formatLikes = formatLikes.substring(0)+"K";
				} else if (formatLikes.length() == 5) {
					formatLikes = formatLikes.substring(0,2)+"K";
				} else if (formatLikes.length() == 6) {
					formatLikes = formatLikes.substring(0,3)+"K";
				} else if (formatLikes.length() == 7) {
					formatLikes = formatLikes.substring(0,4)+"K";
				}
				
				String line = String.format("%s, %s, %s, %d, %s, %s, %s, %s", talk.getTitle(), talk.getPresenter(), talk.getMonth(), talk.getYear(), formatViews, formatLikes, talk.getUrl(), talk.getLikeViewRatio());
				

				
				bw.write(line);
				bw.newLine();

			}
			
			bw.close();
			fw.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void run() {
		writeFile ();
	}

}
