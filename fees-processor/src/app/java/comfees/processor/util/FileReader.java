package comfees.processor.util;

import java.io.IOException;
import java.util.List;

import com.fees.processor.pojo.Transaction;

public interface FileReader {

	public List<Transaction> read(String file) throws IOException;

}
