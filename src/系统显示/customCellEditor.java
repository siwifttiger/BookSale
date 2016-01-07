package 系统显示;
import java.awt.Component;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.EventObject;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.TableCellEditor;

import 数据库模型.TbSpinfo;
import 系统显示.数据库信息.DaoRu;
public class customCellEditor extends JComboBox implements TableCellEditor {
	private CellEditorListener list;
	private String gysName;
	private ChangeEvent ce = new ChangeEvent(this);
	public customCellEditor() {
		super();
	}
	public Object getCellEditorValue() {
		return getSelectedItem();
	}
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		ResultSet set = DaoRu.query("select * from tb_spinfo where gysName='"
				+gysName+"'");
		DefaultComboBoxModel dfcbm = (DefaultComboBoxModel) getModel();
		dfcbm.removeAllElements();
		dfcbm.addElement(new TbSpinfo());
		try {
			while (set.next()) {
				TbSpinfo spinfo=new TbSpinfo();
				spinfo.setSpname(set.getString("spname").trim());
				spinfo.setCd(set.getString("cd").trim());
				spinfo.setJc(set.getString("jc").trim());
				spinfo.setBz(set.getString("bz").trim());
				spinfo.setPh(set.getString("ph").trim());
				dfcbm.addElement(spinfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return this;
	}
	public void addCellEditorListener(CellEditorListener arg0) {
		list = arg0;
	}
	public void cancelCellEditing() {
		list.editingCanceled(ce);
	}
	public boolean isCellEditable(EventObject arg0) {
		return true;
	}
	public void removeCellEditorListener(CellEditorListener arg0) {
	}
	public boolean shouldSelectCell(EventObject arg0) {
		return true;
	}
	public boolean stopCellEditing() {
		list.editingStopped(ce);
		return true;
	}
	public String getGysName() {
		return gysName;
	}
	public void setGysName(String gysName) {
		this.gysName = gysName;
	}
}
