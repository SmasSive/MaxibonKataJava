package com.karumi.maxibonkata;

import com.pholser.junit.quickcheck.From;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;

@RunWith(JUnitQuickcheck.class) public class KarumiHQsProperties {

  @Property public void theNumberOfMaxibonsIsAlwaysGreaterThanTwo(
      @From(DevelopersGenerator.class) Developer developer) {
    KarumiHQs karumiHQs = new KarumiHQs();
    karumiHQs.openFridge(developer);
    assertTrue(karumiHQs.getMaxibonsLeft() > 2);
  }
}
