package network;

public interface ICallbackListener extends ISimpleCallbackListener {
	void onResponse();
	void onFinish();
}