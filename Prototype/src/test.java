import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;



public class test {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		List<CSVRecord> records = null ;
		boolean bird=false;
		if(bird){
		FileWriter out = new FileWriter("test.csv" , true);//appends to file
		CSVPrinter writer = new CSVPrinter(out, CSVFormat.DEFAULT);
		String name="\"Worl,,d,\"";
		writer.printRecord(name, 50, true);
		writer.printRecord("Hello", 10, true);
		writer.close();
		}
		else{
			CSVParser parser = new CSVParser(new FileReader("test.csv"), CSVFormat.DEFAULT);
			records = parser.getRecords();
			parser.close();
		 System.out.println(records.get(6).get(0));
	}System.out.println(records.get(6).get(0));

}}
