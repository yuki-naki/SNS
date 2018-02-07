package ws;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@ServerEndpoint(value = "/ws")
public class WsServer {

	private static final String GUEST_PREFIX = "Guest";
    private static final AtomicInteger connectionIds = new AtomicInteger(0);
    private static final Set<WsServer> connections = new CopyOnWriteArraySet<>();

    private int id;
    private final String nickname;
    private Session session;

    public WsServer() {
    	id = connectionIds.getAndIncrement();
        nickname = GUEST_PREFIX + id;
    }

    @OnOpen
    public void start(Session session){
        this.session = session;
        connections.add(this);
        String message = String.format("* %s %s", nickname, "has joined.");
        System.out.println(message);
        ObjectMapper objectMapper = new ObjectMapper();

    	Message messageObj = new Message();
    	messageObj.setId(String.valueOf(id));
    	try {
			String json = objectMapper.writeValueAsString(messageObj);
			session.getBasicRemote().sendText(json);
		} catch (JsonProcessingException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
        //broadcast(message);
    }

    @OnClose
    public void end() {
        connections.remove(this);
        String message = String.format("* %s %s", nickname, "has disconnected.");
        broadcast(message, id);
    }

    @OnMessage
    public void incoming(String message, Session session) {
        // Never trust the client
        String filteredMessage = String.format("%s: %s", nickname, message.toString());
        System.out.println(filteredMessage);
        broadcast(message,id);
    }

    @OnError
    public void onError(Throwable t) throws Throwable {
    	broadcast(t.getMessage(), id);
    }

    private static void broadcast(String msg, int id) {
        for(WsServer client : connections){
            try {
                synchronized(client) {
                	ObjectMapper objectMapper = new ObjectMapper();

                	Message message = new Message();
                	message.setId(String.valueOf(id));
                	message.setMessage(msg);

                	String json = objectMapper.writeValueAsString(message);
                	System.out.println(json);
                    client.session.getBasicRemote().sendText(json);
                }
            } catch(IOException e) {
                connections.remove(client);
                try {
                    client.session.close();
                } catch(IOException e1){
                    // Ignore
                }
                String message = String.format("* %s %s",client.nickname, "has been disconnected.");
                broadcast(message,id);
            }
        }
    }
}