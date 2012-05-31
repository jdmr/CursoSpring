package knights;

/**
 *
 * @author jdmr
 */
public class SlayDragonQuest implements Quest {
    
    public SlayDragonQuest() {
        System.out.println("CREANDO QUEST");
    }

    public void embark() {
        System.out.println("Buscando dragon...");
    }
    
    @Override
    public String toString() {
        return "Mision de Buscar Dragon";
    }
}
