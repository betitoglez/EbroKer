// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package demo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import org.jfree.chart.plot.PiePlot3D;

class Rotator extends Timer
	implements ActionListener
{

	private PiePlot3D plot;
	private int angle;

	Rotator(PiePlot3D pieplot3d)
	{
		super(100, null);
		angle = 270;
		plot = pieplot3d;
		addActionListener(this);
	}

	public void actionPerformed(ActionEvent actionevent)
	{
		plot.setStartAngle(angle);
		angle = angle + 1;
		if (angle == 360)
			angle = 0;
	}
}
