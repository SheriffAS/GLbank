package Database;

import sample.Employee;
import sample.Globals;

import java.sql.*;

public class GLdatabase {
    //connection
    //overi heslo i sa zhoduje employee
    //get array list , clients


        private static Database database  = new Database();

        private Database() {
        }

        public static Database getInstance() {
            return database ;
        }

        private Connection getConn()
        {
            Connection conn;
            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                System.out.println("Driver loaded!");
                conn = DriverManager.getConnection(Globals.host, Globals.name, Globals.pass);
                return conn;
            }
            catch (ClassNotFoundException e)
            {
                e.printStackTrace();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
            return null;
        }

        private void closeConn(Connection conn){
            if(conn!=null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        public Employee getEmployee(String log, String pass) {
            int employeeId=0;
            String lname="";
            String fname="";

            int loginId=0;

            int positionId=0;
            String position="";

            Connection conn = getConn();


            try {
                PreparedStatement statement = conn.prepareStatement("select employee.id as employeeId, employee.lname,employee.fname,loginemp.id as loginId,loginemp.login,loginemp.password, positions.id as positionId, positions.name as nameposition from employee inner join loginemp on employee.id=loginemp.id inner join positions on employee.position=positions.id where loginemp.login like ? and loginemp.password like ?;";)
                ;
                statement.setString(1, login);
                statement.setString(2, password);
                ResultSet result = statement.executeQuery();

                while (result.next()) {
                    employeeId = result.getInt("employeeId");
                    lname = result.getString("lname");
                    fname = result.getString("fname");
                    loginId = result.getInt("loginId");
                    positionId = result.getInt("positionId");
                    position = result.getString("nameposition");
                }
                if (fname.equals("") || lname.equals("") || position.equals(""))
                    return null;
                else
                    return new Employee(employeeId, fname, lname, loginId, login, password, positionId, position);

            }
                catch (SQLException e){
                    e.printStackTrace();
                }
            return null;

        }
}

