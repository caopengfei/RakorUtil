package network;

import org.apache.http.Header;
import org.apache.http.util.EncodingUtils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.SyncHttpClient;
import android.content.Context;
import android.util.Log;

public class HttpClient {
	
	private Context context;
	private final static String charset="UTF-8";
	private final String tag="HttpClient";
	
	public HttpClient(Context context){
		this.context=context;
	}
	public void get(String url,ISimpleCallbackListener listener){
		get(url,null,true,listener);
	}
	public void get(String url,boolean async,ISimpleCallbackListener listener){
		get(url,null,async,listener);
	}
	public void get(String url,RequestParams params,boolean async,final ISimpleCallbackListener listener){
		AsyncHttpClient client=(async?new AsyncHttpClient():new SyncHttpClient());
		client.get(url,new AsyncHttpResponseHandler() {
			@Override
			public void onFailure(int statusCode, Header[] headers, byte[] errorResponse,Throwable e) {
				Log.e(tag,"Failure["+statusCode+"]");
				String error=e==null?"":e.getMessage();
				if(errorResponse!=null && errorResponse.length>0){
					error=EncodingUtils.getString(errorResponse, charset);
				}
				listener.onError(error);
			}
			@Override
			public void onSuccess(int statusCode, Header[] headers, byte[] response) {
				String data=EncodingUtils.getString(response, charset);
				Log.e(tag,"Success[GET]["+statusCode+"]:"+data);
				listener.onSuccess(data);
			}
		});
	}
	public void post(String protocol,ISimpleCallbackListener listener){
		post(protocol,null,true,listener);
	}
	public void post(String protocol,RequestParams params,ISimpleCallbackListener listener){
		post(protocol,params,true,listener);
	}
	public void post(final String url,RequestParams params,boolean async,final ISimpleCallbackListener listener){
		AsyncHttpClient client=(async?new AsyncHttpClient():new SyncHttpClient());
		client.post(url, params,new AsyncHttpResponseHandler() {
			@Override
			public void onFailure(int statusCode, Header[] headers, byte[] errorResponse,Throwable e) {
				Log.e(tag,"Failure["+statusCode+"]");
				String error=e==null?"":e.getMessage();
				if(errorResponse!=null && errorResponse.length>0){
					error=EncodingUtils.getString(errorResponse, charset);
				}
				listener.onError(error);
			}
			@Override
			public void onSuccess(int statusCode, Header[] headers, byte[] response) {
				String data=EncodingUtils.getString(response, charset);
				Log.e(tag,"Success[GET]["+statusCode+"]:"+data);
				listener.onSuccess(data);
			}
		});
	}
}
