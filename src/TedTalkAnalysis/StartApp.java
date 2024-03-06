package p3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Cathal McGuigan - 14640074
 * 
 *
 */
public class StartApp {
	
	public static Scanner scanner = new Scanner(System.in);//scanner declared statically 
	
	/**
	 * Entry point of program - no need to modify this method
	 * @param args
	 */
	public static void main(String[] args) {
		launchMenu();
		scanner.close();//close scanner once menu system completes
	}
	
	//TODO modify readData method to populate List appropriately - method partially completed already
	//TODO add static methods to this class as required to achieve tasks outlined in menu
	//TODO modify launchMenu method to add calls to new methods you write to accomplish the tasks outlined in the menu

	/**
	 * Launches menu system which in turn calls appropriate methods based on user choices
	 * Partially implemented already
	 * requires updating to add calls to other methods written to achieve the tasks described in tasks 2-10
	 */
	public static void launchMenu() {
		List<TEDTalk> mainList = readData();

		System.out.println();
		int option;
		System.out.println("TED Talk Stat Analysis");
		
		//repeat until quit chosen
		do {
			displayOptions();
			
			//get input
			option = getMenuChoice("Enter option ...");
			System.out.println();
			
			//process choice - invoke relevant methods etc.
			switch (option) {

			case 1:
				mainList = readData();
				break;
			case 2:
				System.out.println("Current Number of Talks in List : "+mainList.size());
				break;
			case 3:
				System.out.println("Displaying Details of All Talks in List");
				printInfo(mainList);
				break;
			case 4:
				System.out.println("Displaying 3 most viewed talks from April 2019");
				Month searchMonth = Month.APR;
				int searchYear = 2019;
				int resultsRequested = 3;
				ArrayList<TEDTalk> mostViewed = getMostViewed(mainList, searchMonth, searchYear, resultsRequested);
				printInfo(mostViewed);
				break;
			case 5:
				System.out.println("Displaying number of unique presenter names in list");
				int uniquePresentersCount = uniquePresenters(mainList);
				System.out.println(String.format("There are %d unique presenter names in the list,", uniquePresentersCount));
				break;
			case 6:
				System.out.println("Display the title of the least liked talk(s) by Al Gore");
				String presenterToSearch = "Al Gore";
				//ArrayList returned instead of String, in case multiple talks have the least likes
				ArrayList<TEDTalk> poorTalks = badPresenterTalk(mainList, presenterToSearch);
				for (TEDTalk talk : poorTalks) {
					System.out.println(String.format("The least liked talk(s) by %s was titled '%s' and had %d likes\n", presenterToSearch, talk.getTitle(), talk.getLikes()));
				}
				System.out.println();
				break;
			case 7:
				System.out.println("Display the average number of views of talks by Alex Gendler");
				String viewedPresenter = "Alex Gendler";
				double avgViews = avgViews(mainList, viewedPresenter);
				System.out.println(String.format("The average views for %s was %.2f", viewedPresenter, avgViews));
				break;
			case 8:
				System.out.println("Displaying details of talks with the word 'brain' in the title, sorted by presenter name");
				String searchWord = "brain";
				ArrayList<TEDTalk> titlesContainingWord = wordInTitleList(mainList, searchWord);
				printInfo(titlesContainingWord);
				break;
			case 9:
				System.out.println("Removing any talks from the list with fewer likes than 2500");
				int likeQuota = 2500;
				removeLowestLikedTalks(mainList, likeQuota);
				
				break;
			case 10:
				System.out.println("Writing Presenter Talk Details to CSV");
				String chosenPresenter = "Joy Lin";
				ArrayList<TEDTalk> talksByPresenter = presenterTalks(mainList, chosenPresenter);
				
				WriteToFile writer = new WriteToFile(talksByPresenter);
				Thread writeThread = new Thread(writer);
				writeThread.start();
				
				break;
			case 11:
				System.out.println("Quitting");
				break;
			default:
				System.out.println("Try options again...");
			}
		} while (option != 11);
	}

	private static int uniquePresenters(List<TEDTalk> mainList) {
		
		ArrayList<String> presenterNames = new ArrayList<String>();
		Map<TEDTalk, String> uniquePresenters = new HashMap<TEDTalk, String>();
		
		int presenterCount = 0;
		
		for (TEDTalk talk : mainList) {
			uniquePresenters.put(talk, talk.getPresenter());
			presenterNames.add(talk.getPresenter());
		}
		
		for (String key : presenterNames) {
			if (uniquePresenters.containsKey(key)) {
				presenterCount++;
			}
		}
		
		return presenterCount;
		
	}

	private static double avgViews(List<TEDTalk> mainList, String viewedPresenter) throws IllegalArgumentException {
		
		if (mainList == null || mainList.isEmpty()) {
			throw new IllegalArgumentException("Invalid List");
		}
		
		ArrayList<TEDTalk> presenterList = new ArrayList<>();

		for (TEDTalk talk : mainList) {
			if (talk.getPresenter().equalsIgnoreCase(viewedPresenter)) {
				presenterList.add(talk);
			}
		}
		
		int totalViews = 0;
		double avgViews= 0;
		
		for (TEDTalk talk : presenterList) {
			totalViews += talk.getViews();
		}
		
		avgViews = (double) totalViews/presenterList.size();
		
		return avgViews;
	}

	private static ArrayList<TEDTalk> wordInTitleList(List<TEDTalk> mainList, String searchWord) throws IllegalArgumentException {
		
		if (mainList == null || mainList.isEmpty()) {
			throw new IllegalArgumentException("Invalid List");
		}

		ArrayList<TEDTalk> titlesWithWord = new ArrayList<>();
		
		for (TEDTalk talk : mainList) {
			if (talk.getTitle().contains(searchWord)) {
				titlesWithWord.add(talk);
			}
		}
		
		Collections.sort(titlesWithWord, new CompareByPresenterName());
		
		return titlesWithWord;
	}

	private static ArrayList<TEDTalk> badPresenterTalk(List<TEDTalk> mainList, String presenterToSearch) throws IllegalArgumentException {
		
		if (mainList == null || mainList.isEmpty()) {
			throw new IllegalArgumentException("Invalid List");
		}

		ArrayList<TEDTalk> presenterTalksList = new ArrayList<>();
		ArrayList<TEDTalk> results = new ArrayList<>();

		for (TEDTalk talk : mainList) {
			if (talk.getPresenter().equalsIgnoreCase(presenterToSearch)) {
				presenterTalksList.add(talk);
			}
		}

		Collections.sort(presenterTalksList, new CompareByLikes());
		
		//Could use Collection.min to find the TEDTalk with the least value
		//however there could be more than one with the least likes

		results.add(presenterTalksList.get(0));
		
		
		for (int i = 1; i < presenterTalksList.size(); i++) {
		
			if (presenterTalksList.get(i).getLikes() == presenterTalksList.get(0).getLikes()) {
				results.add(presenterTalksList.get(i));
			}

		}
		
		return results;
	}

	private static void removeLowestLikedTalks (List<TEDTalk> mainList, int likeQuota) throws IllegalArgumentException {

		if (mainList == null || mainList.isEmpty()) {
			throw new IllegalArgumentException("Invalid List");
		}

		ArrayList<TEDTalk> toRemove = new ArrayList<>();

		for (TEDTalk talk : mainList) {
			if (talk.getLikes() < likeQuota) {
				toRemove.add(talk);
			}
		}

		mainList.removeAll(toRemove);

	}

	private static ArrayList<TEDTalk> presenterTalks(List<TEDTalk> mainList, String chosenPresenter) throws IllegalArgumentException {
		
		if (mainList == null || mainList.isEmpty()) {
			throw new IllegalArgumentException("Invalid List");
		}
		
		ArrayList<TEDTalk> results = new ArrayList<> ();
		
		for (TEDTalk talk : mainList) {
			if (talk.getPresenter().equalsIgnoreCase(chosenPresenter)) {
				results.add(talk);
			}
		}
		
		return results;
	}


	private static ArrayList<TEDTalk> getMostViewed(List<TEDTalk> mainList, Month searchMonth, int searchYear,
			int resultsRequested) throws IllegalArgumentException {
		
		if (mainList == null || mainList.isEmpty()) {
			throw new IllegalArgumentException("Invalid List");
		}
		
		ArrayList<TEDTalk> correctDate = new ArrayList<> ();
		ArrayList<TEDTalk> results = new ArrayList<> ();
		
		for (TEDTalk talk : mainList) {
			if (talk.getMonth().equals(searchMonth) && talk.getYear() == searchYear) {
				correctDate.add(talk);
			}
		}
		
		Collections.sort(correctDate, new CompareByViews());
		
		for (int i = 0; i < resultsRequested; i++) {
			results.add(correctDate.get(i));
		}
		
		return results;
	}

	private static void printInfo(List<TEDTalk> mainList) throws IllegalArgumentException {
		
		if (mainList == null || mainList.isEmpty()) {
			throw new IllegalArgumentException("Invalid List");
		}
		
		int count = 1;
		
		for (TEDTalk tedTalk : mainList) {
			System.out.println(count+")\n");
			tedTalk.printDetails();
			System.out.println();
			count++;
		}
		
	}

	/**
	 * Display prompt and get int user input via static scanner
	 * repeat if invalid input type given
	 * - no need to modify this method
	 * @return int value entered via scanner
	 */
	private static int getMenuChoice(String prompt) {
		try {
			System.out.println(prompt);
			int choice = scanner.nextInt();
			return choice;
		}catch(Exception e) {
			System.out.println("Invalid input try again");
			//clear buffer if required
			if(scanner.hasNext()) {
				scanner.next();//read and discard line break
			}
			return getMenuChoice(prompt);//return recursive call to method to recover
		}
	}

	/**
	 * Writes menu options to console - no need to modify this method
	 */
	private static void displayOptions() {
		System.out.println("1. (Re)read Data From Original File (use to restore removed data for example)");
		System.out.println("2. Display the number of Talks in the Current List");
		System.out.println("3. Display full details for all talk videos in the current list");
		System.out.println("4. Display details of 3 most viewed talks from April 2019");
		System.out.println("5. Display the number of unique presenter names in the current list");
		System.out.println("6. Display the title of the least liked talk(s) by Al Gore");
		System.out.println("7. Display the average number of views of talks by Alex Gendler");
		System.out.println("8. Display details of talks with the word \"brain\" in the title, sorted by presenter name.");
		System.out.println("9. Remove any talks from the list with fewer likes than 2500.");
		System.out.println("10. Write out Joy Lin talk details to formatted csv");
		System.out.println("11. Quit");
	}

	/**
	 * Reads in the data from the provided csv and returns a list of TedTalk
	 * objects for further processing - requires updating for full functionality
	 */
	public static List<TEDTalk> readData() {

		List<TEDTalk> listFromFile = new ArrayList<TEDTalk>();

		File file = new File("tedtalkdata.csv"); // hard coded to specific file

		// try with resources - auto closes reader resources when finished/exception
		// occurs
		try (FileReader fr = new FileReader(file); BufferedReader reader = new BufferedReader(fr);) {

			String line = reader.readLine(); // discard header
			line = reader.readLine(); // read first line

			while (line != null) {
				// Code to process current line
				String [] parts = line.split(",");
				TEDTalk temp = new TEDTalk();
				
				// instantiate TEDTalk object
				
				try {
					temp.setTitle(parts[0]);
					temp.setPresenter(parts[1]);
					
					String [] dateInfo = parts[2].split(" ");
					String monthInfo = dateInfo[0].toUpperCase().substring(0, 3);
					
					temp.setMonth(Month.valueOf(monthInfo));
					
					temp.setYear(Integer.parseInt(dateInfo[1]));
					temp.setViews(Integer.parseInt(parts[3]));
					temp.setLikes(Integer.parseInt(parts[4]));
					temp.setUrl(parts[5]);
				} catch (Exception ex) {
					System.err.println("Bad Data Entered - Skipping");
				}
				
				// add to list
				listFromFile.add(temp);

				line = reader.readLine();// attempt to read next line and loop again
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found error");
		} catch (IOException e) {
			System.out.println("IO Exception");
		} catch (Exception e) {
			System.out.println("Exception occured");
			System.out.println(listFromFile.size() + " lines read successfully");
			System.out.println(e.getMessage());
		}
		System.out.println(listFromFile.size() + " lines read successfully");
		return listFromFile;
	}

}
