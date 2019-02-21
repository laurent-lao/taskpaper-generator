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
		boolean validChoice = false;
		do {
			int choice = keyIn.nextInt();
			validChoice = runTemplate(choice);
		} while (!validChoice);

		// Closing the Scanners
		keyIn.close();
		keyInString.close();
    }

    public static void displayMenu()
	{
		System.out.print("Select a template" + "\n" +
				"1. Create Chapter Tasks (simple)\n" +
				"2. Create Chapter Tasks (extended)\n" +
				"" + // add more options here
				"\n\tInput option> ");
	}

	public static boolean runTemplate(int choice)
	{
		switch (choice) {
			case 1:
				createChapterTasks_simple();
				return true;
			case 2:
				createChapterTasks_extended();
				return true;
			default:
				System.out.print("Wrong choice, try again > ");
				return false;
			}
	}

	public static void createChapterTasks_simple() {
		// Instructions
		System.out.println("You can build a list of chapters in Excel/Numbers.\n" +
				"Start with the Chapter name, then list all subchapters, end the list with \"stop\", and copy one more row (to act as a escape).");

    	//	Prompt user for the amount of Chapters
		System.out.print("How many chapters? > ");
		int howManyChapters = keyIn.nextInt();

		//	Creates the 2-d array that will store the tasks
		Task[][] chapterTasks = new Task[howManyChapters][100];

		//	Prompt user for the prefix
		System.out.print("Whats the name of the class? (or just the book name) If there are multiple textbooks, input class + name here. \ni.e. BIOL 206 LAB BOOK > ");
		String nameOfClass = keyInString.nextLine();
		Task.setTaskNamePrefix((nameOfClass + " Chapter"));

		//	Create a project for the chapters
		Task project = new Task(0, (nameOfClass));
		project.setTaskIsParallel(false);
		project.setTaskIsAutodone(true);

		// Prompt user for the information about the Chapters
		for (int i = 0; i < howManyChapters; i++)
		{
			// Prompt the user for the Title of the Chapter
			System.out.print("What's the title of the Chapter " + (i + 1) + "? > ");
			String chapterTitle = keyInString.nextLine();
			chapterTasks[i][0] = new Task(1, "Read", ((i + 1) + ": " + chapterTitle));
			chapterTasks[i][0].setTaskIsParallel(false);
			chapterTasks[i][0].setTaskIsAutodone(true);

			int subChapterCounter = 1;
			int arrayAt           = 0;

			for (int j = 1; j < 100; j++, subChapterCounter++, arrayAt = j)
			{
				// Prompt the user for the Title of the Sub Chapter
				System.out.print("What's the title of the sub-Chapter #" + (i + 1) + "." + subChapterCounter + "?" +
						"\n(Enter stop to go to the next Chapter) > ");
				String subchapterTitle = keyInString.nextLine();
				if (!subchapterTitle.equalsIgnoreCase("stop"))
				{
					chapterTasks[i][j] = new Task(2, "Read", ((i + 1) + "." + subChapterCounter + ": " + subchapterTitle));
				}
				else
					break;

			} // End of nested for loop
		} // End of first for loop

		// Print the tasks
		createChapterTasks_printing(project, chapterTasks, howManyChapters);
	} // End of method

	// Creates a list of tasks for the chapters to come with extended actions
	// i.e
	// Study: SOEN 228 Textbook Chapter 1: Basic Structure of Computers @parallel(false) @autodone(false)
	//			- Study: SOEN 228 Textbook Chapter 1.1: Computer Types @parallel(false) @autodone(false)
	//				- Read: SOEN 228 Textbook Chapter 1.1: Computer Types @parallel(true) @autodone(false)
	//				- Annotate: SOEN 228 Textbook Chapter 1.1: Computer Types @parallel(true) @autodone(false)
	//				- Analyze Annotation: SOEN 228 Textbook Chapter 1.1: Computer Types @parallel(true) @autodone(false)
	public static void createChapterTasks_extended()
	{
		// Instructions
		System.out.println("You can build a list of chapters in Excel/Numbers.\n" +
				"Start with the Chapter name, then list all subchapters, end the list with \"stop\", and copy one more row (to act as a escape).");

		//	Prompt user for the amount of Chapters
		System.out.print("How many chapters? > ");
		int howManyChapters = keyIn.nextInt();

		//	Creates the 2-d array that will store the tasks
		Task [][] chapterTasks = new Task[howManyChapters][100];

		//	Prompt user for the prefix
		System.out.print("Whats the name of the class or book? If there are multiple textbooks, input Class + name here.\ni.e. BIOL 206 LAB BOOK > ");
		String nameOfClass = keyInString.nextLine();
		Task.setTaskNamePrefix((nameOfClass + " Chapter"));

		//	Create a project for the chapters
		Task project = new Task(0,(nameOfClass));
		project.setTaskIsParallel(false);
		project.setTaskIsAutodone(true);

		// Prompt user for the information about the Chapters
		for (int i = 0; i < howManyChapters; i++)
		{
			// Prompt the user for the Title of the Chapter
			System.out.print("What's the title of the Chapter " + (i+1) + "? > ");
			String chapterTitle = keyInString.nextLine();
			chapterTasks[i][0] = new Task(1, "Study", ((i+1) + ": " + chapterTitle));
			chapterTasks[i][0].setTaskIsParallel(false);
			chapterTasks[i][0].setTaskIsAutodone(true);

			int subChapterCounter = 1;
			int arrayAt = 0;
			for (int j = 1; j < 100; j++, subChapterCounter++, arrayAt = j)
			{
				// Prompt the user for the Title of the Sub Chapter
				System.out.print("What's the title of the sub-Chapter #" + (i+1) + "." + subChapterCounter + "?" +
						"\n(Enter stop to go to the next Chapter) > ");
				String subchapterTitle = keyInString.nextLine();
				if (!subchapterTitle.equalsIgnoreCase("stop")) {
					chapterTasks[i][j] = new Task(2, "Study", ((i + 1) + "." + subChapterCounter + ": " + subchapterTitle));
					chapterTasks[i][j].setTaskIsParallel(false);
					chapterTasks[i][j].setTaskIsAutodone(true);
					j++;
					chapterTasks[i][j] = new Task(3, "Read", ((i + 1) + "." + subChapterCounter + ": " + subchapterTitle));
					j++;
					chapterTasks[i][j] = new Task(3, "Annotate", ((i + 1) + "." + subChapterCounter + ": " + subchapterTitle));
					j++;
					chapterTasks[i][j] = new Task(3, "Analyze Annotation", ((i + 1) + "." + subChapterCounter + ": " + subchapterTitle));
				}
				else
					break;
			}

			chapterTasks[i][arrayAt] = new Task(2, "Review", ((i + 1) + ": " + chapterTitle));
			chapterTasks[i][arrayAt].setTaskIsParallel(false);
			chapterTasks[i][arrayAt].setTaskIsAutodone(true);
			chapterTasks[i][arrayAt + 1] = new Task(3, "Consolidate Annotations", ((i + 1) + ": " + chapterTitle));
			chapterTasks[i][arrayAt + 2] = new Task(3, "Create Flashcards", ((i + 1) + ": " + chapterTitle));


		}

		// Print the tasks
		createChapterTasks_printing(project, chapterTasks, howManyChapters);
	}

	public static void createChapterTasks_printing(Task project, Task [][] chapterArray, int howManyChapters)
	{
		// Printing all the Chapters info
		System.out.println("===== START OF TASKPAPER =====\n\n");

		// Printing project
		System.out.println(project.withoutPrefixSuffix());

		// Printing chapters
		for (int i = 0; i < howManyChapters; i++)
		{
			System.out.println(chapterArray[i][0]);

			for (int j = 1; j < 100; j++)
			{
				if (chapterArray[i][j] != null)
				{
					System.out.println(chapterArray[i][j]);
				}
				else
					break;
			}
		}
	}
}
