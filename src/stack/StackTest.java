package stack;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class StackTest {

  private Stack stack;
  
  @Before
  public void setUp() throws Exception {
	  stack = BoundedStack.Make(2);
  }
  
  @Test
  public void newlyCreatedStack_ShouldBeEmpty() throws Exception {
	assertTrue(stack.isEmpty());
	assertEquals(0,stack.getSize());
  }
  
  @Test
  public void afterOnePush_StackSizeShouldBeOne() throws Exception {
	stack.push(1);
	assertEquals(1,stack.getSize());
	assertFalse(stack.isEmpty());
	}
  
  @Test
  public void afterOnePushAndPop_ShouldBeEmpty() throws Exception {
	stack.push(1);
	stack.pop();
	assertTrue(stack.isEmpty());
	assertEquals(0,stack.getSize());
	}
  
  @Test(expected = BoundedStack.Overflow.class)
  public void whenPushedPastLimit_StackOverflows() throws Exception {
	stack.push(1);
	stack.push(1);
	stack.push(1);
  }
  
  @Test(expected = BoundedStack.Underflow.class)
  public void whenEmptyIsPopped_StackUnderflows() throws Exception {
	stack.pop();
  }
  
  @Test
  public void whenOneIsPushed_OneIsPopped() throws Exception {
	stack.push(1);
	assertEquals(1,stack.pop());
  }
  
  @Test
  public void whenOneAndTwoIsPushed_TwoAndOneIsPopped() throws Exception {
	stack.push(1);
	stack.push(2);
	assertEquals(2,stack.pop());
	assertEquals(1,stack.pop());
  }
  
  @Test(expected = BoundedStack.IllegalCapacity.class)
  public void whenCreatingStackWithNegativeSize_ShouldThrowIllegalCapacity() throws Exception {
	BoundedStack.Make(-1);
  }
  
  @Test(expected = BoundedStack.Overflow.class)
  public void whenCreatingStacWithZeroCapacity_AnyPushShouldOverflow() throws Exception {
	stack = BoundedStack.Make(0);
	stack.push(1);
  }
  
  @Test
  public void whenOneIsPushed_OneIsOnTop() throws Exception {
	  stack.push(1);
	  assertEquals(1,stack.top());
	
  }
  
  @Test(expected = BoundedStack.Empty.class)
  public void whenStackIsEmpty_TopThrowsEmpty() throws Exception {
	stack.top();
  }
  
  @Test(expected = BoundedStack.Empty.class)
  public void whithZeroStack_TopThrowsEmpty() throws Exception {
	stack=BoundedStack.Make(0);
	stack.top();
  }
  
  @Test
  public void GivenStackWithOneTwoPushed_FindOneAndTwo() throws Exception {
	  stack.push(1);
	  stack.push(2);
	  int OneIndex = stack.find(1);
	  int TwoIndex = stack.find(2);
	  assertEquals(1, OneIndex);
	  assertEquals(0, TwoIndex);
	
  }
  
  @Test
  public void GivenStackWithNoTwo_FindTwoShouldReturnNull() throws Exception {
	  assertNull(stack.find(2));
  }
}
