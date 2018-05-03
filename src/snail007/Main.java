package snail007;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import jodd.io.FileUtil;
import jodd.json.JsonParser;
import snail007.bean.ServiceBean;
import snail007.sdk.ProxyJNI;

public class Main extends PFrame {


	/**
	 * Creates new form Main
	 */
	public Main() {
		super();
		initComponents();
		this.setTitle(this.getTitle()+" (sdk v"+ProxyJNI.PLibrary.INSTANCE.Version()+")");
		Utils.init(this);
		new MySystemTray(this).setTray();
		
	}

	/**
	 * This method is called from within the constructor to initialize the
	 * form. WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                jPanel1 = new javax.swing.JPanel();
                jScrollPane1 = new javax.swing.JScrollPane();
                jTable1 = new javax.swing.JTable();
                jMenuBar1 = new javax.swing.JMenuBar();
                jMenu1 = new javax.swing.JMenu();
                jMenuItem1 = new javax.swing.JMenuItem();
                jSeparator1 = new javax.swing.JPopupMenu.Separator();
                jMenuItem2 = new javax.swing.JMenuItem();
                jSeparator2 = new javax.swing.JPopupMenu.Separator();
                jMenuItem3 = new javax.swing.JMenuItem();

                setTitle("GoProxy v4.8");

                jTable1.setModel(new javax.swing.table.DefaultTableModel(
                        new Object [][] {

                        },
                        new String [] {
                                "名称", "命令参数", "记录日志", "证书.crt", "证书.key", "状态", "自动启动", "日志", "操作", "编辑", ""
                        }
                ) {
                        Class[] types = new Class [] {
                                java.lang.String.class, java.lang.String.class, java.lang.Boolean.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
                        };
                        boolean[] canEdit = new boolean [] {
                                false, false, false, false, false, false, false, false, false, false, false
                        };

                        public Class getColumnClass(int columnIndex) {
                                return types [columnIndex];
                        }

                        public boolean isCellEditable(int rowIndex, int columnIndex) {
                                return canEdit [columnIndex];
                        }
                });
                jTable1.getTableHeader().setReorderingAllowed(false);
                jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                jTable1MouseClicked(evt);
                        }
                });
                jScrollPane1.setViewportView(jTable1);
                if (jTable1.getColumnModel().getColumnCount() > 0) {
                        jTable1.getColumnModel().getColumn(10).setMinWidth(0);
                        jTable1.getColumnModel().getColumn(10).setPreferredWidth(0);
                        jTable1.getColumnModel().getColumn(10).setMaxWidth(0);
                }

                javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                jPanel1.setLayout(jPanel1Layout);
                jPanel1Layout.setHorizontalGroup(
                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 801, Short.MAX_VALUE)
                                .addContainerGap())
                );
                jPanel1Layout.setVerticalGroup(
                        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
                                .addContainerGap())
                );

                jMenu1.setText("系统");

                jMenuItem1.setText("添加服务");
                jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jMenuItem1ActionPerformed(evt);
                        }
                });
                jMenu1.add(jMenuItem1);
                jMenu1.add(jSeparator1);

                jMenuItem2.setText("系统配置");
                jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jMenuItem2ActionPerformed(evt);
                        }
                });
                jMenu1.add(jMenuItem2);
                jMenu1.add(jSeparator2);

                jMenuItem3.setText("退出");
                jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jMenuItem3ActionPerformed(evt);
                        }
                });
                jMenu1.add(jMenuItem3);

                jMenuBar1.add(jMenu1);

                setJMenuBar(jMenuBar1);

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                );

                pack();
                setLocationRelativeTo(null);
        }// </editor-fold>//GEN-END:initComponents

        private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
		AddService as = new AddService(null, this, -1, -1);
		as.setVisible(true);
        }//GEN-LAST:event_jMenuItem1ActionPerformed

        private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
		int rowIndex = jTable1.rowAtPoint(evt.getPoint());
		int colIndex = jTable1.columnAtPoint(evt.getPoint());
		if (rowIndex >= 0 && colIndex >= 0) {
			DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
			String status = (String) model.getValueAt(rowIndex, 5);
			//编辑
			if (colIndex == 9) {
				if ("停止".equals(status)) {
					String json;
					try {
						File f = (File) model.getValueAt(rowIndex, 10);
						json = FileUtil.readString(f);
						ServiceBean bean = new JsonParser().parse(json, ServiceBean.class
						);
						bean.file = f;
						new AddService(bean, this, rowIndex, colIndex).setVisible(true);

					} catch (IOException ex) {
						Logger.getLogger(Main.class
							.getName()).log(Level.SEVERE, null, ex);
					}
				} else {
					JOptionPane.showMessageDialog(null, "请先停止服务!");
				}
			} else if (colIndex == 8) {
				//启动/停止
				try {
					String json;
					File f = (File) model.getValueAt(rowIndex, 10);
					json = FileUtil.readString(f);
					ServiceBean bean = new JsonParser().parse(json, ServiceBean.class
					);
					bean.file = f;
					if ("停止".equals(status)) {
						String err = Utils.start(bean);
						if (err.isEmpty()) {
							model.setValueAt("运行中", rowIndex, 5);
						} else {
							JOptionPane.showMessageDialog(null, "启动失败:\n" + err);
						}
					} else {
						Utils.stop(bean);
						model.setValueAt("停止", rowIndex, 5);

					}
				} catch (IOException ex) {
					Logger.getLogger(Main.class
						.getName()).log(Level.SEVERE, null, ex);
				}

			} else if (colIndex == 7) {
				//查看日志
				File f = (File) model.getValueAt(rowIndex, 10);
				File logFile = new File("logs/" + f.getName() + ".log");
				new ViewLog(logFile).setVisible(true);
			}

		}
        }//GEN-LAST:event_jTable1MouseClicked

        private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
		Utils.reloadConfig();
		new SysConfig().setVisible(true);
        }//GEN-LAST:event_jMenuItem2ActionPerformed

        private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
                System.exit(0);
        }//GEN-LAST:event_jMenuItem3ActionPerformed

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
		/* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;

				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(Main.class
				.getName()).log(java.util.logging.Level.SEVERE, null, ex);

		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(Main.class
				.getName()).log(java.util.logging.Level.SEVERE, null, ex);

		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(Main.class
				.getName()).log(java.util.logging.Level.SEVERE, null, ex);

		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(Main.class
				.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				Main main = new Main();
				main.setVisible(!Utils.config.IsStartupAutoHide);
				//main.setVisible(true);
			}
		});
	}

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JMenu jMenu1;
        private javax.swing.JMenuBar jMenuBar1;
        private javax.swing.JMenuItem jMenuItem1;
        private javax.swing.JMenuItem jMenuItem2;
        private javax.swing.JMenuItem jMenuItem3;
        private javax.swing.JPanel jPanel1;
        private javax.swing.JScrollPane jScrollPane1;
        private javax.swing.JPopupMenu.Separator jSeparator1;
        private javax.swing.JPopupMenu.Separator jSeparator2;
        public javax.swing.JTable jTable1;
        // End of variables declaration//GEN-END:variables
}
