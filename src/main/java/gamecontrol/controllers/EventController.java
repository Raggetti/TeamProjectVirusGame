package main.java.gamecontrol.controllers;

import java.util.List;

import main.java.event.types.EventActionEvent;
import main.java.filehandling.gamecontent.realisations.EventGameContent;
import main.java.filehandling.gamecontent.realisations.SaveGameContent;
import main.java.filehandling.gamecontent.realisations.components.EventOption;
import main.java.filehandling.gamecontent.realisations.components.EventOptions;
import main.java.gamecontrol.gamestate.GameState;

public class EventController {

	private EventController() {
		// private constructor to prevent instantiation
	}
	
	public static void handleEvent(EventActionEvent event) {
		
		EventGameContent currentEvent = GameState.getInstance().getCurrentEvent();
		EventOptions eventOptions = currentEvent.getEventOptions();
		List<EventOption> eventOptionsValues = eventOptions.getEventOptionsValues();
		
		EventOption chosenOption = null;
		for (EventOption eventOption : eventOptionsValues) {
			if (eventOption.getEventOptionID().equals(event.getEventOptionID())) {
				chosenOption = eventOption;
				break;
			}
		}
		
		if (chosenOption == null) {
			// TODO handle this
			return;
		}
		
		chosenOption.getEventOptionContagionLevelModifier();
		
		SaveGameContent save = GameState.getInstance().getSave();
		save.alterImmunity(chosenOption.getEventOptionImmunityModification());
		save.alterContagionLevel(chosenOption.getEventOptionContagionLevelModifier());
		save.alterKarma(chosenOption.getEventOptionKarmaModification());
		
	}
}