package 系统显示.系统管理.数据字典;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import 关键字监听器.InputKeyListener;
import 数据库模型.TbDictionary;
import 数据库模型.TbUserlist;
import 登录界面.Denglu;
import 系统显示.基础信息管理.客户信息管理.KeHuAddPanel;
import 系统显示.数据库信息.DaoRu;

public class AddDictionary extends JPanel {
	private TbUserlist user = Denglu.getUser(); // 登录用户信息
	private JComboBox sjlxBox; // 数据类型
	private JTextField jt_zdc; // 字典代码
	private JTextField jt_zdz; // 字典值
	private JTextField jt_czy = new JTextField(user.getName()); // 录入数据操作员
	private JTextField jt_date; // 录入数据时间
	private String s_zdc = "";
	private String s_zdn = "";
	private String[] zdSz = new String[] { "客户分类", "客户性质", "客户分区", "单位", "开户银行" };
	private String[] zdSzc = new String[] { "ClientType", "ClientKind",
			"ClientArea", "Unit", "Bank" };
	private Date lrsjDate;
	private DateFormat lrsjDateFormat;
	private JButton resetButton;

	private void clear() {
		jt_zdc.setText(null);
		jt_zdz.setText("");
		jt_czy.setText("");
		jt_date.setText("");
	}

	public AddDictionary() {
		super();
		setLayout(new GridBagLayout());
		setBounds(0, 0, 400, 287);
		final JLabel label_1 = new JLabel();
		label_1.setFont(new Font("", Font.PLAIN, 15));
		label_1.setText("字典目：");
		setupZuJian(label_1, 0, 0, 1, 0, false);

		sjlxBox = new JComboBox(zdSz);
		setupZuJian(sjlxBox, 1, 0, 3, 135, true);

		final JLabel label_2 = new JLabel();
		label_2.setFont(new Font("", Font.PLAIN, 15));
		label_2.setText("字典代码：");
		setupZuJian(label_2, 0, 1, 1, 0, false);

		jt_zdc = new JTextField();
		jt_zdc.addKeyListener(new InputKeyListener());
		setupZuJian(jt_zdc, 1, 1, 3, 200, true);

		final JLabel label_3 = new JLabel();
		label_3.setFont(new Font("", Font.PLAIN, 15));
		label_3.setText("字典值：");
		setupZuJian(label_3, 0, 2, 1, 0, false);

		jt_zdz = new JTextField();
		setupZuJian(jt_zdz, 1, 2, 3, 200, true);

		final JLabel label_4 = new JLabel();
		label_4.setFont(new Font("", Font.PLAIN, 15));
		label_4.setText("操作员：");
		setupZuJian(label_4, 0, 3, 1, 0, false);

		jt_czy.setEditable(false);
		setupZuJian(jt_czy, 1, 3, 3, 200, true);

		final JLabel label_5 = new JLabel();
		label_5.setFont(new Font("", Font.PLAIN, 15));
		label_5.setText("操作时间：");
		setupZuJian(label_5, 0, 4, 1, 0, false);

		jt_date = new JTextField();
		jt_date.setEditable(false);
		jt_date.setFocusable(false);
		setupZuJian(jt_date, 1, 4, 3, 200, true);

		final JButton saveButton = new JButton("添加");
		saveButton.addActionListener(new SaveButtonActionListener());
		setupZuJian(saveButton, 1, 5, 1, 50, false);
		resetButton = new JButton("重置");
		resetButton.addActionListener(new ResetButtonActionListener());
		setupZuJian(resetButton, 3, 5, 1, 50, false);
	}

	// 添加组件到容器的方法
	private void setupZuJian(JComponent component, int gridx, int gridy,
			int gridwidth, int ipadx, boolean fill) {
		final GridBagConstraints gridBagConstrains = new GridBagConstraints();
		gridBagConstrains.gridx = gridx;
		gridBagConstrains.gridy = gridy;
		gridBagConstrains.gridwidth = gridwidth;
		gridBagConstrains.ipadx = ipadx;
		gridBagConstrains.insets = new Insets(4, 3, 3, 2);
		if (fill) {
			gridBagConstrains.fill = GridBagConstraints.HORIZONTAL;
		}
		add(component, gridBagConstrains);
	}

	private final class SaveButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO 自动生成的方法存根
			if (sjlxBox.getSelectedItem().equals("请选择")// 验证用户输入
					|| jt_zdc.getText().equals("")
					|| jt_zdz.getText().equals("")
					|| jt_czy.getText().equals("")
					|| jt_date.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "请填写所有信息");
				return;
			}
			try {
				ResultSet haveUser = DaoRu
						.query("select * from tb_dictionary where valueName='"
								+ jt_zdz.getText().trim() + "'");
				if (haveUser.next()) {
					JOptionPane.showMessageDialog(AddDictionary.this,
							"字典信息添加失败，存在同名字典值", "字典添加信息",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				TbDictionary dictionary = new TbDictionary();
				s_zdn = (String) sjlxBox.getSelectedItem();
				for (int i = 0; i < zdSz.length; i++) {
					if (s_zdn.equals((String) zdSz[i])) {
						s_zdc = zdSzc[i];
					}
				}
				dictionary.setTypeCode(s_zdc.trim());
				dictionary.setTypeName(s_zdn.trim());
				dictionary.setValueCode(jt_zdc.getText().trim());
				dictionary.setValueName(jt_zdz.getText().trim());
				dictionary.setLrczy(jt_czy.getText().trim());
				dictionary.setLrdate(jt_date.getText().trim());
				DaoRu.addSjZd(dictionary);
				JOptionPane.showMessageDialog(AddDictionary.this, "已成功添加字典信息",
						"字典信息添加", JOptionPane.INFORMATION_MESSAGE);
				resetButton.doClick();
			} catch (Exception er) {
				er.printStackTrace();
			}
		}
	}

	private final class ResetButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO 自动生成的方法存根
			sjlxBox.setSelectedIndex(0);
			jt_zdc.setText("");
			jt_zdz.setText("");
		}
	}

	public void initTimeField() {
		// 启动录入时间进程
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO 自动生成的方法存根
				try {
					while (true) {
						lrsjDate = new Date();
						jt_date.setText(lrsjDateFormat.getDateTimeInstance()
								.format(lrsjDate));
						Thread.sleep(100);
					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}).start();
	}
}
