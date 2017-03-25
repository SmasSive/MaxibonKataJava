package com.karumi.maxibonkata;

import com.pholser.junit.quickcheck.From;
import com.pholser.junit.quickcheck.Property;
import com.pholser.junit.quickcheck.runner.JUnitQuickcheck;
import java.util.List;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(JUnitQuickcheck.class) public class KarumiHQsProperties {

  @Mock Chat mockChat;

  private KarumiHQs karumiHQs;

  @Before public void setUp() throws Exception {
    mockChat = mock(Chat.class);
    karumiHQs = new KarumiHQs(mockChat);
  }

  @Property public void theNumberOfMaxibonsIsAlwaysGreaterThanTwo(
      @From(NotSoHungryDevelopersGenerator.class) Developer developer) {
    System.out.println("Maxibons left: " + karumiHQs.getMaxibonsLeft());
    karumiHQs.openFridge(developer);
    assertTrue(karumiHQs.getMaxibonsLeft() > 2);
  }

  @Property public void theNumberOfMaxibonsIsAlwaysGreaterThanTwoWhenSeveralDevelopers(
      List<@From(NotSoHungryDevelopersGenerator.class) Developer> developers) {
    karumiHQs.openFridge(developers);
    System.out.println("Maxibons left: " + karumiHQs.getMaxibonsLeft());
    assertTrue(karumiHQs.getMaxibonsLeft() > 2);
  }

  @Property(trials = 5) public void messageIsSentWhenThereAreLessThanTwoMaxibons(
      @From(HungryDevelopersGenerator.class) Developer developer) {
    System.out.println(developer);
    karumiHQs.openFridge(developer);
    System.out.println("Maxibons left: " + karumiHQs.getMaxibonsLeft());
    verify(mockChat).sendMessage(anyString());
  }

  @Property(trials = 5) public void messageIsNotSentWhenThereAreLessThanTwoMaxibons(
      @From(NotSoHungryDevelopersGenerator.class) Developer developer) {
    System.out.println(developer);
    karumiHQs.openFridge(developer);
    System.out.println("Maxibons left: " + karumiHQs.getMaxibonsLeft());
    verify(mockChat, never()).sendMessage(anyString());
  }
}
