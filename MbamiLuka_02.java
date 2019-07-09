/**
 * This class creates instances of students from input files
 * and outputs to respective output files.
 * 
 * Input:
 * Project_02_InputXX.txt, where XX means file number.
 * 
 * Process: The file number to be read from is obtained from the 
 * keyboard. Then a valid instance of a student is created through
 * hard-coding, in order to test the toString() method. The same is done
 * for the GradeItem class.
 * 
 * Output:
 * Project_02_OutputXX.txt, where XX means file number.
 * 
 *  @author Mbami and Ryan
 *  
 *  Sesquipedalian - one who uses big words
 *  
 *  "We know who we are, yet we know not who we may be"
 *  										   - Jay Z
 */
import java.io.*;
import java.util.Scanner;

public class MbamiLuka_02                                                           
{	

	public static void main ( String[]args) throws IOException
	{
		String OUTPUT_FILENAME;					// Output file name
		
		OUTPUT_FILENAME = "Project_02_Output01.txt";
		
		File outputFile;						// File which will be written to
		PrintWriter output = null;				// Print writer for output file
		output = new PrintWriter ( OUTPUT_FILENAME);
		
		Scanner input = null;					// Scanner which reads inputFile	
				
		Student student1 ;						// First new student
		GradeItem gradeItem1;					// First new grade item
		Student student2 = null;				// Second new student
		GradeItem gradeItem2 = null;			// Second new grade item
		
		String separator; 						// Divider for each test
		String [] arrOfData;					// Array of data read
		
		separator= "\n*********************************************************\n";											
		
		// TEST SET 1
		
		// Test 1a output - tests constructors and toString() method
		
		student1 = new Student 
				 ( "900940940", "Mbami", "Luka", "mluka@msudenver.edu");
		
		displayCurrentTest( output, "1a");
		System.out.println ( student1.toString() + "\n");
			
		output.println ( );
		output.println ( student1.toString() + "\n");
		
				
		// Test 1b output - tests constructors and toString() method
		displayCurrentTest( output, "1b");
		gradeItem1 = new GradeItem 
				   ( "1",900940940,"MTH2040","HW","20190112",100,95);
		
		
		System.out.println ( gradeItem1.toString());
		System.out.println ( separator);
		
		output.println ( gradeItem1.toString());
		output.println ( separator);
		
		
		
		// TEST SET 2 
		displayCurrentTest ( output, "2a");
		
		// Open inputFile - Here XX will be 01
		input = openInputFile ();
	
		
		// Test 2a - PROCESS STUDENT DATA
		arrOfData = makeInputLine1AnArray ( input);
		
		// Verify if first word of data is STUDENT
		if ( arrOfData[0].equals ( "STUDENT"))
		{
		// Student2 is processed with values of input01
			student2 = processStudentData ( arrOfData);
		}
		else
		{
			System.err.println ( "Expected 1st value: STUDENT");
			System.err.println ( "Detected 1st value: " + arrOfData[0]);
		} // End check for first word - STUDENT
		
		System.out.println ( student2.toString() + "\n");
		output.println ( student2.toString() + "\n");

		
		// Test 2b - Process gradeitem2 data using input02
		displayCurrentTest( output, "2b");
		
		// SPECS - Read the 2nd line from a second input file
		
		//  Open inputFile - Here XX will be 02
		input = openInputFile();
						
		// Read 2nd line of input file and assign to array
		arrOfData = makeInputLine2AnArray ( input);
		
		
		// Verify first word of data is GRADE ITEM
		if ( arrOfData[0].equals ( "GRADE ITEM"))
		{	
			
		// gradeItem2 is processed with values of input02
			gradeItem2 = processGradeItemData ( arrOfData);
			
		}
		else
		{			
			System.err.println ( "Expected 1st value: GRADE ITEM");
			System.err.println ( "Detected 1st value: " + arrOfData[0]);
			
		} // End check for first word - GRADE ITEM
	
		
		System.out.println ( gradeItem2.toString());
		System.out.println ( separator);
		
		output.println ( gradeItem2.toString());
		output.println ( separator);
		
		
		// TEST SET 3 - Testing The Equals Methods
				
		// Test 3a - (i) compare unequal students
		displayCurrentTest ( output, "3a");	
		
		checkStudentEquality ( student1, student2, output);
		
		// (ii) compare equal students - Input01 matches Input03 Student line
		input = openInputFile();
		arrOfData = makeInputLine1AnArray ( input);
		
		// student1 is processed with similar data as student2 from input03
		student1 = processStudentData ( arrOfData);
		
		checkStudentEquality ( student1, student2, output);
		
		// Test 3b - (i) compare unequal grade items
		displayCurrentTest ( output, "3b");
		
		checkGradeItemEquality ( gradeItem1, gradeItem2, output);
		
		// (i) compare equal grade items
		input = openInputFile();
		arrOfData = makeInputLine2AnArray ( input);
		
		// gradeItem1 is assigned similar data as gradeItem2 using input04
		gradeItem1 = processGradeItemData ( arrOfData);
		
		checkGradeItemEquality ( gradeItem1, gradeItem2, output);
		
		output.flush();
		
		// INVALID VALUES TEST 
		output = new PrintWriter ("Project_02_Output02");
		
		// File with Incorrect email
		input = openInputFile();	// Open inputFile05
		arrOfData = makeInputLine1AnArray ( input);
		processStudentData ( arrOfData);
		
		// File with Incorrect grade type
		input = openInputFile();	// Open inputFile06
		arrOfData = makeInputLine2AnArray ( input);
		processGradeItemData ( arrOfData);
		
		// File with Incorrect maximum score
		input = openInputFile();	// Open inputFile07
		arrOfData = makeInputLine2AnArray ( input);
		processGradeItemData ( arrOfData);
		
		// File with Incorrect actual score
		input = openInputFile();	// Open inputFile08
		arrOfData = makeInputLine2AnArray ( input);
		processGradeItemData ( arrOfData);
		
		// FileNotFound test
		input = openInputFile();	// Open inputFile09, which does exist
		arrOfData = makeInputLine2AnArray ( input);
		processGradeItemData ( arrOfData);
	
	} // End main method	
		
	//*************************************************************************
	/**
	 * This method takes in array of data, which initially came from 
	 * lines of input files. Then, it checks the 2nd value (index 1) 
	 * in the array if it is STUDENT. If it is, the next four values in the
	 * array are used as parameters for a new Student object.
	 * 
	 * @param	data which corresponds to lines from input files
	 * 
	 * @return	Student object created with 'data' parameters
	 * 
	 * @throws	IllegalArgumentException if any student parameter is wrong
	 */
	public static Student processStudentData ( String[]data)
	{
		
		// Check the second item in data array if it's 'ADD'
		if(data[1].equals( "ADD"))
		{
			try 
			{
				Student student = new Student ( data[2], data[3], 
											    data[4], data[5]);
				return student;
				
			}
			catch ( IllegalArgumentException e)
			{
				System.err.println(e.getMessage());
				
			}		
		}
		
		else 
		{
			System.err.println 
			( "Expected: ADD from input file's 2nd value");
			
	        System.err.println ( "Detected: " + data[1]);
	        
		} // Check the second item in data array if it's 'ADD'
		return null;
		
	}
	
	//*************************************************************************	
	/**
	 * This method takes in array of data, which initially came from 
	 * lines of input files. Then, it checks the 2nd value (index 1) 
	 * in the array if it is ADD. If it is, the next seven values in the
	 * array are used as parameters for a new Grade Item.
	 * 
	 * @param	data which corresponds to lines from input files
	 * 
	 * @return	GradeItem object created with 'data' parameters
	 * 
	 * @throws	IllegalArgumentException if any GradeItem parameter is wrong
	 */
	public static GradeItem processGradeItemData ( String[] data) 
	{
	      
		// Check the second item in data array if it's 'ADD'
	      if ( data[1].equals ( "ADD")) 
	      {
	         try 
	         {
	        	 
	         GradeItem gradeItem = new GradeItem
	         ( data[3], Integer.parseInt ( data[2]),
	        		 
	           data[4], data[5], data[6], Integer.parseInt ( data[7]),
	           
	           Integer.parseInt( data[8])
	          );
	         
	         return gradeItem;
	         
	         } 
	         catch ( Exception e) 
	         {
	        	 
	            System.err.println
	               ( e.getMessage());
	            
	         } // end try/catch block
	      } 
	      
	      else 
	      {
	         
	    	  System.err.println 
			  ( "Expected: ADD from input file's 2nd value");
				
		       System.err.println ( "Detected: " + data[1]);
	         
	      } // end checking if 2nd item of data array is ADD
		return null;
	      
	   } // end processGradeItemData
	
	//*************************************************************************	
	/**
	 * This method will utilize the 'equals' method from the GradeItem class
	 * to compare two Grade Items. Based on the return value from the equals
	 * method, the appropriate message is printed to a specified text file,
	 * and mirrored to the console as well.
	 * 
	 * @param gradeItem1
	 * @param gradeItem2
	 * @param output
	 */
	public static void checkGradeItemEquality
	( GradeItem gradeItem1, GradeItem gradeItem2, PrintWriter output)
	{
		if (gradeItem1.equals ( gradeItem2))
		{
			System.out.println ( "Grade Item objects are equal: ");		 
			System.out.println
			 ( "Student IDs are " + gradeItem1.getStudentId() + "\n");
			
			
			output.println ( "Grade Item objects are equal: ");
			output.println
			 ( "Student IDs are " + gradeItem1.getStudentId() + "\n");
			
		}
		else
		{
			System.out.println( "Grade Item objects are unequal: ");
			 
			System.out.print
			 ( "Student IDs are " + gradeItem1.getStudentId());
			System.out.println( " and " + gradeItem2.getStudentId() +"\n");
			
			
			output.println( "Grade Item objects are unequal: ");
			 
			output.print
			 ( "Student IDs are " + gradeItem1.getStudentId());
			output.println( " and " + gradeItem2.getStudentId() +"\n");
		}
		
	} // End checkGradeItemEquality
	
	//*************************************************************************
	
	/**
	 * This method will utilize the 'equals' method from the Student class
	 * to compare two Students. Based on the return value from the equals
	 * method, the appropriate message is printed to a specified text file,
	 * and mirrored to the console as well.
	 * 
	 * @param student1
	 * @param student2
	 * @param output
	 */
	public static void checkStudentEquality
	(Student student1, Student student2, PrintWriter output)
	{
		
		if ( student1.equals ( student2))
		{
			
			System.out.println ( "Student objects are equal: ");
			output.println ( "Student objects are equal: ");
			
			System.out.println
			 ( "Student IDs are " + student1.getStudentId() + "\n");
			output.println
			 ( "Student IDs are " + student1.getStudentId() + "\n");
			
		}
		else
		{
			
			System.out.println ( "Student objects are unequal: ");
			output.println ( "Student objects are unequal: ");
			
			
			System.out.print
			 ( "Student IDs are " + student1.getStudentId());
			System.out.println ( " and " + student2.getStudentId() + "\n");
			
			output.print
			 ( "Student IDs are " + student1.getStudentId());
			output.println ( " and " + student2.getStudentId() + "\n");
			
		} // End comparison between student1 & student2
		
	} // End checkStudentEquality
	
	//*************************************************************************
	/**
	 * This method is used in creating a scanner that reads from a specified
	 * input file. The file Name/number is gotten from the keyboard, and 
	 * a file is created with this entry in the name.
	 * 
	 * @return	input - the scanner which can be used in scanning input files
	 */
	public static Scanner openInputFile ()
	{
		// Verify if input file exists
		try
		{
			String xx = "";							
			xx = FileNumber.getFileNumber();
			String INPUT_FILENAME = "Project_02_Input" + xx + ".txt";;					
				
										
			File inputFile = new File ( INPUT_FILENAME);
			Scanner input = new Scanner ( inputFile);
			
			
			return input;
		}
		catch ( IOException e)
		{
			
			System.err.println
			( "Error opening file: " + e.getMessage());
			
			
		} // End verifying existence of input file
		return null;
	}
	
	//*************************************************************************	
	/*
	 * This method displays the current test running to the console
	 * and a specified output file
	 * @param	output - this specified PrintWriter will be written to.
	 * @param	testNum - with this the user can specify the test being run
	 */
	public static void displayCurrentTest( PrintWriter output, String testNum)
	{
		String action;
		
		action = "Running Test: ";
		
		System.out.println ( action + testNum);
		output.println ( action + testNum);
	}
	//*************************************************************************	
	/*
	 * This method takes in an scanner input and returns an array 
	 * of the 1st line
	 * @param	input - this scanner is used to read lines
	 * @return	arrayOfInput - input is transformed to an array
	 */
	public static String [] makeInputLine1AnArray ( Scanner input)
	{
		String [] arrOfInput;
		
		String data;
		data = input.nextLine();
		arrOfInput = data.split(",");
		
		return arrOfInput;
	}
	
	//*************************************************************************	
	/*
	 * This method takes in an scanner input and returns an array 
	 * of the 2nd line
	 * @param	input - this scanner is used to read lines
	 * @return	arrayOfInput - input is transformed to an array
	 */
	public static String [] makeInputLine2AnArray ( Scanner input)
	{
		String [] arrOfInput;
		
		String data;
		data = input.nextLine();
		data = input.nextLine();
		arrOfInput = data.split(",");
		
		return arrOfInput;
	}
	//*************************************************************************

} // End MbamiLuka_02

