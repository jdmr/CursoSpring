package knights;

/**
 *
 * @author jdmr
 */
public class BraveKnight implements Knight {
    private Quest quest;
    
    public BraveKnight(Quest quest) {
        System.out.println("Creando un Caballero Valiente con el Quest "+ quest);
        this.quest = quest;
    }
    
    public void embarkOnQuest() throws QuestException {
        System.out.println("Iniciando mision");
        quest.embark();
        System.out.println("Terminando mision");
    }
}
