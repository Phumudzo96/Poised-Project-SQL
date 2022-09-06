import java.util.*;
import java.text.*;
import java.io.*;
import java.sql.*;

/**
 * my poised subclass is the one that inherit methods from the userInputs super
 * class.
 * <p>
 * This is where my main program runs and even calls other methods. In this
 * method we run the main menu for the user.
 * 
 * @author Phumudzo Mbelengwa
 */
public class Poised {

	/**
	 * This is the main menu that runs the poised method.
	 * <p>
	 * 
	 * @param args java command line arguments
	 * @throws ParseException occurs if a date string is in the wrong format to be
	 *                        parsed
	 */

	public static void main(String[] args){

		/*
		 *  Use username "root", password "Phumuboi1996".
		 *  We will be connecting with the database.
		 */

	
	

            /*
             * The main menu appear for the user to choose from.
             * When you select "2", you then create a new project
             * In this method we'll be able to call upon the other methods that i have
             * The main menu will keep on appearing unless the user selects "8" to exit the
             * program
             */
            
            System.out.println("Welcome to the Poised!!! \n");
            while (true) {
            	System.out.println("\nPlease choose a number option from the menu below: "
            			+ "\n1. View Existing Projects"
            			+ "\n2. Add a New Project"
            			+ "\n3. Update Existing Project Info"
            			+ "\n4. Finalize a Project"
            			+ "\n5. View Incomplete Projects"
            			+ "\n6. Find a Project"
            			+ "\n7. Exit program");

            	Scanner scanner = new Scanner(System.in);
            	int choice = scanner.nextInt();
            	
            	try {
        			Connection connection = DriverManager.getConnection(
                            "jdbc:mysql://127.0.0.1:3306/poisePMS",
                            "root",
                            "Phumuboi1996");
                    
                    // Create a direct line to the database for running our queries.
                    Statement statement = connection.createStatement();
                    ResultSet results;
                    int rowsAffected;
            	
            	if(choice ==1) { // Option 1 allows the user to view projects.
            		
            		results = statement.executeQuery("SELECT projectNum, projectName, building_type FROM project_manager");
        			// Loop over the results, printing them all.
            		while (results.next()) {
            			System.out.println(results.getInt("projectNum") + ", " +results.getString("projectName") + "," + results.getString("building_type"));
            		}

            	}else if(choice == 2) { 
            		
            		Scanner scanner1 = new Scanner(System.in);
            		System.out.println("\nPlease enter the project number: ");
                    int projectNum = scanner1.nextInt();

                    Scanner scanner2 = new Scanner(System.in);
                    System.out.println("Please enter the project name: ");
                    String projectName = scanner2.nextLine();

                    Scanner scanner3 = new Scanner(System.in);
                    System.out.println("Please enter the building type: ");
                    String building = scanner3.nextLine();

                    Scanner scanner4 = new Scanner(System.in);
                    System.out.println("Please enter the physical address for the project: ");
                    String address = scanner4.nextLine();

                    Scanner scanner5 = new Scanner(System.in);
                    System.out.println("Please enter the ERF number: ");
                    int erfNum = scanner5.nextInt();

                    Scanner scanner6 = new Scanner(System.in);
                    System.out.println("Please enter the total fee charged for the project: ");
                    double totalCost = scanner6.nextDouble();

                    Scanner scanner7 = new Scanner(System.in);
                    System.out.println("Please enter the total amount paid to date: ");
                    double paid = scanner7.nextDouble();

                    Scanner scanner8 = new Scanner(System.in);
                    System.out.println("Please enter the project deadline (e.g. year, month, day: 2020-05-12) : ");
                    String deadline = scanner8.nextLine();
                    
                    String completion = "None";
                    
                    String status = "Not finalised";
                    
                    String sql = "INSERT INTO project_manager" + "(projectNum, projectName, building_type, address, erfNum, totalCost, paid, deadline, completion, status)" + 
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement myStmt = connection.prepareStatement(sql);
                    myStmt.setInt(1, projectNum);
                    myStmt.setString(2, projectName);
                    myStmt.setString(3, building);
                    myStmt.setString(4, address);
                    myStmt.setInt(5, erfNum);
                    myStmt.setDouble(6, totalCost);
                    myStmt.setDouble(7, paid);
                    myStmt.setString(8, deadline);
                    myStmt.setString(9, completion);
                    myStmt.setString(10, status);
                    myStmt.executeUpdate();
                    printAllFromTable(myStmt);
                    

            	} else if (choice == 3) { // Option 3 allows the user to edit a project.

            		Scanner scanner9 = new Scanner(System.in);
            		System.out.println(
            				"Please enter the project number of the project you wish to make an update on: \n");
            		int projectInfo = scanner9.nextInt();
            		Scanner scanner10 = new Scanner(System.in);
            		System.out.println("Would you like to:" +
            				"\n1. Edit the project due date or" +
            				"\n2. Edit the total amount paid of the fee to date?" + // Edit options displayed.
            				"\nChoose either 1 or 2");

            		int editChoice = scanner10.nextInt();
				
            		if(editChoice == 1) {
            			Scanner scanner11 = new Scanner(System.in);
            			System.out.println("Enter the new project due date");
            			String dueDate = scanner11.nextLine();
					
            			String sql = "UPDATE project_manager SET deadline=? WHERE projectNum=?";
            			PreparedStatement myStmt = connection.prepareStatement(sql);
            			myStmt.setString(1, dueDate);
            			myStmt.setInt(2, projectInfo);
            			myStmt.executeUpdate();
            			printAllFromTable(myStmt);
	                
            		}else if(editChoice == 2) {
					
            			Scanner scanner11 = new Scanner(System.in);
            			System.out.println("Enter the new amount paid for the project");
            			double newPaid = scanner11.nextDouble();
					
            			String sql = "UPDATE project_manager SET paid=? WHERE projectNum=?";
            			PreparedStatement myStmt = connection.prepareStatement(sql);
            			myStmt.setDouble(1, newPaid);
            			myStmt.setInt(2, projectInfo);
            			myStmt.executeUpdate();
            			printAllFromTable(myStmt);
            		}
			} else if (choice == 4) { 							// Option 4 allows the user to finalize a project.
				
				Scanner scanner12 = new Scanner(System.in);
				System.out.println("Please enter the number of the project that you wish to finalise: ");
				int projectInfo3 = scanner12.nextInt();
				
				ResultSet results2 = statement.executeQuery("SELECT totalCost, paid FROM project_manager WHERE projectNum = " + projectInfo3);
				float totalCost = 0;
				float paid = 0;
				
				while (results2.next()) {
					totalCost = results2.getFloat("totalCost"); 
					paid = results2.getFloat("paid");
					
				}
				
				if (totalCost == paid) {
					System.out.println("This project has already been paid in full. No invoice to be generated.");
					
					// The user is then prompted to enter a completion date, which is written into the 'Completion_Date' column 
					// in the main_project_info table with the executeUpdate() statement.
					Scanner scanner15 = new Scanner(System.in);
					System.out.println("Please add a completion date for the project: ");
					String comp_date = scanner15.nextLine();
					
					// Completion date added to user's chosen project by project number.
					String sql = "UPDATE project_manager SET completion = ? WHERE ProjectNum = ?";
					PreparedStatement myStmt = connection.prepareStatement(sql);
        			myStmt.setString(1, comp_date);
        			myStmt.setInt(2, projectInfo3);
        			myStmt.executeUpdate();
        			printAllFromTable(myStmt);
					
					// The project is then marked as finalised by writing 'Yes' to the finalise column in the table.
					statement.executeUpdate(
		            		 "UPDATE main_project_info SET Finalised = 'Yes' WHERE Project_Num = " + projectInfo3
			                );
					
					// A successful message is displayed and the user is able to view the updated project list.
					System.out.println("Your project has been successfully finalised. View projects below: ");
					printAllFromTable(statement);
				} else if (totalCost != paid) {
					System.out.println("There is still an outstanding amount to be paid for this project. View your invoice below: \n");
					
					// Added to the customer info, is the amount owing on the project.
					System.out.println("\nAmount Outstanding: R" + (totalCost - paid));
					
					// The user is then prompted to enter a completion dated for the project.
					Scanner scanner17 = new Scanner(System.in);
					System.out.println("\nPlease add a completion date for the project: ");
					String comp_date = scanner17.nextLine();
					
					// The date entered by the user is written to the main_project_info table under the 'Completion_Date' column.
					statement.executeUpdate(
							"UPDATE project_manager SET completion = " + "'" + comp_date + "'" + " WHERE projectNum = " + projectInfo3
							);
					
					// The project is then marked as finalised by writing 'Yes' to the finalise column in the table.
					statement.executeUpdate(
		            		 "UPDATE project_manager SET status = 'Finalised' WHERE projectNum = " + projectInfo3
			                );
					
					// A successful message is displayed and the user is able to view the updated project list.
					System.out.println("Your project has been successfully finalised. View projects below: ");
					printAllFromTable(statement);
					
				}
				
			
			} else if (choice == 5) { // Option 5 allows the user to view incomplete projects.
				
				System.out.println("\nPlease view all incomplete projects below: \n");
				
				ResultSet results3 = statement.executeQuery("SELECT * FROM project_manager WHERE status = 'Not finalised' AND completion = 'None'");
				
				// All incomplete projects are displayed using a table iterator.
				while (results3.next()) {
					System.out.println( 
							 "Project Number: \t" + results3.getInt("projectNum")
				                + "\nProject Name: \t" + results3.getString("projectName") 
				                + "\nBuilding Type: \t" + results3.getString("Building_Type")        
				                + "\nPhysical Address: " + results3.getString("Address") 
				                + "\nERF Number: \t" + results3.getString("erfNum") 
				                + "\nTotal Fee: \tR" + results3.getFloat("totalCost") 
				                + "\nAmount Paid: \tR" + results3.getFloat("Paid")  
				                + "\nDeadline: \t" + results3.getString("Deadline") 
				                + "\nFinalised: \t" + results3.getString("status") 
				                + "\nCompletion Date: \t" + results3.getString("completion") 
				                + "\n"
							);
				}

			} else if (choice == 6) { // Option 6 allows the user to find a project to view.
				
				Scanner scanner14 = new Scanner(System.in);
				System.out.println("Please enter the project number of the project you wish to find: ");
				int projectInfo2 = scanner14.nextInt();
				
				ResultSet results5 = statement.executeQuery("SELECT * FROM project_manager WHERE projectNum =" + projectInfo2);
				while (results5.next()) {
					System.out.println(results5.getInt("projectNum") + ", " + results5.getString("projectName") + ", "
							+ results5.getString("building_type") + ", " + results5.getString("address") + ", " +
							results5.getInt("erfNum") + ", " + results5.getDouble("totalCost") + ", " +
							results5.getDouble("paid") + ", " + results5.getString("deadline") + ", " +
							results5.getString("completion") + ", " + results5.getString("status"));
				}
				
			
			} else if (choice == 7 ) { // Option 7 allows the user to exit the program.

				System.out.println("Successfully logged out."); // Successful logout message displayed.
				break;
			}
            	
            	statement.close();
                connection.close();
            
            	
		} catch (SQLException e) {
			// We only want to catch a SQLException
			e.printStackTrace();
		}
	}
	}

	/**
	 * The fileCheck method is run to check for existing projects in the
	 * <p>
	 * It guides the user's menu choices, depending on whether there are existing
	 * projects in SQL.
	 * 
	 */
	public static void printAllFromTable(Statement statement) throws SQLException {
		ResultSet results = statement.executeQuery("SELECT * FROM project_manager");
		while (results.next()) {
			System.out.println(results.getInt("projectNum") + ", " + results.getString("projectName") + ", "
					+ results.getString("building_type") + ", " + results.getString("address") + ", " +
					results.getInt("erfNum") + ", " + results.getDouble("totalCost") + ", " +
					results.getDouble("paid") + ", " + results.getString("deadline") + ", " +
					results.getString("completion") + ", " + results.getString("status"));
		}
	}
}
