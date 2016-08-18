package comfees.processor.util;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Map;
import java.util.Map.Entry;

import com.fees.processor.constant.FeeProcessContants;
import com.fees.processor.pojo.Transaction;

public class FileWriter {

	String ouputFile = "C://Users/rku140/workspace/fees-processor/ouputFileContainer/fees.csv";
	public void write(Map<String, Transaction> map) throws IOException {
		System.out.println("Wwriter--------------------------------------");

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ouputFile), "UTF-8"));

		// write header
		bw.write("External Transaction Id|Client Id|Security Id|Transaction Type|Transaction Date|Market Value|PriorityFlag");
		bw.newLine();
		for (Entry<String, Transaction> map1 : map.entrySet()) {
			StringBuffer oneLine = new StringBuffer();
			oneLine.append(map1.getValue().getClientId()).append(FeeProcessContants.CSV_DELIMITER);
			oneLine.append(map1.getValue().getSecurityId()).append(FeeProcessContants.CSV_DELIMITER);
			oneLine.append(map1.getValue().getTransactionType()).append(FeeProcessContants.CSV_DELIMITER);
			oneLine.append(map1.getValue().getTransactionDate()).append(FeeProcessContants.CSV_DELIMITER);
			oneLine.append(map1.getValue().getPriorityFlag()).append(FeeProcessContants.CSV_DELIMITER);
			oneLine.append(map1.getValue().getFees());
			bw.write(oneLine.toString());
			bw.newLine();
		}
		bw.flush();
		bw.close();

	}

}
