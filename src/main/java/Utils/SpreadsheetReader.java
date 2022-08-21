package Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;

public class SpreadsheetReader {
	/** Application name. */
	private static final String APPLICATION_NAME =
			"Google Sheets API Java Quickstart";
	
	public static final String SPREADSHEET_ID="1UWirtflQfy4h_X7vxAaIX1HqnxyowXVoCo6J6QSJn-8";
	/** Directory to store user credentials for this application. */
	private static final java.io.File DATA_STORE_DIR = new java.io.File(
			System.getProperty("user.home"), ".credentials/2/sheets.googleapis.com-java-quickstart");

	/** Global instance of the {@link FileDataStoreFactory}. */
	private static FileDataStoreFactory DATA_STORE_FACTORY;

	/** Global instance of the JSON factory. */
	private static final JsonFactory JSON_FACTORY =
			JacksonFactory.getDefaultInstance();

	/** Global instance of the HTTP transport. */
	private static HttpTransport HTTP_TRANSPORT;

	/** Global instance of the scopes required by this quickstart.
	 *
	 * If modifying these scopes, delete your previously saved credentials
	 * at ~/.credentials/sheets.googleapis.com-java-quickstart
	 */
	private static final List<String> SCOPES =
			Arrays.asList(SheetsScopes.DRIVE);

	static {
		try {
			HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
			DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
		} catch (Throwable t) {
			t.printStackTrace();
			System.exit(1);
		}
	}
	/**
	 * Creates an authorized Credential object.
	 * @return an authorized Credential object.
	 * @throws IOException
	 */
	public static Credential authorize() throws Exception {
		// Load client secrets.
		InputStream in =
				new FileInputStream("client_secret.json");  
		GoogleClientSecrets clientSecrets =
				GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

		// Build flow and trigger user authorization request.
		GoogleAuthorizationCodeFlow flow =
				new GoogleAuthorizationCodeFlow.Builder(
						HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
				.setDataStoreFactory(DATA_STORE_FACTORY)
				.setAccessType("offline")
				.build();
		Credential credential = new AuthorizationCodeInstalledApp(
				flow, new LocalServerReceiver()).authorize("user");
		System.out.println(
				"Credentials saved to " + DATA_STORE_DIR.getAbsolutePath());
		return credential;
	}
	/**
	 * Build and return an authorized Sheets API client service.
	 * @return an authorized Sheets API client service
	 * @throws IOException
	 */
	public static Sheets getSheetsService() throws Exception {
		Credential credential = authorize();
		return new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
				.setApplicationName(APPLICATION_NAME)
				.build();
	}

	/**
	 * This function used to get Gsheet services to get values, read data and other actions.
	 * @param sheetName
	 * @return
	 * @throws Exception
	 */
	public static List<List<Object>> readSpreadSheet(String sheetName) throws Exception
	{
		// Build a new authorized API client service.
		Sheets service = getSheetsService();

		// Prints the names and majors of students in a sample spreadsheet:
		// https://docs.google.com/spreadsheets/d/1BxiMVs0XRA5nFMdKvBdBZjgmUUqptlbs74OgvE2upms/edit
		String range = sheetName+"!A:AZ";
		ValueRange response = service.spreadsheets().values()
				.get(SPREADSHEET_ID, range)
				.execute();
		System.out.println(response);
		List<List<Object>> values = response.getValues();
		return values;
	}
	
	public void whenWriteSheet_thenReadSheetOk(String sheetName) throws Exception {
		Sheets service = getSheetsService();
		
	    ValueRange body = new ValueRange()
	      .setValues(Arrays.asList(
	        Arrays.asList("Expenses January", "books"), 
	        Arrays.asList("books", "30"), 
	        Arrays.asList("pens", "10"),
	        Arrays.asList("Expenses February"), 
	        Arrays.asList("clothes", "20"),
	        Arrays.asList("shoes", "5")));
	    
	    String range = sheetName+"!A:AZ";
	    service.spreadsheets().values()
	      .update(SPREADSHEET_ID, range, body)
	      .setValueInputOption("RAW")
	      .execute();
	    
//	    List<ValueRange> data = new ArrayList<>();
//	    String range = sheetName+"!A:AZ";
//	    data.add(new ValueRange()
//	      .setRange(range)
//	      .setValues(Arrays.asList(
//	        Arrays.asList("January Total", "=B2+B3"))));
//	    data.add(new ValueRange()
//	      .setRange(range)
//	      .setValues(Arrays.asList(
//	        Arrays.asList("February Total", "=B5+B6"))));
//
//	    BatchUpdateValuesRequest batchBody = new BatchUpdateValuesRequest()
//	      .setValueInputOption("USER_ENTERED")
//	      .setData(data);
//
//	    BatchUpdateValuesResponse batchResult = service.spreadsheets().values()
//	      .batchUpdate(SPREADSHEET_ID, batchBody)
//	      .execute();
		
//		String range = sheetName+"!A:AZ";
//	    
//	    ValueRange appendBody = new ValueRange()
//	    		  .setValues(Arrays.asList(
//	    		    Arrays.asList("Total", "=E1+E4")));
//	    		AppendValuesResponse appendResult = service.spreadsheets().values()
//	    		  .append(SPREADSHEET_ID, "A8", appendBody)
//	    		  .setValueInputOption("USER_ENTERED")
//	    		  .setInsertDataOption("INSERT_ROWS")
//	    		  .setIncludeValuesInResponse(true)
//	    		  .execute();
	    		        
	    		
	    
//	    System.out.println("abc");
	}
}