import java.awt.event.KeyEvent;

public class Player extends SpriteObject
{
	private static final long SECOND = 1000000000L;
	private static final long DISABLE_LENGTH = 3 * SECOND;
	private long         m_disableTime;
	private boolean      m_isActive;

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
	}
}
