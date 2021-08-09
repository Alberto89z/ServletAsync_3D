package mx.edu.utez.user;

import mx.edu.utez.model.game.DaoGame;
import mx.edu.utez.service.ConnectionMySQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoUser {
    private Connection con;
    private CallableStatement cstm;
    private ResultSet rs;
    final private Logger CONSOLE = LoggerFactory.getLogger(DaoGame.class);

    public boolean createSession(String email, String password){
        boolean flag = false;
        try{
           con = ConnectionMySQL.getConnection();
           cstm = con.prepareCall("SELECT * FROM user WHERE email = ? AND password = ?");
           cstm.setString(1,email);
           cstm.setString(2,password);
           rs = cstm.executeQuery();

           if(rs.next()){
               flag = true;
           }
        }catch(SQLException e){
           CONSOLE.error("Error: "+e.getMessage());
        }finally{
            ConnectionMySQL.closeConnections(con,cstm,rs);
        }
        return flag;
    }
}
