import java.awt.event.KeyEvent;

public class DestroyableObject extends Entity
{
	private static final float PLAYER_SIZE = 0.05f;

	private float        m_width;
	private float        m_height;
	private final Bitmap m_image;
	
	public DestroyableObject(float x, float y, 
			float width, float height)
	{
		super(x, y, Util.VectorLength(width * PLAYER_SIZE,
					height * PLAYER_SIZE));
		m_width = width * PLAYER_SIZE;
		m_height = height * PLAYER_SIZE;

		m_image = new Bitmap(100, 100);
		m_image.ClearScreen((byte)0x00, (byte)0xFF, (byte)0x00, (byte)0x00);
	}

	@Override
	public void Update(Input input, float delta)
	{
	}

	@Override
	public void Render(RenderContext target)
	{
		target.DrawImage(m_image, GetX(), GetY(),
			m_width, m_height);
	}
}
