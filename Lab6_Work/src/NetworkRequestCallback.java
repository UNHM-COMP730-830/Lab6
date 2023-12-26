
public interface NetworkRequestCallback<T> {
	void onResult(T data);
	void onError(Exception e);
}
