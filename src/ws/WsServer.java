package ws;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.fasterxml.jackson.databind.ObjectMapper;

import bean.Message;
import dao.AbstractDaoFactory;
import dao.MessageDao;
import dao.OracleConnectionManager;

@ServerEndpoint(value = "/ws/{groupId}")
public class WsServer {

    private static final Set<WsServer> connections = new CopyOnWriteArraySet<>();

    private Session session;
    private String groupId;

    public WsServer() {
    }

    @OnOpen
    public void start(final Session session, @PathParam("groupId") final String groupId){
        this.session = session;
        connections.add(this);
        this.groupId = groupId;

        //broadcast(message);
    }

    @OnClose
    public void end() {
        connections.remove(this);
        //broadcast(message, id);
    }

    @OnMessage
    public void incoming(String JsonMessage, Session session) {

    	ObjectMapper mapper = new ObjectMapper();
    	Message message;
		try {
			message = mapper.readValue(JsonMessage, Message.class);
			message.setGroupId(groupId);
			Calendar calendar = Calendar.getInstance(new Locale("ja", "JAPAN"));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
			String date = sdf.format(calendar.getTime());
			message.setDate(date);

			AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
			OracleConnectionManager.getInstance().beginTransaction();
			MessageDao messageDao = factory.getMessageDao();
			messageDao.addMessage(message);
			OracleConnectionManager.getInstance().closeConnection();

			broadcast(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @OnError
    public void onError(Throwable t) throws Throwable {
    	//broadcast(t.getMessage(), id);
    }

    private static void broadcast(Message message) {
        for(WsServer client : connections){
            try {
                synchronized(client) {
                	ObjectMapper objectMapper = new ObjectMapper();
                	String json = objectMapper.writeValueAsString(message);
                    client.session.getBasicRemote().sendText(json);
                }
            } catch(IOException e) {
                connections.remove(client);
                try {
                    client.session.close();
                } catch(IOException e1){
                    // Ignore
                }
                //String message = String.format("* %s %s",client.nickname, "has been disconnected.");
                //broadcast(message,id);
            }
        }
    }
}