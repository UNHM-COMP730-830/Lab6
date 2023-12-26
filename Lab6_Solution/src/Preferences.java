import java.util.ArrayList;
import java.util.List;

public class Preferences {
	public final static String CITY_KEY = "CITY";
	private final static String CITY_DEFAULT = "Manchester, NH";
	public final static String UNITS_KEY = "UNITS";
	private final static String UNITS_DEFAULT = "I";
	
	private static Preferences INSTANCE;
	
	private final List<PreferenceObserver> observerCollection = new ArrayList<>();
	
	private Preferences() {
	}
	
	public static Preferences getInstance() {
		if (INSTANCE == null) INSTANCE = new Preferences();
		return INSTANCE;
	}

	public String getCity() {
		return null;
	}
	
	public void setCity(String value) {
		notifyPreferenceChanged(CITY_KEY, value);
	}
	
	public String getUnits() {
		return null;
	}
	
	public void setUnits(String value) {
		notifyPreferenceChanged(UNITS_KEY, value);
	}
	
	public void registerObserver(PreferenceObserver observer) {
		observerCollection.add(observer);
	}
	
	public void unregisterObserver(PreferenceObserver observer) {
		observerCollection.remove(observer);
	}
	
	private void notifyPreferenceChanged(String key, Object value) {
		for (PreferenceObserver observer : observerCollection) {
			observer.preferenceChanged(key, value);
		}
	}
}
