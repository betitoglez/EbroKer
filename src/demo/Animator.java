// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package demo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import org.jfree.data.category.DefaultCategoryDataset;

class Animator extends Timer
	implements ActionListener
{

	private DefaultCategoryDataset dataset;

	Animator(DefaultCategoryDataset defaultcategorydataset)
	{
		super(20, null);
		dataset = defaultcategorydataset;
		addActionListener(this);
	}

	public void actionPerformed(ActionEvent actionevent)
	{
		int i = (int)(Math.random() * (double)dataset.getRowCount());
		Comparable comparable = dataset.getRowKey(i);
		int j = (int)(Math.random() * (double)dataset.getColumnCount());
		Comparable comparable1 = dataset.getColumnKey(j);
		int k = Math.random() - 0.5D >= 0.0D ? 5 : -5;
		dataset.setValue(Math.max(0.0D, dataset.getValue(i, j).doubleValue() + (double)k), comparable, comparable1);
	}
}
