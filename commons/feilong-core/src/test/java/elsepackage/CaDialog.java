/**
 * Copyright (C) 2008 feilong (venusdrogon@163.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/**
 * This product currently only contains code developed by authors
 * of specific components, as identified by the source code files.
 *
 * Since product implements StAX API, it has dependencies to StAX API
 * classes.
 *
 * For additional credits (generally to people who reported problems)
 * see CREDITS file.
 */
package elsepackage;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.BevelBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

public class CaDialog extends javax.swing.JDialog{

	private int			row;

	private int			column;

	private JTextField	jtf;

	public String		day		= null;

	private JSpinner	yearSpinner;

	private JTable		dadeTable;

	private JScrollPane	jScrollPane1;

	private JComboBox	monthComboBox;

	Calendar			time	= Calendar.getInstance();					// 实例化一个日历类对象

	Object[][]			data	= new Object[6][7];

	String[]			head	= { "日", "一", "二", "三", "四", "五", "六" };

	public static void main(String[] args){
		JFrame frame = new JFrame();
		CaDialog inst = new CaDialog(frame);
		inst.setVisible(true);
	}

	public CaDialog(JFrame frame){
		super(frame);
		initGUI();
	}

	public CaDialog(JTextField jtf){
		super();
		this.jtf = jtf;
		initGUI();
	}

	private void initGUI(){
		try{
			{
				getContentPane().setLayout(null);
				this.setModal(true);
			}
			this.setSize(253, 202);
			{
				ComboBoxModel jComboBox1Model = new DefaultComboBoxModel(new String[] {
						"一月",
						"二月",
						"三月",
						"四月",
						"五月",
						"六月",
						"七月",
						"八月",
						"九月",
						"十月",
						"十一月",
						"十二月" });
				monthComboBox = new JComboBox();
				getContentPane().add(monthComboBox);
				monthComboBox.setModel(jComboBox1Model);
				monthComboBox.setSelectedIndex(Calendar.getInstance().get(Calendar.MONTH));
				monthComboBox.setBounds(10, 5, 105, 21);
				monthComboBox.setMaximumRowCount(4);
				monthComboBox.addActionListener(new ActionListener(){

					public void actionPerformed(ActionEvent evt){
						monthComboBoxActionPerformed(evt);
					}
				});
			}
			{
				SpinnerNumberModel yearSpinnerModel = new SpinnerNumberModel(1983, 1950, 2099, 1);
				yearSpinner = new JSpinner(yearSpinnerModel);
				yearSpinner.setValue(Calendar.getInstance().get(Calendar.YEAR));
				JSpinner.NumberEditor editor = new JSpinner.NumberEditor(yearSpinner, "###");
				yearSpinner.setEditor(editor);
				this.add(yearSpinner);
				getContentPane().add(yearSpinner);
				yearSpinner.setModel(yearSpinnerModel);
				yearSpinner.setBounds(127, 5, 105, 21);
				yearSpinner.addChangeListener(new ChangeListener(){

					public void stateChanged(ChangeEvent evt){
						yearSpinnerStateChanged(evt);
					}
				});
			}
			{
				jScrollPane1 = new JScrollPane();
				getContentPane().add(jScrollPane1);
				jScrollPane1.setBounds(9, 37, 224, 117);
				{
					this.in();
					TableModel jTable1Model = new DefaultTableModel(data, head){

						public boolean isCellEditable(int row,int column){
							return false;
						}
					};
					;
					dadeTable = new JTable(jTable1Model);
					dadeTable.getTableHeader().setReorderingAllowed(false);
					dadeTable.getTableHeader().setResizingAllowed(false);
					jScrollPane1.setViewportView(dadeTable);
					dadeTable.setCellSelectionEnabled(true);
					dadeTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					dadeTable.getTableHeader().setEnabled(false);
					dadeTable.setGridColor(Color.WHITE);
					dadeTable.setDefaultRenderer(Object.class, new TableCellRenderer(){

						//      @Override
						public Component getTableCellRendererComponent(JTable table,Object value,boolean isSelected,boolean hasFocus,int r,int c){
							// TODO Auto-generated method stub
							JLabel cell = new JLabel(value == null ? "" : String.valueOf(value), 0);
							cell.setOpaque(true);
							if (isSelected && dadeTable.getValueAt(r, c) != null){
								cell.setBackground(Color.GRAY);
							}
							if (r == row && c == column){
								cell.setBorder(BorderFactory.createEtchedBorder(BevelBorder.LOWERED));
							}
							return cell;
						}
					});
					dadeTable.addMouseListener(new MouseAdapter(){

						public void mouseClicked(MouseEvent evt){
							dateTableMouseClicked(evt);
						}
					});
				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	private void dateTableMouseClicked(MouseEvent evt){
		try{
			String date = String.valueOf(dadeTable.getValueAt(dadeTable.getSelectedRow(), dadeTable.getSelectedColumn()));
			if (date.equals("null")){
				JOptionPane.showMessageDialog(this, "请选择日期！");
				return;
			}
			if (evt.getClickCount() == 2){
				String year = yearSpinner.getValue().toString();
				int month = monthComboBox.getSelectedIndex() + 1;
				day = year + "-" + (String.valueOf(month).length() == 1 ? "0" + month : month) + "-" + (String.valueOf(date).length() == 1 ? "0" + date : date);
				this.jtf.setText(day);
				this.dispose();
			}
		}catch (Exception e){}
	}

	private void monthComboBoxActionPerformed(ActionEvent evt){
		time.set(Calendar.MONTH, monthComboBox.getSelectedIndex());
		data = (Object[][]) this.clear();
		this.in();
		TableModel dateTableModel = new DefaultTableModel(data, head){

			public boolean isCellEditable(int row,int column){
				return false;
			}
		};
		dadeTable.setModel(dateTableModel);
	}

	private void yearSpinnerStateChanged(ChangeEvent evt){
		time.set(Calendar.YEAR, Integer.parseInt(String.valueOf(yearSpinner.getValue())));
		data = (Object[][]) this.clear();
		this.in();
		TableModel dateTableModel = new DefaultTableModel(data, head){

			public boolean isCellEditable(int row,int column){
				return false;
			}
		};
		dadeTable.setModel(dateTableModel);
	}

	public void in(){
		int today = Calendar.getInstance().get(Calendar.DATE);
		time.set(Calendar.DATE, 1);// 设置日期为1号
		int w = time.get(Calendar.DAY_OF_WEEK) - 1;// 得一号星期几
		time.add(Calendar.MONTH, 1); // 月份+1
		time.add(Calendar.DAY_OF_MONTH, -1);// 退一天
		int day = time.get(Calendar.DATE);// 得到本月最后一天,本月天数
		int c = 0;
		for (int i = 1; i <= day; i++){
			if ((i + w) % 7 == 0){
				c = (int) (i + w) / 7 - 1;
			}else{
				c = (int) (i + w) / 7;
			}
			data[c][(w + i - 1) % 7] = i;
			if (i == today){
				row = c;
				column = (w + i - 1) % 7;
			}
		}
	}

	public Object clear(){
		return new Object[][] {
				{ null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null },
				{ null, null, null, null, null, null, null }, };
	}
}
