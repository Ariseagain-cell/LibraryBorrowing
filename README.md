# [LibraryBorrowing](https://github.com/Ariseagain-cell/LibraryBorrowing/tree/main#libraryborrowing)

该项目为数据库系统课设

## 开发工具

系统开发环境：Windows 10

编程开发语言：Java

Java  JDK版本：JDK 1.8

编译软件： Eclipse

数据库软件：SQL Server 2019



## 使用说明

本系统需要在JDK环境下进行使用，建议使用MyEclipse或Eclipse进行安装使用，具体步骤如下：

1.	MyEclipse或Eclipse导入工程文件，查看环境是否有错，有的话进行调整，若出现乱码可将编码模式改为GB2312；

2.	用SQL Server导入数据库文件；
2.	创建一个数据源，该数据源必须属于某一个数据库用户，然后将jdbcdemo1.java和DataBaseManager.java中的user和password更改为该用户和对应密码（若无法连接可能是该用户权限不足，给予管理者权限应该可以解决）；

4. 通过源码jdbc包中附带的JDBC驱动器，加载驱动然后指明路径
5. 用数据库执行此存储过程：exec sys.sp_readerrorlog 0, 1, 'listening'得到端口号；
6. 将jdbcdemo1.java中的端口号改为上步所得，编译成功运行连上数据库后，将AdminLogin.java和DataBaseManager.java中的端口一并修改；
7. 编译运行程序，弹出图书借阅管理系统界面，开始使用



## 功能需求

用户管理：登录、添加、修改密码、删除

书籍管理：添加、修改、删除

借书管理：出借信息修改

还书管理：还入信息修改

信息查询：书籍信息、借阅信息、用户列表



## 关系模型

采用C/S逻辑结构的设计。管理员直接通过数据库登录密码登录，其他每一个用户登录都要输入用户名和密码以验证身份。然后在后台数据库系统查询用户是否存在，返回给用户一个结果。用户根据自己的权限进行相应的操作。

关系模型为（满足第三范式3NF）：

用户表UserTable(用户ID，用户名，用户密码，用户权限)

书籍表books（书籍ID，书名，出版社，作者，地址，出版日期，价格，备注，总数量，已借走数量）

借阅表booksBrowse(ID，书名，学生姓名，出版社，作者，地址，借/还书日期，价格，备注，总数量，已借走数量)

***备注：***a. ID字段均被设置为“标识”，标识种子为1，增量为1。被设置为“标志”的字段的数据必须为数值类型；b. 下划线表示主键。



**管理员登陆界面**

关键代码：

***\*public\**** ***\*void\**** actionPerformed(ActionEvent e) {

***\*if\**** (e.getSource() == cancelBtn) {

mainFrame.setEnable("else");

***\*this\****.dispose();

}

***\*else\**** { 

**DataBaseManager1**();

***\*char\****[] password = passwordTextField.getPassword();

**username** = userTextField.getText().trim();

**passwordSTR** = ***\*new\**** String(password);

***\*if\**** (userTextField.getText().trim().equals("")) {

JOptionPane.**showMessageDialog**(***\*null\****, "用户名不可为空!");

***\*return\****;

}

***\*if\**** (**passwordSTR**.equals("")) {

JOptionPane.**showMessageDialog**(***\*null\****, "密码不可为空!");

***\*return\****;

}

JOptionPane.**showMessageDialog**(***\*null\****, "登陆成功"); 

mainFrame.setEnable("系统管理员"); 

***\*this\****.dispose();

}

}



**书籍出借**

关键代码：

***\*public\**** ***\*void\**** actionPerformed(ActionEvent e) {

***\*if\**** (e.getSource() == cancelBtn) {

***\*this\****.dispose();

}

***\*else\**** ***\*if\**** (e.getSource() == clearBtn) {

borrowedBookStudentTextField.setText("");

borrowedBook_Student_ID_TextField.setText("");

borrowedBook_Book_ID_TextField.setText("");

borrowedDateTextField.setText("");

borrowedCommentTextField.setText("");

}

***\*else\**** ***\*if\**** (e.getSource() == yesBtn) {

***\*if\**** (borrowedBookStudentTextField.getText().trim().equals("")) {

JOptionPane.**showMessageDialog**(***\*null\****, "请输入借阅者的姓名。。。");

}

***\*else\**** ***\*if\**** (bookNameComboBox.getSelectedItem().equals("")) {

JOptionPane.**showMessageDialog**(***\*null\****, "对不起，现在书库里没有书，\n你现在不能借书!");

}

***\*else\**** {

rs = db.getResult(strSQL);

strSQL =

"update books set borrowed_count=borrowed_count+1 where bookname='" +

bookNameComboBox.getSelectedItem() + "'";

JOptionPane.**showMessageDialog**(***\*null\****, "借阅完成！");

***\*this\****.dispose();

db.closeConnection();



**书籍查询**

关键代码：

***\*public\**** BookList() {

***\*super\****("书籍信息查询！");

c = getContentPane();

c.setLayout(***\*new\**** BorderLayout());

bookNameLabel = ***\*new\**** JLabel("名称", JLabel.***\**CENTER\**\***);

authorLabel = ***\*new\**** JLabel("作者", JLabel.***\**CENTER\**\***);

pressLabel = ***\*new\**** JLabel("出版社", JLabel.***\**CENTER\**\***);

bookNameTextField = ***\*new\**** JTextField(15);

authorTextField = ***\*new\**** JTextField(15);

pressTextField = ***\*new\**** JTextField(15);

searchBtn = ***\*new\**** JButton("查询");

searchBtn.addActionListener(***\*this\****);

exitBtn = ***\*new\**** JButton("退出");

exitBtn.addActionListener(***\*this\****);

panel1 = ***\*new\**** JPanel();

panel3 = ***\*new\**** JPanel();

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