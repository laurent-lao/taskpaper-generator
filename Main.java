import java.util.Scanner;

public class Main {

	public static Scanner keyIn = new Scanner(System.in);
	public static Scanner keyInString = new Scanner(System.in);

    public static void main(String[] args) {

		// Listing created templates
		// When creating more templates, add the menu option to the displayMenu()
		displayMenu();

		// Prompt user for which template to use
		// When creating more templates, add the run option to the runTemplate()
		int choice = keyIn.nextInt();
		runTemplate(choice);



		// Closing the Scanners
		keyIn.close();
		keyInString.close();
    }

    public static void displayMenu()
	{
		System.out.print("Select a template" + "\n" +
				"1. Creating Chapter Tasks\n" +
				"" +
				"\n\tInput option> ");
	}

	public static void runTemplate(int choice)
	{
		boolean correctChoice = false;
		while (!correctChoice) {

			switch (choice) {
				case 1:
					createChapterTasks();
					correctChoice = true;
					break;
				default:
					System.out.print("Wrong choice, try again > ");
					break;
			}
		}
	}

	// Creates a list of tasks for the chapters to come
	public static void createChapterTasks()
	{
		//	Prompt user for the amount of Chapters
		System.out.print("How many chapters? > ");
		int howManyChapters = keyIn.nextInt();

		//	Creates the 2-d array that will store the tasks
		Task [][] chapterTasks = new Task[howManyChapters][100];

		//	Prompt user for the prefix
		System.out.print("Whats the name of the class? If there are multiple textbooks, input here.\ni.e. BIOL 206 LAB BOOK > ");
		String nameOfClass = keyInString.nextLine();
		Task.setTaskNamePrefix((nameOfClass + " Chapter"));

		//	Create a project for the chapters
		Task project = new Task(0,(nameOfClass));
		project.setTaskIsParallel(false);

		// Prompt user for the information about the Chapters
		for (int i = 0; i < howManyChapters; i++)
		{
			// Prompt the user for the Title of the Chapter
			System.out.print("What's the title of the Chapter " + (i+1) + "? > ");
			chapterTasks[i][0] = new Task(1, "Study", ((i+1) + ": " + keyInString.nextLine()));
			chapterTasks[i][0].setTaskIsParallel(false);

			int subChapterCounter = 1;
			for (int j = 1; j < 100; j++, subChapterCounter++)
			{
				// Prompt the user for the Title of the Sub Chapter
				System.out.print("What's the title of the sub-Chapter #" + (i+1) + "." + subChapterCounter + "?" +
						"\n(Enter stop to go to the next Chapter) > ");
				String subchapterTitle = keyInString.nextLine();
				if (!subchapterTitle.equalsIgnoreCase("stop")) {
					chapterTasks[i][j] = new Task(2, "Study", ((i + 1) + "." + subChapterCounter + ": " + subchapterTitle));
					chapterTasks[i][j].setTaskIsParallel(false);
					j++;
					chapterTasks[i][j] = new Task(3, "Read", ((i + 1) + "." + subChapterCounter + ": " + subchapterTitle));
					j++;
					chapterTasks[i][j] = new Task(3, "Annotate", ((i + 1) + "." + subChapterCounter + ": " + subchapterTitle));
					j++;
					chapterTasks[i][j] = new Task(3, "Analyze Annotation: ", ((i + 1) + "." + subChapterCounter + ": " + subchapterTitle));
				}
				else
					break;
			}
		}

		// Printing all the Chapters info
		System.out.println("===== START OF TASKPAPER =====\n\n");

		// Printing project
		System.out.println(project.withoutPrefixSuffix());

		// Printing chapters
		for (int i = 0; i < howManyChapters; i++)
		{
			System.out.println(chapterTasks[i][0]);

			for (int j = 1; j < 100; j++)
			{
				if (chapterTasks[i][j] != null)
				{
					System.out.println(chapterTasks[i][j]);
				}
				else
					break;
			}
		}
	}
}
