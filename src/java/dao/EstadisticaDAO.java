package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Estadistica;

public class EstadisticaDAO {
    public List<Estadistica> listarEstadistica() throws SQLException{
        List<Estadistica> lista = null;
        ResultSet rs;
        Connection cn = null;
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/rostros?user=root&password=");
            PreparedStatement st = cn.prepareStatement("select codigo,estado,cantidad from estados");
            rs=st.executeQuery();
            lista = new ArrayList();
            while (rs.next()){
                Estadistica esta = new Estadistica();
                esta.setCodigo(rs.getInt("codigo"));
                esta.setEstado(rs.getString("estado"));
                esta.setCantidad(rs.getInt("cantidad"));
                lista.add(esta);
                System.out.print(rs.getInt("cantidad"));
            }
            rs.close();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(cn!=null){
                cn.close();
            }
        }
        return lista;
    }
}
