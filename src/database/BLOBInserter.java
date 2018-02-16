package database;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dao.OracleConnectionManager;

public class BLOBInserter {

    private Connection cn = null;
    private PreparedStatement st = null;
    private FileInputStream fip = null;

    public void insertBlob(String filename){
        try {
            cn = OracleConnectionManager.getInstance().getConnection();
            cn.setAutoCommit(false);

            File file = new File(filename);
            fip = new FileInputStream(file);

            st = cn.prepareStatement("UPDATE user_t SET user_icon=?");
            st.setBinaryStream(1, fip, (int) file.length());
            st.executeUpdate();
            cn.commit();
        }
        catch (IOException ex) {
            ex.printStackTrace();

            if(cn != null && st != null){
                try { cn.rollback(); }
                catch(SQLException e){ e.printStackTrace();}
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            if(cn != null && st != null){
                try { cn.rollback(); }
                catch(SQLException e){ e.printStackTrace();}
            }
        }
        finally {
            try {
                if(st != null){
                    st.close();
                }
            }
            catch(SQLException ex){ ex.printStackTrace(); }
            finally {
                try {
                    if(fip != null){
                        fip.close();
                    }
                }
                catch(IOException ex){ ex.printStackTrace(); }
                finally {
                    if(cn != null){
						OracleConnectionManager.getInstance().closeConnection();
					}
                }
            }
        }
    }
}
