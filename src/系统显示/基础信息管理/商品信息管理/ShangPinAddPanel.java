package ϵͳ��ʾ.������Ϣ����.��Ʒ��Ϣ����;

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
import javax.swing.JTextField;

import ���ݿ�ģ��.TbSpinfo;
import ϵͳ��ʾ.���ݿ���Ϣ.DaoRu;
import ϵͳ��ʾ.����.Item;

public class ShangPinAddPanel extends JPanel{
	private JTextField beiZhu;//��ע
	private JTextField piHao;  //��ISBN
	private JTextField chanDi;  //������
	private JTextField jianCheng; //����
	private JTextField quanCheng;//����
	private JButton resetButton;

	private JComboBox nianFen;//�������
	public ShangPinAddPanel() {
		setLayout(new GridBagLayout());
		setBounds(10, 10, 550, 400);
		setupComponent(new JLabel("ͼ��ISBN��"), 0, 0, 1, 1, false);
		piHao = new JTextField();
		setupComponent(piHao, 1, 0, 3, 1, true);
		setupComponent(new JLabel("ͼ�����ƣ�"), 0, 1, 1, 1, false);
		quanCheng = new JTextField();
		setupComponent(quanCheng, 1, 1, 3, 10, true);
		setupComponent(new JLabel("���ߣ�"), 0, 2, 1, 1, false);
		jianCheng = new JTextField();
		setupComponent(jianCheng, 1, 2, 3, 300, true);
		setupComponent(new JLabel("�����磺"), 0, 3, 1, 1, false);
		chanDi = new JTextField();
		setupComponent(chanDi, 1, 3, 3, 130, true);
		setupComponent(new JLabel("������ݣ�"), 0, 4, 1, 1, false);
		
		String[] arry = new String[70];
        for (int i = 1949; i < 2019; i++) {
            arry[i-1949] = i + "";
        }
		nianFen = new JComboBox(arry);;
		setupComponent(nianFen, 1, 4, 1, 50, true);
		
		
		setupComponent(new JLabel("��ע��"), 0, 5, 1, 1, false);
		beiZhu = new JTextField();
		setupComponent(beiZhu, 1, 5, 3, 1, true);
		final JButton tjButton = new JButton();
		tjButton.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				if (chanDi.getText().equals("")
						|| jianCheng.getText().equals("")
						|| piHao.getText().equals("")
						|| quanCheng.getText().equals("")) {
					JOptionPane.showMessageDialog(ShangPinAddPanel.this,
							"�����δ��д����Ϣ��", "��Ʒ���", JOptionPane.ERROR_MESSAGE);
					return;
				}
				ResultSet haveUser = DaoRu
						.query("select * from bookinfo where isbn='"
								+ piHao.getText().trim() + "'");
				try {
					if (haveUser.next()) {
						System.out.println("error");
						JOptionPane.showMessageDialog(
								ShangPinAddPanel.this, "��Ʒ��Ϣ���ʧ�ܣ�������ͬISBN��",
								"�ͻ������Ϣ", JOptionPane.INFORMATION_MESSAGE);
						return;
					}
				} catch (Exception er) {
					er.printStackTrace();
				}
				TbSpinfo spInfo = new TbSpinfo();
				
				spInfo.setCd(chanDi.getText().trim());
				spInfo.setJc(jianCheng.getText().trim());
				spInfo.setBz(beiZhu.getText().trim());
				spInfo.setPh(piHao.getText().trim());
				spInfo.setSpname(quanCheng.getText().trim());
				spInfo.setNf((nianFen.getSelectedItem()+"").trim());
				DaoRu.addSp(spInfo);
				JOptionPane.showMessageDialog(ShangPinAddPanel.this,
						"������Ϣ�Ѿ��ɹ����", "������", JOptionPane.INFORMATION_MESSAGE);
				resetButton.doClick();
			}
		});
		tjButton.setText("���");
		setupComponent(tjButton, 1, 8, 1, 1, false);
		/*
		final GridBagConstraints gridBagConstraints_20 = new GridBagConstraints();
		gridBagConstraints_20.weighty = 1.0;
		gridBagConstraints_20.insets = new Insets(0, 65, 0, 15);
		gridBagConstraints_20.gridy = 8;
		
		gridBagConstraints_20.gridx = 1;
		*/
		// ����ť���¼�������
		resetButton = new JButton();
		setupComponent(tjButton, 3, 8, 1, 1, false);
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				chanDi.setText("");
				jianCheng.setText("");
				beiZhu.setText("");
				piHao.setText("");
				quanCheng.setText("");
				nianFen.setSelectedIndex(0);
			}
		});
		resetButton.setText("����");
	}
	// �������λ�ò���ӵ�������
	private void setupComponent(JComponent component, int gridx, int gridy,
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
		add(component, gridBagConstrains);
	}
}
