package ϵͳ��ʾ.������Ϣ����.��Ӧ����Ϣ����;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import ���ݿ�ģ��.TbGysinfo;
import ϵͳ��ʾ.���ݿ���Ϣ.DaoRu;
import ϵͳ��ʾ.����.Time;

public class GysAddPanel extends JPanel{
	private JTextField gongYingDaiMa;		//��Ӧ��id
	private JTextField EMailF;    //����
	private JTextField yinHangF;  //�����˺�
	private JTextField lianXiRenDianHuaF;   //��ϵ�˵绰
	private JTextField lianXiRenF;  //��ϵ��
	private JTextField dianHuaF;  //��˾�绰
	private JTextField diZhiF; //��˾��ַ
	private JTextField quanChengF;   //��˾����
	private JTextArea beiZhu;			//��ע
	
	private JButton resetButton;
	private String code = null;			//��Ӧ�̴�������

	
	private JScrollPane bz;				//��ע������
	
	public GysAddPanel() {
		setLayout(new GridBagLayout());
		setBounds(10, 10, 510, 302);
		
		final JLabel khDM = new JLabel("��Ӧ��ID��");
		gongYingDaiMa = new JTextField();		//�ͻ�����
		code = new Time().getTime();
		gongYingDaiMa.setText(code);
		gongYingDaiMa.setEditable(false);
		setupComponet(khDM, 0, 0, 1, 0, false);
		setupComponet(gongYingDaiMa, 1, 0, 3, 300, true);
		
		setupComponet(new JLabel("��Ӧ�����ƣ�"), 0, 1, 1, 0, false);

		quanChengF = new JTextField();
		setupComponet(quanChengF, 1, 1, 1, 160, true);
		
		setupComponet(new JLabel("��ַ��"), 2, 1, 1, 1, false);
		diZhiF = new JTextField();
		setupComponet(diZhiF, 3, 1, 3, 160, true);

		setupComponet(new JLabel("�绰��"), 0, 2, 1, 1, false);
		dianHuaF = new JTextField();
		setupComponet(dianHuaF, 1, 2, 1, 160, true);

		setupComponet(new JLabel("�����˺ţ�"), 2, 2, 1, 1, false);
		yinHangF = new JTextField();
		setupComponet(yinHangF, 3, 2, 1, 160, true);
		
		setupComponet(new JLabel("��ϵ�ˣ�"), 0, 3, 1, 1, false);
		lianXiRenF = new JTextField();
		setupComponet(lianXiRenF, 1, 3, 1, 160, true);

		setupComponet(new JLabel("��ϵ�˵绰��"), 2, 3, 1, 1, false);
		lianXiRenDianHuaF = new JTextField();
		setupComponet(lianXiRenDianHuaF, 3, 3, 1, 160, true);

		setupComponet(new JLabel("���䣺"), 0, 4, 1, 1, false);
		EMailF = new JTextField();
		setupComponet(EMailF, 1, 4, 1, 160, true);

		final JLabel beiZ = new JLabel("��ע��");	
		beiZhu = new JTextArea();			//��ע
		bz = new JScrollPane(beiZhu);

		setupComponet(beiZ, 0, 5, 1, 0, false);
		setupComponet(bz, 1, 5, 3, 300, true);
		
		final JButton tjButton = new JButton();
		tjButton.setText("���");
		tjButton.addActionListener(new TjActionListener());
		setupComponet(tjButton, 2, 9, 1, 0, false);

		resetButton = new JButton();
		setupComponet(resetButton, 3, 9, 1, 0, false);
		resetButton.addActionListener(new ResetActionListener());
		resetButton.setText("����");
	}
	// �������λ�ò���ӵ�������
	private void setupComponet(JComponent component, int gridx, int gridy,
			int gridwidth, int ipadx, boolean fill) {
		final GridBagConstraints gridBagConstrains = new GridBagConstraints();
		gridBagConstrains.gridx = gridx;
		gridBagConstrains.gridy = gridy;
		gridBagConstrains.insets = new Insets(5, 1, 3, 1);
		if (gridwidth > 1)
			gridBagConstrains.gridwidth = gridwidth;
		if (ipadx > 0)
			gridBagConstrains.ipadx = ipadx;
		if (fill)
			gridBagConstrains.fill = GridBagConstraints.HORIZONTAL;
		if(component==bz){
			beiZhu.setLineWrap(true);
			gridBagConstrains.gridheight = 3;
			gridBagConstrains.ipadx = ipadx;
			gridBagConstrains.ipady = 50;
		}
		add(component, gridBagConstrains);
	}
	
	class ResetActionListener implements ActionListener {// ���ť���¼�������
		public void actionPerformed(final ActionEvent e) {
			diZhiF.setText("");
			lianXiRenF.setText("");
			lianXiRenDianHuaF.setText("");
			EMailF.setText("");
			quanChengF.setText("");
			dianHuaF.setText("");
			yinHangF.setText("");
			beiZhu.setText("");
			code = new Time().getTime();
			gongYingDaiMa.setText(code);
		}
	}
	class TjActionListener implements ActionListener {// ��Ӱ�ť���¼�������
		public void actionPerformed(final ActionEvent e) {
			if (diZhiF.getText().equals("") || quanChengF.getText().equals("") //��֤�û�����
					|| yinHangF.getText().equals("")
					|| diZhiF.getText().equals("")
					|| lianXiRenF.getText().equals("")
					|| lianXiRenDianHuaF.getText().equals("")
					|| EMailF.getText().equals("")
					|| dianHuaF.getText().equals("")) {
				JOptionPane.showMessageDialog(GysAddPanel.this, "����дȫ����Ϣ");
				return;
			}
			try {                                                            //��֤�Ƿ����ͬ����Ӧ��
				ResultSet haveUser = DaoRu
						.query("select * from supplyinfo where supplyname='"
								+ quanChengF.getText().trim() + "'");
				if (haveUser.next()) {
					JOptionPane.showMessageDialog(GysAddPanel.this,
							"��Ӧ����Ϣ���ʧ�ܣ�����ͬ����Ӧ��", "��Ӧ�������Ϣ",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				TbGysinfo gysInfo = new TbGysinfo();                            //������Ӧ��ʵ�����
				gysInfo.setId(gongYingDaiMa.getText().trim());                                              //��ʼ����Ӧ�̶���
				gysInfo.setAddress(diZhiF.getText().trim());
				gysInfo.setYh(yinHangF.getText().trim());
				gysInfo.setName(quanChengF.getText().trim());
				gysInfo.setLian(lianXiRenF.getText().trim());
				gysInfo.setLtel(lianXiRenDianHuaF.getText().trim());
				gysInfo.setMail(EMailF.getText().trim());
				gysInfo.setTel(dianHuaF.getText().trim());
				gysInfo.setRemark(beiZhu.getText().trim());
				DaoRu.addGys(gysInfo);                                           //����addGys()�����洢��Ӧ��
				JOptionPane.showMessageDialog(GysAddPanel.this, "�ѳɹ���ӹ�Ӧ��",
						"��Ӧ�������Ϣ", JOptionPane.INFORMATION_MESSAGE);
				resetButton.doClick();                                         //����"����"��ť�ĵ�������
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
}
