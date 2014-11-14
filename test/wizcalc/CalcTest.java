/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wizcalc;

import java.awt.event.ActionEvent;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Wizard
 */
public class CalcTest {
    
    public CalcTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        mf = new MainFormCopy();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testNum() 
    {
        System.out.println("test num\n");
        
        mf.resText.setText("0");
        ActionEvent e = new ActionEvent(mf.oneButton, 0, "");
        mf.numButtonActionPerformed(e);
        assertEquals("1", mf.resText.getText());
        
        mf.resText.setText("12345");
        e = new ActionEvent(mf.nineButton, 0, "");
        mf.numButtonActionPerformed(e);
        assertEquals("123459", mf.resText.getText());
    }
    
    @Test
    public void testBack()
    {
        System.out.println("test back\n");
        
        mf.resText.setText("1");
        mf.backButtonActionPerformed(null);
        assertEquals("0", mf.resText.getText());
        
        mf.resText.setText("12345");
        mf.backButtonActionPerformed(null);
        assertEquals("1234", mf.resText.getText());
        
        mf.resText.setText("-7");
        mf.backButtonActionPerformed(null);
        assertEquals("0", mf.resText.getText());
    }
    
    @Test
    public void testPoint()
    {
        System.out.println("test point\n");
        
        mf.resText.setText("12345");
        mf.ptButtonActionPerformed(null);
        assertEquals("12345.", mf.resText.getText());
        
        mf.resText.setText("123.45");
        mf.ptButtonActionPerformed(null);
        assertEquals("123.45", mf.resText.getText());
    }
    
    @Test
    public void testClear()
    {
        System.out.println("test clear\n");
        
        mf.status = 1;
        mf.resText.setText("12345");
        mf.clearButtonActionPerformed(null);
        assertEquals("0", mf.resText.getText());
        assertEquals(0, mf.status);
    }
    
    @Test
    public void testNeg()
    {
        System.out.println("test neg\n");
        
        mf.resText.setText("12345");
        mf.negButtonActionPerformed(null);
        assertEquals("-12345", mf.resText.getText());
        
        mf.resText.setText("-12345");
        mf.negButtonActionPerformed(null);
        assertEquals("12345", mf.resText.getText());
        
        mf.resText.setText("0");
        mf.negButtonActionPerformed(null);
        assertEquals("0", mf.resText.getText());
    }
    
	@Test
	public void testMc() {
		ActionEvent e=new ActionEvent(mf.mcButton,0,"");
		mf.mcButtonActionPerformed(e);
		assertEquals(0.0,mf.memory);
		mf.memory=1234;
		mf.mcButtonActionPerformed(e);
		assertEquals(0.0,mf.memory);
		
		
	}
	
	@Test
	public void testMr() {
		mf.memory=12345;
		ActionEvent e=new ActionEvent(mf.mrButton,0,"");
		mf.mrButtonActionPerformed(e);
		assertEquals("12345.0",mf.resText.getText());
		
	}
	
	/*
		8.mpButtonActionPerformed
		输入：
		memory = 100
		resText.text = “100”
		输出：
		memory = 200.0
	 */
	@Test
	public void testMp() {
		mf.memory=100;
		mf.resText.setText("100");
		ActionEvent e=new ActionEvent(mf.mpButton,0,"");
		mf.mpButtonActionPerformed(e);
		assertEquals(200.0,mf.memory);
	}
	
	/*
		9.mmButtonActionPerformed
		输入：
		memory = 10
		resText.test = “-20”
		输出：
		memory = 30.0
	 */
	@Test
	public void testMm() {
		mf.memory=10;
		mf.resText.setText("-20");
		ActionEvent e=new ActionEvent(mf.mmButton,0,"");
		mf.mmButtonActionPerformed(e);
		assertEquals(30.0,mf.memory);
	}
	
	/*
		10.1.
		输入：
		status = 1
		last = 1
		op = ‘+’
		resText.text = “1”
		输出：
		status = 0
		last = 2
		resText.text = “2” 或 “2.0”

		10.2.
		输入：
		status = 1
		last = 1
		op = ‘-’
		resText.text = “2”
		输出：
		status = 0
		last = -1
		resText.text = “-1” 或 “-1.0”

		10.3.
		输入：
		status = 1
		last = 3
		op = ‘*’
		resText.text = “5”
		输出：
		status = 0
		last = 15
		resText.text = “15” 或 “15.0”

		10.4.
		输入：
		status = 1
		last = 10
		op = ‘/’
		resText.text = “2”
		输出：
		status = 0
		last = 5
		resText.text = “5” 或 “5.0”

		10.5.
		输入：
		status = 1
		last = 5
		op = ‘/’
		resText.text = “0”
		输出：
		status = 0
		last = 0
		resText.text = “除数不得为零！”。
	 */
	@Test
	public void testCompute() {
		mf.status=1;
		mf.last=1;
		mf.op='+';
		mf.resText.setText("1");
		ActionEvent e=new ActionEvent(mf.eqButton,0,"");
		mf.eqButtonActionPerformed(e);
		assertEquals("2.0",mf.resText.getText());
		assertEquals(2.0,mf.last);
		assertEquals(0,mf.status);
		
		mf.status=1;
		mf.last=1;
		mf.op='-';
		mf.resText.setText("2");
		mf.eqButtonActionPerformed(e);
		assertEquals("-1.0",mf.resText.getText());
		assertEquals(-1.0,mf.last);
		assertEquals(0,mf.status);
		
		mf.status=1;
		mf.last=3;
		mf.op='*';
		mf.resText.setText("5");
		mf.eqButtonActionPerformed(e);
		assertEquals("15.0",mf.resText.getText());
		assertEquals(15.0,mf.last);
		assertEquals(0,mf.status);
		
		mf.status=1;
		mf.last=10;
		mf.op='/';
		mf.resText.setText("2");
		mf.eqButtonActionPerformed(e);
		assertEquals("5.0",mf.resText.getText());
		assertEquals(5.0,mf.last);
		assertEquals(0,mf.status);
		
		mf.status=1;
		mf.last=5;
		mf.op='/';
		mf.resText.setText("0");
		mf.eqButtonActionPerformed(e);
		//assertEquals("除数不得为零！",mf.resText.getText());
		assertEquals(0.0,mf.last);
		assertEquals(0,mf.status);
	}

	/*
		输入：
		status = 1
		last = 3
		op = ‘*’
		resText.text = “5”
		evt.source = addButton
		输出：
		status = 1
		last = 15
		resText.text = “0”
		op = ‘+’

		12.2.
		输入：
		status = 0
		last = 0
		resText.text = “5”
		evt.source = addButton
		输出：
		status = 1
		last = 5
		resText.text = “0”
		op = ‘+’
	 */
	@Test
	public void testop()
	{
		mf.status=1;
		mf.last=3;
		mf.op='*';
		mf.resText.setText("5");
		ActionEvent e=new ActionEvent(mf.addButton,0,"");
		mf.opButtonActionPerformed(e);
		assertEquals(1,mf.status);
		assertEquals(15.0,mf.last);
		assertEquals('+',mf.op);
		assertEquals("0",mf.resText.getText());
		
		mf.status=0;
		mf.last=0;
		mf.resText.setText("5");
		mf.opButtonActionPerformed(e);
		assertEquals(1,mf.status);
		assertEquals(5.0,mf.last);
		assertEquals('+',mf.op);
		mf.resText.setText("5");
	}
	
    private MainFormCopy mf;
}
