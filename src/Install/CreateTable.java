package Install;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTable extends ConnectDatabase{
    final String mTable1="CREATE TABLE IF NOT EXISTS `book` (\n" +
                         "  `id` int(11) NOT NULL auto_increment,\n" +
                         "  `bookname` varchar(50) collate utf8_bin NOT NULL,\n" +
                         "  `netid` varchar(12) collate utf8_bin NOT NULL,\n" +
                         "  `author` varchar(32) collate utf8_bin NOT NULL,\n" +
                         "  `registered` varchar(32) collate utf8_bin NOT NULL,\n" +
                         "  `section` varchar(32) collate utf8_bin NOT NULL,\n" +
                         "  `number` int(32) NOT NULL,\n" +
                         "  `image` longblob NOT NULL,\n" +
                         "  PRIMARY KEY  (`id`)\n" +
                         ") ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_bin";
    final String mTable2="CREATE TABLE IF NOT EXISTS `book_details` (\n" +
                         "  `id` int(11) NOT NULL auto_increment,\n" +
                         "  `netid` varchar(20) collate utf8_bin NOT NULL,\n" +
                         "  `hostid` varchar(20) collate utf8_bin NOT NULL,\n" +
                         "  `borrower` varchar(32) collate utf8_bin default 'Not Issued',\n" +
                         "  PRIMARY KEY  (`id`)\n" +
                         ") ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_bin";
    final String mTable3="CREATE TABLE IF NOT EXISTS `image` (\n" +
                         "  `id` double NOT NULL auto_increment,\n" +
                         "  `date` varchar(30) NOT NULL,\n" +
                         "  `image` longblob NOT NULL,\n" +
                         "  PRIMARY KEY  (`id`)\n" +
                         ") ENGINE=InnoDB  DEFAULT CHARSET=latin1";
    final String mTable4="CREATE TABLE IF NOT EXISTS `library_admin` (\n" +
                         "  `id` double NOT NULL auto_increment,\n" +
                         "  `date` datetime NOT NULL,\n" +
                         "  `username` varchar(32) NOT NULL,\n" +
                         "  `password` varchar(32) NOT NULL,\n" +
                         "  `name` int(32) NOT NULL,\n" +
                         "  PRIMARY KEY  (`id`)\n" +
                         ") ENGINE=InnoDB  DEFAULT CHARSET=latin1";
    final String mTable5="CREATE TABLE IF NOT EXISTS `name` (\n" +
                         "  `id` int(11) NOT NULL auto_increment,\n" +
                         "  `account_holder` varchar(32) collate utf8_bin NOT NULL,\n" +
                         "  `student_id` varchar(20) collate utf8_bin NOT NULL,\n" +
                         "  `dob` varchar(20) collate utf8_bin NOT NULL,\n" +
                         "  `rfid_number` varchar(32) collate utf8_bin NOT NULL,\n" +
                         "  `thau` varchar(32) collate utf8_bin NOT NULL,\n" +
                         "  `gender` varchar(32) collate utf8_bin NOT NULL,\n" +
                         "  `date` varchar(32) collate utf8_bin NOT NULL,\n" +
                         "  `mobilenumber` varchar(15) collate utf8_bin NOT NULL,\n" +
                         "  `image` longblob NOT NULL,\n" +
                         "  `account_status` varchar(20) collate utf8_bin NOT NULL,\n" +
"  PRIMARY KEY  (`id`)\n" +
") ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_bin";
    final String mTable6="CREATE TABLE IF NOT EXISTS `transaction` (\n" +
"  `id` int(11) NOT NULL auto_increment,\n" +
"  `student_id` varchar(32) collate utf8_bin NOT NULL,\n" +
"  `net_id` varchar(32) collate utf8_bin NOT NULL,\n" +
"  `host_id` varchar(4) collate utf8_bin NOT NULL,\n" +
"  `date` varchar(32) collate utf8_bin NOT NULL,\n" +
"  `status` int(2) NOT NULL default '0',\n" +
"  `issue` int(2) default NULL,\n" +
"  PRIMARY KEY  (`id`)\n" +
") ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE=utf8_bin";
    
    Statement st;
    PreparedStatement pull;
    public CreateTable(){
        mProcessCreateTable(mTable1);
        mProcessCreateTable(mTable2);
        mProcessCreateTable(mTable3);
        mProcessCreateTable(mTable4);
        mProcessCreateTable(mTable5);
        mProcessCreateTable(mTable6);
    }
    private void mProcessCreateTable(String sql){
        st=super.getStatement();
        try{
            pull=conn.prepareStatement(sql);
            int s=pull.executeUpdate();
            if(s>0){
                System.out.println("Successfully created the tabale.");
            }
        }catch(SQLException e){
            System.out.println("uanble to create the database");
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }
}
