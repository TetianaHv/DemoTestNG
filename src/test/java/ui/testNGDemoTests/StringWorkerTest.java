package ui.testNGDemoTests;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;
import ui.testNGDemoClasses.StringWorker;

public class StringWorkerTest extends BaseTest {

  StringWorker stringWorker = new StringWorker();

  @Test
  public void test1StringWorker(){
    assertEquals(stringWorker.concatinate("123", "qwerty","ZXC"), "123qwertyZXC");
  }

}
