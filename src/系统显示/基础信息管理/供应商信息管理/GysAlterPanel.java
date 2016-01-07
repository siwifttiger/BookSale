package ϵͳ��ʾ.������Ϣ����.��Ӧ����Ϣ����;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import ���ݿ�ģ��.TbGysinfo;
import ϵͳ��ʾ.���ݿ���Ϣ.DaoRu;
import ϵͳ��ʾ.����.Item;
import ϵͳ��ʾ.����.Time;

public class GysAlterPanel extends JPanel{
	private JTextField gongYingDaiMa;		//��Ӧ��id
	private JTextField EMailF;
	private JTextField yinHangF;
	private JTextField lianXiRenDianHuaF;
	private JTextField lianXiRenF;
	private JTextField dianHuaF;
	private JTextField diZhiF;
	private JTextField quanChengF;
	private JComboBox gys;

	private JTextArea beiZhu;			//��ע
	private JScrollPane bz;				//��ע������
	public GysAlterPanel() {
		setLayout(new GridBagLayout());
		setBounds(10, 10, 510, 302);


		final JLabel khDM = new JLabel("��Ӧ��ID��");
		gongYingDaiMa = new JTextField();		//�ͻ�����
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
		

		setupComponet(new JLabel("ѡ��Ӧ��"), 0, 9, 1, 0, false);
		gys = new JComboBox();
		gys.setPreferredSize(new Dimension(230, 21));
		initComboBox();// ��ʼ������ѡ���
		// ����Ӧ����Ϣ������ѡ����ѡ���¼�
		gys.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doGysSelectAction();
			}
		});
		// ��λ��Ӧ����Ϣ������ѡ���
		setupComponet(gys, 1, 9, 2, 0, true);
		JButton modifyButton = new JButton("�޸�");
		JButton delButton = new JButton("ɾ��");
		JPanel panel = new JPanel();
		panel.add(modifyButton);
		panel.add(delButton);
		// ��λ��ť
		setupComponet(panel, 3, 9, 1, 0, false);
		// ����ɾ����ť�ĵ����¼�
		delButton.addActionListener(new DelActionListener());
		// �����޸İ�ť�ĵ����¼�
		modifyButton.addActionListener(new ModifyActionListener());
	}
	// �������λ�ò���ӵ�������
	private void setupComponet(JComponent component, int gridx, int gridy,
			int gridwidth, int ipadx, boolean fill) {
		final GridBagConstraints gridBagConstrains = new GridBagConstraints();
		gridBagConstrains.gridx = gridx;
		gridBagConstrains.gridy = gridy;
		if (gridwidth > 1)
			gridBagConstrains.gridwidth = gridwidth;
		if (ipadx > 0)
			gridBagConstrains.ipadx = ipadx;
		gridBagConstrains.insets = new Insets(5, 1, 3, 1);
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
	// ��ʼ����Ӧ������ѡ���
	public void initComboBox() {
		List gysInfo = DaoRu.getGysInfos();                          //����getGysInfos()������ȡ��Ӧ���б�
		List<Item> items = new ArrayList<Item>();                  //����Item�б�
		gys.removeAllItems();                                      //��������б����ԭ�е�ѡ��
		for (Iterator iter = gysInfo.iterator(); iter.hasNext();) {
			List element = (List) iter.next();
			Item item = new Item();                                //��װ��Ӧ����Ϣ
			item.setId(element.get(0).toString().trim());
			item.setName(element.get(1).toString().trim());
			if (items.contains(item))                             //���Items�б��а����ù�Ӧ�̵ķ�װ����
				continue;                                         //��������ѭ��
			items.add(item);                                      //������Ӹö��������б����
			gys.addItem(item);
		}
		doGysSelectAction();
	}
	// ����Ӧ��ѡ���¼������ڸ���ѡ��Ĺ�Ӧ�����ƣ��ѹ�Ӧ�̵�������Ϣ��䵽��Ӧ���ı����С�
	private void doGysSelectAction() {
		Item selectedItem;
		if (!(gys.getSelectedItem() instanceof Item)) {
			return;
		}
		selectedItem = (Item) gys.getSelectedItem();      //��ȡItem����
		TbGysinfo gysInfo = DaoRu.getGysInfo(selectedItem); //ͨ��Item�������getGysInfo()������ȡ��Ӧ����Ϣ
		quanChengF.setText(gysInfo.getName());            //��乩Ӧ����Ϣ���ı�����
		diZhiF.setText(gysInfo.getAddress());
		dianHuaF.setText(gysInfo.getTel());
		lianXiRenF.setText(gysInfo.getLian());
		lianXiRenDianHuaF.setText(gysInfo.getLtel());
		EMailF.setText(gysInfo.getMail());
		yinHangF.setText(gysInfo.getYh());
		beiZhu.setText(gysInfo.getRemark());
		gongYingDaiMa.setText(gysInfo.getId());
	}
	//�޸İ�ť���¼�������
	class ModifyActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			TbGysinfo gysInfo = new TbGysinfo();                  //������Ӧ��ʵ�����
			gysInfo.setId(gongYingDaiMa.getText().trim());                          //��ʼ����Ӧ��ʵ�����
			gysInfo.setAddress(diZhiF.getText().trim());
			gysInfo.setYh(yinHangF.getText().trim());
			gysInfo.setName(quanChengF.getText().trim());
			gysInfo.setLian(lianXiRenF.getText().trim());
			gysInfo.setLtel(lianXiRenDianHuaF.getText().trim());
			gysInfo.setMail(EMailF.getText().trim());
			gysInfo.setTel(dianHuaF.getText().trim());
			gysInfo.setRemark(beiZhu.getText().trim());
			if (DaoRu.updateGys(gysInfo) == 1)                              //���¹�Ӧ����Ϣ
				JOptionPane.showMessageDialog(GysAlterPanel.this, "�޸����");
			else
				JOptionPane.showMessageDialog(GysAlterPanel.this, "�޸�ʧ��");
		}
	}
	//ɾ����ť���¼�������
	class DelActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Item item = (Item) gys.getSelectedItem();               //��ȡ��ǰѡ��Ĺ�Ӧ��
			if (item == null || !(item instanceof Item))
				return;
			int confirm = JOptionPane.showConfirmDialog(            //����ȷ��ɾ���Ի���
					GysAlterPanel.this, "ȷ��ɾ����Ӧ����Ϣ��");
			if (confirm == JOptionPane.YES_OPTION) {               //���ȷ��ɾ��
				int rs = DaoRu.delete("delete supplyinfo where supplyid='" //����delete()����
						+ item.getId() + "'");
				if (rs > 0) {
					JOptionPane.showMessageDialog(GysAlterPanel.this,   //��ʾɾ���ɹ��Ի���
							"��Ӧ�̣�" + item.getName() + "��ɾ���ɹ�");
					gys.removeItem(item);
				} else {
					JOptionPane.showMessageDialog(GysAlterPanel.this,
							"�޷�ɾ���ͻ���" + item.getName() + "��");
				}
			}
		}
	}
}
