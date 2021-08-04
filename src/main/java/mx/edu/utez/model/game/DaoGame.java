package mx.edu.utez.model.game;

import mx.edu.utez.model.category.BeanCategory;
import mx.edu.utez.service.ConnectionMySQL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class DaoGame {
    private Connection con;
    private CallableStatement cstm;
    private ResultSet rs;
    final private Logger CONSOLE = LoggerFactory.getLogger(DaoGame.class);


    public List<BeanGame> findAll(){
        List<BeanGame> ListGames = new ArrayList<>();
        try{
            con = ConnectionMySQL.getConnection();
            cstm = con.prepareCall("{call findgame}");
            rs = cstm.executeQuery();
            while(rs.next()){
                BeanCategory category = new BeanCategory();
                BeanGame game = new BeanGame();

                category.setIdCategory(rs.getInt("idcategory"));
                category.setNameCategory(rs.getString("nameCategory"));
                category.setStatus(rs.getInt("status"));

                game.setIdGame(rs.getInt("idGame"));
                game.setNameGame(rs.getString("nameGame"));
                game.setCategory_idCategory(category);
                game.setDatePremiere(rs.getString("date_premiere"));
                game.setStatus(rs.getInt("status"));
                //BeanGame.setImgGame(Base64.getEncoder().encodeToString(rs.getBytes("img_game")));
                ListGames.add(game);
            }
        }catch(SQLException e){
            CONSOLE.error("Ha sucedido un error: "+e.getMessage());
        }finally{
            ConnectionMySQL.closeConnections(con,cstm,rs);
        }
        return ListGames;
    }


    public BeanGame findById(int id){
        BeanGame game = null;
        try{
            con = ConnectionMySQL.getConnection();
            cstm = con.prepareCall("SELECT * FROM game AS G INNER JOIN category AS C ON G.Category_idCategory = C.idCategory WHERE G.idGame = ?");
            cstm.setLong(1,id);
            rs = cstm.executeQuery();
            if(rs.next()){
                BeanCategory category = new BeanCategory();
                game = new BeanGame();

                category.setIdCategory(rs.getInt("idcategory"));
                category.setNameCategory(rs.getString("nameCategory"));
                category.setStatus(rs.getInt("status"));

                game.setIdGame(rs.getInt("idGame"));
                game.setNameGame(rs.getString("nameGame"));
                game.setCategory_idCategory(category);
                game.setDatePremiere(rs.getString("date_premiere"));
                game.setStatus(rs.getInt("status"));
               // BeanGame.setImgGame(Base64.getEncoder().encodeToString(rs.getBytes("img_game")));
            }
        }catch (SQLException e){
            CONSOLE.error("Ha ocurrido un error: "+e.getMessage());
        }finally{
            ConnectionMySQL.closeConnections(con,cstm,rs);
        }
        return game;
    }

    public boolean create(BeanGame game){
        boolean flag = false;
        try{
            con = ConnectionMySQL.getConnection();
            cstm = con.prepareCall("{call game_create(?,?,?,?,?)}");
            cstm.setString(1,game.getNameGame());
            //cstm.setInt(2,game.getCategory_idCategory());
            cstm.setString(3,game.getDatePremiere());
            cstm.setString(4,game.getImgGame());
            cstm.setInt(5,game.getStatus());
            flag = true;
        }catch (SQLException e){
            CONSOLE.error("Ha ocurrido un error: "+e.getMessage());
        }finally{
            ConnectionMySQL.closeConnections(con,cstm,rs);
        }
        return flag;
    }
    public boolean update(BeanGame game){
        boolean flag = false;
        try{
            con = ConnectionMySQL.getConnection();
            cstm = con.prepareCall("{call game_update(?,?,?,?,?,?)}");
            cstm.setString(1,game.getNameGame());
          //  cstm.setInt(2,game.getCategory_idCategory());
            cstm.setString(3,game.getDatePremiere());
            cstm.setString(4,game.getImgGame());
            cstm.setInt(5,game.getStatus());
            cstm.setInt(6,game.getIdGame());

            flag = cstm.execute();
        }catch (SQLException e){
            CONSOLE.error("Ha ocurrido un error: "+e.getMessage());
        }finally{
            ConnectionMySQL.closeConnections(con,cstm);
        }
        return flag;
    }
    public boolean delete(int idGame){
        boolean flag = false;
        try{
            con = ConnectionMySQL.getConnection();
            cstm = con.prepareCall("{call game_delete(?)}");
            cstm.setInt(1,idGame);
            flag = cstm.execute();
        }catch(SQLException e){
            CONSOLE.error("Ha ocurrido un error: "+e.getMessage());
        }finally{
            ConnectionMySQL.closeConnections(con,cstm);
        }
        return flag;
    }
}
