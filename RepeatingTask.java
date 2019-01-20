public class RepeatingTask extends Task {

	// Attributes
	private String taskRepeatMethod = "";				// 	Stores how the task is repeated
	private String taskRepeatRule = "";					// 	Stores the repeat rule of the task
	// TODO Figure out better repeat method tagging syntax

	public RepeatingTask() 								// Default Constructor
	{
		super();

		System.out.println("The Default constructor for RepeatingTask was used. Check your object creation call.");
	}

	public String toString()
	{
		String taskpaperString = "";

		// Attach the task level to the output, then attach task definer
		for (int i = getTaskLevel(); i < 0; i--)
		{
			taskpaperString += "\t";
		}
		taskpaperString += "- ";

		// Attach the task verb to the output if there is one
		if (!getTaskNameVerb().equals(""))
		{
			taskpaperString += (getTaskNameVerb() + ": ");
		}

		// Attach the task prefix to the output if there is one
		if (!getTaskNamePrefix().equals(""))
		{
			taskpaperString += (getTaskNamePrefix() + " ");
		}

		// Attach the task name to the output if there is one
		if (!getTaskName().equals(""))
		{
			taskpaperString += (getTaskName() + " ");
		}

		// Attach the task suffix to the output if there is one
		if (!getTaskNameSuffix().equals(""))
		{
			taskpaperString += (getTaskNameSuffix() + " ");
		}

		// Attach the parallel value to the output
		taskpaperString += "@parallel(" + isTaskIsParallel() + ") ";

		// Attach the autodone value to the output
		taskpaperString += "@autodone(" + isTaskIsAutodone() + ") ";

		// Attach estimated time value to the output
		if (!getTaskEstimate().equals(""))
		{
			taskpaperString += "@estimate(" + getTaskEstimate() + ") ";
		}

		// Attach the contexts and tags
		if (getTaskTags() != null)
		{
			setTaskContext(getTaskTags()[0]);
			taskpaperString += "@context(" + getTaskContext() + ") ";

			taskpaperString += "@tags(";				// Open the tag attribute
			for (int i = 0; i < getTaskTags().length; i++)
			{
				// Add separating comma first more than one tag
				if (i != 0)
				{
					taskpaperString += ", ";
				}

				// Add tags
				taskpaperString += getTaskTags()[i];
			}
			taskpaperString += ") ";					// Close the tag attribute
		}

		// Attach due date to the output
		if (!getTaskDue().equals(""))
		{
			taskpaperString += "@due(" + getTaskDue() + ") ";
		}

		// Attach defer date to the output
		if (!getTaskDefer().equals(""))
		{
			taskpaperString += "@defer(" + getTaskDefer() + ") ";
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
		if (!getTaskNote().equals(""))
		{
			// add the escape character
			taskpaperString += "\n";

			// properly indent the note format
			for (int i = getTaskLevel() + 1; i < 0; i--)
			{
				taskpaperString += "\t";
			}
			// add the note
			taskpaperString += getTaskNote();

			// add another escape character
			taskpaperString += "\n";
		}

		// Finish the task by adding an escape character
		// Maybe not needed if using System.out.println()
		// taskpaperString += "\n";

		return taskpaperString;
	}

}
