package domini.test;
import domini.CelaNum;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

public class CelaNumTest {
    CelaNum celaNum;

    @BeforeEach
    public void setUp() {
        celaNum = new CelaNum();
    }

    @Test
    @DisplayName("getInputUsuari hauria de retornar l'input")
    public void testGetInput() {

    }
}
