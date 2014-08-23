import java.awt.event.KeyEvent;
import java.util.*;

public class Game
{
	private final ArrayList<Entity> m_entities;

	private final ArrayList<Entity> m_laserStarts;
	private final ArrayList<Entity> m_laserEnds;

	public Game()
	{
		m_entities    = new ArrayList<Entity>();
		m_laserStarts = new ArrayList<Entity>();
		m_laserEnds   = new ArrayList<Entity>();

		m_entities.add(new Player(true, -1.0f, -1.0f,
					1.0f, 1.0f));

		for(int i = 0; i < 10; i++)
		{
			float x = (float)Math.random();
			float y = (float)Math.random();
			m_entities.add(new Player(false, x, y,
						0.5f, 0.5f));
		}
	}

	public void Update(Input input, float delta)
	{
		for(int i = 0; i < m_entities.size(); i++)
		{
			m_entities.get(i).Update(input, delta);
		}

		for(int i = 0; i < m_entities.size(); i++)
		{
			for(int j = i + 1; j < m_entities.size(); j++)
			{
				Entity current = m_entities.get(i);
				Entity other = m_entities.get(j);

				if(current instanceof Player &&
				   other instanceof Player)
				{
					Player currentPlayer = (Player)current;
					Player otherPlayer   = (Player)other;

					if(currentPlayer.GetIsActive() 
					   && !otherPlayer.GetIsActive()
					   && currentPlayer.SphereIntersect(otherPlayer))
					{
						m_laserStarts.add(currentPlayer);
						m_laserEnds.add(otherPlayer);
						otherPlayer.SetIsActive(true);
					}
					else if(otherPlayer.GetIsActive()
					   && !currentPlayer.GetIsActive()
					   && currentPlayer.SphereIntersect(otherPlayer))
					{
						m_laserStarts.add(otherPlayer);
						m_laserEnds.add(currentPlayer);
						currentPlayer.SetIsActive(true);
					}
				}
			}
		}
	}

	public void Render(RenderContext target)
	{
		target.Clear((byte)0x00);

		if(m_laserStarts.size() != m_laserEnds.size())
		{
			System.err.println("Error: Laser start/end has fallen "
					+ "out of alignment");
			System.exit(1);
		}

		for(int i = 0; i < m_laserStarts.size(); i++)
		{
			Entity start = m_laserStarts.get(i);
			Entity end   = m_laserEnds.get(i);

			target.DrawLine(start.GetX(), start.GetY(),
				   	end.GetX(), end.GetY(),
				(byte)0x00, (byte)0x79, (byte)0xbf, (byte)0x10);
		}

		for(int i = 0; i < m_entities.size(); i++)
		{
			m_entities.get(i).Render(target);
		}
//		//10bf79
//		//79bf10
//		target.DrawLine(0.1f, 0.1f, 0.3f, 0.5f,
//				(byte)0x00, (byte)0x79, (byte)0xbf, (byte)0x10);
	}
}
