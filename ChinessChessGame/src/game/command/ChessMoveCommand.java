package game.command;
public interface ChessMoveCommand {

	public abstract void execute();

	public abstract void rollback();

}
