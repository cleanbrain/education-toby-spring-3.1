package springbook.user.dao;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: 노상현
 * Date: 2018-12-17
 */
public interface ConnectionMaker {

    public Connection makeConnection() throws ClassNotFoundException, SQLException;

}
