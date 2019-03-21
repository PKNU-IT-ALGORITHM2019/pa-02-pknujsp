package tour;

import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.Scanner;
import java.util.Stack;

public class tourmain
{
	static private int CityNum;
	static private pointval[] CityPoint;
	static double min = Double.MAX_VALUE;
	static int[] visited_node;
	static Stack<Integer> stack = new Stack<Integer>();
	static ArrayList<Integer> solution;

	public static void main(String[] args)
	{
		String[] files = { "input0.txt", "input1.txt", "input2.txt", "input3.txt", "input4.txt", "input5.txt",
				"input6.txt" };
		long start_time = System.currentTimeMillis();
		long end_time = 0;
		double seconds = 0.0;
		long minutes = 0;
		for (int a = 0; a < files.length; a++)
		{

			readtxt readtext = new readtxt();
			readtext.ReadFile(files[a]);
			CityPoint = new pointval[readtext.LIST.size()];
			CityNum = readtext.LIST.size();
			visited_node = new int[CityNum];

			Arrays.fill(visited_node, 0);

			for (int i = 0; i < readtext.LIST.size(); i++)
			{
				CityPoint[i] = new pointval(readtext.LIST.get(i).number, readtext.LIST.get(i).x,
						readtext.LIST.get(i).y);
			}
			System.out.println("계산 중...");
			long tsp_start = System.currentTimeMillis();
			tsp(0, CityPoint, CityNum, 0, 0);
			end_time = System.currentTimeMillis();
			seconds = ((end_time - tsp_start) / 1000.0) % 60;
			minutes = ((end_time - tsp_start) / 60000) % 60;
			System.out.println("answer : " + min + " " + solution);

			System.out.println("걸린 시간 : " + minutes + "분 " + seconds + "초");
			System.out.println();
			min = Double.MAX_VALUE;
		}

		seconds = ((end_time - start_time) / 1000.0) % 60;
		minutes = ((end_time - start_time) / 60000) % 60;
		System.out.println("총 걸린 시간 : " + minutes + "분 " + seconds + "초");
	}

	static private double GetDistance(pointval city1, pointval city2)
	{
		return Math.sqrt((city1.x - city2.x) * (city1.x - city2.x) + (city1.y - city2.y) * (city1.y - city2.y));
	}

	static private void tsp(int start, pointval[] city, int num, double sum, int now)
	{
		int i;
		int count = 0;
		if (sum > min)
			return;
		for (i = 0; i < num; i++)
		{
			if (visited_node[i] == 0)
			{
				count++;
				visited_node[i] = 1;
				stack.push(city[i].number);
				tsp(start, city, num, sum + GetDistance(city[now], city[i]), i);
				visited_node[i] = 0;
				stack.pop();
			}
		}
		if (count == 0)
		{
			sum += GetDistance(city[now], city[start]);
			if (min > sum)
			{
				min = sum;
				solution = new ArrayList<>(stack);
			}
		}

	}
}
