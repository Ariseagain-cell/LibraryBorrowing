package 图书借阅管理系统;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import javax.swing.table.*;
import java.util.Vector;

public class BookList
    extends JFrame
    implements ActionListener {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
  DataBaseManager db = new DataBaseManager();
  ResultSet rs;
  Container c;
  JPanel panel1, panel2, panel3;
  JLabel bookNameLabel, authorLabel, pressLabel;
  JTextField bookNameTextField, authorTextField,
      pressTextField;
  JButton searchBtn, exitBtn;
  JTable table = null;
  DefaultTableModel defaultModel = null;
  public BookList() {
    super("书籍信息查询！");
    c = getContentPane();
    c.setLayout(new BorderLayout());
    bookNameLabel = new JLabel("名称", JLabel.CENTER);
    authorLabel = new JLabel("作者", JLabel.CENTER);
    pressLabel = new JLabel("出版社", JLabel.CENTER);
    bookNameTextField = new JTextField(15);
    authorTextField = new JTextField(15);
    pressTextField = new JTextField(15);
    searchBtn = new JButton("查询");
    searchBtn.addActionListener(this);
    exitBtn = new JButton("退出");
    exitBtn.addActionListener(this);
    panel1 = new JPanel();
    panel3 = new JPanel();
    panel1.add(bookNameLabel);
    panel1.add(bookNameTextField);
    panel1.add(authorLabel);
    panel1.add(authorTextField);
    panel3.add(pressLabel);
    panel3.add(pressTextField);
    panel3.add(searchBtn);
    panel3.add(exitBtn);
    String[] name = {
        "书名", "出版社", "作者", "地址", "出版日期", "定价", "评论"};
    String[][] data = new String[0][0];
    defaultModel = new DefaultTableModel(data, name);
    table = new JTable(defaultModel);
    table.setPreferredScrollableViewportSize(new Dimension(800, 120));
    JScrollPane s = new JScrollPane(table);
    panel2 = new JPanel();
    panel2.add(s);
    c.add(panel1, BorderLayout.NORTH);
    c.add(panel3, BorderLayout.CENTER);
    c.add(panel2, BorderLayout.SOUTH);
  }

  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == searchBtn) {
      String strSQL =
          "select bookName,press,author,address,pressDate,price,com from books";
      String strSql = null;
      if (bookNameTextField.getText().trim().equals("") &&
          authorTextField.getText().trim().equals("") &&
          pressTextField.getText().trim().equals("")) {
        strSql = strSQL;
      }
      else if (bookNameTextField.getText().trim().equals("") &&
               authorTextField.getText().trim().equals("")) {
        strSql = strSQL + " where press='" + pressTextField.getText().trim() +
            "'";
      }
      else if (bookNameTextField.getText().trim().equals("") &&
               pressTextField.getText().trim().equals("")) {
        strSql = strSQL + " where author='" + authorTextField.getText().trim() +
            "'";
      }
      else if (authorTextField.getText().trim().equals("") &&
               pressTextField.getText().trim().equals("")) {
        strSql = strSQL + " where bookName='" +
            bookNameTextField.getText().trim() + "'";
      }
      else if (bookNameTextField.getText().trim().equals("")) {
        strSql = strSQL + " where author='" + authorTextField.getText().trim() +
            "'and press='" + pressTextField.getText().trim() + "'";
      }
      else if (authorTextField.getText().trim().equals("")) {
        strSql = strSQL + " where bookName='" +
            bookNameTextField.getText().trim() +
            "'and press='" + pressTextField.getText().trim() + "'";
      }
      else if (pressTextField.getText().trim().equals("")) {
        strSql = strSQL + " where bookname='" +
            bookNameTextField.getText().trim() +
            "'and author='" + authorTextField.getText().trim() + "'";
      }
      else {
        strSql = strSQL + " where bookname='" +
            bookNameTextField.getText().trim() +
            "'and author='" + authorTextField.getText().trim() + "'and press='" +
            pressTextField.getText().trim() + "'";
      }
      try {
        //首先要删除table中的数据先：
        int rowCount = defaultModel.getRowCount() - 1; //取得table中的数据行；
        int j = rowCount;
        for (int i = 0; i <= rowCount; i++) {
          defaultModel.removeRow(j); //删除rowCount行的数据；
          defaultModel.setRowCount(j); //重新设置行数；
          j = j - 1;
        }
        rs = db.getResult(strSql);
        while (rs.next()) {
          Vector data = new Vector();
          data.addElement(rs.getString(1));
          data.addElement(rs.getString(2));
          data.addElement(rs.getString(3));
          data.addElement(rs.getString(4));
          data.addElement(rs.getString(5));
          data.addElement(rs.getString(6));
          data.addElement(rs.getString(7));
          defaultModel.addRow(data);
        }
        table.revalidate();
      }
      catch (SQLException sqle) {
        System.out.println(sqle.toString());
      }
      catch (Exception ex) {
        System.out.println(ex.toString());
      }
    }
    else if (e.getSource() == exitBtn) {
      db.closeConnection();
      this.dispose();
    }
  }
}
