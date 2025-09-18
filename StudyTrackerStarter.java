import java.util.*;
import java.time.LocalDate;
import java.io.*;


/////////////////////////////////////////////////////////////////////////////////
//
//  Class Name 	:       StudyLog
//  Description :       Represents a study session record containing date,
//                      subject, duration, and description.
//  Author 		:       Gaurav Subhash Kumbhar
//
/////////////////////////////////////////////////////////////////////////////////

class StudyLog
{
	public LocalDate Date;
	public String Subject;
	public Double Duration;
	public String Description;

	//////////////////////////////////////////////////////////////////////////////
	//
	//  Function Name :     StudyLog (Constructor)
	//  Description   :     Initializes a study log entry with given date, subject,
	//                      duration, and description.
	//  Author        :     Gaurav Subhash Kumbhar
	//
	//////////////////////////////////////////////////////////////////////////////
	
	public StudyLog(LocalDate A,String B, Double C, String D)
	{
		this.Date = A;
		this.Subject = B;
		this.Duration = C;
		this.Description = D;
		
	}
	
	//////////////////////////////////////////////////////////////////////////////
	//
	//  Function Name :     toString
	//  Description   :     Returns the string representation of a StudyLog entry
	//                      in the format "Date | Subject | Duration | Description".
	//  Author        :     Gaurav Subhash Kumbhar
	//
	//////////////////////////////////////////////////////////////////////////////
	
	@Override
	public String toString()
	{
		 return  Date+ " | " +Subject+ " | " +Duration+ " | " +Description;
	}

	//////////////////////////////////////////////////////////////////////////////
	//
	//  Function Name :     getDate
	//  Description   :     Returns the date of the study log entry.
	//  Author        :     Gaurav Subhash Kumbhar
	//
	//////////////////////////////////////////////////////////////////////////////
	
	public LocalDate getDate()
	{
		return Date;
	}


	//////////////////////////////////////////////////////////////////////////////
	//
	//  Function Name :     getSubject
	//  Description   :     Returns the subject of the study log entry.
	//  Author        :     Gaurav Subhash Kumbhar
	//
	//////////////////////////////////////////////////////////////////////////////
	
	public String getSubject()
	{
		return Subject;
	}

	//////////////////////////////////////////////////////////////////////////////
	//
	//  Function Name :     getDuration
	//  Description   :     Returns the duration of study in hours.
	//  Author        :     Gaurav Subhash Kumbhar
	//
	//////////////////////////////////////////////////////////////////////////////
	
	public double getDuration()
	{
		return Duration;
	}
	
	//////////////////////////////////////////////////////////////////////////////
	//
	//  Function Name :     getDescription
	//  Description   :     Returns the description of the study log entry.
	//  Author        :     Gaurav Subhash Kumbhar
	//
	//////////////////////////////////////////////////////////////////////////////
	
	public String getDescription()
	{
		return Description; 
	}
}

///////////////////////////////////////////////////////////////////////////////////////////
//
//  Class Name :        StudyTracker
//  Description :       This class maintains a database of study logs, allows insertion of
//                      new logs, displays all logs, generates summaries by date or
//                      subject, and exports logs to a CSV file.
//  Author :            Gaurav Subhash Kumbhar
//
///////////////////////////////////////////////////////////////////////////////////////////


class StudyTracker
{
	// Data Structure to hold data about study
	private ArrayList <StudyLog> Database = new ArrayList <StudyLog> ();
	
	/////////////////////////////////////////////////////////////////////////////
	//
	//  Function Name :     InsertLog
	//  Description   :     It is used to insert Study details.
	//  Author 		  :     Gaurav Subhash Kumbhar
	//
	/////////////////////////////////////////////////////////////////////////////

	public void InsertLog()
	{
		Scanner Scannerobj = new Scanner(System.in);
		System.out.println("----------------------------------------------------------------------");
		System.out.println("--------------Please enter the valid details of your study------------");
		System.out.println("----------------------------------------------------------------------");
		
		LocalDate Dateobj = LocalDate.now();
		
		
		System.out.println("Please provide the name of subject like C/C++/Java/OS/DS");
		String sub = Scannerobj.nextLine();
		
		System.out.println("Enter the time period of your study in hours");
		double dur = Scannerobj.nextDouble();
		Scannerobj.nextLine();
		
		System.out.println("Please provide the Description about the study for future reference");
		String desc = Scannerobj.nextLine();
		
		StudyLog Studyobj = new StudyLog(Dateobj,sub,dur,desc);
		
		Database.add(Studyobj);
		
		System.out.println("Study log get stored sucessfully");
		System.out.println("---------------------------------------------------------------------");
	}//end of Insert log

	/////////////////////////////////////////////////////////////////////////////
	//
	//  Function Name :     DisplayLog
	//  Description   :     It is used to display Study details.
	//  Author        :     Gaurav Subhash Kumbhar
	//
	/////////////////////////////////////////////////////////////////////////////

	public void DisplayLog()
	{
		System.out.println("---------------------------------------------------------------------");
		
		if(Database.isEmpty())
		{
			System.out.println("Noting to display as database is empty");
			System.out.println("---------------------------------------------------------------------");
			return;
		}
		
		System.out.println("---------------------------------------------------------------------");
		System.out.println("---------------Log report from marvellous study tracker--------------");
		System.out.println("---------------------------------------------------------------------");
		
		for(StudyLog sobj : Database)
		{
			System.out.println(sobj);
		}
		System.out.println("---------------------------------------------------------------------");
	}// end of DisplayLog
	
	
	/////////////////////////////////////////////////////////////////////////////
	//
	//  Function Name :     ExportCSV
	//  Description   :     It is used to create a CSV log file
	//  Author        :     Gaurav Subhash Kumbhar
	//
	/////////////////////////////////////////////////////////////////////////////

	public void ExportCSV()
	{
		if(Database.isEmpty())
		{
			System.out.println("---------------------------------------------------------------------");
			System.out.println("Noting to Export as database is empty");
			System.out.println("---------------------------------------------------------------------");
			return;
		}
		
		String FileName = "MyStudyTracker.csv";
		
		// Create new csv file
		try(FileWriter fwobj = new FileWriter(FileName))
		{
			//
			fwobj.write("Date,Subject,Duration,Description\n");
			
			// Travel Datbase
			for(StudyLog sobj : Database)
			{
				fwobj.write(sobj.getDate() + ","+
							sobj.getSubject().replace(","," ") + ","+
							sobj.getDuration() + ","+
							sobj.getDescription().replace(","," ") + "\n"
				);
			}
			
			System.out.println("Log created sucessfully");
		}
		catch(Exception eobj)
		{
			System.out.println("Exception occured while creating the CSV");
			System.out.println("Report this to My Project");
		}
	}//end of ExportCSV
	
	///////////////////////////////////////////////////////////////////////////////////
	//
	//  Function Name :     SummaryByDate
	//  Description   :     This function generates Summary of study log by date
	//  Author        :     Gaurav Subhash Kumbhar
	//
	///////////////////////////////////////////////////////////////////////////////////
	
	public void SummaryByDate()
	{
		System.out.println("---------------------------------------------------------------------");
		
		if(Database.isEmpty())
		{
			System.out.println("Noting to display as database is empty");
			System.out.println("---------------------------------------------------------------------");
			return;
		}
		
		System.out.println("---------------------------------------------------------------------");
		System.out.println("----------------Summary by date from study tracker-------------------");
		System.out.println("---------------------------------------------------------------------");
		
		TreeMap <LocalDate,Double> tobj = new TreeMap <LocalDate,Double> ();
		
		LocalDate lobj = null;
		double d;
		double old;
		
		for(StudyLog sobj : Database)
		{
			lobj = sobj.getDate();
			d = sobj.getDuration();
			
			if(tobj.containsKey(lobj))
			{
				old = tobj.get(lobj);
				tobj.put(lobj,d + old);
			}
			else
			{
				tobj.put(lobj,d);
			}
		}
		
		// Display the details as per date
		
		for(LocalDate ldobj : tobj.keySet())
		{
			System.out.println("Date : "+lobj+" Total Study "+tobj.get(lobj));
		}
		
		System.out.println("---------------------------------------------------------------------");
	}// end of SummaryByDate
	
	///////////////////////////////////////////////////////////////////////////////////
	//
	//  Function Name :     SummaryBySubject
	//  Description   :     This function generates Summary of study log by subjects
	//  Author        :     Gaurav Subhash Kumbhar
	//
	///////////////////////////////////////////////////////////////////////////////////

	public void SummaryBySubject()
	{
		System.out.println("---------------------------------------------------------------------");
		
		if(Database.isEmpty())
		{
			System.out.println("Noting to display as database is empty");
			System.out.println("---------------------------------------------------------------------");
			return;
		}
		
		System.out.println("---------------------------------------------------------------------");
		System.out.println("----------------Summary by subject from study tracker----------------");
		System.out.println("---------------------------------------------------------------------");
		
		TreeMap <String,Double> tobj = new TreeMap <String,Double> ();
		
		double d;
		double old;
		String s;
		
		for(StudyLog sobj : Database)
		{
			s = sobj.getSubject();
			d = sobj.getDuration();
			
			if(tobj.containsKey(s))
			{
				old = tobj.get(s);
				tobj.put(s,d + old);
			}
			else
			{
				tobj.put(s,d);
			}
		}
		
		// Display the details as per subject
		
		for(String str : tobj.keySet())
		{
			System.out.println("Date : "+str+" Total Study "+tobj.get(str));
		}
		
		System.out.println("---------------------------------------------------------------------");
	}// end of SummaryBySubject
	
}// end of StudyTracker class


///////////////////////////////////////////////////////////////////////////////////////
//
//  Class Name :        StudyTrackerStarter
//  Description :       Acts as the entry point for the Study Tracker application.
//  Author :            Gaurav Subhash Kumbhar
//
//////////////////////////////////////////////////////////////////////////////////////

class StudyTrackerStarter	
{
	public static void main(String A[])
	{
		StudyTracker stobj = new StudyTracker();
		
		Scanner Scannerobj = new Scanner(System.in);
		
		int iChoice = 0;
		
		System.out.println("---------------------------------------------------------------------");
		System.out.println("-----------------Welcome to study tracker application----------------");
		System.out.println("---------------------------------------------------------------------");
		
		do
		{
			System.out.println("please select the appropriate option");
			System.out.println("1 : Insert new study log into database");
			System.out.println("2 : View all the study log");
			System.out.println("3 : Summary of study log by date");
			System.out.println("4 : Summary of study log by subject");
			System.out.println("5 : Export study log to CSV file");
			System.out.println("6 : Exit the application");
			
			iChoice = Scannerobj.nextInt();
			
			switch(iChoice)
			{
				case 1 :		// Insert new study log into database
				
					stobj.InsertLog();
					break;
				
				case 2 :		// View all the study log
					
					stobj.DisplayLog();
					break;
			
				case 3 :		// Summary of study log by date
					
					stobj.SummaryByDate();
					break;
				
				case 4 :		// Summary of study log by subject
				
					stobj.SummaryBySubject();
					break;
				
				case 5 :		// Export study log to CSV file
				
					stobj.ExportCSV();
				
					break;
				
				case 6 :		// Exit the application
					
					System.out.println("----------------------------------------------------");
					System.out.println("Thank you for using marvellous study log application");
					System.out.println("----------------------------------------------------");
					
					
					break;

				default:
				
					System.out.println("Please valid input");
			}
		}while(true);
		
	}

}
