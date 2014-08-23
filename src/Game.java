import java.awt.event.KeyEvent;
import java.util.*;

public class Game
{
	private final ArrayList<Entity> m_entities;

	public Game()
	{
		m_entities = new ArrayList<Entity>();

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
					   && currentPlayer.SphereIntersect(otherPlayer))
					{
						otherPlayer.SetIsActive(true);
					}
					else if(otherPlayer.GetIsActive()
					   && currentPlayer.SphereIntersect(otherPlayer))
					{
						currentPlayer.SetIsActive(true);
					}
				}
			}
		}
	}

	public void Render(RenderContext target)
	{
		target.Clear((byte)0x00);
		for(int i = 0; i < m_entities.size(); i++)
		{
			m_entities.get(i).Render(target);
		}
	}
}
