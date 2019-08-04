package javafx.util;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;

public class LocalDateAdapter extends XmlAdapter<String,LocalDate> {

	@Override
	public String marshal(LocalDate v) throws Exception {
		// TODO Auto-generated method stub
		return v.toString();
	}

	@Override
	public LocalDate unmarshal(String v) throws Exception {
		// TODO Auto-generated method stub
		return LocalDate.parse(v);
	}

}
