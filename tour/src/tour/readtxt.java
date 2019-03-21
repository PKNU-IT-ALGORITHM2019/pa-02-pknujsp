package tour;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import java.util.ArrayList;

public class readtxt
{
	public ArrayList<pointval> LIST = new ArrayList<pointval>();
	private ArrayList<String> values = new ArrayList<String>();
	private int count = 0;

	public void ReadFile(String filename)
	{
		String aa = null;

		try
		{
			// byte[] encoded = Files.readAllBytes(Paths.get(filename));
			// Charset encoding = Charset.defaultCharset();
			// aa = new String(encoded, encoding);

			File file = new File(filename);

			BufferedReader br = new BufferedReader(new FileReader(file));
			aa = br.readLine();
			while (aa != null)
			{
				if (aa.equals(""))
					break;
				values.add(aa);
				aa = br.readLine();
			}
			br.close();

		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			for (int i = 1; i < values.size(); i++)
				Save_Point(values.get(i).toString());
			System.out.println(filename + " 읽기 완료");
		}
	}

	private void Save_Point(String string)
	{
		String[] a = string.split(" ");
		pointval pv = new pointval(count++, Integer.valueOf(a[0]), Integer.valueOf(a[1]));
		LIST.add(pv);
	}

}
