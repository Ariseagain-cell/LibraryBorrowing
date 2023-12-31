package 图书借阅管理系统;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class ReturnInfo
    extends JFrame
    implements ActionListener {
  DataBaseManager db = new DataBaseManager();
  ResultSet rs;
  JPanel panel1, panel2;
  Container c;
  JLabel tipLabel = new JLabel("输入还书者姓名和书名点击确定，将调出此书的相关信息");
  JLabel returnedBookStudentLabel, returnedBookNameLabel,
      returnedDateLabel, returnedCommentLabel;
  JTextField returnedBookStudentTextField, returnedBookNameTextField,
      returnedDateTextField, returnedCommentTextField;
  JButton clearBtn, yesBtn, updateBtn, cancelBtn;
  public ReturnInfo() {
    super("修改书籍还入信息");
    c = getContentPane();
    c.setLayout(new BorderLayout());
    c.add(tipLabel, BorderLayout.NORTH);
    returnedBookStudentLabel = new JLabel("还书者姓名", JLabel.CENTER);
    returnedBookNameLabel = new JLabel("书名", JLabel.CENTER);
    returnedDateLabel = new JLabel("还书日期", JLabel.CENTER);
    returnedCommentLabel = new JLabel("备注", JLabel.CENTER);
    returnedBookStudentTextField = new JTextField(15);
    returnedBookNameTextField = new JTextField(15);
    returnedDateTextField = new JTextField(15);
    returnedCommentTextField = new JTextField(15);
    panel1 = new JPanel();
    panel1.setLayout(new GridLayout(4, 2));
    panel1.add(returnedBookStudentLabel);
    panel1.add(returnedBookStudentTextField);
    panel1.add(returnedBookNameLabel);
    panel1.add(returnedBookNameTextField);
    panel1.add(returnedDateLabel);
    panel1.add(returnedDateTextField);
    panel1.add(returnedCommentLabel);
    panel1.add(returnedCommentTextField);
    c.add(panel1, BorderLayout.CENTER);
    panel2 = new JPanel();
    panel2.setLayout(new GridLayout(1, 4));
    clearBtn = new JButton("清空");
    yesBtn = new JButton("确定");
    updateBtn = new JButton("更新");
    cancelBtn = new JButton("取消");
    clearBtn.addActionListener(this);
    yesBtn.addActionListener(this);
    updateBtn.addActionListener(this);
    updateBtn.setEnabled(false);
    cancelBtn.addActionListener(this);
    panel2.add(clearBtn);
    panel2.add(yesBtn);
    panel2.add(updateBtn);
    panel2.add(cancelBtn);
    c.add(panel2, BorderLayout.SOUTH);
  }

  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == clearBtn) {
      returnedBookStudentTextField.setText("");
      returnedBookNameTextField.setText("");
      returnedDateTextField.setText("");
      returnedCommentTextField.setText("");
    }
    else if (e.getSource() == cancelBtn) {
      this.dispose();
    }
    else if (e.getSource() == yesBtn) {
      try {
        String strSQL =
            "select studentName,bookName,borrowDate,com from BookBrowse where studentName='" +
            returnedBookStudentTextField.getText().trim() + "'and bookName='" +
            returnedBookNameTextField.getText().trim() + "'";
        rs = db.getResult(strSQL);
        if (!rs.first()) {
          JOptionPane.showMessageDialog(null, "此学生没有借过书！或者没有此书！");

        }
        else {
          returnedBookStudentTextField.setText(rs.getString(1).trim());
          returnedBookNameTextField.setText(rs.getString(2).trim());
          returnedDateTextField.setText(rs.getString(3).trim());
          returnedCommentTextField.setText(rs.getString(4).trim());
          updateBtn.setEnabled(true);

        }
      }
      catch (Exception ex) {
        System.out.println(ex.toString());
      }

    }
    else if (e.getSource() == updateBtn) {
      String strSQL = "update bookBrowse set returnDate='" +
          returnedDateTextField.getText().trim() + "',com='" +
          returnedCommentTextField.getText().trim() + "' where studentName='" +
          returnedBookStudentTextField.getText().trim() + "'and bookName='" +
          returnedBookNameTextField.getText().trim() + "'";
      //if (db.updateSql(strSQL)>0) {
        db.getResult(strSQL);
        JOptionPane.showMessageDialog(null, "更新成功！");
        this.dispose();
        db.closeConnection();
     
      }
    //  else {
    //    JOptionPane.showMessageDialog(null, "更新失败！");
    //    db.closeConnection();
    //    this.dispose();
    //  }
   // }
  }
}
