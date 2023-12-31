package 图书借阅管理系统;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class BorrowInfo
    extends JFrame
    implements ActionListener {
  DataBaseManager db = new DataBaseManager();
  ResultSet rs;
  JPanel panel1, panel2, panel3;
  Container c;
  JLabel TipLabel = new JLabel("输入借阅者姓名和书名点击确定，将调出此书的相关信息");
  JLabel borrowedBookStudentLabel, borrowedBookNameLabel,
      borrowedDateLabel, borrowedCommentLabel;
  JTextField borrowedBookStudentTextField, borrowedBookNameTextField,
      borrowedDateTextField, borrowedCommentTextField;
  JButton clearBtn, yesBtn, updateBtn, cancelBtn;
  public BorrowInfo() {
    super("修改书籍出借信息");
    c = getContentPane();
    c.setLayout(new BorderLayout());
    panel3 = new JPanel();
    panel3.add(TipLabel);
    c.add(panel3, BorderLayout.NORTH);
    borrowedBookStudentLabel = new JLabel("借阅者姓名", JLabel.CENTER);
    borrowedBookNameLabel = new JLabel("书名", JLabel.CENTER);
    borrowedDateLabel = new JLabel("借书日期", JLabel.CENTER);
    borrowedCommentLabel = new JLabel("备注", JLabel.CENTER);
    borrowedBookStudentTextField = new JTextField(15);
    borrowedBookNameTextField = new JTextField(15);
    borrowedDateTextField = new JTextField(15);
    borrowedCommentTextField = new JTextField(15);
    panel1 = new JPanel();
    panel1.setLayout(new GridLayout(4, 2));
    panel1.add(borrowedBookStudentLabel);
    panel1.add(borrowedBookStudentTextField);
    panel1.add(borrowedBookNameLabel);
    panel1.add(borrowedBookNameTextField);
    panel1.add(borrowedDateLabel);
    panel1.add(borrowedDateTextField);
    panel1.add(borrowedCommentLabel);
    panel1.add(borrowedCommentTextField);
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
      borrowedBookStudentTextField.setText("");
      borrowedBookNameTextField.setText("");
      borrowedDateTextField.setText("");
      borrowedCommentTextField.setText("");
    }
    else if (e.getSource() == cancelBtn) {
      this.dispose();
    }
    else if (e.getSource() == yesBtn) {
      try {
        String strSQL =
            "select studentName,bookName,borrowDate,com from BookBrowse where studentName='" +
            borrowedBookStudentTextField.getText().trim() + "'and bookName='" +
            borrowedBookNameTextField.getText().trim() + "'";
        rs = db.getResult(strSQL);
        if (!rs.first()) {
          JOptionPane.showMessageDialog(null, "此学生没有借过书！或者没有此书！");

        }
        else {
          borrowedBookStudentTextField.setText(rs.getString(1).trim());
          borrowedBookNameTextField.setText(rs.getString(2).trim());
          borrowedDateTextField.setText(rs.getString(3).trim());
          borrowedCommentTextField.setText(rs.getString(4).trim());
          updateBtn.setEnabled(true);

        }
      }
      catch (Exception ex) {
        System.out.println(ex.toString());
      }

    }
    else if (e.getSource() == updateBtn) {
      String strSQL = "update bookBrowse set borrowDate='" +
          borrowedDateTextField.getText().trim() + "',com='" +
          borrowedCommentTextField.getText().trim() + "' where studentName='" +
          borrowedBookStudentTextField.getText().trim() + "'and bookName='" +
          borrowedBookNameTextField.getText().trim() + "'";
      
      //if (db.updateSql(strSQL)>0) {
        rs = db.getResult(strSQL);
        JOptionPane.showMessageDialog(null, "更新成功！");
        db.closeConnection();
        this.dispose();
     // }
     // else {
      //  JOptionPane.showMessageDialog(null, "更新失败！");
     //   db.closeConnection();
     //   this.dispose();
    //  }
    }

  }
}
