import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/*
*
* @author charles scruggs @studentid 110612856
* @class CIS 331 @section 1
* Assignment 6
* This work was created honorably and adheres to the JMU honor code.
*
*/
public class UserApplication
{
	public static void main(String[] args) throws Exception
	{
		Scanner input = new Scanner(System.in);
		boolean continueInput = true;
		int choice = 1;
		
		/*
		 * Loop, continuously presenting a menu of choices until the user wants to quit. 
		 */
		do 
		{
			try
			{
				//print menu
				System.out.println("(1) Read user file data\n"
						+ "(2) Add new user\n"
						+ "(3) List users\n"
						+ "(4) Display information about a user\n"
						+ "(5) User login\n"
						+ "(6) Add an admin user\n"
						+ "(7) List admin users\n"
						+ "(8) Write user file\n"
						+ "(9) quit\n");
		
				System.out.println("What is your choice? (1-9)");
				
				choice = input.nextInt();
				
				// if true, valid input has been found
				if (choice > 0 && choice < 9)
				{
					// reading the user file data,
					if (choice == 1)
					{
						//helper method to read file data
						readFileData();
					}
					
					// adding a new user via keyboard input
					else if (choice == 2)
					{
						addNewUser();
					}
					
					//listing the users
					else if (choice == 3)
					{
						System.out.println("User list");
						System.out.println(User.listUsers());
					}
					
					// finding and displaying information about a user based on their name,
					else if (choice == 4)
					{
						findUserHelper();
					}
					
					//attempting a user login
					else if (choice == 5)
					{
						userLoginHelper();
					}
					
					//add admin user
					else if (choice == 6)
					{
						addnewAdminUser();
					}
					
					//list admin users
					else if (choice == 7)
					{
						System.out.println("Admin user list");
						
						System.out.println("Which permission do you want to focus on? ");
						
						Scanner focus = new Scanner(System.in);
						String perm = focus.next();

						System.out.println(AdminUser.listAdminUsers(perm.toLowerCase()));
					}
					
					//writing user data to a new file
					else if (choice == 8)
					{
						writeUserFile();
					}
					
				}
				
				else if (choice == 9)
				{
					continueInput = false;
				}
				
				else
				{
					System.out.println("Invalid choice, please enter a number between 1 - 9");
				}
			}
			catch (InputMismatchException ex) {
				System.out.println("Invalid choice, please try again : ");
				input.nextLine();
			}
		} while(continueInput);
		
		input.close();
	}

	//write the data from the users array into a new text file, with formatting
	private static void writeUserFile() 
	{
		
		try 
		{
			FileWriter fWriter = new FileWriter("users.txt");
			
			for (int i = 0; i < User.totUsers; i++)
			{
				fWriter.write(User.getUser(i).getFirstName() + "\t" + User.getUser(i).getLastName() +
							"\t" + User.getUser(i).getUserID() + "\t" + User.getUser(i).getPass() + "\n");
				
			}
			
			fWriter.close();
			System.out.println("User File written");
		}
		catch(IOException e)
		{
			System.out.println("An error occured.");
			e.printStackTrace();
		}
	}



	// you should prompt for user id and password
	// then call the User class’s static userLogin method. Display the results that are returned.
	private static void userLoginHelper() 
	{
		System.out.println("Enter user id and password");
		Scanner input = new Scanner(System.in);
		
		String id = input.next();
		
		String pass = input.next();
		
		System.out.println(User.userLogin(id, pass));
		
	}

	// ask the user to enter the name of the user, then call the findUser method passing this value as a parameter.
	// If findUser returns a value of -1 (indicating that the desired user was not found), you should display an error 
	// message that indicates this.
	// If it returns a positive value, you should use this value to obtain the user at that index of the array
	// (call the getUser method to do this),
	// and then call the userInfo method (or whatever you called it in your User class) 
	// passing it a true value in order to obtain the string containing full information about that user.
	// The string that returns from the usertInfo method shoulD then be displayed to the user.
	private static void findUserHelper()
	{
		System.out.print("Enter the user's first and last name to search for: ");
		
		Scanner input = new Scanner(System.in);
		
		String f = input.next();
		String l = input.next();
		
		int index = User.findUser(f + " " + l);
		
		if (index == -1)
		{
			System.out.println(f + " " + l + " not found");
		}
		
		else
		{
			System.out.println(User.getUser(index).userInfo(true));
		}
		
		
	}

	// add new user via keyboard input
	// prompt the user in order to obtain the first and last names, user id, and password of the user
	// then call the addUSer method of the user class passing these as parameters, along with an indication
	// that the password needs to be hashed.
	// If addUser returns a false value (indicating that the array was full), display an error message to the user
	// otherwise display a message indicating a successful add.
	private static void addNewUser() 
	{
		System.out.println("Enter the user's first and last names, separated with a space: ");
		Scanner input = new Scanner(System.in);
		
		String f = input.next();
		String l = input.next();
		
		System.out.println("Enter the user id: ");
		String uid = input.next();
		
		System.out.println("Enter the user password: ");
		String pass = input.next();
		
		// if addUser returns false display error message
		if (!User.addUser(f, l, uid, pass, true))
		{
			System.out.println("Error, user list is full");
		}
		
		else {
			System.out.println("User added");
		}
	}
	
	/*
	 * Similiar to the addNewUserMethod, but instead of creating a User instance, it creates an AdminUser instance.
	 * 
	 */
	private static void addnewAdminUser()
	{
		System.out.println("Enter the user's first and last names, separated with a space: ");
		Scanner input = new Scanner(System.in);
		
		String f = input.next();
		String l = input.next();
		
		System.out.println("Enter the user id: ");
		String uid = input.next();
		
		System.out.println("Enter the user password: ");
		String pass = input.next();
		
		System.out.print("Enter permissions separated by a space: ");
		String perm = "";
		
		input.nextLine();
		
		perm = input.nextLine();
		
		
		// if addUser returns false display error message
		if (!AdminUser.addAdminUser(f, l, uid, pass, perm))
		{
			System.out.println("Error, user list is full");
		}
		
		else {
			System.out.println("Admin user added");
		}
	}

	// read the data in the file. For each line, call User’s static addUser method, passing appropriate arguments.
	// Note that in this case, the password is already hashed, so this should be conveyed in the addUser method call.
	// If this process is done correctly, your array will now have the data from the file.
	private static void readFileData() throws Exception
	{
		String fileName = "users.txt";
		
		File login = new File(fileName);
		
		if(login.exists())
		{
			Scanner input = new Scanner(login);
			
			//an arrayLIst instead of an array is used because we don't know how many lines 
			//the file may have
			String[] result = new String[4];
			
			//this loop simply adds each line from the file to the arraylist
			while (input.hasNext()) 
			{
				for (int i = 0; i < 4; i++)
				{
					result[i] = input.next();
				}

				User.addUser(result[0], result[1], result[2], result[3], false);

			}
			
			System.out.println("File read successfully");
			
			input.close();
		}
		
		
		else 
		{
			System.out.println("Error, file not found");
		}
	}
}
