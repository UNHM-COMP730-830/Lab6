
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public final class WeatherAPI {
	
	private static final String TAG = "WeatherAPI";
    private static final String BASE_URL = "https://api.weatherbit.io/";
    private static final String VERSION = "v2.0";
    private static final String FORECAST_ENDPOINT = "forecast";
    private static final String DAILY_ENDPOINT = "daily";
    private static final String API_KEY_PARAM = "key";
    private static final String CITY_PARAM = "city";
    private static final String DAYS_PARAM = "days";
    private static final String UNITS_PARAM = "units";

    private static final String API_KEY = "f2dc3deab81d4199879c642c49a0b516";

    private static final String DAYS = "5";
    private static String CITY = "Manchester,NH";
    private static String UNITS = "I";

    private static URL buildDailyForecastUrl() {
    	String str = BASE_URL;
    	str += VERSION + "/";
    	str += FORECAST_ENDPOINT + "/";
    	str += DAILY_ENDPOINT + "?";
    	str += API_KEY_PARAM + "=" + API_KEY;
    	str += "&" + DAYS_PARAM + "=" + DAYS;
    	str += "&" + UNITS_PARAM + "=" + UNITS;
    	str += "&" + CITY_PARAM + "=" + CITY;
        try {
        	URL weatherQueryUrl = new URL(str);
            return weatherQueryUrl;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static List<Forecast> parseForecast(String json) throws JSONException, ParseException {
        List<Forecast> data = new ArrayList<>();

        JSONObject response = new JSONObject(json);
        JSONArray forecastArray = response.getJSONArray("data");
        for (int i = 0; i < forecastArray.length(); i++) {
            JSONObject forecastObject = forecastArray.getJSONObject(i);
            Forecast forecast = Forecast.fromJson(forecastObject);
            data.add(forecast);
        }
        return data;
    }


    private static class WeatherRunnable implements Runnable {
    	private static NetworkRequestCallback<List<Forecast>> cb;
    	private static boolean err = false;
    	private static List<Forecast> data;
    	private static Exception exc;
    	public WeatherRunnable(NetworkRequestCallback<List<Forecast>> callback) {
    		try {
				err = false;
	    		cb = callback;
	    		URL url = WeatherAPI.buildDailyForecastUrl();
				String json = NetworkUtils.getResponseFromHttpUrl(url);
//				System.out.println("++ json: " + json);
				data = parseForecast(json);
    		}
    		catch (Exception e) {
    			System.out.println("<<< Exception: " + e.getMessage());
    			err = true;
    			exc = e;
    		}
    	}
    	public void run() {
    		if (err) cb.onError(exc);
    		else cb.onResult(data);
    	}
    }
    
    public static void requestForecast(NetworkRequestCallback<List<Forecast>> callback) {
    	new Thread(new WeatherRunnable(callback)) {}.start();
    }
    
    public static void setCity(String city) {
    	CITY = city;
    }
    
    public static String getCity() {
    	return CITY;
    }
    
    public static void setUnits(String units) {
    	UNITS = units;
    }
    
}
