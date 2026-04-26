package monopoly;

import java.util.Random;

/**
 * Team:    Group 2
 * Members: Logan Chess, Bryant Gonzalez Guzman, Alex Gonzalez Monreal
 * Course:  CS 2430, Section 502
 * Project: Programming Project 4 - Spring 2026
 * 
 * This class is the application entry point for the Monopoly simulation.
 * It creates and connects together all the top-level components like
 * Board, Decks, GameContexts, Dice, PlayerState, and TurnEngine.
 * 
 */
public class MonopolyApp {

	public static void main(String[] args) {
		Random rng = new Random(0);
        Board board = Board.buildStandard();
        Deck chance = DeckFactory.buildChance(rng);
        Deck community = DeckFactory.buildCommunityChest(rng);
        GameContext ctx = new GameContext(board, chance, community);
        Dice dice = new Dice(rng);
        PlayerState player = new PlayerState();
        TurnEngine engine = new TurnEngine(ctx, dice);
        
        // interesting stuff goes here.
	}

}
