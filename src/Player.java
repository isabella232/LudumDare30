import java.awt.event.KeyEvent;

public class Player extends Entity
{
	private static final float PLAYER_SIZE = 0.05f;

	private boolean      m_isActive;
	private float        m_width;
	private float        m_height;
	private final Bitmap m_image;

	public boolean GetIsActive() { return m_isActive; }

	public void SetIsActive(boolean val) { m_isActive = val; }
	
	public Player(boolean isActive, float x, float y, 
			float width, float height)
	{
		super(x, y, Util.VectorLength(width * PLAYER_SIZE,
					height * PLAYER_SIZE));
		m_isActive = isActive;
		m_width = width * PLAYER_SIZE;
		m_height = height * PLAYER_SIZE;

		m_image = new Bitmap(100, 100);
		m_image.ClearScreen((byte)0x00, (byte)0x00, (byte)0x00, (byte)0xFF);
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

	@Override
	public void Render(RenderContext target)
	{
		target.DrawImage(m_image, GetX(), GetY(),
			m_width, m_height);
	}
}
