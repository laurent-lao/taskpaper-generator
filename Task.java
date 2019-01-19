// ====================================================
// Author: Laurent Lao
// Date: Jan 19 2019
//
// This Class lays down the foundation of my TaskPaper generator.
// Every object of this class generates a properly formatted TaskPaper
// task in its toString in order to be easily pasted into OmniFocus.
//
// Last Modified: Jan 19, 2019 at 12:51:20
// ====================================================

public class Task {

	// Attributes
	private String taskName = "";						//	Stores the name of the task
	private String taskNameVerb = "";					//	Stores the name of the task's verb (What is written in front of the task)
	private static String taskNamePrefix = "";			//	Stores a prefix for the name of the task (follows the taskNameVerb)
	private static String taskNameSuffix = "";			//	Stores a suffix for the name of the task (follows whatever taskName is)
	private int taskLevel;								//	Stores the level of a task (main, subgroup, etc) to properly loop a "\t"
	private boolean taskIsParallel;						//	Stores if task isParallel or not
	private String taskEstimate = "";					// 	Stores the estimated time for the task
	private boolean taskIsAutodone;						//	Stores whether task is autodone or not
	private String taskContext = "";					// 	Stores the task's first tag
	private String [] taskTags;							// 	Stores the all the task's tags
	private String taskDue = "";						// 	Stores when the task is due
	private String taskDefer = "";						// 	Stores when the task is deferred
	private String taskRepeatMethod = "";				// 	Stores how the task is repeated
	private String taskRepeatRule = "";					// 	Stores the repeat rule of the task
	private String taskNote = "";						// 	Stores the task's note
	// TODO Figure out better repeat method tagging syntax
	// TODO	Figure out custom notification syntax

	// ** Constructors **

	public Task() // Default constructor
	{
		taskLevel = 0;
		taskIsParallel = true;
		taskIsAutodone = false;
	}

	public Task(int taskLevel, String taskName) // Basic task constructor
	{
		this.taskLevel = taskLevel;
		this.taskName = taskName;
		taskIsParallel = true;
		taskIsAutodone = false;
	}

	// ** Methods **

	// Output Methods

	public String toString()
	{
		String taskpaperString = "";

		// Attach the task level to the output, then attach task definer
		for (int i = taskLevel; i < 0; i--)
		{
			taskpaperString += "\t";
		}
		taskpaperString += "- ";

		// Attach the task verb to the output if there is one
		if (!taskNameVerb.equals(""))
		{
			taskpaperString += (taskNameVerb + ": ");
		}

		// Attach the task prefix to the output if there is one
		if (!taskNamePrefix.equals(""))
		{
			taskpaperString += (taskNamePrefix + " ");
		}

		// Attach the task suffix to the output if there is one
		if (!taskNameSuffix.equals(""))
		{
			taskpaperString += (taskNameSuffix + " ");
		}

		// Attach the parallel value to the output
		taskpaperString += "@parallel(" + taskIsParallel + ") ";

		// Attach the autodone value to the output
		taskpaperString += "@autodone(" + taskIsAutodone + ") ";

		// Attach estimated time value to the output
		if (!taskEstimate.equals(""))
		{
			taskpaperString += "@estimate(" + taskEstimate + ") ";
		}

		// Attach the contexts and tags
		if (taskTags != null)
		{
			taskContext = taskTags[0];
			taskpaperString += "@context(" + taskContext + ") ";

			taskpaperString += "@tags(";				// Open the tag attribute
			for (int i = 0; i < taskTags.length; i++)
			{
				// Add separating comma first more than one tag
				if (i != 0)
				{
					taskpaperString += ", ";
				}

				// Add tags
				taskpaperString += taskTags[i];
			}
			taskpaperString += ") ";					// Close the tag attribute
		}

		// Attach due date to the output
		if (!taskDue.equals(""))
		{
			taskpaperString += "@due(" + taskDue + ") ";
		}

		// Attach defer date to the output
		if (!taskDefer.equals(""))
		{
			taskpaperString += "@defer(" + taskDefer + ") ";
		}

		// Attach repeat method to the output
		if (!taskRepeatMethod.equals(""))
		{
			taskpaperString += "@repeat-method(" + taskRepeatMethod + ") ";
		}

		// Attach repeat rule to the output
		if (!taskRepeatRule.equals(""))
		{
			taskpaperString += "@repeat-rule(" + taskRepeatRule + ") ";
		}

		// Attach the task's note to the output
		if (!taskNote.equals(""))
		{
			// add the escape character
			taskpaperString += "\n";

			// properly indent the note format
			for (int i = taskLevel + 1; i < 0; i--)
			{
				taskpaperString += "\t";
			}

			// add another escape character
			taskpaperString += "\n";
		}

		// Finish the task by adding an escape character
		// Maybe not needed if using System.out.println()
		// taskpaperString += "\n";

		return taskpaperString;
	}



}
