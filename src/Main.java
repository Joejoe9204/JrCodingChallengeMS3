import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


public class Main {
	static File f = new File("src/ms3InterviewMod.csv");
	static SQLiteDB db = new SQLiteDB();
	
	public static void main(String[] args) throws SQLException {
		
		db.createTable();
		CSVReader(f);	
		db.last();
	}
	
	
	public static void CSVReader(File f) {
		BufferedReader br = null;
		String line;
		int failed = 0;
		int success = 0;

		try {
			br = new BufferedReader(new FileReader(f));
			while ((line = br.readLine()) != null) {
				
				User user = addUser(split(line));
				
				for (int i = 0; i <= 9; i++) {
					if(split(line).get(i) != "" && i == 9) { //if we don't see any blanks for all 10 data points add to database
						DBGoodWrite(user);
						success += 1;							//OTHERWISE
					}
					else if (split(line).get(i) == "") { 	//find the first blank in data
						CSVBadWrite(user); 					//write it to bad db
						i = 9; 								//force quit - found bad data in line
						failed += 1;
					}
				}	
			}
			
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Database finished");
		System.out.println("Bad data recorded");
		System.out.println("Log written");
		LogWrite(success + failed, failed, success);
	}
	
	
	public static void CSVBadWrite(User user) {
		try {
			PrintWriter csvWriter = new PrintWriter(new FileOutputStream(new File("JrCodingChallengeMS3-bad.csv"),true)); //opens file to be appended
			
			csvWriter.append(user.getFirstName() + ",");
			csvWriter.append(user.getLastName()+ ",");
			csvWriter.append(user.getEmail()+ ",");
			csvWriter.append(user.getGender()+ ",");
			csvWriter.append(user.getImage()+ ",");
			csvWriter.append(user.getBank()+ ",");
			csvWriter.append(user.getTransaction()+ ",");
			csvWriter.append(user.getBool1()+ ",");
			csvWriter.append(user.getBool2()+ ",");
			csvWriter.append(user.getCity()+ "\n");
			
			csvWriter.flush();
			csvWriter.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	
	public static void DBGoodWrite(User user) throws SQLException {
		db.insertData(user);
	}
	
	
	public static void LogWrite(int totcount, int badcount, int goodcount) {
		Logger logger = Logger.getLogger("JrCodingChallengeMS3");
		FileHandler fh;
		
		try {
			fh = new FileHandler("JrCodingChallengeMS3.log");
			logger.addHandler(fh);
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);
		}catch(SecurityException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
		
		logger.info("Records received: " + totcount);
		logger.info("Successful records: " + goodcount);
		logger.info("Failed records: " + badcount);
	}
	
		
	public static List<String> split(String line) {
		String current = "";
		List<String> data = new ArrayList<>();
		for (int i = 0; i < line.length(); i++) { 		// write every char we see up until a comma and append to a String variable
			if (line.charAt(i) == ',') {
				data.add(current); 						// put String into array
				current = ""; 							// reset current
			} 
			else if (line.charAt(i) == '\"') { 			//if we see a quote
				int temp = line.indexOf('\"', i + 1);   //we now look for the last quote in the line
				current = line.substring(i + 1, temp);  //record positions of the quotes
				i = temp; 								//sets i so that we don't need to look at the chars between the quotes
			} 
			else {
				current = current + line.charAt(i);
			}
		}
		data.add(current);
		return data;
	}
	
	
	public static User addUser(List<String> userinfo) {
		User user = new User();
		user.firstName = userinfo.get(0);
		user.lastName = userinfo.get(1);
		user.email = userinfo.get(2);
		user.gender = userinfo.get(3);
		user.image = userinfo.get(4);
		user.bank = userinfo.get(5);
		user.transaction = userinfo.get(6);
		user.bool1 = userinfo.get(7);
		user.bool2 = userinfo.get(8);
		user.city = userinfo.get(9);
		return user;
	}
	
}