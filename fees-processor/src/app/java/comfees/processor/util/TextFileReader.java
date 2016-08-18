package comfees.processor.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.fees.processor.constant.FeeProcessContants;
import com.fees.processor.pojo.Transaction;

public class TextFileReader implements comfees.processor.util.FileReader {

	private static final Logger log = Logger.getLogger(TextFileReader.class);
	public List<Transaction> read(String file) throws IOException {

		final List<Transaction> inputList = new ArrayList<Transaction>();
		BufferedReader br = null;
		String line = null;
		try {

			br = new BufferedReader(new FileReader(file));
			// remove header
			br.readLine();
			while ((line = br.readLine()) != null) {
				String[] strArray = line.split(FeeProcessContants.TXT_DELIMITER);
				inputList.add(new Transaction(strArray[FeeProcessContants.EXTERNAL_TRANSACTION_ID], strArray[FeeProcessContants.CLIENT_ID], strArray[FeeProcessContants.SECURITY_ID], strArray[FeeProcessContants.TRANSACTION_TYPE],
						strArray[FeeProcessContants.TRANSACTION_DATE], Double.parseDouble(strArray[FeeProcessContants.MARKET_VALUE]), strArray[FeeProcessContants.PRIORITY_FLAG], 0));

			}
		} catch (FileNotFoundException e) {
			log.info(e);
		} finally {
			br.close();
		}

		return inputList;
	}

}
