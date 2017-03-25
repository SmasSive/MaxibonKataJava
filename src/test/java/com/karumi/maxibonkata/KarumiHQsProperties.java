package com.karumi.maxibonkata;

import com.pholser.junit.quickcheck.From;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;

@RunWith(JUnitQuickcheck.class) public class KarumiHQsProperties {

  private static KarumiHQs karumiHQs;

  @BeforeClass public static void setUp() throws Exception {
    karumiHQs = new KarumiHQs();
  }

  @Property public void theNumberOfMaxibonsIsAlwaysGreaterThanTwo(
      @From(NotSoHungryDevelopersGenerator.class) Developer developer) {
    System.out.println("Maxibons left: " + karumiHQs.getMaxibonsLeft());
    karumiHQs.openFridge(developer);
    assertTrue(karumiHQs.getMaxibonsLeft() > 2);
  }
}
