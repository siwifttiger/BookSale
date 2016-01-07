package ϵͳ��ʾ.������Ϣ����.�ͻ���Ϣ����;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import �ؼ��ּ�����.InputKeyListener;
import ���ݿ�ģ��.TbDictionary;
import ���ݿ�ģ��.TbKhXinX;
import ϵͳ��ʾ.���ݿ���Ϣ.DaoRu;
import ϵͳ��ʾ.����.Item;
import ϵͳ��ʾ.����.Time;

public class KeHuAddPanel extends JPanel{
	private JTextField keHuDaiMa;		//�ͻ�����
	private JTextField gongSiMingCheng;	//��˾����
	private JTextField diZhi;			//��˾��ַ
	private JTextField dianHua;			//��˾�绰
	private JTextField lianXiRen;		//��ϵ��
	private JTextField lianXiDianHua;	//��ϵ�绰
	private JTextField touDiDiZhi;		//Ͷ�ݵ�ַ
	private JTextField yinHangZhangHao;	//�����˺�
	private JTextField EMail;			//E-Mail
	private JTextArea beiZhu;			//��ע
	private JScrollPane bz;				//��ע������
	private JButton resetButton;
	private String code = null;			//�ͻ���������

	public KeHuAddPanel(){
		super();
		//JPanel ��������
		setBounds(10, 10, 300, 350);	//���ò���λ��
		setLayout(new GridBagLayout());	//����Ϊ���񲼾ֹ�����
		setVisible(true);				//������ͼ�ɼ�
		//������̬��ǩ���������ı��������޸�
		final JLabel khDM = new JLabel("�ͻ�ID��");			//�ͻ�����
		final JLabel gsName = new JLabel("��˾���ƣ�");		//��˾����
		final JLabel gsSite = new JLabel("��˾��ַ��");		//��˾��ַ
		final JLabel phone = new JLabel("��˾�绰��");			//��˾�绰
		final JLabel linkMan = new JLabel("��ϵ�ˣ�");		//��ϵ��
		final JLabel linkPhone = new JLabel("��ϵ�绰��");		//��ϵ�绰		//�ͻ�����
		final JLabel touDDZ = new JLabel("Ͷ�ݵ�ַ��");		//Ͷ�ݵ�ַ

		final JLabel yhZH = new JLabel("�����˺ţ�");			//�����˺�
		final JLabel email = new JLabel("E-Mail��");			//��ҳ
		final JLabel beiZ = new JLabel("��ע��");				//��ע
		//�����ı����Լ��������
		keHuDaiMa = new JTextField();		//�ͻ�����
		code = new Time().getTime();
		keHuDaiMa.setText(code);
		keHuDaiMa.setEditable(false);
		gongSiMingCheng = new JTextField();	//��˾����
		diZhi = new JTextField();			//��˾��ַ

		dianHua = new JTextField();			//��˾�绰
		dianHua.addKeyListener(new InputKeyListener());

		lianXiRen = new JTextField();		//��ϵ��

		lianXiDianHua = new JTextField();	//��ϵ�绰
		lianXiDianHua.addKeyListener(new InputKeyListener());

		touDiDiZhi = new JTextField();		//Ͷ�ݵ�ַ
		yinHangZhangHao = new JTextField();	//�����ʺ�
		EMail = new JTextField();			//E-Mail
		beiZhu = new JTextArea();			//��ע
		bz = new JScrollPane(beiZhu);
		//������������̬��ť
		final JButton saveButton = new JButton("����");	//���水ť
		saveButton.addActionListener(new SaveButtonActionListener());	//��Ӽ�����
		resetButton = new JButton("����");				//���ð�ť
		resetButton.addActionListener(new ChongZheButtonActionListener());	//��Ӽ�����
		//��λ ��ǩ���ı��� λ��
		//��һ��
		setupZuJian(khDM, 0, 0, 1, 0, false);
		setupZuJian(keHuDaiMa, 1, 0, 3, 300, true);
		//��2��
		setupZuJian(gsName, 0, 1, 1, 0, false);
		setupZuJian(gongSiMingCheng, 1, 1, 1, 150, true);
		setupZuJian(gsSite, 2, 1, 1, 0, false);
		setupZuJian(diZhi, 3, 1, 1, 150, true);
		//��3��
		setupZuJian(phone, 0, 2, 1, 0, false);
		setupZuJian(dianHua, 1, 2, 1, 150, true);
		setupZuJian(yhZH, 2, 2, 1, 0, false);
		setupZuJian(yinHangZhangHao, 3, 2, 1, 150, true);
		//��4��
		setupZuJian(linkMan, 0, 3, 1, 0, false);
		setupZuJian(lianXiRen, 1, 3, 1, 150, true);
		setupZuJian(linkPhone, 2, 3, 1, 0, false);
		setupZuJian(lianXiDianHua, 3, 3, 1, 150, true);
		setupZuJian(touDDZ, 0, 4, 1, 0, false);
		setupZuJian(touDiDiZhi, 1, 4, 1, 150, true);
		setupZuJian(email, 2, 4, 1, 0, false);
		setupZuJian(EMail, 3, 4, 1, 150, true);
		//��6��
		setupZuJian(beiZ, 0, 5, 1, 0, false);
		setupZuJian(bz, 1, 5, 3, 300, true);
		//��λ ��ť λ��
		setupZuJian(saveButton, 1, 9, 1, 0, false);
		setupZuJian(resetButton, 3, 9, 1, 0, false);

	}
	// �������λ�ò���ӵ�������
	private void setupZuJian(JComponent component, int gridx, int gridy,
			int gridwidth, int ipadx, boolean fill) {
		final GridBagConstraints gridBagConstrains = new GridBagConstraints();
		gridBagConstrains.gridx = gridx;
		gridBagConstrains.gridy = gridy;
		gridBagConstrains.insets = new Insets(3, 1, 2, 1);
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

	private final class SaveButtonActionListener implements ActionListener{

		@Override
		public void actionPerformed(final ActionEvent e) {
			// TODO �Զ����ɵķ������
			if(gongSiMingCheng.getText().equals("")
					||diZhi.getText().equals("")
					||dianHua.getText().equals("")
					||lianXiRen.getText().equals("")
					||lianXiDianHua.getText().equals("")
					||touDiDiZhi.getText().equals("")
					||yinHangZhangHao.getText().equals("")){
				JOptionPane.showMessageDialog(null, "����дȫ����Ϣ");
				return;
			}
			ResultSet haveUser = DaoRu
					.query("select * from customerinfo where customername='"
							+ gongSiMingCheng.getText().trim() + "'");
			try {
				if (haveUser.next()){
					System.out.println("error");
					JOptionPane.showMessageDialog(KeHuAddPanel.this,
							"�ͻ���Ϣ���ʧ�ܣ�����ͬ���ͻ�", "�ͻ������Ϣ",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
			} catch (Exception er) {
				er.printStackTrace();
			}
			TbKhXinX khinfo = new TbKhXinX();
			khinfo.setId(keHuDaiMa.getText().trim());
			khinfo.setName(gongSiMingCheng.getText().trim());
			khinfo.setCompanySite(diZhi.getText().trim());
			khinfo.setCompanyPhone(dianHua.getText().trim());
			khinfo.setLinkman(lianXiRen.getText().trim());
			khinfo.setLinkPhone(lianXiDianHua.getText().trim());
			khinfo.setAddress(touDiDiZhi.getText().trim());
			khinfo.setBankAccout(yinHangZhangHao.getText().trim());
			khinfo.setEmail(EMail.getText().trim());
			khinfo.setRemark(beiZhu.getText().trim());
			DaoRu.addKeHu(khinfo);
			JOptionPane.showMessageDialog(KeHuAddPanel.this, "�ѳɹ���ӿͻ�",
					"�ͻ������Ϣ", JOptionPane.INFORMATION_MESSAGE);
			resetButton.doClick();
		}
	}
	// ���ð�ť���¼�������
	private class ChongZheButtonActionListener implements ActionListener {
		public void actionPerformed(final ActionEvent e) {
			code = new Time().getTime();
			keHuDaiMa.setText(code);
			gongSiMingCheng.setText("");
			diZhi.setText("");
			dianHua.setText("");
			lianXiRen.setText("");
			lianXiDianHua.setText("");
			touDiDiZhi.setText("");
			yinHangZhangHao.setText("");
			EMail.setText("");
			beiZhu.setText("");
		}
	}
	
}
