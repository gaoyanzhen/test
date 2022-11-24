package test;


import org.junit.jupiter.api.*;

/**
 * JunitTest
 *
 * @author gaoyanzhen
 * @since 2022-05-30
 */
public class JunitTest {
    @BeforeAll
    public static void beforeAll() {
        System.out.println("BeforeAll");
    }

    @BeforeAll
    public static void beforeAll2() {
        System.out.println("BeforeAll 2");
    }

    @BeforeEach
    public void beforeEach() {
        System.out.println("BeforeEach");
    }

    @BeforeEach
    public void beforeEach2() {
        System.out.println("BeforeEach 2");
    }

    @Test
    public void test() {
        System.out.println("Test");
    }

    @Test
    @Tag("second")
    public void test2() {
        System.out.println("Test 2");
    }

    @Test
    @DisplayName("测试 3")
    public void test3() {
        System.out.println("Test 3");
    }

    @Test
    @Disabled("跳过 test4")
    public void test4() {
        System.out.println("Test 4");
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("AfterAll");
    }

    @AfterAll
    public static void afterAll2() {
        System.out.println("AfterAll 2");
    }

    @AfterEach
    public void afterEach() {
        System.out.println("AfterEach");
    }

    @AfterEach
    public void afterEach2() {
        System.out.println("AfterEach 2");
    }
}
