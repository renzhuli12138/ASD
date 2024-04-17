package bookmanage.view;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import bookmanage.dao.UserDao;
import bookmanage.model.User;
public class





ListUser extends JPanel {
    Vector rowData, columnNames;
    JTable jt = null;
    JScrollPane jsp = null;

    public ListUser() {
        ArrayList<User> users = new UserDao().getUserByAccout();
        columnNames = new Vector();
        // 设置列名
        columnNames.add("用户名");
        columnNames.add("密码");

        rowData = new Vector();
        for (int i = 0; i < users.size(); i++) {
            //实例化每一行数据
            Vector hang = new Vector();
            hang.add(users.get(i).getAccout());
            hang.add(users.get(i).getPass());
            // 加入到rowData
            rowData.add(hang);
        }
        // 初始化Jtable
        jt = new JTable(rowData, columnNames);
        // 初始化 jsp
        jsp = new JScrollPane(jt);
        this.add(jsp);
    }
}
