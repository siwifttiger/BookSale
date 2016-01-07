package ϵͳ��ʾ.������Ϣ����.��Ʒ��Ϣ����;

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
import javax.swing.JTextField;

import ���ݿ�ģ��.TbGysinfo;
import ���ݿ�ģ��.TbSpinfo;
import ϵͳ��ʾ.���ݿ���Ϣ.DaoRu;
import ϵͳ��ʾ.����.Item;

public class ShangPinAlterPanel extends JPanel {

	private JTextField beiZhu;//��ע
	private JTextField piHao;  //��ISBN
	private JTextField chanDi;  //������
	private JTextField jianCheng; //����
	private JTextField quanCheng;//����
	private JComboBox nianFen;//�������
	
	private JButton modifyButton;
	private JButton delButton;
	private JComboBox sp;

	public ShangPinAlterPanel() {
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
		setupComponent(new JLabel("ѡ����Ʒ"), 0, 6, 1, 0, false);
		sp = new JComboBox();
		sp.setPreferredSize(new Dimension(230, 21));
		// ����ͻ���Ϣ������ѡ����ѡ���¼�
		sp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doSpSelectAction();
			}
		});
		// ��λ��Ʒ��Ϣ������ѡ���
		setupComponent(sp, 1, 6, 2, 0, true);
		modifyButton = new JButton("�޸�");
		delButton = new JButton("ɾ��");
		JPanel panel = new JPanel();
		panel.add(modifyButton);
		panel.add(delButton);
		// ��λ��ť
		setupComponent(panel, 3, 7, 1, 0, false);
		// ����ɾ����ť�ĵ����¼�
		delButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Item item = (Item) sp.getSelectedItem();
				if (item == null || !(item instanceof Item))
					return;
				int confirm = JOptionPane.showConfirmDialog(
						ShangPinAlterPanel.this, "ȷ��ɾ����Ʒ��Ϣ��");
				if (confirm == JOptionPane.YES_OPTION) {
					int rs = DaoRu.delete("delete from bookinfo where isbn='"
							+ item.getId() + "'");
					if (rs > 0) {
						JOptionPane.showMessageDialog(ShangPinAlterPanel.this,
								"��Ʒ��" + item.getName() + "��ɾ���ɹ�");
						sp.removeItem(item);
					}
				}
			}
		});
		// �����޸İ�ť�ĵ����¼�
		modifyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Item item = (Item) sp.getSelectedItem();
				TbSpinfo spInfo = new TbSpinfo();
				spInfo.setCd(chanDi.getText().trim());
				spInfo.setJc(jianCheng.getText().trim());
				spInfo.setBz(beiZhu.getText().trim());
				spInfo.setPh(piHao.getText().trim());
				spInfo.setSpname(quanCheng.getText().trim());
				spInfo.setNf((nianFen.getSelectedItem()+"").trim());
				if (DaoRu.updateSp(spInfo) == 1)
					JOptionPane.showMessageDialog(ShangPinAlterPanel.this,
							"�޸����");
				else
					JOptionPane.showMessageDialog(ShangPinAlterPanel.this,
							"�޸�ʧ��");
			}
		});
	}

	// �������λ�ò���ӵ�������
	private void setupComponent(JComponent component, int gridx, int gridy,
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
		add(component, gridBagConstrains);
	}

	// ��ʼ����Ʒ����ѡ���
	public void initComboBox() {
		List khInfo = DaoRu.getSpInfos();
		List<Item> items = new ArrayList<Item>(); //��ʵ�����Ǽ�������ظ�
		sp.removeAllItems();
		for (Iterator iter = khInfo.iterator(); iter.hasNext();) {
			List element = (List) iter.next();
			Item item = new Item();
			item.setId(element.get(0).toString().trim());
			item.setName(element.get(1).toString().trim());
			if (items.contains(item))//��ʵ�����Ǽ�������ظ�
				continue;
			items.add(item);
			sp.addItem(item);
		}
		doSpSelectAction();
	}

	// ������Ʒѡ���¼�
	private void doSpSelectAction() {
		Item selectedItem;
		if (!(sp.getSelectedItem() instanceof Item)) {
			return;
		}
		selectedItem = (Item) sp.getSelectedItem();
		TbSpinfo spInfo = DaoRu.getSpInfo(selectedItem);
		if (!spInfo.getPh().isEmpty()) {
			quanCheng.setText(spInfo.getSpname());
			chanDi.setText(spInfo.getCd());
			jianCheng.setText(spInfo.getJc());
			beiZhu.setText(spInfo.getBz());
			piHao.setText(spInfo.getPh());
			nianFen.setSelectedIndex(Integer.parseInt(spInfo.getNf() ) - 1949);
		}
	}
}
