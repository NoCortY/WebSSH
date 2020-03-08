package cn.objectspace.webssh.service;

import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

/**
 * @Description: WebSSH的业务逻辑
 * @Author: NoCortY
 * @Date: 2020/3/7
 */
public interface WebSSHService {
    /**
     * @Description: 初始化ssh连接
     * @Param:
     * @return:
     * @Author: NoCortY
     * @Date: 2020/3/7
     */
    public void initConnection(WebSocketSession session);

    /**
     * @Description: 处理客户段发的数据
     * @Param:
     * @return:
     * @Author: NoCortY
     * @Date: 2020/3/7
     */
    public void recvHandle(String buffer, WebSocketSession session);

    /**
     * @Description: 数据写回前端 for websocket
     * @Param:
     * @return:
     * @Author: NoCortY
     * @Date: 2020/3/7
     */
    public void sendMessage(WebSocketSession session, byte[] buffer) throws IOException;

    /**
     * @Description: 关闭连接
     * @Param:
     * @return:
     * @Author: NoCortY
     * @Date: 2020/3/7
     */
    public void close(WebSocketSession session);
}
