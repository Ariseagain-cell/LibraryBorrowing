package 图书借阅管理系统;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import javax.swing.table.*;
import java.util.Vector;

public class BorrowBookList
    extends JFrame
    implements ActionListener {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
  DataBaseManager db = new DataBaseManager();
  ResultSet rs;
  Container c;
  JPanel panel1, panel2;
  JLabel bookNameLabel, studentNameLabel;
  JTextField bookNameTextField, studentNameTextField;
  JButton searchBtn, exitBtn;
  JTable table = null;
  DefaultTableModel defaultModel = null;
  public BorrowBookList() {
    super("借阅信息查询！");
    c = getContentPane();
    c.setLayout(new BorderLayout());
    bookNameLabel = new JLabel("书名   ", JLabel.CENTER);
    studentNameLabel = new JLabel("借阅者", JLabel.CENTER);
    bookNameTextField = new JTextField(15);
    studentNameTextField = new JTextField(15);
    searchBtn = new JButton("查询");
    exitBtn = new JButton("退出");
    searchBtn.addActionListener(this);
    exitBtn.addActionListener(this);
    Box box1 = Box.createHorizontalBox();
    box1.add(studentNameLabel);
    box1.add(studentNameTextField);
    box1.add(searchBtn);
    Box box2 = Box.createHorizontalBox();
    box2.add(bookNameLabel);
    box2.add(bookNameTextField);
    box2.add(exitBtn);
    Box boxH = Box.createVerticalBox();
    boxH.add(box1);
    boxH.add(box2);
    boxH.add(Box.createVerticalGlue());
    panel1 = new JPanel();
    panel1.add(boxH);
    panel2 = new JPanel();
    String[] name = {
        "借阅者ID", "借阅者","书籍ID", "书名", "借阅日期", "还入日期", "备注"};
    String[][] data = new String[0][0];
    defaultModel = new DefaultTableModel(data, name);
    table = new JTable(defaultModel);
    table.setPreferredScrollableViewportSize(new Dimension(600, 120));
    JScrollPane s = new JScrollPane(table);
    panel2.add(s);
    c.add(panel1, BorderLayout.NORTH);
    c.add(panel2, BorderLayout.SOUTH);
  }

  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == exitBtn) {
      db.closeConnection();
      this.dispose();
    }
    else if (e.getSource() == searchBtn) {
//      String strSQL =
//          "select studentname,bookname,borrowdate,returndate,com from bookbrowse";
      String strSQL =
      "select ID,Username,BookID,BookName,borrowdate,returndate,com from Student_Borrow";
      String strSql = null;
      if (studentNameTextField.getText().trim().equals("") &&
          bookNameTextField.getText().trim().equals("")) {
        strSql = strSQL;
      }
      else if (studentNameTextField.getText().trim().equals("")) {
        strSql = strSQL + " where BookName='" +
            bookNameTextField.getText().trim() + "'";
      }
      else if (bookNameTextField.getText().trim().equals("")) {
        strSql = strSQL + " where Username='" +
            studentNameTextField.getText().trim() + "'";
      }
      else {
        strSql = strSQL + " where Username='" +
            studentNameTextField.getText().trim() + "'and bookName='" +
            bookNameTextField.getText().trim() + "'";
      }
      try {
        //首先要删除table中的数据：
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
  }
}
