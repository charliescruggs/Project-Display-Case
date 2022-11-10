import java.security.Permission;

public class AdminUser extends User
{
	private static String permissions;
	
	/*
	 * default constructor that invokes the superclass’s default constructor and then
	 * sets the permissions to an empty string
	 */
	public AdminUser()
	{
		super();
		permissions = "";
	}
	
	/*
	 * overloaded constructor that takes
	 * the first name, last name, user id, password, and permissions string as parameters. The overloaded
	 * constructor passes the User parameters on to the User class’s overloaded constructor, then sets the
	 * permissions variable according to the parameter.
	 */
	public AdminUser(String fn,String ln, String id, String pass, String permissions)
	{
		super(fn, ln, id, pass);
		setPermissions(permissions);
	}
	
	/*
	 * checks that the values of the permissions are among the above valid values. If the parameter contains an
	 * invalid permission, your set- method should not include it, although other valid values should be set into
	 * the member variable.
	 */
	public void setPermissions(String perm)
	{
		String[] permArr = perm.split(" ");
		
		String permRes = ""; 
		
		for (int i = 0; i < permArr.length; i++)
		{
			if (permArr[i].equals("database") || permArr[i].equals("cloud") || permArr[i].equals("security"))
			{
				permRes += permArr[i] + " ";
			}
		}
		permissions = permRes.strip();
	}
	
	/*
	 * getter method for permissions
	 */
	public String getPermissions()
	{
		return permissions;
	}
	
	/*
	 * overridden instance method userInfo. This method should first invoke the User
	 * class's userInfo to obtain the User information, and (if detailed information is required) append to this the
	 * permissions of the admin user. Then the string should be returned to the caller.
	 */
	public String userInfo(boolean full)
	{
		String info = super.userInfo(full);
		
		if (!full)
		{
			return info;
		}
		
		else {
			return info + " " + getPermissions();
		}
	}
	
	/*
	 * creating a AdminUser instance, similar to addUser() method
	 */
	public static boolean addAdminUser(String first, String last, String id, String pass, String permissions)
	{
		// if a null value is found, the full value is set to false, if no null value is found, the full value
		// remains true, and false is returned
		boolean full = true;
		for (User user: users)
		{
			if (user == null)
			{
				full = false;
			}
		}
		
		//the array is full
		if (full)
		{
			return false;
		}
		
		//where should i get permissions from when creating this admin?
		AdminUser admin = new AdminUser(first, last, id, pass, permissions);
		
		//increments the total number of users, then sets the position in the user array for that user
		users[totUsers] = admin;
		
		totUsers += 1;

		
		//a user has been added successfully so return true
		return true;
	}
	
	/*
	 * method takes a parameter to indicate which permission type should be included in the list. 
	 * users that are AdminUsers from the users array should be listed.
	 * When looping through the array (focusing only on admin users), you should check to see if 
	 * the permissions variable contains the passed-in value; alternatively, if “any” is passed you
	 * can show all admin users from the array
	 * This method returns a string containing the list of admin users with the specified permission.
	 */
	public static String listAdminUsers(String permType)
	{
		String adminList = "";
		
		
		if (permType.equals("security"))
		{
			
			for (int i = 0; i < users.length; i++)
			{
				if (users[i] != null)
				{
					if (users[i].toString().equals("Admin user"))
					{
						if (((AdminUser) users[i]).getPermissions().contains("security"))
						{
							adminList += "\t" + users[i].getFirstName() + " " + users[i].getLastName() + " " + users[i].getUserID() + " " + ((AdminUser) users[i]).getPermissions();
						}
						//adds a newline only if the end of the list has not been reached yet
						if (i < users.length - 1)
						{
							adminList += "\n";
						}
					}
				}
			}
		}
		
		else if (permType.equals("database"))
		{
			for (int i = 0; i < users.length; i++)
			{
				if (users[i] != null)
				{
					if (users[i].toString().equals("Admin user"))
					{
						if (((AdminUser) users[i]).getPermissions().contains("database"))
						{
							adminList += "\t" + users[i].getFirstName() + " " + users[i].getLastName() + " " + users[i].getUserID() + " " + ((AdminUser) users[i]).getPermissions();
						}
						//adds a newline only if the end of the list has not been reached yet
						if (i < users.length - 1)
						{
							adminList += "\n";
						}
					}
				}
			}
		}
		
		else if (permType.equals("cloud"))
		{
			for (int i = 0; i < users.length; i++)
			{
				if (users[i] != null)
				{
					if (users[i].toString().equals("Admin user"))
					{
						if (((AdminUser) users[i]).getPermissions().contains("cloud"))
						{
							adminList += "\t" + users[i].getFirstName() + " " + users[i].getLastName() + " " + users[i].getUserID() + " " + ((AdminUser) users[i]).getPermissions();
						}
						//adds a newline only if the end of the list has not been reached yet
						if (i < users.length - 1)
						{
							adminList += "\n";
						}
					}
				}
			}
		}
		
		else if (permType.equals("any"))
		{
			for (int i = 0; i < users.length; i++)
			{
				if (users[i] != null)
				{
					if (users[i].toString().equals("Admin user"))
					{
						adminList += "\t" + users[i].getFirstName() + " " + users[i].getLastName() + " " + users[i].getUserID() + " " + ((AdminUser) users[i]).getPermissions();

						//adds a newline only if the end of the list has not been reached yet
						if (i < users.length - 1)
						{
							adminList += "\n";
						}
					}
				}
			}
		}
		
		return adminList;
	}
	
	/*
	 * toString method that overrides the one of the User
	 * class. In this one, you should just return the value "Admin user" in order to identify the instance
	 */
	public String toString()
	{
		return "Admin user";
	}
}
