package knights;

import org.junit.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author jdmr
 */
public class BraveKnightTest {
    
    public BraveKnightTest() {
    }

    /**
     * Test of embarkOnQuest method, of class BraveKnight.
     */
    @Test
    public void knightShouldEmbarkOnQuest() throws QuestException {
        Quest mockQuest = mock(Quest.class);
        
        BraveKnight knight = new BraveKnight(mockQuest);
        knight.embarkOnQuest();
        
        verify(mockQuest, times(1)).embark();
    }
}
