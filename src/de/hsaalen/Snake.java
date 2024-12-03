package de.hsaalen;

import java.util.List;
import java.util.LinkedList;



public class Snake 
{
	private static final int INITIAL_X = 5;
	private static final int INITIAL_Y = 5;

	List<IntPair> positions;
		
	public Snake( int initial_snake_size ) 
	{
		initializePosition();
		place_at_initial_location( initial_snake_size );
    }
	
	private void initializePosition()
	{
		positions = new LinkedList<IntPair>();
	}
	
	public void place_at_initial_location( int initial_snake_size ) 
	{
        for (int i = 0; i < initial_snake_size; i++) 
		{
            int x = INITIAL_X - i;
            int y = INITIAL_Y;
			IntPair new_position = new IntPair(x,y);
			positions.add(new_position );
        }		
	}
	
	public void move(Direction direction) {
		moveBody();
		moveHead(direction);
	}
	
	private void moveBody() {
		for (int i = length() - 1; i > 0; i--) {
			position(i).x = position(i - 1).x;
        	position(i).y = position(i - 1).y;
		}
	}
	
	private void moveHead(Direction direction) {
		headPosition().move(direction);
	}
	
	
	public void grow( Direction direction )
	{
		IntPair new_headPosition = headPosition().clone();
		new_headPosition.move( direction );
		positions.add( 0, new_headPosition );
 	}
	
	public boolean is_snake_colliding( int board_width_in_tiles, int board_height_in_tiles )
	{
		if ( is_colliding_with_itself() )
			return true;			

		if ( is_outside_board( board_width_in_tiles, board_height_in_tiles ) )
			return true;			

		return false;
	}

	public boolean is_colliding_with_itself()
	{
        for ( int i = length()-1; i > 1; i-- )
		{
			if ( position( i ).x == headPosition().x &&
                 position( i ).y == headPosition().y )
				return true;
		}
		return false;
 	}

	public boolean is_outside_board( int board_width_in_tiles, int board_height_in_tiles )
	{
		if ( headPosition().x < 0 )
			return true;
		if ( headPosition().x >= board_width_in_tiles )
			return true;
		if ( headPosition().y < 0 )
			return true;
		if ( headPosition().y >= board_height_in_tiles )
			return true;
		return false;
	}
 	
	public int length() 
	{
		return positions.size();
	}
	
	public IntPair position( int index )
	{
		return positions.get( index );
	}
	
	public IntPair headPosition()
	{
		return position (0 );
	}
	
	public String toString() 
	{
		String result = "Snake" + positions.toString();
		return result;
	}

}