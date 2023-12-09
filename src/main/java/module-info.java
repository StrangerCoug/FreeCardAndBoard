module com.github.strangercoug.freecardandboard {
	requires lombok;
	requires java.logging;
	exports com.github.strangercoug.freecardandboard.objs;
	exports com.github.strangercoug.freecardandboard.exceptions;
	exports com.github.strangercoug.freecardandboard.enums;
	exports com.github.strangercoug.freecardandboard.games.model.card;
	exports com.github.strangercoug.freecardandboard.games.model.board;
	exports com.github.strangercoug.freecardandboard;
	exports com.github.strangercoug.freecardandboard.games.model;
}