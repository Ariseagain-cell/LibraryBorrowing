package 图书借阅管理系统;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class BookModify
    extends JFrame
    implements ActionListener {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
  DataBaseManager db = new DataBaseManager();
  ResultSet rs;
  JPanel panel1, panel2, panel3;
  JLabel TipLabel = new JLabel("输入书名点确定，将调出此书相关信息");
  JLabel bookNameLabel, pressNameLabel,
      authorLabel, addressLabel,
      pressDateLabel, priceLabel, commentLabel;
  JTextField bookNameTextField, pressNameTextField,
      authorTextField, addressTextField,
      pressDateTextField, priceTextField, commentTextField;
  Container c;
  JButton clearBtn, yesBtn, updateBtn, exitBtn;
  public BookModify() {
    super("修改书籍信息");
    c = getContentPane();
    c.setLayout(new BorderLayout());
    panel3 = new JPanel();
    panel3.add(TipLabel);
    c.add(panel3, BorderLayout.NORTH);
    bookNameLabel = new JLabel("名称", JLabel.CENTER);
    pressNameLabel = new JLabel("出版社", JLabel.CENTER);
    authorLabel = new JLabel("作者", JLabel.CENTER);
    addressLabel = new JLabel("地址", JLabel.CENTER);
    pressDateLabel = new JLabel("出版日期", JLabel.CENTER);
    priceLabel = new JLabel("价格", JLabel.CENTER);
    commentLabel = new JLabel("备注", JLabel.CENTER);
    bookNameTextField = new JTextField(25);
    pressNameTextField = new JTextField(25);
    authorTextField = new JTextField(25);
    addressTextField = new JTextField(25);
    pressDateTextField = new JTextField(25);
    priceTextField = new JTextField(25);
    commentTextField = new JTextField(25);
    panel1 = new JPanel();
    panel1.setLayout(new GridLayout(7, 2));
    panel1.add(bookNameLabel);
    panel1.add(bookNameTextField);
    panel1.add(pressNameLabel);
    panel1.add(pressNameTextField);
    panel1.add(authorLabel);
    panel1.add(authorTextField);
    panel1.add(addressLabel);
    panel1.add(addressTextField);
    panel1.add(pressDateLabel);
    panel1.add(pressDateTextField);
    panel1.add(priceLabel);
    panel1.add(priceTextField);
    panel1.add(commentLabel);
    panel1.add(commentTextField);
    panel2 = new JPanel();
    panel2.setLayout(new GridLayout(1, 4));
    clearBtn = new JButton("清空");
    yesBtn = new JButton("确定");
    updateBtn = new JButton("更新");
    exitBtn = new JButton("退出");
    panel2.add(clearBtn);
    panel2.add(yesBtn);
    panel2.add(updateBtn);
    panel2.add(exitBtn);
    clearBtn.addActionListener(this);
    yesBtn.addActionListener(this);
    updateBtn.addActionListener(this);
    exitBtn.addActionListener(this);
    updateBtn.setEnabled(false);
    c.add(panel1, BorderLayout.CENTER);
    c.add(panel2, BorderLayout.SOUTH);
  }

  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == exitBtn) {
      this.dispose();
    }
    else if (e.getSource() == clearBtn) {
      bookNameTextField.setText("");
      pressNameTextField.setText("");
      authorTextField.setText("");
      addressTextField.setText("");
      pressDateTextField.setText("");
      priceTextField.setText("");
      commentTextField.setText("");
    }
    else if (e.getSource() == yesBtn) {
      try {
        String strSQL = "select * from books where bookName='" +
            bookNameTextField.getText().trim() + "'";
        if (bookNameTextField.getText().trim().equals("")) {
          JOptionPane.showMessageDialog(null, "请输入书名：<*v*>");
        }
        else if (!db.getResult(strSQL).first()) {
          JOptionPane.showMessageDialog(null, "此书没有在书库中...");
        }
        else {
          rs = db.getResult(strSQL);
          rs.first();
          bookNameTextField.setText(rs.getString(2).trim());
          pressNameTextField.setText(rs.getString(3).trim());
          authorTextField.setText(rs.getString(4).trim());
          addressTextField.setText(rs.getString(5).trim());
          pressDateTextField.setText(rs.getString(6).trim());
          priceTextField.setText(rs.getString(7).trim());
          commentTextField.setText(rs.getString(8).trim());
          updateBtn.setEnabled(true);
        }
      }
      catch (NullPointerException upe) {
        System.out.println(upe.toString());
      }
      catch (SQLException sqle) {
        System.out.println(sqle.toString());
      }
      catch (Exception ex) {
        System.out.println(ex.toString());
      }
    }
    else if (e.getSource() == updateBtn) {
      try {
        String strSQL = "update books set bookName='" +
            bookNameTextField.getText().trim() + "',press='" +
            pressNameTextField.getText().trim() + "',author='" +
            authorTextField.getText().trim() + "',address='" +
            addressTextField.getText().trim() + "',pressDate='" +
            pressDateTextField.getText().trim() + "',price='" +
            priceTextField.getText().trim() + "',com='" +
            commentTextField.getText().trim() + "' where bookname = '"+
            bookNameTextField.getText().trim()+ "'";
      //  if (db.updateSql(strSQL)>0) {
          rs = db.getResult(strSQL);
          JOptionPane.showMessageDialog(null, "更新书籍信息成功！");
          db.closeConnection();
          this.dispose();
      //  }
      //  else {
      //    JOptionPane.showMessageDialog(null, "更新书籍信息失败！");
      //    db.closeConnection();
      //    this.dispose();
      //  }
      }
      catch (Exception ex) {
        System.out.println(ex.toString());
      }
    }
  }
}
