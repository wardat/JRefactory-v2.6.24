/*
 *  Author:  Chris Seguin
 *
 *  This software has been developed under the copyleft
 *  rules of the GNU General Public License.  Please
 *  consult the GNU General Public License for more
 *  details about use and distribution of this software.
 */
package org.acm.seguin.ide.common;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import org.acm.seguin.awt.CenterDialog;
import org.acm.seguin.summary.PackageSummary;

/**
 *  The package selector dialog box
 *
 *@author    Chris Seguin
 */
public class PackageSelectorDialog extends JDialog implements ActionListener {
	private PackageSelectorArea selection;
	private PackageSummary summary;
	private ButtonPanel buttons;


	/**
	 *  Constructor for the PackageSelectorDialog object
	 *
	 *@param  parent  the parent dialog rame
	 */
	public PackageSelectorDialog(JFrame parent)
	{
		super(parent, "Select package to view", true);

		getContentPane().setLayout(new BorderLayout());
		super.setSize(350, 325);

		selection = new PackageSelectorArea();
		selection.loadPackages();
		JScrollPane pane = selection.getScrollPane();
		pane.setBorder(new EmptyBorder(10, 10, 10, 10));
		getContentPane().add(pane, BorderLayout.CENTER);

		buttons = new ButtonPanel(this);
		buttons.setLocation(220, 0);
		getContentPane().add(buttons, BorderLayout.EAST);

		CenterDialog.center(this, parent);
	}


	/**
	 *  Gets the summary that has been selected
	 *
	 *@return    the selected package summary
	 */
	public PackageSummary getSummary()
	{
		return summary;
	}


	/**
	 *  Selects the package when the user presses OK
	 *
	 *@param  evt  the action event
	 */
	public void actionPerformed(ActionEvent evt)
	{
		if (evt.getActionCommand().equals("OK")) {
			summary = selection.getSelection();
			dispose();
		}
		if (evt.getActionCommand().equals("Cancel")) {
			summary = null;
			dispose();
		}
	}


	/**
	 *  The main program for the PackageSelectorDialog class
	 *
	 *@param  args  The command line arguments
	 */
	public static void main(String[] args)
	{
		(new PackageSelectorDialog(null)).setVisible(true);
	}


	/**
	 *  Quick and dirty panel to hold the buttons so that they are not resized as
	 *  the window is adjusted.
	 *
	 *@author    Chris Seguin
	 */
	private class ButtonPanel extends JPanel {
		private ActionListener listener;
		private Dimension preferredSize;


		/**
		 *  Constructor for the ButtonPanel object
		 *
		 *@param  listener  Description of Parameter
		 */
		public ButtonPanel(ActionListener listener)
		{
			this.listener = listener;
			init();

			preferredSize = new Dimension();
			preferredSize.width = 110;
			preferredSize.height = 80;

			this.setSize(preferredSize);
		}


		/**
		 *  Gets the PreferredSize attribute of the ButtonPanel object
		 *
		 *@return    The PreferredSize value
		 */
		public Dimension getPreferredSize()
		{
			return preferredSize;
		}


		/**
		 *  Gets the MaximumSize attribute of the ButtonPanel object
		 *
		 *@return    The MaximumSize value
		 */
		public Dimension getMaximumSize()
		{
			return preferredSize;
		}


		/**
		 *  Gets the MinimumSize attribute of the ButtonPanel object
		 *
		 *@return    The MinimumSize value
		 */
		public Dimension getMinimumSize()
		{
			return preferredSize;
		}


		/**
		 *  Initialize the components of this panel
		 */
		private void init()
		{
			setLayout(null);

			JButton okButton = new JButton("OK");
			okButton.setBounds(0, 10, 100, 25);
			add(okButton);
			okButton.addActionListener(listener);

			JButton cancelButton = new JButton("Cancel");
			cancelButton.setBounds(0, 45, 100, 25);
			add(cancelButton);
			cancelButton.addActionListener(listener);
		}
	}
}
