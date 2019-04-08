package main_pack;

import static org.junit.Assert.*;

import org.junit.Test;

import main_pack.Main_frame;

public class Room_calc {
	@Test
	//корректный тест
	public final void test1() {
		double x = 50;
		double y = 100;
		int delta = 0;
		double expect =5000;
		double actual_answer = Main_frame.parasumm(x, y);
		assertEquals(actual_answer,expect,delta);
	}
	@Test
	//намеренно указано несоответствующее ожидание => failure
	public final void test2() {
		double x = 0;
		double y = 0;
		int delta = 0;
		double expect =5000;
		double actual_answer = Main_frame.parasumm(x, y);
		assertEquals(actual_answer,expect,delta);
	}
	@Test
	//корректный тест
	public final void test3() {
		double x = 1.43;
		double y = 2;
		int delta = 0;
		double expect =2.86;
		double actual_answer = Main_frame.parasumm(x, y);
		assertEquals(actual_answer,expect,delta);
	}
}