package ϵͳ��ʾ.������Ϣ����;

import java.awt.Color;

import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;
import javax.swing.border.BevelBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import ϵͳ��ʾ.������Ϣ����.�ͻ���Ϣ����.KeHuAddPanel;
import ϵͳ��ʾ.������Ϣ����.�ͻ���Ϣ����.KeHuAlterPanel;

public class KeHuXinX extends JInternalFrame{
	public KeHuXinX(){
		setTitle("�ͻ���Ϣ����");
		setIconifiable(true);
		setClosable(true);
		JTabbedPane tabPane = new JTabbedPane();
		tabPane.setFocusable(false);				//����ѡ����㲻��ȡ
		tabPane.setBackground(new Color(211, 230, 192));	//����ѡ�������ɫ
		tabPane.setBorder(new BevelBorder(BevelBorder.RAISED));	//����ѡ��߿�
		final KeHuAddPanel khtjPanel = new KeHuAddPanel();
		final KeHuAlterPanel khxgPanel = new KeHuAlterPanel();
		tabPane.addTab("�ͻ���Ϣ���", null, khtjPanel, "�ͻ����");
		tabPane.addTab("�ͻ���Ϣ�޸���ɾ��", null, khxgPanel, "�޸���ɾ��");
		getContentPane().add(tabPane);
		tabPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				//�ͻ��޸�
				khxgPanel.initComboBox();
			}
		});
		addInternalFrameListener(new InternalFrameAdapter() {

			@Override
			public void internalFrameActivated(InternalFrameEvent e) {
				// TODO �Զ����ɵķ������
				super.internalFrameActivated(e);
			}

		});
		pack();
		setVisible(true);
	}
}
