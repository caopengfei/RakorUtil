package network;

public interface ISimpleCallbackListener {
	void onSuccess(String data);
	void onError(String error);
}