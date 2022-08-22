package Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
import com.google.api.services.sheets.v4.model.AddSheetRequest;
import com.google.api.services.sheets.v4.model.BatchUpdateSpreadsheetRequest;
import com.google.api.services.sheets.v4.model.Request;
import com.google.api.services.sheets.v4.model.SheetProperties;
import com.google.api.services.sheets.v4.model.UpdateValuesResponse;
import com.google.api.services.sheets.v4.model.ValueRange;

public class SpreadsheetReader {
	/** Application name. */
	private static final String APPLICATION_NAME = "Google Sheets API Java Quickstart";

	public static final String SPREADSHEET_ID = "1UWirtflQfy4h_X7vxAaIX1HqnxyowXVoCo6J6QSJn-8";

	/** Directory to store user credentials for this application. */
	private static final java.io.File DATA_STORE_DIR = new java.io.File(System.getProperty("user.home"), ".credentials/2/sheets.googleapis.com-java-quickstart");

	/** Global instance of the {@link FileDataStoreFactory}. */
	private static FileDataStoreFactory DATA_STORE_FACTORY;

	/** Global instance of the JSON factory. */
	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

	static List<List<Object>> values;

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
		InputStream in = new FileInputStream("client_secret.json");  

		GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

		// Build flow and trigger user authorization request.
		GoogleAuthorizationCodeFlow flow = 
				new GoogleAuthorizationCodeFlow.Builder(
						HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
				.setDataStoreFactory(DATA_STORE_FACTORY)
				.setAccessType("offline")
				.build();
		Credential credential = new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
		System.out.println("Credentials saved to " + DATA_STORE_DIR.getAbsolutePath());
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
	public static List<List<Object>> readCompleteSpreadSheet(String sheetName) throws Exception
	{
		// Build a new authorized API client service.
		Sheets service = getSheetsService();

		// Prints the names and majors of students in a sample spreadsheet:
		// https://docs.google.com/spreadsheets/d/1UWirtflQfy4h_X7vxAaIX1HqnxyowXVoCo6J6QSJn-8/edit
		String range = sheetName+"!A:AZ";
		ValueRange response = service.spreadsheets().values()
				.get(SPREADSHEET_ID, range)
				.execute();
		System.out.println(response);
		List<List<Object>> values = response.getValues();
		return values;
	}

	public static int getTotalNumberOfRows(String sheetName) throws Exception {

		// Build a new authorized API client service.
		Sheets service = getSheetsService();

		List<List<Object>> response = service.spreadsheets().values()
				.get(SPREADSHEET_ID, sheetName)
				.execute().getValues();

		int numRows = response != null ? response.size() : 0;
		System.out.printf("%d rows retrieved. in '" + sheetName + "'\n", numRows);
		return numRows;
	}

	public void createSheetAndColumn(String sheetName, List<Object> columnNames) throws Exception {
		if (!sheetName.equals("")) {
			createNewSheet(SPREADSHEET_ID, sheetName);
			writeSheet(columnNames, sheetName + "!A1");
		}
	}

	public void writeSheet(List<Object> inputData, String sheetNameAndRange) throws Exception {

		// Build a new authorized API client service.
		Sheets service = getSheetsService();

		List<List<Object>> values = Arrays.asList(inputData);
		ValueRange body = new ValueRange().setValues(values);

		UpdateValuesResponse result = service.spreadsheets().values().update(SPREADSHEET_ID, sheetNameAndRange, body)
				.setValueInputOption("USER_ENTERED").execute();

		System.out.printf("%d cells updated.\n", result.getUpdatedCells());
	}
	

	public void createNewSheet(String SPREADSHEET_ID, String newsheetTitle) throws Exception {

		// Build a new authorized API client service.
		Sheets service = getSheetsService();

		// Create a new AddSheetRequest
		AddSheetRequest addSheetRequest = new AddSheetRequest();

		SheetProperties sheetProperties = new SheetProperties();
		sheetProperties.setIndex(0);

		// Add the sheetName to the sheetProperties
		addSheetRequest.setProperties(sheetProperties);
		addSheetRequest.setProperties(sheetProperties.setTitle(newsheetTitle));

		// Create batchUpdateSpreadsheetRequest
		BatchUpdateSpreadsheetRequest batchUpdateSpreadsheetRequest = new BatchUpdateSpreadsheetRequest();

		// Create requestList and set it on the batchUpdateSpreadsheetRequest
		List<Request> requestsList = new ArrayList<Request>();
		batchUpdateSpreadsheetRequest.setRequests(requestsList);

		// Create a new request with containing the addSheetRequest and add it to the
		// requestList
		Request request = new Request();
		request.setAddSheet(addSheetRequest);
		requestsList.add(request);

		// Add the requestList to the batchUpdateSpreadsheetRequest
		batchUpdateSpreadsheetRequest.setRequests(requestsList);

		// Call the sheets API to execute the batchUpdate
		service.spreadsheets().batchUpdate(SPREADSHEET_ID, batchUpdateSpreadsheetRequest).execute();
	}
}