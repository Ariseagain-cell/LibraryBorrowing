package 图书借阅管理系统;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class MainFrame
    extends JFrame
    implements ActionListener {
  /**
	 * ]
	 */
	private static final long serialVersionUID = 1L;
  JPanel panel1;
  Container c;
  JMenuBar menuB;
  JMenu systemMenu, bookMGRMenu, borrowBookMenu, returnBookMenu,
      infoBrowseMenu, userMGRMenu,aboutMenu;
  JMenuItem adminLoginMenuItem, userLoginMenuItem, userAddMenuItem, userModifyMenuItem,
      userDeleteMenuItem, exitMenuItem, bookAddMenuItem, bookModifyMenuItem,
      bookDeleteMenuItem,
      borrowBookMenuItem, borrowInfoMenuItem, returnBookMenuItem,
      returnInfoMenuItem,
      bookListMenuItem, borrowBookListMenuItem, userListMenuItem,
      aboutMenuItem;
  public MainFrame() {
    super("图书馆管理系统");
    //--系统管理菜单--
    menuB = new JMenuBar();
    systemMenu = new JMenu("系统管理");
    userMGRMenu = new JMenu("用户管理");
    adminLoginMenuItem = new JMenuItem("管理员登录");
    userLoginMenuItem = new JMenuItem("用户登录");
    userAddMenuItem = new JMenuItem("添加用户");
    userModifyMenuItem = new JMenuItem("修改密码");
    userDeleteMenuItem = new JMenuItem("删除用户");
    exitMenuItem = new JMenuItem("退出");
    systemMenu.add(adminLoginMenuItem);
    systemMenu.add(userLoginMenuItem);
    userMGRMenu.add(userAddMenuItem);
    userMGRMenu.add(userModifyMenuItem);
    userMGRMenu.add(userDeleteMenuItem);
    systemMenu.add(userMGRMenu);
    systemMenu.add(exitMenuItem);
    adminLoginMenuItem.addActionListener(this);
    userLoginMenuItem.addActionListener(this);
    userAddMenuItem.addActionListener(this);
    userModifyMenuItem.addActionListener(this);
    userDeleteMenuItem.addActionListener(this);
    exitMenuItem.addActionListener(this);
    menuB.add(systemMenu);
    //---书籍管理菜单--
    bookMGRMenu = new JMenu("书籍管理");
    bookAddMenuItem = new JMenuItem("添加书籍");
    bookModifyMenuItem = new JMenuItem("修改书籍");
    bookDeleteMenuItem = new JMenuItem("删除书籍");
    bookMGRMenu.add(bookAddMenuItem);
    bookMGRMenu.add(bookModifyMenuItem);
    bookMGRMenu.add(bookDeleteMenuItem);
    bookAddMenuItem.addActionListener(this);
    bookModifyMenuItem.addActionListener(this);
    bookDeleteMenuItem.addActionListener(this);
    menuB.add(bookMGRMenu);
    //--借书管理菜单--
    borrowBookMenu = new JMenu("借书管理");
    borrowBookMenuItem = new JMenuItem("书籍出借");
    borrowInfoMenuItem = new JMenuItem("出借信息修改");
    borrowBookMenu.add(borrowBookMenuItem);
    borrowBookMenu.add(borrowInfoMenuItem);
    borrowBookMenuItem.addActionListener(this);
    borrowInfoMenuItem.addActionListener(this);
    menuB.add(borrowBookMenu);
    //--还书管理菜单--
    returnBookMenu = new JMenu("还书管理");
    returnBookMenuItem = new JMenuItem("书籍还入");
    returnInfoMenuItem = new JMenuItem("书籍还入信息修改");
    returnBookMenu.add(returnBookMenuItem);
    returnBookMenu.add(returnInfoMenuItem);
    returnBookMenuItem.addActionListener(this);
    returnInfoMenuItem.addActionListener(this);
    menuB.add(returnBookMenu);
    //--信息一览菜单--
    infoBrowseMenu = new JMenu("信息查询");
    bookListMenuItem = new JMenuItem("书籍信息查询");
    borrowBookListMenuItem = new JMenuItem("借阅信息查询");
    userListMenuItem = new JMenuItem("用户列表");
    infoBrowseMenu.add(bookListMenuItem);
    infoBrowseMenu.add(borrowBookListMenuItem);
    infoBrowseMenu.add(userListMenuItem);
    bookListMenuItem.addActionListener(this);
    borrowBookListMenuItem.addActionListener(this);
    userListMenuItem.addActionListener(this);
    menuB.add(infoBrowseMenu);
    //--关于菜单--
    aboutMenu=new JMenu("帮助");
    aboutMenuItem = new JMenuItem("关于");
    aboutMenuItem.addActionListener(this);
    aboutMenu.add(aboutMenuItem);
    menuB.add(aboutMenu);
    //----------------------------------
    setJMenuBar(menuB);

    c = getContentPane();
    c.setLayout(new BorderLayout());
    panel1 = new JPanel();
    panel1.setLayout(new BorderLayout());

    c.add(panel1, BorderLayout.CENTER);

    //--设置初始功能:--
    userMGRMenu.setEnabled(false);
    bookMGRMenu.setEnabled(false);
    borrowBookMenu.setEnabled(false);
    returnBookMenu.setEnabled(false);
    infoBrowseMenu.setEnabled(false);
    aboutMenu.setEnabled(false);
  }

  //--设置每个菜单点击后出现的窗口和窗口显示的位置--
  public void actionPerformed(ActionEvent e) {
	  if (e.getActionCommand() == "管理员登录") {
	      
		  AdminLogin AdminLoginFrame = new AdminLogin(this);//创建用例类实例对象
	      Dimension FrameSize = AdminLoginFrame.getPreferredSize();//子窗口大小
	      Dimension MainFrameSize = getSize();//主窗口大小
	      Point loc = getLocation();//主窗口位置
	      AdminLoginFrame.setLocation( (MainFrameSize.width - FrameSize.width) / 2 +
	                                 loc.x,
	                                 (MainFrameSize.height - FrameSize.height) / 2 +
	                                 loc.y);//设置子窗口的位置位于主窗口的中央
	      AdminLoginFrame.pack();
	      AdminLoginFrame.show();
	    }
	  
    if (e.getActionCommand() == "用户登录") {
      
      UserLogin UserLoginFrame = new UserLogin(this);//创建用例类实例对象
      Dimension FrameSize = UserLoginFrame.getPreferredSize();//子窗口大小
      Dimension MainFrameSize = getSize();//主窗口大小
      Point loc = getLocation();//主窗口位置
      UserLoginFrame.setLocation( (MainFrameSize.width - FrameSize.width) / 2 +
                                 loc.x,
                                 (MainFrameSize.height - FrameSize.height) / 2 +
                                 loc.y);//设置子窗口的位置位于主窗口的中央
      UserLoginFrame.pack();
      UserLoginFrame.show();
    }
    else if (e.getActionCommand() == "添加用户") {
      UserAdd UserAddFrame = new UserAdd();
      Dimension FrameSize = UserAddFrame.getPreferredSize();
      Dimension MainFrameSize = getSize();
      Point loc = getLocation();
      UserAddFrame.setLocation( (MainFrameSize.width - FrameSize.width) / 2 +
                               loc.x,
                               (MainFrameSize.height - FrameSize.height) / 2 +
                               loc.y);
      UserAddFrame.pack();
      UserAddFrame.show();
    }
    else if (e.getActionCommand() == "修改密码") {
      UserModify UserModifyFrame = new UserModify();
      Dimension FrameSize = UserModifyFrame.getPreferredSize();
      Dimension MainFrameSize = getSize();
      Point loc = getLocation();
      UserModifyFrame.setLocation( (MainFrameSize.width - FrameSize.width) / 2 +
                                  loc.x,
                                  (MainFrameSize.height - FrameSize.height) / 2 +
                                  loc.y);
      UserModifyFrame.pack();
      UserModifyFrame.show();
    }
    else if (e.getActionCommand() == "删除用户") {
      UserDelete UserDeleteFrame = new UserDelete();
      Dimension FrameSize = UserDeleteFrame.getPreferredSize();
      Dimension MainFrameSize = getSize();
      Point loc = getLocation();
      UserDeleteFrame.setLocation( (MainFrameSize.width - FrameSize.width) / 2 +
                                  loc.x,
                                  (MainFrameSize.height - FrameSize.height) / 2 +
                                  loc.y);
      UserDeleteFrame.pack();
      UserDeleteFrame.show();
    }
    else if (e.getActionCommand() == "添加书籍") {
      BookAdd BookAddFrame = new BookAdd();
      Dimension FrameSize = BookAddFrame.getPreferredSize();
      Dimension MainFrameSize = getSize();
      Point loc = getLocation();
      BookAddFrame.setLocation( (MainFrameSize.width - FrameSize.width) / 2 +
                               loc.x,
                               (MainFrameSize.height - FrameSize.height) / 2 +
                               loc.y);
      BookAddFrame.pack();
      BookAddFrame.show();
    }
    else if (e.getActionCommand() == "修改书籍") {
      BookModify BookModifyFrame = new BookModify();
      Dimension FrameSize = BookModifyFrame.getPreferredSize();
      Dimension MainFrameSize = getSize();
      Point loc = getLocation();
      BookModifyFrame.setLocation( (MainFrameSize.width - FrameSize.width) / 2 +
                                  loc.x,
                                  (MainFrameSize.height - FrameSize.height) / 2 +
                                  loc.y);
      BookModifyFrame.pack();
      BookModifyFrame.show();
    }
    else if (e.getActionCommand() == "删除书籍") {
      BookDelete BookDeleteFrame = new BookDelete();
      Dimension FrameSize = BookDeleteFrame.getPreferredSize();
      Dimension MainFrameSize = getSize();
      Point loc = getLocation();
      BookDeleteFrame.setLocation( (MainFrameSize.width - FrameSize.width) / 2 +
                                  loc.x,
                                  (MainFrameSize.height - FrameSize.height) / 2 +
                                  loc.y);
      BookDeleteFrame.pack();
      BookDeleteFrame.show();
    }
    else if (e.getActionCommand() == "书籍出借") {
      BorrowBook BorrowBookFrame = new BorrowBook();
      Dimension FrameSize = BorrowBookFrame.getPreferredSize();
      Dimension MainFrameSize = getSize();
      Point loc = getLocation();
      BorrowBookFrame.setLocation( (MainFrameSize.width - FrameSize.width) / 2 +
                                  loc.x,
                                  (MainFrameSize.height - FrameSize.height) / 2 +
                                  loc.y);
      BorrowBookFrame.pack();
      BorrowBookFrame.show();
    }
    else if (e.getActionCommand() == "出借信息修改") {
      BorrowInfo BorrowInfoFrame = new BorrowInfo();
      Dimension FrameSize = BorrowInfoFrame.getPreferredSize();
      Dimension MainFrameSize = getSize();
      Point loc = getLocation();
      BorrowInfoFrame.setLocation( (MainFrameSize.width - FrameSize.width) / 2 +
                                  loc.x,
                                  (MainFrameSize.height - FrameSize.height) / 2 +
                                  loc.y);
      BorrowInfoFrame.pack();
      BorrowInfoFrame.show();
    }
    else if (e.getActionCommand() == "书籍还入") {
      ReturnBook ReturnBookFrame = new ReturnBook();
      Dimension FrameSize = ReturnBookFrame.getPreferredSize();
      Dimension MainFrameSize = getSize();
      Point loc = getLocation();
      ReturnBookFrame.setLocation( (MainFrameSize.width - FrameSize.width) / 2 +
                                  loc.x,
                                  (MainFrameSize.height - FrameSize.height) / 2 +
                                  loc.y);
      ReturnBookFrame.pack();
      ReturnBookFrame.show();
    }
    else if (e.getActionCommand() == "书籍还入信息修改") {
      ReturnInfo ReturnInfoFrame = new ReturnInfo();
      Dimension FrameSize = ReturnInfoFrame.getPreferredSize();
      Dimension MainFrameSize = getSize();
      Point loc = getLocation();
      ReturnInfoFrame.setLocation( (MainFrameSize.width - FrameSize.width) / 2 +
                                  loc.x,
                                  (MainFrameSize.height - FrameSize.height) / 2 +
                                  loc.y);
      ReturnInfoFrame.pack();
      ReturnInfoFrame.show();
    }
    else if (e.getActionCommand() == "书籍信息查询") {
      BookList BookListFrame = new BookList();
      Dimension FrameSize = BookListFrame.getPreferredSize();
      Dimension MainFrameSize = getSize();
      Point loc = getLocation();
      BookListFrame.setLocation( (MainFrameSize.width - FrameSize.width) / 2 +
                                loc.x,
                                (MainFrameSize.height - FrameSize.height) / 2 +
                                loc.y);
      BookListFrame.pack();
      BookListFrame.show();
    }
    else if (e.getActionCommand() == "借阅信息查询") {
      BorrowBookList BorrowBookListFrame = new BorrowBookList();
      Dimension FrameSize = BorrowBookListFrame.getPreferredSize();
      Dimension MainFrameSize = getSize();
      Point loc = getLocation();
      BorrowBookListFrame.setLocation( (MainFrameSize.width - FrameSize.width) /
                                      2 + loc.x,
                                      (MainFrameSize.height - FrameSize.height) /
                                      2 + loc.y);
      BorrowBookListFrame.pack();
      BorrowBookListFrame.show();
    }
    else if (e.getActionCommand() == "用户列表") {
      UserList UserListFrame = new UserList();
      Dimension FrameSize = UserListFrame.getPreferredSize();
      Dimension MainFrameSize = getSize();
      Point loc = getLocation();
      UserListFrame.setLocation( (MainFrameSize.width - FrameSize.width) / 2 +
                                loc.x,
                                (MainFrameSize.height - FrameSize.height) / 2 +
                                loc.y);
      UserListFrame.pack();
      UserListFrame.show();
    }
    else if (e.getActionCommand() == "关于") {
      String str = "本程序为图书借阅管理系统，设计者为\n"+
          "     张威浩     网络工程2班\n";
      JOptionPane.showMessageDialog(null, str, "关于",
                                    JOptionPane.INFORMATION_MESSAGE);
    }
    else if (e.getActionCommand() == "退出") {
      this.dispose();
      System.exit(0);
    }
  }

  //--设置登录用户的权限--
  public void setEnable(String powerType) {
    if (powerType.trim().equals("系统管理员")) {
      userMGRMenu.setEnabled(true);
      bookMGRMenu.setEnabled(true);
      borrowBookMenu.setEnabled(true);
      returnBookMenu.setEnabled(true);
      infoBrowseMenu.setEnabled(true);
      userListMenuItem.setEnabled(true);
      aboutMenu.setEnabled(true);
    }
    else if (powerType.trim().equals("书籍管理员")) {
      userMGRMenu.setEnabled(false);
      bookMGRMenu.setEnabled(true);
      borrowBookMenu.setEnabled(false);
      returnBookMenu.setEnabled(false);
      infoBrowseMenu.setEnabled(true);
      userListMenuItem.setEnabled(false);
      aboutMenu.setEnabled(true);
    }
    else if (powerType.trim().equals("学生")) {
      userMGRMenu.setEnabled(false);
      bookMGRMenu.setEnabled(false);
      borrowBookMenu.setEnabled(true);
      returnBookMenu.setEnabled(true);
      infoBrowseMenu.setEnabled(true);
      userListMenuItem.setEnabled(false);
      aboutMenu.setEnabled(true);
    }
    else if (powerType.trim().equals("else")) {
      userMGRMenu.setEnabled(false);
      bookMGRMenu.setEnabled(false);
      borrowBookMenu.setEnabled(false);
      returnBookMenu.setEnabled(false);
      infoBrowseMenu.setEnabled(false);
      aboutMenu.setEnabled(false);
    }
  }

  public static void main(String args[]) {
	    try {
	      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	    }
	    catch (Exception e) {
	      e.printStackTrace();
	    }

	    MainFrame mainFrame = new MainFrame();
	    mainFrame.setSize(500,400);
	    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
	    mainFrame.setLocation( (d.width - mainFrame.getSize().width) / 2,
	                      (d.height - mainFrame.getSize().height) / 2);
	    mainFrame.show();
	  }
}
