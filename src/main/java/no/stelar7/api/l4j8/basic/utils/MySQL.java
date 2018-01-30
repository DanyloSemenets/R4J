package no.stelar7.api.l4j8.basic.utils;

import java.sql.*;

public class MySQL
{
    private String hostname;
    private int    portnmbr;
    private String username;
    private String password;
    private String database;
    
    protected Connection connection = null;
    
    public MySQL(final String hostname, final int portnmbr, final String database, final String username, final String password)
    {
        super();
        this.hostname = hostname;
        this.portnmbr = portnmbr;
        this.database = database;
        this.username = username;
        this.password = password;
    }
    
    /**
     * checks if the connection is still active
     *
     * @return true if still active
     */
    public boolean checkConnection()
    {
        try
        {
            return !this.connection.isClosed() && this.connection.isValid(5);
        } catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * close database connection
     */
    public void close()
    {
        try
        {
            if (this.connection != null)
            {
                this.connection.close();
            }
        } catch (final SQLException e)
        {
            e.printStackTrace();
        }
    }
    
    
    /**
     * returns the active connection
     *
     * @return Connection
     */
    
    public Connection getConnection()
    {
        return this.connection;
    }
    
    /**
     * open database connection
     */
    public void open()
    {
        try
        {
            final String parameters = "?useUnicode=true&characterEncoding=UTF-8&useServerPrepStmts=false&rewriteBatchedStatements=true&serverTimezone=UTC";
            final String url        = String.format("jdbc:mysql://%s:%d/%s%s", this.hostname, this.portnmbr, this.database, parameters);
            this.connection = DriverManager.getConnection(url, this.username, this.password);
        } catch (final SQLException e)
        {
            System.err.print("Could not connect to MySQL server! ");
            System.err.println(e.getMessage());
        }
    }
}
