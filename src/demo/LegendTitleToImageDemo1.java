// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 

package demo;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;
import org.jfree.chart.*;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.Size2D;

public class LegendTitleToImageDemo1
{

	public LegendTitleToImageDemo1()
	{
	}

	public static void main(String args[])
		throws IOException
	{
		DefaultPieDataset defaultpiedataset = new DefaultPieDataset();
		defaultpiedataset.setValue("A", 1.0D);
		defaultpiedataset.setValue("B", 2D);
		defaultpiedataset.setValue("C", 3D);
		JFreeChart jfreechart = ChartFactory.createPieChart("Test", defaultpiedataset, true, false, false);
		LegendTitle legendtitle = jfreechart.getLegend();
		legendtitle.setMargin(0.0D, 0.0D, 1.0D, 1.0D);
		BufferedImage bufferedimage = new BufferedImage(1, 1, 2);
		Graphics2D graphics2d = bufferedimage.createGraphics();
		Size2D size2d = legendtitle.arrange(graphics2d);
		graphics2d.dispose();
		int i = (int)Math.rint(size2d.width);
		int j = (int)Math.rint(size2d.height);
		BufferedImage bufferedimage1 = new BufferedImage(i, j, 2);
		Graphics2D graphics2d1 = bufferedimage1.createGraphics();
		legendtitle.draw(graphics2d1, new java.awt.geom.Rectangle2D.Double(0.0D, 0.0D, i, j));
		graphics2d1.dispose();
		BufferedOutputStream bufferedoutputstream = new BufferedOutputStream(new FileOutputStream(new File("LegendTitleToImageDemo1.png")));
		ChartUtilities.writeBufferedImageAsPNG(bufferedoutputstream, bufferedimage1);
		bufferedoutputstream.close();
	}
}
