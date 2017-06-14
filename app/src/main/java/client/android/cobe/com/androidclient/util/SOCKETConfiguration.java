package client.android.cobe.com.androidclient.util;

import android.content.Context;
import android.content.Intent;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONObject;

import client.android.cobe.com.androidclient.GameJoiningActivity;
import client.android.cobe.com.androidclient.web_interface.WaitPlayersActivity;

import static android.support.v4.app.ActivityCompat.startActivity;
import static android.support.v4.content.ContextCompat.createDeviceProtectedStorageContext;

public class SOCKETConfiguration {

    /**
     * The constant STATE_CONNECTING.
     */
    public static final int STATE_CONNECTING = 1;
    /**
     * The constant STATE_CONNECTED.
     */
    public static final int STATE_CONNECTED = 2;
    /**
     * The constant STATE_DISCONNECTED.
     */
    public static final int STATE_DISCONNECTED = 3;

    /**
     * The constant CONNECTING.
     */
    public static final String CONNECTING = "Connecting";
    /**
     * The constant CONNECTED.
     */
    public static final String CONNECTED = "Connected";
    /**
     * The constant DISCONNECTED.
     */
    public static final String DISCONNECTED = "Disconnected";

    private boolean waitForPlayers;

    private static SOCKETConfiguration instance;


    private SOCKETConfiguration() {
        waitForPlayers = false;
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public synchronized static SOCKETConfiguration getInstance() {
        if (instance == null) {
            instance = new SOCKETConfiguration();
        }
        return instance;
    }

    /**
     * The constant TAG.
     */
    //public static final String TAG = SOCKETConfiguration.class.getSimpleName();
    private Socket socket;
    //private List<OnSocketConnectionListener> onSocketConnectionListenerList;

    /**
     * Connect socket.
     *
     * @param host   the host
     */
    //public void connectSocket(String token,String userId, String host) {
    public void connectSocket(String host) {
        try {
            if(socket==null){
                //String serverAddress = host;
                //IO.Options opts = new IO.Options();
                //opts.forceNew = true;
                //opts.reconnection = true;
                //opts.reconnectionAttempts=5;
                //opts.secure = true;
                //opts.query = "token=" + token + "&" + "user_id=" + userId;
                //socket = IO.socket(host, opts);
                 //final Intent intent = new Intent(this, WaitPlayersActivity.class);
                socket = IO.socket(host);

                socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
                    @Override
                    public void call(Object... args) {}

                }).on("connected", new Emitter.Listener() {
                    @Override
                    public void call(Object... args) {
                        JSONObject obj = (JSONObject)args[0];
                        Logger.getGlobal().log(Level.INFO, obj.toString());
                    }

                }).on("playerJoinedRoom", new Emitter.Listener() {
                    @Override
                    public void call(Object... args) {
                        waitForPlayers = true;
                        Logger.getGlobal().log(Level.INFO, "Message recu 5/5");
                    }

                }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
                    @Override
                    public void call(Object... args) {}

                });
                socket.connect();

            } else if(!socket.connected()){
                socket.connect();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isWaitForPlayers() {
        return waitForPlayers;
    }

    private int lastState = -1;

    /**
     * Fire socket status intent.
     *
     * @param socketState the socket state
     */
    /*public synchronized void fireSocketStatus(final int socketState) {
        if(onSocketConnectionListenerList !=null && lastState!=socketState){
            lastState = socketState;
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    for(OnSocketConnectionListener listener: onSocketConnectionListenerList){
                        listener.onSocketConnectionStateChange(socketState);
                    }
                }
            });
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    lastState=-1;
                }
            },1000);
        }
    }*/

    /**
     * Fire internet status intent.
     *
     * @param socketState the socket state
     */
    /*public synchronized void fireInternetStatusIntent(final int socketState) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                if(onSocketConnectionListenerList !=null){
                    for(OnSocketConnectionListener listener: onSocketConnectionListenerList){
                        listener.onInternetConnectionStateChange(socketState);
                    }
                }
            }
        });
    }*/

    /**
     * Gets socket.
     *
     * @return the socket
     */
    public Socket getSocket() {
        return socket;
    }

    /**
     * Sets socket.
     *
     * @param socket the socket
     */
    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    /**
     * Destroy.
     */
    public void destroy(){
        if (socket != null) {
            socket.off();
            socket.disconnect();
            socket.close();
            socket=null;
        }
    }

    /**
     * Sets socket connection listener.
     *
     * @param onSocketConnectionListenerListener the on socket connection listener listener
     */
    /*public void setSocketConnectionListener(OnSocketConnectionListener onSocketConnectionListenerListener) {
        if(onSocketConnectionListenerList ==null){
            onSocketConnectionListenerList = new ArrayList<>();
            onSocketConnectionListenerList.add(onSocketConnectionListenerListener);
        }else if(!onSocketConnectionListenerList.contains(onSocketConnectionListenerListener)){
            onSocketConnectionListenerList.add(onSocketConnectionListenerListener);
        }
    }*/

    /**
     * Remove socket connection listener.
     *
     * @param onSocketConnectionListenerListener the on socket connection listener listener
     */
    /*public void removeSocketConnectionListener(OnSocketConnectionListener onSocketConnectionListenerListener) {
        if(onSocketConnectionListenerList !=null
                && onSocketConnectionListenerList.contains(onSocketConnectionListenerListener)){
            onSocketConnectionListenerList.remove(onSocketConnectionListenerListener);
        }
    }*/

    /**
     * Remove all socket connection listener.
     */
    /*public void removeAllSocketConnectionListener() {
        if(onSocketConnectionListenerList !=null){
            onSocketConnectionListenerList.clear();
        }
    }*/

    /**
     * The type Net receiver.
     */
    //public static class NetReceiver extends BroadcastReceiver {

        /**
         * The Tag.
         */
        //public final String TAG = NetReceiver.class.getSimpleName();

        /*@Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager cm =
                    (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            boolean isConnected = activeNetwork != null &&
                    activeNetwork.isConnectedOrConnecting();

            SocketManager.getInstance().fireInternetStatusIntent(
                    isConnected?SocketManager.STATE_CONNECTED:SocketManager.STATE_DISCONNECTED);
            if (isConnected) {
                if(SocketManager.getInstance().getSocket()!=null
                        && !SocketManager.getInstance().getSocket().connected()){
                    SocketManager.getInstance().fireSocketStatus(SocketManager.STATE_CONNECTING);
                }
                PowerManager powerManager =
                        (PowerManager) context.getSystemService(Context.POWER_SERVICE);
                boolean isScreenOn;
                if (android.os.Build.VERSION.SDK_INT
                        >= android.os.Build.VERSION_CODES.KITKAT_WATCH) {
                    isScreenOn = powerManager.isInteractive();
                }else{
                    //noinspection deprecation
                    isScreenOn = powerManager.isScreenOn();
                }

                if (isScreenOn && SocketManager.getInstance().getSocket() !=null) {
                    Log.d(TAG, "NetReceiver: Connecting Socket");
                    if(!SocketManager.getInstance().getSocket().connected()){
                        SocketManager.getInstance().getSocket().connect();
                    }
                }
            }else{
                SocketManager.getInstance().fireSocketStatus(SocketManager.STATE_DISCONNECTED);
                if(SocketManager.getInstance().getSocket() !=null){
                    Log.d(TAG, "NetReceiver: disconnecting socket");
                    SocketManager.getInstance().getSocket().disconnect();
                }
            }
        }
    }*/

}