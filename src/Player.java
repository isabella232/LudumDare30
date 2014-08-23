import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Player extends SpriteObject
{
	private static final long SECOND = 1000000000L;
	private static final long DISABLE_LENGTH = 3 * SECOND;
	private long         m_disableTime;
	private boolean      m_isActive;
	private final ArrayList<Player> m_children;

	public void AddChild(Player other)
	{
		m_children.add(other);
	}

	public Player CheckCollisionRecursive(Player other)
	{
		if(SphereIntersect(other))
		{
			return this;
		}

		for(int i = 0; i < m_children.size(); i++)
		{
			Player result = m_children.get(i)
				.CheckCollisionRecursive(other);
			if(result != null)
			{
				return result;
			}
		}

		return null;
	}

	public void CheckLaserCollision(Entity other,Player[] result)
	{
		float startX = GetX();
		float startY = GetY();
		for(int i = 0; i < m_children.size(); i++)
		{	
			if(!m_children.get(i).GetIsActive())
			{
				continue;
			}

			float endX = m_children.get(i).GetX();
			float endY = m_children.get(i).GetY();
			
			if(other.LineIntersect(startX, startY, endX, endY))
			{
				result[0] = this;
				result[1] = m_children.get(i);
				return;
			}

			m_children.get(i).CheckLaserCollision(other, result);
			if(result[0] != null)
			{
				return;
			}
		}
	}

	public boolean GetIsActive() { return m_isActive; }
	public void SetIsActive(boolean val) 
	{ 
		if(val == true 
		 && System.nanoTime() >= 
		 	(m_disableTime + DISABLE_LENGTH))
		{
			m_isActive = val;
		}
		else
		{
			m_isActive = val;
			m_disableTime = System.nanoTime();
		}
	}
	
	public Player(boolean isActive, float x, float y, 
			float width, float height)
	{
		super(x, y, width, height,
				new Bitmap(100, 100)
				.ClearScreen((byte)0x00, (byte)0x00, 
					(byte)0x00, (byte)0xFF));
		m_isActive = isActive;
		m_children = new ArrayList<Player>();
		m_disableTime = 0;
	}

	@Override
	public void Update(Input input, float delta)
	{
		if(m_isActive)
		{
			float speed = 1.0f;
			if(input.GetKey(KeyEvent.VK_W))
			{
				SetY(GetY() - delta * speed);
			}
			if(input.GetKey(KeyEvent.VK_S))
			{
				SetY(GetY() + delta * speed);
			}
			if(input.GetKey(KeyEvent.VK_D))
			{
				SetX(GetX() + delta * speed);
			}
			if(input.GetKey(KeyEvent.VK_A))
			{
				SetX(GetX() - delta * speed);
			}
		}

		for(int i = 0; i < m_children.size(); i++)
		{
			m_children.get(i).Update(input, delta);
		}
	}

	@Override
	public void Render(RenderContext target)
	{
		if(m_isActive)
		{
			for(int i = 0; i < m_children.size(); i++)
			{
				if(m_children.get(i).GetIsActive())
				{
					target.DrawLine(GetX(), GetY(),
							m_children.get(i).GetX(), 
							m_children.get(i).GetY(),
						(byte)0x00, 
						(byte)0x79, (byte)0xbf, (byte)0x10);
				}
			}
		}

		for(int i = 0; i < m_children.size(); i++)
		{
			m_children.get(i).Render(target);
		}
		super.Render(target);
	}
}
