package ϵͳ��ʾ.������Ϣ����.�ͻ���Ϣ����;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Dictionary;
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
import ���ݿ�ģ��.TbGysinfo;
import ���ݿ�ģ��.TbKhXinX;
import ϵͳ��ʾ.���ݿ���Ϣ.DaoRu;
import ϵͳ��ʾ.����.Item;

public class KeHuAlterPanel extends JPanel {
	private JTextField keHuDaiMa; // �ͻ�����
	private JTextField gongSiMingCheng; // ��˾����
	private JTextField diZhi; // ��˾��ַ
	private JTextField dianHua; // ��˾�绰
	private JTextField lianXiRen; // ��ϵ��
	private JTextField lianXiDianHua; // ��ϵ�绰
	private JTextField touDiDiZhi; // Ͷ�ݵ�ַ
	private JTextField yinHangZhangHao; // �����˺�
	private JTextField EMail; // E-Mail
	private JTextArea beiZhu; // ��ע
	private JScrollPane bz; // ��ע������
	private JButton modifyButton;
	private JButton delButton;
	private JComboBox kehu;

	public KeHuAlterPanel() {
		// ���淽��
		setBounds(10, 10, 300, 350); // ����λ���Լ���С
		setLayout(new GridBagLayout()); // �������񲼾ֹ�����
		setVisible(true); // ������ͼ�ɼ�
		// ������̬��ǩ���������ı��������޸�
		final JLabel khDM = new JLabel("�ͻ�ID��"); // �ͻ�����
		final JLabel gsName = new JLabel("��˾���ƣ�"); // ��˾����
		final JLabel gsSite = new JLabel("��˾��ַ��"); // ��˾��ַ
		final JLabel phone = new JLabel("��˾�绰��"); // ��˾�绰
		final JLabel linkMan = new JLabel("��ϵ�ˣ�"); // ��ϵ��
		final JLabel linkPhone = new JLabel("��ϵ�绰��"); // ��ϵ�绰
		final JLabel touDDZ = new JLabel("Ͷ�ݵ�ַ��"); // Ͷ�ݵ�ַ
		final JLabel yhZH = new JLabel("�����˺ţ�"); // �����˺�
		final JLabel email = new JLabel("E-Mail��"); // E-Mail
		final JLabel beiZ = new JLabel("��ע��"); // ��ע
		final JLabel xzKH = new JLabel("ѡ��ͻ�"); // ѡ��ͻ�
		// �����ı����Լ��������
		keHuDaiMa = new JTextField(); // �ͻ�����
		gongSiMingCheng = new JTextField(); // ��˾����
		diZhi = new JTextField(); // ��˾��ַ
		dianHua = new JTextField(); // ��˾�绰
		dianHua.addKeyListener(new InputKeyListener());

		lianXiRen = new JTextField(); // ��ϵ��
		lianXiDianHua = new JTextField(); // ��ϵ�绰
		lianXiDianHua.addKeyListener(new InputKeyListener());

		touDiDiZhi = new JTextField(); // Ͷ�ݵ�ַ

		yinHangZhangHao = new JTextField(); // �����ʺ�
		EMail = new JTextField(); // E-Mail
		beiZhu = new JTextArea(); // ��ע
		bz = new JScrollPane(beiZhu);
		// ���� ��ť
		modifyButton = new JButton("�޸�");
		delButton = new JButton("ɾ��");
		// ���������� �м�����
		JPanel panel = new JPanel();
		// �Ѱ�ť��ӵ��м�������
		panel.add(modifyButton);
		panel.add(delButton);
		// ����ɾ����ť�ĵ����¼�
		delButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Item item = (Item) kehu.getSelectedItem();
				if (item == null || !(item instanceof Item))
					return;
				int confirm = JOptionPane.showConfirmDialog(
						KeHuAlterPanel.this, "ȷ��ɾ���ͻ���Ϣ��");
				if (confirm == JOptionPane.YES_OPTION) {
					int rs = DaoRu.delete("delete from customerinfo where customerid='"
							+ item.getId() + "'");
					if (rs > 0) {
						JOptionPane.showMessageDialog(KeHuAlterPanel.this,
								"�ͻ���" + item.getName() + "��ɾ���ɹ�");
						kehu.removeItem(item);
					}
				}
			}
		});
		// �����޸İ�ť�ĵ����¼�
		modifyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
				if (DaoRu.updateKeHu(khinfo) == 1)
					JOptionPane.showMessageDialog(KeHuAlterPanel.this, "�޸����");
				else
					JOptionPane.showMessageDialog(KeHuAlterPanel.this, "�޸�ʧ��");
			}
		});
		// ���� �����˵�
		kehu = new JComboBox();
		initComboBox();// ��ʼ������ѡ���

		// ����ͻ���Ϣ������ѡ����ѡ���¼�
		kehu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doKeHuSelectAction();
			}
		});

		// ��������
		gongSiMingCheng.setEditable(false); // �����ı����ɱ༭
		keHuDaiMa.setEditable(false); // �����ı����ɱ༭
		kehu.setPreferredSize(new Dimension(230, 21));
		// ��λ ��ǩ���ı��� λ��
		// ��һ��
		setupZuJian(khDM, 0, 0, 1, 0, false);
		setupZuJian(keHuDaiMa, 1, 0, 3, 300, true);
		// �ڶ���
		// ������
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
		// ��12��
		setupZuJian(xzKH, 0, 9, 1, 0, false);
		setupZuJian(kehu, 1, 9, 2, 0, true);
		// ��λ �м�������2����ť�� λ��
		setupZuJian(panel, 3, 9, 1, 0, false);
		// ��λ�ͻ���Ϣ������ѡ���

	}

	// �������λ�ò���ӵ�������
	private void setupZuJian(JComponent component, int gridx, int gridy,
			int gridwidth, int ipadx, boolean fill) {
		final GridBagConstraints gridBagConstrains = new GridBagConstraints();
		gridBagConstrains.gridx = gridx;
		gridBagConstrains.gridy = gridy;
		if (gridwidth > 1)
			gridBagConstrains.gridwidth = gridwidth;
		if (ipadx > 0)
			gridBagConstrains.ipadx = ipadx;
		gridBagConstrains.insets = new Insets(3, 1, 2, 1);
		if (fill)
			gridBagConstrains.fill = GridBagConstraints.HORIZONTAL;
		if (component == bz) {
			beiZhu.setLineWrap(true);
			gridBagConstrains.gridheight = 3;
			gridBagConstrains.ipadx = ipadx;
			gridBagConstrains.ipady = 50;

		}
		add(component, gridBagConstrains);
	}

	// ��ʼ���ͻ�����ѡ���
	public void initComboBox() {
		List khInfo = DaoRu.getKhInfos();
		List<Item> items = new ArrayList<Item>();
		kehu.removeAllItems();
		for (Iterator iter = khInfo.iterator(); iter.hasNext();) {
			List element = (List) iter.next();
			Item item = new Item();
			item.setId(element.get(0).toString().trim());
			item.setName(element.get(1).toString().trim());
			if (items.contains(item))
				continue;
			items.add(item);
			kehu.addItem(item);
		}
		doKeHuSelectAction();
	}

	private void doKeHuSelectAction() {
		Item selectedItem;
		if (!(kehu.getSelectedItem() instanceof Item)) {
			return;
		}
		selectedItem = (Item) kehu.getSelectedItem();
		TbKhXinX khInfo = DaoRu.getKhInfo(selectedItem);
		keHuDaiMa.setText(khInfo.getId());
		gongSiMingCheng.setText(khInfo.getName());
		diZhi.setText(khInfo.getCompanySite());
		dianHua.setText(khInfo.getCompanyPhone());
		lianXiRen.setText(khInfo.getLinkman());
		lianXiDianHua.setText(khInfo.getLinkPhone());
		touDiDiZhi.setText(khInfo.getAddress());
		yinHangZhangHao.setText(khInfo.getBankAccout());
		EMail.setText(khInfo.getEmail());
		beiZhu.setText(khInfo.getRemark());
	}


}
