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
	private boolean taskIsParallel = true;				//	Stores if task isParallel or not
	private String taskEstimate = "";					// 	Stores the estimated time for the task
	private boolean taskIsAutodone = false;				//	Stores whether task is autodone or not
	private String taskContext = "";					// 	Stores the task's first tag
	private String [] taskTags;							// 	Stores the all the task's tags
	private String taskDue = "";						// 	Stores when the task is due
	private String taskDefer = "";						// 	Stores when the task is deferred
	private String taskNote = "";						// 	Stores the task's note
	// TODO	Figure out custom notification syntax

	// ** Constructors **

	public Task() // Default constructor
	{
		taskLevel = 0;

		// Error message
		System.out.println("The Default constructor for Task was used. Check your object creation call.");
	}

	public Task(int taskLevel, String taskName) // Basic task constructor
	{
		this.taskLevel = taskLevel;
		this.taskName = taskName;
	}

	public Task(int taskLevel,  String taskNameVerb, String taskName) // Task Constructor with a verb
	{
		this(taskLevel, taskName);
		this.taskNameVerb = taskNameVerb;
	}

	// ** Methods **

	// Accessors
	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskNameVerb() {
		return taskNameVerb;
	}

	public void setTaskNameVerb(String taskNameVerb) {
		this.taskNameVerb = taskNameVerb;
	}

	public static String getTaskNamePrefix() {
		return taskNamePrefix;
	}

	public static void setTaskNamePrefix(String taskNamePrefix) {
		Task.taskNamePrefix = taskNamePrefix;
	}

	public static String getTaskNameSuffix() {
		return taskNameSuffix;
	}

	public static void setTaskNameSuffix(String taskNameSuffix) {
		Task.taskNameSuffix = taskNameSuffix;
	}

	public int getTaskLevel()
	{
		return taskLevel;
	}

	public void setTaskLevel(int taskLevel) {
		this.taskLevel = taskLevel;
	}

	public boolean isTaskIsParallel() {
		return taskIsParallel;
	}

	public void setTaskIsParallel(boolean taskIsParallel) {
		this.taskIsParallel = taskIsParallel;
	}

	public String getTaskEstimate() {
		return taskEstimate;
	}

	public void setTaskEstimate(String taskEstimate) {
		this.taskEstimate = taskEstimate;
	}

	public boolean isTaskIsAutodone() {
		return taskIsAutodone;
	}

	public void setTaskIsAutodone(boolean taskIsAutodone) {
		this.taskIsAutodone = taskIsAutodone;
	}

	public String getTaskContext() {
		return taskContext;
	}

	public void setTaskContext(String taskContext) {
		this.taskContext = taskContext;
	}

	public String[] getTaskTags() {
		return taskTags;
	}

	public void setTaskTags(String[] taskTags) {
		this.taskTags = taskTags;
	}

	public String getTaskDue() {
		return taskDue;
	}

	public void setTaskDue(String taskDue) {
		this.taskDue = taskDue;
	}

	public String getTaskDefer() {
		return taskDefer;
	}

	public void setTaskDefer(String taskDefer) {
		this.taskDefer = taskDefer;
	}

	public String getTaskNote() {
		return taskNote;
	}

	public void setTaskNote(String taskNote) {
		this.taskNote = taskNote;
	}

	// Output Methods

	public String withoutPrefixSuffix()
	{
		// Task gets printed in this order
		/* 	<# tabs according to taskLevel>
		- <taskNameVerb>: <taskNamePrefix> taskName <taskNameSuffix>
		<@isParallel> <@isAutodone> <@Estimate> <@context + tags> <@Due> <@Defer>

		NEWLINE <#tabs to taskLevel + 1> <note>
	 	*/

		String taskpaperString = "";

		// Attach the task level to the output, then attach task definer
		for (int i = taskLevel; i >= 0; i--)
		{
			taskpaperString += "\t";
		}
		taskpaperString += "- ";

		// Attach the task verb to the output if there is one
		if (!taskNameVerb.equals(""))
		{
			taskpaperString += (taskNameVerb + ": ");
		}

		// Attach the task name to the output if there is one
		if (!taskName.equals(""))
		{
			taskpaperString += (taskName + " ");
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

			// add the note
			taskpaperString += taskNote;

			// add another escape character
			taskpaperString += "\n";
		}

		// Finish the task by adding an escape character
		// Maybe not needed if using System.out.println()
		// taskpaperString += "\n";

		return taskpaperString;

	}

	public String toString()
	{
		// Task gets printed in this order
		/* 	<# tabs according to taskLevel>
			- <taskNameVerb>: <taskNamePrefix> taskName <taskNameSuffix>
			<@isParallel> <@isAutodone> <@Estimate> <@context + tags> <@Due> <@Defer>

			NEWLINE <#tabs to taskLevel + 1> <note>
		 */

		String taskpaperString = "";

		// Attach the task level to the output, then attach task definer
		for (int i = taskLevel; i >= 0; i--)
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

		// Attach the task name to the output if there is one
		if (!taskName.equals(""))
		{
			taskpaperString += (taskName + " ");
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

			// add the note
			taskpaperString += taskNote;

			// add another escape character
			taskpaperString += "\n";
		}

		// Finish the task by adding an escape character
		// Maybe not needed if using System.out.println()
		// taskpaperString += "\n";

		return taskpaperString;
	}
}

