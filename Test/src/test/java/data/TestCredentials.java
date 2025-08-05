package data;

public class TestCredentials {
	
		//Login TestCases data
	 	public static final String VALID_EMAIL = "superadmin@m3tech.com.pk";
	    public static final String VALID_PASSWORD = "superAdmin123@";
	    public static final String INVALID_PASSWORD = "wrongpass";
	    public static final String EMPTY_EMAIL = "";
	    public static final String EMPTY_PASSWORD = "";
	    public static final String INVALID_EMAIL = "super@gamil";

	    //Reset Password Details
	    public static final String CURRENT_PASSWORD = "superAdmin123@";
	    public static final String NEW_PASSWORD = "superAdmin123@@";
	    
	    //Create User Details
	    public static final String User_Email = "testing@m3tech.com.pk";
	    public static final String User_Email_N = "test_1@m3tech.com.pk";
	    public static final String User_Name = "Testing";
	    public static final String User_Name_N = "TestAuto_1";
	    public static final String User_UserName = "Testinging";
	    public static final String User_UserName_N = "TestingAuto_1";
	    public static final String User_Contact = "03215874695";
	    public static final int User_RoleId = 2;
	    public static final String UserPassword = "superAdmin123@";
	    
	    //Expected Messages
	    public static final String Success_Status ="success";
	    public static final String Success_Error ="error";
	    public static final String Success_Message = "Successfully Created";
	    public static final String Success_Get_Message = "Successfully Get";
	    public static final String Success_Delete ="Successfully Deleted";
	    //User
	    public static final String User_DuplicateName_Message = "User Already exist!";
	    public static final String User_Invalid_Body = "Invalid body";
	    public static final String User_DuplicateEmail_Message = "email must be unique";
	    public static final String User_DuplicateUserName_Message = "userName must be unique";
	    public static final String User_Success_Delete ="User deleted successfully";
	    public static final String Delete_SuperAdmin_Message ="Cannot delete SuperAdmin user";
	    //Auth
	    public static final String Invalid_Email_Password = "Email or password is invalid";
	    public static final String Success_Login ="Login Successful";
	    public static final String Success_Logout ="Logout successful";
	    //Module Data
	    public static final String Module_Name ="TestModule1";
	    public static final String Module_Path ="/testAuto";
	    public static final String Module_Icon ="ri:icon";
	    public static final String Module_Duplicate_Message ="Module already exists";
	    //ActionData
	    public static final String Action_Name ="Add_Action";
	    public static final String Action_Duplicate_Message ="Action Already exists!";
	    public static final String Action_Update_Name ="Add_Action_update";
	    public static final String Action_Update_Success_Message ="Action updated successfully";
	    //Page Data
	    public static final String Page_Name ="TestModule1";
	    public static final String Page_Path ="/testAuto";
	    public static final String Page_Icon ="ri:icon";
	    public static final String Page_Duplicate_Message ="Path already exists";
	    public static final String Page_Invalid_ParentId_Message ="Parent is not exists with given ID";
	  //Role Data
	    public static final String Role_Name ="TestRole213";
	    public static final String Role_Name_New ="TestRoleDummy213";
	    public static final String Role_Decription ="Mid Access Tesing Role213";
	    public static final String Role_Type_User ="User";
	    public static final String Role_Type_User_Invalid ="TestUser";
	    public static final String Role_Type_Admin ="Admin";
	    public static final String Role_Invalid_Page_Message ="Requested Page(s) does not exist";
	    public static final String Role_Invalid_Action_Message ="Requested Action(s) does not exist.";
	    public static final String Role_Invalid_Body_Message ="Invalid body";
	    public static final String Role_Get_Success_Message ="Role fetched Successfully";
	    public static final String Role_Get_Success_ByID_Message ="Role retrieved successfully by ID";
	    public static final String Role_Delete_Successfully_Message ="Role Deleted Successfully";
	    public static final String Role_Duplicate_Message ="Role already exists!";
	    public static final String Role_Success_Message ="Role Created Successfully";
	    
	    //Expected ResponseCode
	    public static final int StatusCode_OK = 200;
	    public static final int StatusCode_Created = 201;
	    public static final int StatusCode_BadRequest = 400;
	    public static final int StatusCode_Unauthorized = 401;
	    public static final int StatusCode_Forbidden = 403;
	    public static final int StatusCode_NotFound = 404;
	    
	
	 
	    
	   
	    
}
