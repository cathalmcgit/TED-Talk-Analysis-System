package p3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class TedTalkTest {
	
	String stringValidGeneral, stringValidOneCharUpper, stringValidOneCharLower, stringValidNonAlpha;

	String stringInvalidEmpty, stringInvalidNull;

	Month January, July, November, Null;

	int validIntZero, validIntFifty, validIntEighty;

	int invalidIntNegative;
	
	String validLikeToViewRatio;
	
	String InvalidLikeToViewRatio;
	
	TEDTalk testTalk;

	@BeforeEach
	void setUp() throws Exception {
		
		stringValidGeneral = "Word";
		stringValidOneCharUpper = "W";
		stringValidOneCharLower = "w";
		stringValidNonAlpha = "5";

		stringInvalidEmpty = "";
		stringInvalidNull = null;

		January = Month.JAN;
		July = Month.JUL;
		November = Month.NOV;
		Null = null;

		validIntZero = 0;
		validIntFifty = 50;
		validIntEighty = 80;

		invalidIntNegative = -1;
		
		validLikeToViewRatio = "5:65";
		
		InvalidLikeToViewRatio = "g:lx";
		
		testTalk = new TEDTalk(stringValidGeneral, stringValidOneCharUpper, January, validIntZero, validIntFifty, validIntEighty, stringValidNonAlpha);
	}

	@Test
	void testTEDTalkConstructor() {
		
		testTalk = new TEDTalk(stringValidOneCharLower, stringValidNonAlpha, November, validIntEighty, validIntZero, validIntFifty, stringValidOneCharUpper);
		
		assertEquals(stringValidOneCharLower, testTalk.getTitle());
		assertEquals(stringValidNonAlpha, testTalk.getPresenter());
		assertEquals(November, testTalk.getMonth());
		assertEquals(validIntEighty, testTalk.getYear());
		assertEquals(validIntZero, testTalk.getViews());
		assertEquals(validIntFifty, testTalk.getLikes());
		assertEquals(stringValidOneCharUpper, testTalk.getUrl());
		
		assertNotNull(testTalk.getLikeViewRatio());
		
	}

	
	@Test
	void testNegativeTedTalkConstructor() {

		Exception ex = assertThrows(IllegalArgumentException.class, () -> {
			testTalk = new TEDTalk(stringInvalidEmpty, stringValidOneCharUpper, January, validIntZero, validIntFifty, validIntEighty, stringValidNonAlpha);
			testTalk = new TEDTalk(stringValidGeneral, stringInvalidNull, January, validIntZero, validIntFifty, validIntEighty, stringValidNonAlpha);
			testTalk = new TEDTalk(stringValidGeneral, stringValidOneCharUpper, Null , validIntZero, validIntFifty, validIntEighty, stringValidNonAlpha);
			testTalk = new TEDTalk(stringValidGeneral, stringValidOneCharUpper, January, invalidIntNegative, validIntFifty, validIntEighty, stringValidNonAlpha);
			testTalk = new TEDTalk(stringValidGeneral, stringValidOneCharUpper, January, validIntZero, invalidIntNegative, validIntEighty, stringValidNonAlpha);
			testTalk = new TEDTalk(stringValidGeneral, stringValidOneCharUpper, January, validIntZero, validIntFifty, invalidIntNegative, stringValidNonAlpha);
			testTalk = new TEDTalk(stringValidGeneral, stringValidOneCharUpper, January, validIntZero, validIntFifty, validIntEighty, stringInvalidEmpty);
		
		});

	}
	
	@Test
	void testGetSetTitle() {

		testTalk.setTitle(stringValidGeneral);
		assertEquals(stringValidGeneral, testTalk.getTitle());
		
		testTalk.setTitle(stringValidNonAlpha);
		assertEquals(stringValidNonAlpha, testTalk.getTitle());
		
		testTalk.setTitle(stringValidOneCharLower);
		assertEquals(stringValidOneCharLower, testTalk.getTitle());
		
		testTalk.setTitle(stringValidOneCharUpper);
		assertEquals(stringValidOneCharUpper, testTalk.getTitle());

	}
	
	@Test
	void testNegativeSetTitle() {

		Exception ex = assertThrows(IllegalArgumentException.class, () -> {
			testTalk.setTitle(stringInvalidEmpty);
			testTalk.setTitle(stringInvalidNull);
		});

		assertEquals("Invalid Title", ex.getMessage());

	}

	@Test
	void testGetSetPresenter() {
		testTalk.setPresenter(stringValidNonAlpha);
		assertEquals(stringValidNonAlpha, testTalk.getPresenter());
		
		testTalk.setPresenter(stringValidGeneral);
		assertEquals(stringValidGeneral, testTalk.getPresenter());
		
		testTalk.setPresenter(stringValidOneCharLower);
		assertEquals(stringValidOneCharLower, testTalk.getPresenter());
		
		testTalk.setPresenter(stringValidOneCharUpper);
		assertEquals(stringValidOneCharUpper, testTalk.getPresenter());
		
	}

	@Test
	void testNegativeSetPresenter() {

		Exception ex = assertThrows(IllegalArgumentException.class, () -> {
			
			testTalk.setPresenter(stringInvalidEmpty);
			testTalk.setPresenter(stringInvalidNull);
		
		});

		assertEquals("Invalid Presenter", ex.getMessage());

	}
	
	
	@Test
	void testGetSetMonth() {
		testTalk.setMonth(January);
		assertEquals(January, testTalk.getMonth());
		
		testTalk.setMonth(July);
		assertEquals(July, testTalk.getMonth());
		
		testTalk.setMonth(November);
		assertEquals(November, testTalk.getMonth());
		
	}

	@Test
	void testNegativeSetMonth() {
		Exception ex = assertThrows(IllegalArgumentException.class, () -> {

			testTalk.setMonth(Null);

		});

		assertEquals("Invalid Month", ex.getMessage());

	}
	
	@Test
	void testGetSetYear() {
		testTalk.setYear(validIntEighty);
		assertEquals(validIntEighty, testTalk.getYear());
		
		testTalk.setYear(validIntFifty);
		assertEquals(validIntFifty, testTalk.getYear());
		
		testTalk.setYear(validIntZero);
		assertEquals(validIntZero, testTalk.getYear());
	}
	
	@Test
	void testNegativeSetYear() {

		Exception ex = assertThrows(IllegalArgumentException.class, () -> {

			testTalk.setYear(invalidIntNegative);

		});

		assertEquals("Invalid Year", ex.getMessage());

	}

	@Test
	void testGetSetViews() {
		testTalk.setViews(validIntFifty);;
		assertEquals(validIntFifty, testTalk.getViews());
		
		testTalk.setViews(validIntEighty);;
		assertEquals(validIntEighty, testTalk.getViews());
		
		testTalk.setViews(validIntZero);;
		assertEquals(validIntZero, testTalk.getViews());
		
	}
	
	@Test
	void testNegativeSetViews() {
		
		Exception ex = assertThrows(IllegalArgumentException.class, () -> {

			testTalk.setViews(invalidIntNegative);

		});

		assertEquals("Invalid Views", ex.getMessage());


	}

	@Test
	void testGetSetLikes() {
		testTalk.setLikes(validIntZero);;
		assertEquals(validIntZero, testTalk.getLikes());
		
		testTalk.setLikes(validIntFifty);;
		assertEquals(validIntFifty, testTalk.getLikes());
		
		testTalk.setLikes(validIntEighty);;
		assertEquals(validIntEighty, testTalk.getLikes());
		
	}

	
	@Test
	void testNegativeSetLikes() {

		
		Exception ex = assertThrows(IllegalArgumentException.class, () -> {

			testTalk.setLikes(invalidIntNegative);

		});

		assertEquals("Invalid Likes", ex.getMessage());

	}

	
	@Test
	void testGetSetUrl() {
		testTalk.setUrl(stringValidGeneral);
		assertEquals(stringValidGeneral, testTalk.getUrl());
		
		testTalk.setUrl(stringValidNonAlpha);
		assertEquals(stringValidNonAlpha, testTalk.getUrl());
		
		testTalk.setUrl(stringValidOneCharLower);
		assertEquals(stringValidOneCharLower, testTalk.getUrl());
		
		testTalk.setUrl(stringValidOneCharUpper);
		assertEquals(stringValidOneCharUpper, testTalk.getUrl());
		
	}
	
	@Test
	void testNegativeSetURL() {

		Exception ex = assertThrows(IllegalArgumentException.class, () -> {

			
			testTalk.setUrl(stringInvalidEmpty);
			testTalk.setUrl(stringInvalidNull);

		});

		assertEquals("Invalid URL", ex.getMessage());
		
	}


	@Test
	void testGetLikeViewRatio() {
		fail("Not yet implemented");
	}

}
