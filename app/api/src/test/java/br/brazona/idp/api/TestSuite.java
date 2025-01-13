package br.brazona.idp.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TestSuite {

    @Test
    @DisplayName("Sample Test")
    public void sampleTest() {
        // Add your test logic here
        Assertions.assertTrue(true);
    }
}
