package com.bibal;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.bibal.util.PropertiesManager;

public class BibalApplicationTests {

	
	@Test
	public void testLoadProperties() {
		assertEquals(15, PropertiesManager.getDelai("delaiLivre"));
		assertEquals(10, PropertiesManager.getDelai("delaiMagazine"));
	}
	
	@Test
	public void testSetProperties() {
		PropertiesManager.setDelai("delaiLivre", 20);
		PropertiesManager.setDelai("delaiMagazine", 17);
		assertEquals(20, PropertiesManager.getDelai("delaiLivre"));
		assertEquals(17, PropertiesManager.getDelai("delaiMagazine"));
		PropertiesManager.setDelai("delaiLivre", 15);
		PropertiesManager.setDelai("delaiMagazine", 10);
		assertEquals(15, PropertiesManager.getDelai("delaiLivre"));
		assertEquals(10, PropertiesManager.getDelai("delaiMagazine"));
	}
	
}
