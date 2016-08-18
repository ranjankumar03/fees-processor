package comfees.processor.util;

public enum FileTypeEnum {

	TXT("txt"), CSV("csv"), XML("xml"), XLS("xls");

	@SuppressWarnings("unused")
	private String value;

	FileTypeEnum(String value) {
		this.value = value;
	}

}
