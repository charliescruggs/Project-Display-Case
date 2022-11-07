import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/*********************
 * 
 * 		@author Charlie Scruggs/
 * 		@date 8/1/2016
 * 
 ********************/

public class makeXML 
{

	

	public static void main (String[] args)
	{		 
		
		 
		 /////////Opens file, change path if you would like to choose a different user data file	 
		 String csvFile = "src/SampleUserData.csv";
		 BufferedReader br = null;
	     String line = "";
	     String csvSplitBy = ",";
	    
	     try
	     {
	    	 
	    	 DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
	         DocumentBuilder domBuilder = domFactory.newDocumentBuilder();;
	         
	    	 
	    	 Document doc = domBuilder.newDocument();
	         
	         /////////Reads file stored in object "csvFile"
	    	 br = new BufferedReader(new FileReader(csvFile));
    		 int iteration = 0;
    		 
    		 /////////Creates the root element
	 	     Element rootElement = doc.createElement("users");
	 	     doc.appendChild(rootElement);
	 	     
	    	 while ((line = br.readLine()) != null)
	    	 {
	    		 /////////Read the header row and then forget about it.
	    		 if(iteration == 0) 
	    		 {
	    			 iteration++;  
	    		     continue;
	    		 }
	    		 
	    		 /////////prints csv File without headers
	    		 String[] column = line.split(csvSplitBy);
	    		 System.out.println(column[0] + " " + column[1] + " " + column[2] + " " + column[3] + " " + column[4] + " " + column[5] + " " + column[6] + " " + 
	    				 			column[7] + " " + column[8] + " " + column[9] + " " + column[10] + " " + column[11]);	
	    		 
	    		 
//*********************************make xml tags*******************************************************************************************************		    		 
	    		 
	    		 /////////creates user element
	    		 Element user = doc.createElement("user");
	    		 //************adds user to root element ("users")
	    		 rootElement.appendChild(user);	
	    		 
	    		 /////////creates record type element (one attritbute)
	    		 Element recordType = doc.createElement("record_type");
	    		 //************creates attribute
	    		 recordType.setTextContent("PUBLIC");
	    		 recordType.setAttribute("desc", "Public");	
	    		 //************adds record type to child element ("user")
	    		 user.appendChild(recordType);
	    		 
	    		 /////////creates primary id element (no attributes)
	    		 Element primaryID = doc.createElement("primary_id");
	    		 primaryID.setTextContent("ID" + column[0]);
	    		 user.appendChild(primaryID);
	    		 
	    		 /////////creates first name element (no attributes)
	    		 Element fName = doc.createElement("first_name");
	    		 fName.setTextContent(column[2]);
	    		 user.appendChild(fName);
	    		 
	    		 /////////creates middle name element (no attributes)
	    		 Element mName = doc.createElement("middle_name");
	    		 mName.setTextContent(column[3]);
	    		 user.appendChild(mName);

	    		 /////////creates last name element (no attributes)
	    		 Element lName = doc.createElement("last_name");
	    		 lName.setTextContent(column[1]);
	    		 user.appendChild(lName);

	    		 /////////creates full name element (no attributes)
	    		 Element fullName = doc.createElement("full_name");
	    		 fullName.setTextContent(column[2] + " " + column[3] + " " + column[1]);
	    		 user.appendChild(fullName);

	    		 /////////creates user group element (one attritbute)
	    		 Element usrgrgp = doc.createElement("user_group");
	    		 //************creates attribute
	    		 usrgrgp.setTextContent("UG-RBC");
	    		 usrgrgp.setAttribute("desc", "Undergraduate Richard Bland College");	
	    		 //************adds usrgrp to child element ("user")
	    		 user.appendChild(usrgrgp);

	    		 /////////creates campus code element (one attritbute)
	    		 Element cmpsCode = doc.createElement("campus_code");
	    		 //************creates attribute
	    		 cmpsCode.setTextContent("RBC");
	    		 cmpsCode.setAttribute("desc", "Richard Bland College Library");	
	    		 //************adds cmpscode group to child element ("user")
	    		 user.appendChild(cmpsCode);

	    		 /////////creates account type element (one attritbute)
	    		 Element acntType = doc.createElement("account_type");
	    		 //************creates attribute
	    		 acntType.setTextContent("EXTERNAL");
	    		 acntType.setAttribute("desc", "External");	
	    		 //************adds acntType group to child element ("user")
	    		 user.appendChild(acntType);

	    		 /////////creates external id element (no attributes)
	    		 Element exID = doc.createElement("external_id");
	    		 exID.setTextContent("SIS");
	    		 user.appendChild(exID);
	    		 
	    		 /////////creates status element (one attritbute)
	    		 Element status = doc.createElement("status");
	    		 //************creates attribute
	    		 status.setTextContent("ACTIVE");
	    		 status.setAttribute("desc", "Active");	
	    		 //************adds status to child element ("user")
	    		 user.appendChild(status);
	    		 
//****************************************************************************************************************************************	
	    		 
	    		 /////////creates contact info element (contains elements)
	    		 Element cntctInfo = doc.createElement("contact_info");	 
	    		 
//****************************************************************************************************************************************	
	    		 
	    		 /////////creates addresses element (child of contact info element)
	    		 Element addresses = doc.createElement("addresses");
	    		 
	    		 /////////creates address element (two attributes)
	    		 Element address = doc.createElement("address");
	    		 //*************creates attributes 1 & 2    		 
	    		 address.setAttribute("segement_type", "External");
	    		 address.setAttribute("preferred", "true");
	    		 
	    		 /////////creates first line of the address
	    		 Element line1 = doc.createElement("line1");
	    		 line1.setTextContent(column[4]);
	    		 //*************adds first line to address element
	    		 address.appendChild(line1);
	    		 
	    		 /////////creates second line of the address
	    		 Element line2 = doc.createElement("line2");
	    		 line2.setTextContent(column[5]);
	    		 //*************adds second line to address element
	    		 address.appendChild(line2);
	    		 
	    		 /////////creates city element
	    		 Element city = doc.createElement("city");
	    		 city.setTextContent(column[6]);
	    		 address.appendChild(city);
	    		 
	    		 Element sProvince = doc.createElement("state_province");
	    		 sProvince.setTextContent(column[7]);
	    		 //*************adds state/province to the address element
	    		 address.appendChild(sProvince);
	    		 
	    		 Element postCode = doc.createElement("postal_code");
	    		 postCode.setTextContent(column[8]);
	    		 //*************adds postal code to the address element
	    		 address.appendChild(postCode);
	    		 
	    		 Element country = doc.createElement("country");
	    		 country.setTextContent("");
	    		 //*************adds postal code to the address element
	    		 address.appendChild(country);
	    		 
	    		 Element addressNote = doc.createElement("address_note");
	    		 addressNote.setTextContent("");
	    		 //*************adds address note to the address element
	    		 address.appendChild(addressNote);
	    		 
	    		 Element addressTypes = doc.createElement("address_types");	    		 
	    		 
	    		 /////////creates address type element (child of address types)&(one attrinute)
	    		 Element addressType = doc.createElement("address_type");
	    		 addressType.setTextContent("school");
	    		 addressType.setAttribute("desc","School");
	    		 addressTypes.appendChild(addressType);
	    		 //*************adds address types to the address element
	    		 address.appendChild(addressTypes);
	    		 
	    		 ///////// adds address(child) to addresses(parent) 
	    		 addresses.appendChild(address);
	    		 
//**************************************************ENDS addresses AS A PARENT ELEMENT**************************************************************************************		    		 
	    		 
	    		 
	    		 /////////creates emails element
	    		 Element emails = doc.createElement("emails");
	    		 
	    		 /////////creates email element (two attributes)
	    		 Element email = doc.createElement("email");
	    		 email.setAttribute("segment_type", "External");
	    		 email.setAttribute("preferred", "true");
	    		 
	    		 /////////creates element email address (child of email)
	    		 Element emailAdd = doc.createElement("email_address");
	    		 emailAdd.setTextContent(column[11]);
	    		 //************adds emailAdd (child) to email(parent)
	    		 email.appendChild(emailAdd);
	    		 
	    		 ////////creates email types element (child of email)
	    		 Element emailTypes = doc.createElement("email_types");
	    		 
	    		 
	    		 Element emailType = doc.createElement("email_type");
	    		 emailType.setTextContent("school");
	    		 emailType.setAttribute("desc","School");
	    		 //************adds email type to email types
	    		 emailTypes.appendChild(emailType);
	    		 
	    		 //************adds emailType(child) to email(parent)
	    		 email.appendChild(emailTypes);	    		 
	    		 
	    		 ///////// adds email(child) to emails(parent) 
	    		 emails.appendChild(email);
	    		 
//**************************************************ENDS emails AS A PARENT ELEMENT**************************************************************************************	    		 
	    		 
	    		 
	    		 /////////creates phones element
	    		 Element phones = doc.createElement("phones");
	    		 
	    		 ////////creates phone element (three attributtes)
	    		 Element phone = doc.createElement("phone");
	    		 phone.setAttribute("segment_type", "External");
	    		 phone.setAttribute("preffered", "true");
	    		 phone.setAttribute("preferred_sms", "false");
	    		 
	    		 ////////creates phone number element
	    		 Element phnNmbr = doc.createElement("phone_number");
	    		 
	    		 ////////inserts a "-"dash to format phone number 
	    		 String formatNumber = column[10];
	    		 formatNumber.replaceAll("-", "");
	    		 
	    		 if(column[10].length() == 0)
	    		 {
	    			 formatNumber = "";
	    		 }
	    		 
	    		 else
	    		 {
	    			 formatNumber = formatNumber.substring(0, 3) + "-" + formatNumber.substring(3,7);	    		 
		    		 phnNmbr.setTextContent(column[9] + "-" + formatNumber);
	    		 }
	    		 
	    		 
	    		 
	    		 System.out.println(formatNumber);
	    		 //*************adds phnNmbr(child) to phone(parent)
	    		 phone.appendChild(phnNmbr);
	    		 	    		 
	    		 ////////creates phone types element
	    		 Element phnTypes = doc.createElement("phone_types");
	    		 
	    		 ///////creates phone type element (one attribute)
	    		 Element phnType = doc.createElement("phone_type");
	    		 phnType.setTextContent("home");
	    		 phnType.setAttribute("desc", "Home");
	    		 //************adds phntype(child) to phnTypes(parent)
	    		 phnTypes.appendChild(phnType);
	    		 //************adds phnTypes(child) to phone(parent)
	    		 phone.appendChild(phnTypes);
	    		 ///////// adds phone(child) to phones(parent) 
	    		 phones.appendChild(phone);
	    		 
//**************************************************ENDS phones AS A PARENT ELEMENT**************************************************************************************		    		 
	   
	    		 
	    		 /////////adds childs of contact info element (these 3 elements also contain child elements)/////////////
	    		 cntctInfo.appendChild(addresses);    		 
	    		 cntctInfo.appendChild(emails);
	    		 cntctInfo.appendChild(phones);
//****************************************************************************************************************************************	
	    		 
	    		 
	    		 
	    		 ///////// creates parent element: user identifiers
	    		 Element usrIDs = doc.createElement("user_identifiers");
//****************************************************************************************************************************************	
	    		     		 
	    		 ///////// creates user identifier element (one attribute)
	    		 Element usrID1 = doc.createElement("user_identifier");
	    		 usrID1.setAttribute("segment_type", "External");
	    		 
	    		 /////////creates id type element (one attribute)
	    		 Element idType1 = doc.createElement("id_type");
	    		 idType1.setTextContent("OTHER_ID_1");
	    		 idType1.setAttribute("segment_type", "Additional ID 1");
	    		 //*************adds ID type to user ID
	    		 usrID1.appendChild(idType1);
	    		 
	    		 /////////creates value element called "userName
	    		 Element value1 = doc.createElement("value");
	    		 //**********formats username by removing the "@" char and its following contents
	    		 String emailString = column[11];
	    		 String[] emailArray = emailString.split("@");
	    		 value1.setTextContent(emailArray[0]);	    		 
	    		 
	    		 //*************adds value to user ID
	    		 usrID1.appendChild(value1);
	    		 
	    		 /////////creates element status
	    		 Element status1 = doc.createElement("status");
	    		 status1.setTextContent("ACTIVE");
	    		 //*************adds status1 to user ID
	    		 usrID1.appendChild(status1);
	    		 
	    		//*************adds userID1(child) to userIDS (parent)
	    		 usrIDs.appendChild(usrID1);
	    		 
//****************************************************************************************************************************************	    		 
	    		 
	    		 ///////// creates second user identifier element (one attribute)
	    		 Element usrID2 = doc.createElement("user_identifier");
	    		 usrID2.setAttribute("segment_type", "External");
	    		 
	    		 /////////creates id type element (one attribute)
	    		 Element idType2 = doc.createElement("id_type");
	    		 idType2.setTextContent("BARCODE");
	    		 idType2.setAttribute("desc", "Barcode");
	    		 //*************adds ID type to user ID
	    		 usrID2.appendChild(idType2);
	    		 
	    		 /////////creates value element
	    		 Element value2 = doc.createElement("value");
	    		 value2.setTextContent(column[0]);
	    		 //*************adds value2 to user ID
	    		 usrID2.appendChild(value2);
	    		 
	    		 /////////creates element status
	    		 Element status2 = doc.createElement("status");
	    		 status2.setTextContent("ACTIVE");
	    		 //*************adds status2 to user ID
	    		 usrID2.appendChild(status2);
	    		    		 	    		     		 
	    		 
	    		 //*************adds userID(child) to userIDS (parent)
	    		 usrIDs.appendChild(usrID2);
	    		 	    		 
	    		 
	    		 
//****************************************************************************************************************************************		    		 
	    		 ///////adds childs of the "user" element (these elements contain child elements	    		 
	    		 user.appendChild(cntctInfo);
	    		 user.appendChild(usrIDs);
//****************************************************************************************************************************************	 
	    		 
	    	 }
//*************************************Saves new XML file to disk*************************************************************************************	    	 
	      String NewXMLdoc = "src/output.xml"; 
	      TransformerFactory tranFactory = TransformerFactory.newInstance();
	      Transformer aTransformer = tranFactory.newTransformer();
	      aTransformer .setOutputProperty(OutputKeys.INDENT, "yes");
	      aTransformer .setOutputProperty(OutputKeys.METHOD, "xml");
	      aTransformer .setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
	      Source src = new DOMSource(doc);
	      Result result = new StreamResult(new File(NewXMLdoc));
	      aTransformer.transform(src, result);
	      
	     }	     


	     		
	     catch (Exception e)
	     {
	    	 e.printStackTrace();
	     }
	     		
	     finally 
	     {
	    	 if (br != null)
	    	 {
	    		 try 
	    		 {
	    			 br.close();
	    		 }
	    		 catch (IOException e) 
	    		 {
	    			 e.printStackTrace();
	    		 }
	    	 }
	     }
	     
	     
	}
}