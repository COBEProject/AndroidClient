package client.android.cobe.com.androidclient.util;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONException;
import org.json.JSONObject;

import client.android.cobe.com.androidclient.event.EndGameEvent;
import client.android.cobe.com.androidclient.event.NewQuestionDataEvent;
import client.android.cobe.com.androidclient.event.WaitForBeginningEvent;
import client.android.cobe.com.androidclient.model.Answer;
import client.android.cobe.com.androidclient.model.Question;
import de.greenrobot.event.EventBus;

public class SOCKETConfiguration {

    public static final int STATE_CONNECTING = 1;

    public static final int STATE_CONNECTED = 2;

    public static final int STATE_DISCONNECTED = 3;

    public static final String CONNECTING = "Connecting";

    public static final String CONNECTED = "Connected";

    public static final String DISCONNECTED = "Disconnected";

    private final String userId = java.util.UUID.randomUUID().toString();

    private int gameId;

    private Question question;
    private Answer answers;

    private static SOCKETConfiguration instance;

    private SOCKETConfiguration() {}

    public synchronized static SOCKETConfiguration getInstance() {
        if (instance == null) {
            instance = new SOCKETConfiguration();
        }
        return instance;
    }

    private Socket socket;

    public void connectSocket(String host) {
        try {
            if(socket==null){
                IO.Options opts = new IO.Options();
                opts.query = "clientId=" + userId;
                socket = IO.socket(host, opts);

                socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
                    @Override
                    public void call(Object... args) {}

                }).on("connected", new Emitter.Listener() {
                    @Override
                    public void call(Object... args) {
                        Logger.getGlobal().log(Level.INFO, "connected");
                    }

                }).on("playerJoinedRoom", new Emitter.Listener() {
                    @Override
                    public void call(Object... args) {
                        Logger.getGlobal().log(Level.INFO, "Message recu 5/5");
                    }

                }).on("beginNewGame", new Emitter.Listener() {
                    @Override
                    public void call(Object... args) {
                        EventBus.getDefault().postSticky(new WaitForBeginningEvent(true));
                        Logger.getGlobal().log(Level.INFO, "Game begin in 5 sec");
                    }

                }).on("newQuestionData", new Emitter.Listener() {
                    @Override
                    public void call(Object... args) {
                        Logger.getGlobal().log(Level.INFO, args[0].toString());
                        JSONObject obj = (JSONObject)args[0];
                        JSONObject objAns;
                        question = new Question();
                        answers = new Answer();
                        try {
                            question.setNumquestion(obj.getInt("numQuestion"));
                            question.setQuestion(obj.getString("question"));
                            objAns = obj.getJSONObject("answers");
                            answers.setA(objAns.getString("A"))
                                    .setB(objAns.getString("B"))
                                    .setC("")
                                    .setD("");
                            question.setAnswer(answers);
                            question.setCorresctAnswer(obj.getString("correctAnswer"));
                        } catch (JSONException e) {
                            Logger.getGlobal().log(Level.WARNING, e.toString());
                        }

                        try {
                            objAns = obj.getJSONObject("answers");
                            Answer answer2 = question.getAnswer();
                            answer2.setC(objAns.getString("C"))
                                    .setD(objAns.getString("D"));
                            question.setAnswer(answer2);
                        } catch (JSONException e) {
                            Logger.getGlobal().log(Level.WARNING, e.toString());
                        }
                        EventBus.getDefault().postSticky(new NewQuestionDataEvent(true));
                    }
                }).on("endGame", new Emitter.Listener() {
                    @Override
                    public void call(Object... args) {
                        EventBus.getDefault().postSticky(new EndGameEvent(true));
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

    public Question getQuestion() {
        return question;
    }

    public SOCKETConfiguration setQuestion(Question question) {
        this.question = question;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public int getGameId() {
        return gameId;
    }

    public SOCKETConfiguration setGameId(int gameId) {
        this.gameId = gameId;
        return this;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void destroy(){
        if (socket != null) {
            socket.off();
            socket.disconnect();
            socket.close();
            socket=null;
        }
    }
}